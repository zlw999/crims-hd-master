package com.ireal.crims.sgws.structs;


import lombok.Data;

import java.io.Serializable;
@Data
public class SgAppBody implements Serializable {

    private SgAppHeader header = new SgAppHeader();//消息头

    private byte[] message;


 public boolean Init(byte[] header, byte[] body)
    {
        int length = 0;
        if( null == header )
        {
            return false;
        }
        else
        {
            length = header.length;
        }
        if( null != body )
        {
            length += body.length;
        }

        this.message = new byte[length];
        int destPos = 0;
        System.arraycopy(header, 0, message, destPos, header.length);
        destPos += header.length;
        //1.原数组，2，原数组地址，3，目标数组，4，目标数组地址，5，被拷贝数组的长度
        System.arraycopy(body, 0,message, destPos, body.length);
        return true;


    }

}
