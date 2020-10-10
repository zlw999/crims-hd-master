package com.ireal.crims.sgws.utils;;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 
 * @Description 字节转换大端工具类
 * @author 
 * @date 
 * @since 
 * @group 
 */
public class TypeConvert {

	/**
	 * 数组转long
	 * @param v_byte
	 * @param v_nBeginPos
	 * @param nBytes
	 * @return
	 */
	public static long ByteArrayToLong(byte v_byte[], int v_nBeginPos, int nBytes)
	{
        long ret = 0L;
        for (int i = 0; i < nBytes; i++)
        {
            int nTmp = v_byte[(v_nBeginPos + (nBytes - 1)) - i];
            if (nTmp < 0)
                nTmp += 256;
            ret += nTmp << i * 8;
        }

        return ret;
    }

	 /**
	  * long转数组
	  * @param nData
	  * @param v_bytes
	  * @param nBeginPos
	  * @param nBytes
	  */
	 public static void LongToByteArray(long nData, byte v_bytes[], int nBeginPos, int nBytes)
	 {
        for (int i = 0; i < nBytes; i++)
            v_bytes[(nBeginPos + (nBytes - 1)) - i] = (byte)(int)(nData >>> i * 8);
	 }
	 
	 /**
	  * Long转数组
	  * @param nValue
	  * @param nBytes
	  * @return
	  */
	 public static byte[] LongToByteArray(long nValue, int nBytes){
        byte bValue[] = new byte[nBytes];
        LongToByteArray(nValue, bValue, 0, nBytes);
        return bValue;
	 }


	 public static void main(String[] args) {
		System.out.println(Arrays.toString(LongToByteArray(128, 1)));
	}
	 
	 public static byte[] StringToByteArray(String sValue){
		int nBytes = 0;
		try {
			nBytes = sValue.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        byte bValue[] = new byte[nBytes];
        UtilFun.Memset(bValue, 0, (byte)0, nBytes);
        try
        {
            UtilFun.memcpy(bValue, sValue.getBytes("GBK"), sValue.getBytes("GBK").length);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return bValue;
    }
	 
	 public static byte[] StringToByteArray(String sValue, int nBytes){
		try {
			if( nBytes == 0 )
			{
				nBytes = sValue.getBytes("GBK").length;
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        byte bValue[] = new byte[nBytes];
        UtilFun.Memset(bValue, 0, (byte)0, nBytes);
        try
        {
            UtilFun.memcpy(bValue, sValue.getBytes("GBK"), sValue.getBytes("GBK").length);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return bValue;
    }

	public static byte[] StringToByteArray(String sValue, String charsetName){
		int nBytes = 0;
		try {
			nBytes = sValue.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		byte bValue[] = new byte[nBytes];
		UtilFun.Memset(bValue, 0, (byte)0, nBytes);
		try
		{
			UtilFun.memcpy(bValue, sValue.getBytes(charsetName), sValue.getBytes(charsetName).length);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return bValue;
	}

}