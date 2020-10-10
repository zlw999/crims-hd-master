package com.ireal.crims.sgws.process;

import com.ireal.crims.sgws.main.SgwsClientMain;
import com.ireal.crims.sgws.structs.SgAppHeader;
import com.ireal.crims.sgws.structs.SgBody;
import com.ireal.crims.sgws.structs.SgHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MsgProcesserTest extends Thread {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static MsgProcesserTest instance = new MsgProcesserTest();
    }

    public static MsgProcesserTest getInstance() {
        return SingletonHolder.instance;
    }

    private MsgProcesserTest() {
        this.setName("CRIMS_SGWS_MSGPROCESSER");
        msgQueue = new MsgQueue();
    }

    /**
     * 消息队列
     */
    private MsgQueue msgQueue;
    private boolean blRuning = false;

    public boolean isRun()
    {
        return this.blRuning;
    }

    @Override
    public void run() {

        this.blRuning = true;

        while (blRuning) {
            try
            {
                //取消息并分发处理
                byte[] msg = getMsg();

                if( msg != null )
                {
                    Thread.sleep(3000);

                    SgHeader tmpHeader = new SgHeader();
                    SgAppHeader tmpAppHeader = new SgAppHeader();
                    SgBody.decodeHeader(msg, tmpHeader, tmpAppHeader);

                    String tmpData = SgBody.decodeBody(msg, tmpHeader, tmpAppHeader);

                   SgwsClientMain.getInstance().getCtrlCB().OnReceiveData(tmpAppHeader, tmpData);


                }

                Thread.sleep(10);
            }
            catch (Throwable e)
            {
                logger.error("",e);
            }
            finally
            {

            }

            if (blRuning == false)
            {
                if (this.msgQueue.getNum() == 0)
                {
                    break;
                }
            }
        }
        cleanUp();
    }

    public void stopThread(){
        blRuning = false;
    }

    /**
     * 清理
     */
    private void cleanUp()
    {
     }

    protected void finalize() throws Throwable
    {
        cleanUp();
        super.finalize();
    }

    /**
     * 消息处理过程
     * @param msg 待处理

     */
    public void addMsg(byte[] msg)
    {
        msgQueue.put(msg); //将消息放入消息队列

    }

    /*
     * 从消息队列中取得一个待处理的消息，如果没有消息将挂起线程直到有消息出现
     * 取出后消息自动从队列中清除

     * @return 消息
     */
    private byte[] getMsg()
    {
        byte[] msg = null;

        while (true)
        {
            synchronized (this.msgQueue)
            {
                msg =  msgQueue.get();
                if (msg == null)
                {
                    try
                    {
                        msgQueue.wait();
                    }
                    catch (InterruptedException x)
                    {
                    }
                }
                else
                {
                    if (MsgProcesserTest.getInstance().msgQueue.getNum() > 0)
                    {
                        System.out.print("g." + MsgProcesserTest.getInstance().msgQueue.getNum());
                    }

                    break;
                }
            }
        }
        return msg;
    }

    public void printMsgNum()
    {
        System.out.println("队列中未处理消息数目为:" + Integer.toString(msgQueue.getNum()));
    }


}


/**
 * <p>Title: 消息队列</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 北京世纪瑞尔技术股份公司</p>
 * @author gms
 * @version 1.0
 */

class MsgQueue
{
    private List<byte[]> list = new ArrayList<byte[]>();
    /**
     * 添加新元素到队列
     * @param msg   待加入元素


     */
    public void put(byte[] msg)
    {
        synchronized (this)
        {
            list.add(msg);
            notifyAll();
        }
    }
    /**
     * 得到最早加入队列的一个元素

     * @return  最早加入队列的一个元素

     */
    public byte[] get()
    {
        if (list.isEmpty())
        {
            return null;
        }
        else
        {
            return list.remove(0);
        }
    }

    /**
     * 取得队列的消息数目

     * @return 队列中对象的数目
     */
    public int getNum()
    {
        return list.size();
    }
}