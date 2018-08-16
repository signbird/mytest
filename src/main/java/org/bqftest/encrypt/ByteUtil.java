package org.bqftest.encrypt;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public abstract class ByteUtil
{

	public static void main(String[] args)
    {
	    String hexString = "45dea1392f51549e433031148bea7c2e";
	    byte[] bytes = hexStringToBytes(hexString);
	    System.out.println(new String(bytes));
	    
	    String src = "hello world.";
	    String hex = bytesToHexString(src.getBytes());
	    System.out.println(new String(hexStringToBytes(hex)));
    }
	
	/**
	 * Convert byte[] to hex string.
	 * 这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src byte[] data
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src)
	{
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0)
		{
			return null;
		}
		for (int i = 0; i < src.length; i++)
		{
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2)
			{
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString)
	{
		if (hexString == null || hexString.equals(""))
		{
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++)
		{
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c char
	 * @return byte
	 */
	private static byte charToByte(char c)
	{
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	public static String byte2base64(byte[] bytes)
	{
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bytes);
	}
	
	public static byte[] base642byte(String base64) throws IOException
	{
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(base64);
	}
	
	public static String base64EncodeAsString(String text)
	{
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(text.getBytes());
	}
	
	public static String base64DecodeAsString(String text)
	{
		BASE64Decoder decoder = new BASE64Decoder();
		
		String encoded = null;
		try
        {
			encoded = new String(decoder.decodeBuffer(text), "ASCII");
        } catch (IOException e)
        {
	        e.printStackTrace();
        }
		return encoded;
	}
}
