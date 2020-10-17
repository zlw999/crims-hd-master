package com.ireal.crims.alarm.alarmprocessapi.apps.controller;

import com.github.pagehelper.PageInfo;
import com.ireal.crims.alarm.alarmprocessapi.vo.AlarmInfoVO;
import com.ireal.crims.alarm.alarmprocessapi.vo.DeviceStatStateInfoVO;
import com.ireal.crims.alarm.alarmprocessapi.vo.StationInfoVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stateStatInfo")
public class StateStatisticsController {
    public static Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);

    @GetMapping("/getDevState")
    @ApiOperation(value = "以线路+车站分类，获取当前的设备状态(在线数和故障数)")
    public List<DeviceStatStateInfoVO> FindDevGroupStat(@RequestParam(required = false, defaultValue = "000000") String lineId, @RequestParam(required = false, defaultValue = "00") String stationId)
    {
        /*
         *   从设备表crimsdbs.conf_device中统计设备数量；
         *   从告警表crimsrec.rec_alarminfo中统计离线数和故障数(同一个设备的不同类型的故障算一个故障)；
         *   lineId:XXXXXX,若为000000，表示所有线路，stationId将无效；若为XXXX00,表示XXXX的线路
         *   stationId:XX,若为00,则以lineId的值为统计条件；非00时，以lineId+stationId为统计条件
         */
        return null;
    }

    @GetMapping("/getStationInfo")
    @ApiOperation(value = "获取指定线路的所有车站信息:按起点到终点排序")
    public List<StationInfoVO> GetStationInfo(@RequestParam(required = true) String lineId)
    {

        return null;
    }

    @GetMapping("/getStationDevFaultInfo")
    @ApiOperation(value = "获取指定线路的所有车站设备故障信息")
    public List<DeviceStatStateInfoVO> GetStationDevFaultInfo(@RequestParam(required = true) String lineId)
    {
        /*
         *   从设备表中统计设备数量；
         *   从告警表中统计离线数和故障数(同一个设备的不同类型的故障算一个故障)；
         *   lineId:XXXXXX,若为000000，表示所有线路；若为XXXX00或XXXX,表示XXXX的线路;
         *   前端的判断逻辑：当离线数或故障数大于0时，该站存在设备故障
         */
        return null;
    }


    @GetMapping("/getTodoProcessAlarmInfo")
    @ApiOperation(value = "获取待处理的告警")
    public PageInfo<AlarmInfoVO> GetTodoProcessAlarmInfo(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                         @RequestParam(required = false, defaultValue = "10") int pageSize)
    {
        /*
        * 查询条件为：rec_alarminfo.AlarmDisTime='2000-01-01 01:01:01' AND rec_alarminfo.AlarmAffirmTime='2000-01-01 01:01:01'
        */
        return null;
    }
}
