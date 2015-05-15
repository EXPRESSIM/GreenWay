package com.stardust.express.app.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.stardust.express.app.db.DbConstants;
import com.stardust.express.app.entity.HistoryRecordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sylar on 15/5/12.
 */
public class HistoryRecordDao extends BaseDao {

    public HistoryRecordDao(Context context) {
        super(context);
    }

    public List<HistoryRecordEntity> getRecordList() {
        List<HistoryRecordEntity> result = new ArrayList<HistoryRecordEntity>();
        Cursor cursor = null;
        try {
            cursor = database.rawQuery("select * from " + DbConstants.TABLE.history_record, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    HistoryRecordEntity record = new HistoryRecordEntity();
                    record.id = cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.id));
                    record.vehicleNumber = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.vehicleNumber));
                    record.entranceName = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.entranceName));
                    record.exitName = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.exitName));
                    record.recordDate = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.recordDate));
                    record.amount = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.amount));
                    record.comment = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.comment));
                    record.merchandiseType = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.merchandiseType));
                    record.vehicleType = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.vehicleType));
                    record.channelNumber = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.channelNumber));
                    record.carBodyImage = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.carBodyImage));
                    record.carFrontImage = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.carFrontImage));
                    record.carBackImage = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.carBackImage));
                    record.goodsImage = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.goodsImage));
                    record.adjustAmount = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.adjustAmount));
                    record.isGreen = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.isGreen)));
                    record.isCommit = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.isCommit)));
                    record.channelType = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.channelType));
                    record.tollCollector = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.tollCollector));
                    record.operatorId = cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.operatorId));
                    record.leaderId = cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.leaderId));
                    record.video = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.video));
                    record.operatorName = cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_HISTORY_RECORD.operatorName));
                    result.add(record);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void save(HistoryRecordEntity historyRecordEntity) {
        ContentValues values = new ContentValues();
        values.put(DbConstants.COLUMN_HISTORY_RECORD.vehicleNumber, historyRecordEntity.vehicleNumber);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.entranceName, historyRecordEntity.entranceName);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.exitName, historyRecordEntity.exitName);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.recordDate, historyRecordEntity.recordDate);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.amount, historyRecordEntity.amount);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.comment, historyRecordEntity.comment);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.merchandiseType, historyRecordEntity.merchandiseType);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.vehicleType, historyRecordEntity.vehicleType);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.channelNumber, historyRecordEntity.channelNumber);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.carBodyImage, historyRecordEntity.carBodyImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.carFrontImage, historyRecordEntity.carFrontImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.carBackImage, historyRecordEntity.carBackImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.goodsImage, historyRecordEntity.goodsImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.adjustAmount, historyRecordEntity.adjustAmount);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.isGreen, historyRecordEntity.isGreen);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.isCommit, historyRecordEntity.isCommit);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.channelType, historyRecordEntity.channelType);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.tollCollector, historyRecordEntity.tollCollector);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.operatorId, historyRecordEntity.operatorId);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.leaderId, historyRecordEntity.leaderId);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.video, historyRecordEntity.video);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.operatorName, historyRecordEntity.operatorName);
        database.insert(DbConstants.TABLE.history_record, null, values);
    }

    public void update(HistoryRecordEntity historyRecordEntity) {
        ContentValues values = new ContentValues();
        values.put(DbConstants.COLUMN_HISTORY_RECORD.vehicleNumber, historyRecordEntity.vehicleNumber);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.entranceName, historyRecordEntity.entranceName);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.exitName, historyRecordEntity.exitName);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.recordDate, historyRecordEntity.recordDate);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.amount, historyRecordEntity.amount);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.comment, historyRecordEntity.comment);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.merchandiseType, historyRecordEntity.merchandiseType);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.vehicleType, historyRecordEntity.vehicleType);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.channelNumber, historyRecordEntity.channelNumber);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.carBodyImage, historyRecordEntity.carBodyImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.carFrontImage, historyRecordEntity.carFrontImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.carBackImage, historyRecordEntity.carBackImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.goodsImage, historyRecordEntity.goodsImage);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.adjustAmount, historyRecordEntity.adjustAmount);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.isGreen, historyRecordEntity.isGreen);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.isCommit, historyRecordEntity.isCommit);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.channelType, historyRecordEntity.channelType);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.tollCollector, historyRecordEntity.tollCollector);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.operatorId, historyRecordEntity.operatorId);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.leaderId, historyRecordEntity.leaderId);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.operatorName, historyRecordEntity.operatorName);
        values.put(DbConstants.COLUMN_HISTORY_RECORD.video, historyRecordEntity.video);
        database.update(DbConstants.TABLE.history_record, values, null, new String[]{
                DbConstants.COLUMN_HISTORY_RECORD.id + " = " + historyRecordEntity.id});
    }

    public void clear() {
        database.delete(DbConstants.TABLE.history_record, null, null);
    }
}
