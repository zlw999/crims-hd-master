package com.ireal.crims.nivm.syncagent.main;

import com.ireal.crims.nivm.syncagent.process.SyncMsgProcesser;
import com.ireal.crims.sgws.main.SgwsClientMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

public class MainApp extends Thread {

        public Logger logger = LoggerFactory.getLogger(getClass());

        private static class SingletonHolder {
            public static MainApp instance = new MainApp();
        }

        public static MainApp getInstance() {
            return SingletonHolder.instance;
        }

	private MainApp() {
            this.setName("CRIMS_NIVM_SYNC_AGENT_MainApp"

            );
        }

        public boolean Init() {
            return true;
        }

        @Override
        public void run() {

            do {
                if( !this.InitSgws() )
                {
                    break;
                }

                if( !this.StartSgws() )
                {
                    break;
                }

                SyncMsgProcesser.getInstance().run();


            } while (false);
        }

        // 初始化消息中间件
        private boolean InitSgws() {
            try {
                if(!SgwsClientMain.getInstance().Init("", 0, "",""))
                {
                    return false;
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return false;
            }
                       //获取链接
            SgwsClientMain.getInstance().getConnect();
                   //初始化消息模块回调接口  OnReceiveData()
            SgwsClientMain.getInstance().setCtrlCB(SystemBus.getInstance());

            return true;
        }

        // 启动消息中间件

        private boolean StartSgws()
        {
            if( !SgwsClientMain.getInstance().OnStart() )
            {
                return false;
            }
            return true;
        }
}
