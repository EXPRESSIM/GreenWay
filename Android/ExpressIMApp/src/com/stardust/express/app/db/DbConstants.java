package com.stardust.express.app.db;

/**
 * Created by sylar on 14-3-12.
 */
public class DbConstants {

    public static final String DB_NAME = "expressim.db";

    public static final int DB_VERSION = 1;

    public static interface TABLE {
        String history_record = "history_record";
    }

    public static interface COLUMN_HISTORY_RECORD {
        public String id = "id";
        public String vehicleNumber = "vehicleNumber";//车牌
        public String entranceName = "entranceName";//入站名称
        public String exitName = "exitName";//出站名称
        public String recordDate = "recordDate";//记录时间
        public String amount = "amount";//价格
        public String comment = "comment";//备注
        public String merchandiseType = "merchandiseType";//货物类别
        public String vehicleType = "vehicleType";//车型
        public String channelNumber = "channelNumber";//车道
        public String carBodyImage = "carBodyImage";//车身照片
        public String carFrontImage = "carFrontImage";//车牌照片
        public String carBackImage = "carBackImage";//车尾照片
        public String goodsImage = "goodsImage";//获取照片
        public String adjustAmount = "adjustAmount";//调整价格
        public String video = "video";//视频
        public String isGreen = "isGreen";//是否绿通
        public String isCommit = "isCommit";//是否已提交,true：已提交，false:未提交
        public String operatorId = "operatorId";//操作员
        public String leaderId = "leaderId";//审核员
        public String tollCollector = "tollCollector";//收费员工号
        public String channelType = "channelType";//放行方式
        public String operatorName = "operatorName";//操作员名称
        public String reason = "reason";//假冒原因
    }

    //创建服务器信息表
    public static StringBuffer create_history_record_table = new StringBuffer();

    static {

        create_history_record_table.append("create table ").append(TABLE.history_record);
        create_history_record_table.append("(");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.id).append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.vehicleNumber).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.entranceName).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.exitName).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.recordDate).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.amount).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.comment).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.merchandiseType).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.vehicleType).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.channelNumber).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.carFrontImage).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.carBackImage).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.carBodyImage).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.goodsImage).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.adjustAmount).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.isCommit).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.isGreen).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.video).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.operatorId).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.leaderId).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.channelType).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.tollCollector).append(" TEXT,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.reason).append(" INTEGER,");
        create_history_record_table.append(COLUMN_HISTORY_RECORD.operatorName).append(" TEXT");
        create_history_record_table.append(")");
    }

}
