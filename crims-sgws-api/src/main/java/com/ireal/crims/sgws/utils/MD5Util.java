package com.ireal.crims.sgws.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public static final int MD5Check(byte[] btResourse,int iCheckMode)
    {
        int iRes = 0;//BomitErrorcode.m_Fail;
        if(iCheckMode == 1)
        {//内部协议帧头合法性校验，btResourse总长16字节，前12字节为数据，后4字节为MD5码的前4字节
            if(btResourse.length >= 16) {
                try {
                    byte[] data;
                    byte[] btRes = new byte[12];
                    System.arraycopy(btResourse, 0, btRes, 0, 12);
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    data = digest.digest(btRes);
                    if (data[0] == btResourse[12]
                            && data[1] == btResourse[13]
                            && data[2] == btResourse[14]
                            && data[3] == btResourse[15]
                            )
                    {
                        iRes = 1;//BomitErrorcode.m_Success;
                    }


                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }else{
                iRes = 0;//BomitErrorcode.m_Datalenerror;
            }


        }else {
            iRes = 0;//BomitErrorcode.m_ErrCode_ParameterError;
        }

        return iRes;


    }

    public static final int MD5Generate(byte[] btResourse,int iMode, byte[] byteMD5Low4 )
    {
        int iRes = 0;//BomitErrorcode.m_Fail;

        if(iMode == 1)
        {//内部协议头MD5码生成
            if(btResourse.length >= 12 && byteMD5Low4.length == 4 ) {
                try {
                    byte[] MD5data;
                    byte[] btRes = new byte[12];
                    System.arraycopy(btResourse, 0, btRes, 0, 12);
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    MD5data = digest.digest(btRes);
                    System.arraycopy(MD5data, 0, byteMD5Low4, 0, 4);
                    iRes = 1;//BomitErrorcode.m_Success;

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }else{
                iRes = 0;//BomitErrorcode.m_Datalenerror;
            }
        }else {
            iRes = 0;//BomitErrorcode.m_ErrCode_ParameterError;
        }

        return iRes;

    }

}
