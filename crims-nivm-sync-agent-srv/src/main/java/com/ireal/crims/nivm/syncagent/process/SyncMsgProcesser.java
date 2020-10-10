package com.ireal.crims.nivm.syncagent.process;

import com.ireal.crims.common.enums.MsgCmdEnum;
import com.ireal.crims.nivm.syncagent.manage.DeviceManager;
import com.ireal.crims.sgws.structs.SgAppHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SyncMsgProcesser extends Thread {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static SyncMsgProcesser instance = new SyncMsgProcesser();
    }

    public static SyncMsgProcesser getInstance() {
        return SyncMsgProcesser.SingletonHolder.instance;
    }

    private SyncMsgProcesser() {
        this.setName("CRIMS_NIVM_SYNC_AGENT_MSGPROCESSER");
        msgQueue = new SyncMsgQueue();
    }

    /**
     * 消息队列
     */
    private SyncMsgQueue msgQueue;
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
                SgAppHeader msg = getMsg();

                if( msg != null )
                {
                    MsgCmdEnum msgCmd = MsgCmdEnum.getEnumType(msg.getReqNo());
                    switch (msgCmd)
                    {
                        case NivmDeviceInfoSync:
                        {
                            DeviceManager.getInstance().BuildDeviceSyncResponse(msg);
                        }
                        break;
                        default:
                            break;
                    }

                }


                Thread.sleep(10);
            }
            catch (java.lang.Throwable e)
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

    protected void finalize() throws java.lang.Throwable
    {
        cleanUp();
        super.finalize();
    }

    /**
     * 消息处理过程
     * @param msg 待处理
     */
    public void processSyncMsg(SgAppHeader msg)
    {
        msgQueue.put(msg); //将消息放入消息队列
    }

    /*
     * 从消息队列中取得一个待处理的消息，如果没有消息将挂起线程直到有消息出现
     * 取出后消息自动从队列中清除
     * @return 消息
     */
    private SgAppHeader getMsg()
    {
        SgAppHeader msg = null;

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
                    if (SyncMsgProcesser.getInstance().msgQueue.getNum() > 0)
                    {
                        System.out.print("g." + SyncMsgProcesser.getInstance().msgQueue.getNum());
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

class SyncMsgQueue
{
    private List<SgAppHeader> list = new ArrayList<SgAppHeader>();
    /**
     * 添加新元素到队列
     * @param msg   待加入元素

     */
    public void put(SgAppHeader msg)
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
    public SgAppHeader get()
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