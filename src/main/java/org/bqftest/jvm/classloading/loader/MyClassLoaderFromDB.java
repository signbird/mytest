package org.bqftest.jvm.classloading.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.bqftest.mysql.DBHelper;

/**
 * 将本地的class文件存入数据库（longClob类型），再从数据库读取class文件，使用自定义的类加载器加载该class。
 * 
 * mysql存取class文件部分 参考 http://lishun618-163-com.iteye.com/blog/1461443
 * 
 */
public class MyClassLoaderFromDB extends ClassLoader
{

	private String loaderName;

	public MyClassLoaderFromDB(ClassLoader parent, String name)
	{
		super(parent); // 显示指定该类加载器的父加载器
		this.loaderName = name;
	}

	@Override
	public String toString()
	{
		return this.loaderName;
	}

	private static void preparedb()
	{
		// String createSql =
		// "CREATE TABLE t_classfile(id INT(11) NOT NULL AUTO_INCREMENT, filename VARCHAR(20) CHARACTER SET utf8, content LONGBLOB, size INT(11) DEFAULT NULL, `date` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id));";
		String insertSql = "insert into t_classfile (filename, content, size, date) values (?,?,?,?)";

		DBHelper db = new DBHelper();
		PreparedStatement preStmt = db.getStatement(insertSql);
		try
		{
			preStmt.setString(1, "SampleB.class");
			File f = new File("E:/workspace/mytest/target/classes/org/bqftest/jvm/classloading/loader/SampleB.class");
			FileInputStream fin = new FileInputStream(f);
			preStmt.setBinaryStream(2, fin, fin.getChannel().size());
			preStmt.setInt(3, (int) f.length());
			preStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

			preStmt.executeUpdate();
			System.out.println("成功上传SampleB.class文件到数据库。");
		} catch (SQLException | IOException e)
		{
			e.printStackTrace();
		} finally
		{
			db.close();
		}
	}

	/**
	 * 重写JVM调用的加载器的方法
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] data = loadClassData(name);
		return defineClass(name, data, 0, data.length);
	}

	/**
	 * 从数据库读取class文件作为二进制流放入到byte数组中去
	 */
	private byte[] loadClassData(String name)
	{
		InputStream in = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;

		// 从db获取class文件 作为InputStream
		String sql = "select * from t_classfile where filename = ? ";
		DBHelper db = new DBHelper();
		String fileNameFromDB;
		try
		{
			PreparedStatement pst = db.getStatement(sql);
			pst.setString(1, name.substring(name.lastIndexOf(".") + 1) + ".class");
			ResultSet ret = pst.executeQuery();

			while (ret.next())
			{
				fileNameFromDB = ret.getString(2);
				System.out.println("从数据库获取到文件：" + fileNameFromDB);
				in = ret.getBinaryStream("content");
				System.out.println("文件二进制流为：" + in);
			}

			ret.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			db.close();
		}

		try
		{
			baos = new ByteArrayOutputStream();
			int ch = 0;
			while (-1 != (ch = in.read()))
			{
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				in.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			} finally
			{
				try
				{
					baos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
	        IllegalAccessException
	{
		// preparedb();

		MyClassLoaderFromDB loader = new MyClassLoaderFromDB(null, "loaderFromDB");
		Class<?> clazz = loader.loadClass("org.bqftest.jvm.classloading.loader.SampleB");
		Object o = clazz.newInstance();
	}
}
