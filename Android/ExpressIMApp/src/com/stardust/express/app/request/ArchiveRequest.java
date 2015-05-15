package com.stardust.express.app.request;

import android.content.Context;
import com.stardust.express.app.Constants;
import com.stardust.express.app.entity.HistoryRecordEntity;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.utils.SharedUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sylar on 15/5/14.
 */
public class ArchiveRequest extends Thread {

    private HistoryRecordEntity entity;
    private Context context;
    private StringResponseListener listener;

    public ArchiveRequest(Context context, HistoryRecordEntity historyRecord,
                          StringResponseListener listener) {
        this.entity = historyRecord;
        this.context = context;
        this.listener = listener;
    }

    protected String getRequestUrl() {
        return SharedUtil.getString(context, "Host") + Constants.ARCHIVE_URL;
    }

    protected Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("historyRecord.vehicleNumber", entity.vehicleNumber);
        params.put("historyRecord.entranceGateway", entity.entranceName);
        params.put("historyRecord.exitGateway", entity.exitName);
        params.put("historyRecord.date", entity.recordDate);
        params.put("historyRecord.amount", entity.amount);
        params.put("historyRecord.comment", entity.comment);
        params.put("historyRecord.merchandiseType", entity.merchandiseType);
        params.put("historyRecord.vehicleType", entity.vehicleType);
        params.put("historyRecord.channel", entity.channelNumber);
        params.put("historyRecord.adjustAmount", entity.adjustAmount);
        params.put("historyRecord.isAffectation", String.valueOf(!entity.isGreen));
        params.put("historyRecord.operator.id", String.valueOf(entity.operatorId));
        params.put("historyRecord.leader.id", String.valueOf(entity.leaderId));
        params.put("historyRecord.tollCollector", String.valueOf(entity.tollCollector));
        params.put("historyRecord.channelType", entity.channelType);
        return params;
    }

    private Map<String, File> getRequestUploadFileParams() {
        Map<String, File> params = new HashMap<String, File>();
        params.put("carFrontImage", new File(entity.carFrontImage));
        params.put("carBodyImage", new File(entity.carBodyImage));
        params.put("carBackImage", new File(entity.carBackImage));
        params.put("goodsImage", new File(entity.goodsImage));
        params.put("video", new File(entity.video));
        return params;
    }

    private String postFile(String actionUrl, Map<String, String> params,
                            Map<String, File> files)
            throws Exception {
        String result = null;
        HttpClient httpClient = getHttpClient();
        HttpPost post = new HttpPost(actionUrl);
        MultipartEntity entity = new MultipartEntity();

        if (params != null) {

            for (Map.Entry<String, String> entry : params.entrySet()) {
                entity.addPart(entry.getKey(), new StringBody(entry.getValue(), Charset.forName(HTTP.UTF_8)));
            }
        }

        if (files != null) {
            for (Map.Entry<String, File> entry : files.entrySet()) {
                File file = entry.getValue();
                if (file != null && file.exists()) {
                    entity.addPart(entry.getKey(), new FileBody(file));
//                    if (file.getName().endsWith(".jpg") || file.getName().endsWith(".JPG")) {
////                        byte[] imageBytes = BitmapUtils.compressImage(file.getAbsolutePath());
////                        ContentBody contentBody = new InputStreamBody(new ByteArrayInputStream(imageBytes), file.getName());
//
//                    } else {
//                        entity.addPart(entry.getKey(), new FileBody(file));
//                    }
                }
            }
        }
        post.setEntity(entity);
        HttpResponse resp = httpClient.execute(post);
        if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity resEntity = resp.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, "utf-8");
                resEntity.consumeContent();
            }
        } else {
            throw new Exception("Post file error");
        }

        httpClient.getConnectionManager().shutdown();
        return result;
    }

    private HttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 180 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 180 * 1000);
        HttpClientParams.setRedirecting(httpParams, true);
        return new DefaultHttpClient(httpParams);
    }

    @Override
    public void run() {
        try {
            String response = postFile(getRequestUrl(), getRequestParams(), getRequestUploadFileParams());
            listener.onResponse(response);
        } catch (Exception e) {
            listener.onError(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void execute() {
        start();
    }
}
