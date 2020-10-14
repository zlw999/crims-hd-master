package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.ireal.crims.alarm.alarmprocessapi.main.AlarmProcessApiMain;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmProcessResponseInfo;
import com.ireal.crims.alarm.alarmprocessapi.vo.AlarmProcessInfoVO;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


//告警处理管理类
public class AlarmProcessManager  {
    public Logger logger = LoggerFactory.getLogger(getClass());


    private static class SingletonHolder {
        public static AlarmProcessManager instance = new AlarmProcessManager();
    }

    public static AlarmProcessManager getInstance() {
        return SingletonHolder.instance;
    }

    private AlarmProcessManager() {

    }

    private ConcurrentMap<Integer, AlarmProcessResponseInfo> alarmProcessMap = new ConcurrentHashMap<Integer, AlarmProcessResponseInfo>();

    //处理告警数据
    public int OnAlarmProcess(AlarmProcessInfoVO alarmProcessInfoVO, String cmd) {
        // @TODO 将AlarmProcessInfoVO转化为AlarmProcessInfo ,并赋值

        AlarmProcessInfo alarmProcessInfo = new AlarmProcessInfo();
        alarmProcessInfo.setCmd(cmd);
        alarmProcessInfo.setUserid(alarmProcessInfoVO.getUserid());
        alarmProcessInfo.setUsername(alarmProcessInfoVO.getUsername());

        alarmProcessInfo.setAlarmlist(alarmProcessInfoVO.getAlarmList());

        AlarmProcessResponseInfo alarmProcessResponseInfo = new AlarmProcessResponseInfo();

        //将转换后的获得的数据放入回调方法中，去做请求处理，返回发序号
        int sequenceNo;
        sequenceNo = AlarmProcessApiMain.getInstance().getCtrlCB().OnAlarmProcessRequest(alarmProcessInfo);

        if (sequenceNo > 0) {

            this.alarmProcessMap.put(Integer.valueOf(sequenceNo), alarmProcessResponseInfo);

        }
        return sequenceNo;
    }


    //判断是否有响应
    public boolean isResponse(int sequenceNo) {

        //如果   alarmProcessMap中符合映射的sequenceNo，则返回false
        if (!this.alarmProcessMap.containsKey(Integer.valueOf(sequenceNo))) {
            return false;
        }
        //获取自增的发送序号

        AlarmProcessResponseInfo alarmProcessResponseInfo = this.alarmProcessMap.get(Integer.valueOf(sequenceNo));
        if (null == alarmProcessResponseInfo
                || null == alarmProcessResponseInfo.getErrorCode()) {
            return false;
        }

        return true;
        
    }


    //如果有响应获取响应的数据
    public AlarmProcessInfo getAlarmProcessInfo(int sequenceNo) {
        //如果   alarmProcessMap中不包含sequenceNo，则返回空

        if (!this.alarmProcessMap.containsKey(Integer.valueOf(sequenceNo))) {
            return null;
        }
        //获取 自增的发送序号

        AlarmProcessResponseInfo alarmProcessResponseInfo = this.alarmProcessMap.get(Integer.valueOf(sequenceNo));
        if (null == alarmProcessResponseInfo) {
            return null;
        }
        //返回响应数据
        return alarmProcessResponseInfo.getAlarmProcessInfo();
    }


    //处理并解析告警响应数据
    public int OnAlarmProcessResponse(int sequenceNo, int appType, ErrorCodeEnum result, AlarmProcessInfo alarmProcessInfo) {

        if (!this.alarmProcessMap.containsKey(Integer.valueOf(sequenceNo))) {
            return 0;
        }
        //获取到自增的发送序号，
        AlarmProcessResponseInfo alarmProcessResponseInfo = this.alarmProcessMap.get(Integer.valueOf(sequenceNo));
        if (null == alarmProcessResponseInfo) {
            return 0;
        }

        //对其响应的数据赋值
        alarmProcessResponseInfo.setErrorCode(result);

        alarmProcessResponseInfo.setReceiveTime(new Date());

        alarmProcessResponseInfo.setAlarmProcessInfo(alarmProcessInfo);

        return sequenceNo;
    }



    //获取告警响应的信息的结果返回给前端
    public AlarmProcessResponseInfo getResultByAlarmProcess(int alarmProcess) {
        if (this.alarmProcessMap.get(alarmProcess) != null) {
            return alarmProcessMap.get(alarmProcess);
        }
        return null;
    }

}
