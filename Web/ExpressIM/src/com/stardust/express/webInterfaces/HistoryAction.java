package com.stardust.express.webInterfaces;

import com.stardust.express.bo.HistoryRecordBO;
import com.stardust.express.models.HistoryRecord;
import com.stardust.express.tools.ResponseObject;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sylar on 15/5/12.
 */
public class HistoryAction extends BaseAction {

    //车牌照片
    public File carFrontImage;
    //车身照片
    public File carBodyImage;
    //车尾照片
    public File carBackImage;
    //货物照片
    public File goodsImage;
    //视频
    public File video;

    private HistoryRecord historyRecord;

    private static final String SNAPSHOOT_DIR = "/snapshoot";
    private static final String VIDEO_DIR = "/video";

    public String archive() {
        HistoryRecordBO bo = new HistoryRecordBO(context);
//        HistoryRecord historyRecord = new HistoryRecord(context);
        String snapShootPath = ServletActionContext.getServletContext().getRealPath(SNAPSHOOT_DIR);
        String videoPath = ServletActionContext.getServletContext().getRealPath(VIDEO_DIR);
        ResponseObject.Builder builder = new ResponseObject.Builder();
        try {
            historyRecord.setSnapshoot1(copyFileToDest(snapShootPath, carFrontImage));
            historyRecord.setSnapshoot2(copyFileToDest(snapShootPath, carBodyImage));
            historyRecord.setSnapshoot3(copyFileToDest(snapShootPath, carBackImage));
            historyRecord.setSnapshoot4(copyFileToDest(snapShootPath, goodsImage));
            historyRecord.setVideo(copyFileToDest(videoPath, video));
            historyRecord = (HistoryRecord) bo.update(historyRecord);
            if (historyRecord != null && historyRecord.getId() > 0) {
                builder.setSuccess(true);
                builder.setData(null);
                builder.setMessage("数据录入成功");
            } else {
                builder.setSuccess(false);
                builder.setData(null);
                builder.setMessage("数据录入失败");
            }
            responseData = builder.build();
        } catch (IOException e) {
            builder.setSuccess(false);
            builder.setData(null);
            builder.setMessage("数据录入失败");
            e.printStackTrace();
        }
        return SUCCESS;
    }

    private String copyFileToDest(String baseDir, File file) throws IOException {
        if (file == null) return "";
        String destPath = baseDir + File.separator + file.getName();
        FileUtils.copyFile(file, new File(destPath));
        return destPath;
    }

    public File getVideo() {
        return video;
    }

    public void setVideo(File video) {
        this.video = video;
    }

    public File getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(File goodsImage) {
        this.goodsImage = goodsImage;
    }

    public File getCarBackImage() {
        return carBackImage;
    }

    public void setCarBackImage(File carBackImage) {
        this.carBackImage = carBackImage;
    }

    public File getCarBodyImage() {
        return carBodyImage;
    }

    public void setCarBodyImage(File carBodyImage) {
        this.carBodyImage = carBodyImage;
    }

    public File getCarFrontImage() {
        return carFrontImage;
    }

    public void setCarFrontImage(File carFrontImage) {
        this.carFrontImage = carFrontImage;
    }

    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }

    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }
}
