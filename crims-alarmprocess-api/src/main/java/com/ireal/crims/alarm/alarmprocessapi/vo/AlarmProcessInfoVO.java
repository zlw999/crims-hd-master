package com.ireal.crims.alarm.alarmprocessapi.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ApiModel("与前端进行交互的告警处理信息实体类")
public class AlarmProcessInfoVO implements Serializable {
    @JSONField(ordinal=1)
    private String userid;
    @JSONField(ordinal=2)
    private String username;
    @JSONField(ordinal=3)
    private  String cmd;
    @JSONField(ordinal=4)
    private String result;


  /*@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(
          pattern = "yyyy-MM-dd HH:mm:ss",
          timezone = "GMT+8"
  )*/
  // private Date alarmAffirmTime;

    /*@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
   private Date alarmClearTime;*/
    @JSONField(ordinal=5)
   private List<AlarmInfo> alarmList ;

    

}
