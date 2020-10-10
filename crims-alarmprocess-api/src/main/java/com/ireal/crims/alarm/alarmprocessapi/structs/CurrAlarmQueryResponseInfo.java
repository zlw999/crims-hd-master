package com.ireal.crims.alarm.alarmprocessapi.structs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;
import com.ireal.crims.record.model.alarminfo.Rec_alarminfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther zhanglongwei
 * @create 2020-09-11-11:43
 */
@Data
@ApiModel(value = "封装分页信息和最新更新时间的实体类")
public class CurrAlarmQueryResponseInfo {


    private PageInfo<Rec_alarminfo>  pageInfo;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date updatetime;
}
