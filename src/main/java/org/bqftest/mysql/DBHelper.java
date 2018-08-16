package org.bqftest.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 参考  http://www.cnblogs.com/taoweiji/archive/2012/12/11/2812852.html
 * http://hzy3774.iteye.com/blog/1689525
 *  
 *
 */
public class DBHelper
{
	// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
    // 避免中文乱码要指定useUnicode和characterEncoding
    String url = "jdbc:mysql://localhost:3306/bqftest?"
            + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";

	private Connection conn = null;
	private PreparedStatement pst = null;

	public DBHelper()
	{
		try
		{
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();
            
			// 获取连接
			conn = DriverManager.getConnection(url);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (conn != null)
		{
			System.out.println("Succeeded connecting to the Database.");
		}
	}

	public PreparedStatement getStatement(String sql)
	{
		
		if (conn == null)
		{
			System.out.println("Can't connecting to the Database!");
			return null;
		}
		
		try
		{
			// 准备执行语句
			pst = conn.prepareStatement(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return pst;
	}

	public void close()
	{
		try
		{
			this.conn.close();
			this.pst.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}