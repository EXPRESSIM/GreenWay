package com.stardust.express.app.entity;

import java.io.Serializable;

/**
 * Created by Sylar on 15/5/12.
 */
public class HistoryRecordEntity implements Serializable {

    public int id;
    public String vehicleNumber;//车牌
    public String entranceName;//入站名称
    public String exitName;//出站名称
    public String recordDate;//记录时间
    public String amount;//价格
    public String comment;//备注
    public String merchandiseType;//货物类别
    public String vehicleType;//车型
    public String channelNumber;//车道
    public String carBodyImage;//车身照片
    public String carFrontImage;//车牌照片
    public String carBackImage;//车尾照片
    public String goodsImage;//获取照片
    public String adjustAmount;//追加金额
    public String video;//视频文件
    public boolean isGreen;//是否绿通
    public boolean isCommit;//是否已提交,true：已提交，false:未提交
    public long operatorId;//操作员Id
    public long leaderId;//审核员Id
    public String tollCollector;//收费员工号
    public String channelType;//放行方式
    public String operatorName;//操作员名称
    public int reason;//假冒原因
}
