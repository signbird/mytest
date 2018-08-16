package org.bqftest.encrypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.junit.Assert;
import org.junit.Test;

public class RSA
{
	public static final String RSA = "RSA";

	/**
	 * 试图根据浏览器自带的数字证书中的 公钥、签名、摘要信息， 将数字证书中的签名用公钥解密，和摘要比对，结果应该是一致的，可惜未能成功。
	 * TODO  有时间再看下  http://blog.csdn.net/chaijunkun/article/details/7275632/
	 */
	@Test
	public void test_decryptSign() throws NoSuchAlgorithmException, InvalidKeySpecException, Exception
    {
	    // 数字证书中的签名值
		String signHexString = "843029C3AF1B61F2E50FA64FC12FD06B4182099B087C39F2C17EE2589719A9A775F4D90B4866063C65FC478AD58F569168C9288832D773B125CC40E20CBE29B9B751DFCF9E92FA522DF6AA9BCD50CCF122F8272DE94EC26BB0E36340A24E4B4C2C07A3C20F946E79DB4CDC6605B389FF802BB2CDC5E246811FA231C6F8FD445C492BDD3C392C1CA4BC1579E35CB489642D54DE22ABA33B30AD4A05FC41149180B9BCEEEFDB8D0357266ECC66AC6430106B4DE7A5E0F25D6C3B8F649A1CFAD9B674A55BFCE9A5A4E1E4624C6A3670F79EFC2E8562F2D6CB2B593C8FC422C6D590588E0C0592555448C6CEE8587382A881E619D1A26173859A2BAB4B10DC25D6D0";
		// 数字证书中的公钥
		String publicKeyHexString = "88A48FF6FA5A744F736069479CB51DC58296B41B6F4B45107B215FCDD33FB1C8E8776859F63048DE3C7AC1F2D8DE6A686D91EC6C24FD873C30AA1B9C3564B0F693464F50247E1427791E9DD507192890262B9DB0C5A76CCB947D4D914E2706F193623FDF379DE16ABF81ACEC5F86179737EA18FC861EB2B2BF286335D90B45188CAA6DFC9F3D72E99A743A891A65D5958E014C57F7C03279A0F3DE362B8E109973F4D80CFD010744EE406630F9735F9374222E6AD631D73556A3478997774E1B77AF85882191FFC2AE5BECCCF781763525BFD3D677E0294C182DE9711C7DB990423BF13B6ABA6A5E403EFB48E7A739426D7EF2AC078029E253138BA79B3C107F";
		// 数字证书中的sha256摘要（指纹）
		String summeryHexString = "90FAFD8FC5C9FF79259D038DC1E091D6BE25DDE062A4B078C22F4EB42CA2FF8E";
		
		byte[] publicKeyBytes = ByteUtil.hexStringToBytes(publicKeyHexString);
		
//		KeyPair keyPair = getKeyPair();
//		byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
		
		PublicKey publicKey = bytes2PublicKey(publicKeyBytes);
		
		byte[] signBytes = ByteUtil.hexStringToBytes(signHexString); 
		
		// 解密结果即为证书的摘要
		byte[] summary = publicDecrypt(signBytes, publicKey);
		
		// 转为16进制摘要， 方便和证书中的摘要值比较
		String hexSummary = ByteUtil.bytesToHexString(summary);
		Assert.assertEquals(summeryHexString, hexSummary);
    }
	
	
	/**
	 * RSA加解密测试
	 */
	@Test
    public void testRSA() throws Exception
	{
		String source = "hello, world!";
		
	    KeyPair keyPair = getKeyPair();
	    
	    // 公钥加密
	    byte[] encryptByte = publicEncrypt(source.getBytes(), keyPair.getPublic());
	    // 私钥解密
	    byte[] decryptByte = privateDecrypt(encryptByte, keyPair.getPrivate());
	    Assert.assertEquals(source, new String(decryptByte));
	    
	    // 私钥加密
	    byte[] encryptByte2 = privateEncrypt(source.getBytes(), keyPair.getPrivate());
	    // 公钥解密
	    byte[] decryptByte2 = publicDecrypt(encryptByte2, keyPair.getPublic());
	    Assert.assertEquals(source, new String(decryptByte2));
	}
	
	
	public static PublicKey bytes2PublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		
		return keyFactory.generatePublic(keySpec);
	}
	
	/**
	 * 公钥加密。
	 * @param content 源文件
	 * @param publicKey 公钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}
	
	/**
	 * 私钥解密
	 */
	public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}
	
	/**
	 * 私钥加密。
	 */
	public static byte[] privateEncrypt(byte[] content, PrivateKey privateKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}
	
	/**
	 * 公钥解密
	 */
	public static byte[] publicDecrypt(byte[] content, PublicKey publicKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}
	
	
	/**
	 * 获取RSA密钥对 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair getKeyPair() throws NoSuchAlgorithmException
	{
		KeyPairGenerator gen = KeyPairGenerator.getInstance(RSA);
		gen.initialize(1024);
		
		return gen.generateKeyPair();
	}
	
	
	public static String getPublicKey(KeyPair keyPair)
	{
		PublicKey publicKey = keyPair.getPublic();
		return ByteUtil.byte2base64(publicKey.getEncoded());
	}
	
	public static String getPrivateKey(KeyPair keyPair)
	{
		PrivateKey privateKey = keyPair.getPrivate();
		return ByteUtil.byte2base64(privateKey.getEncoded());
	}
	
}
