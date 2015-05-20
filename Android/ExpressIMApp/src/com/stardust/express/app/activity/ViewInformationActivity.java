package com.stardust.express.app.activity;

import android.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
import com.stardust.express.app.db.dao.HistoryRecordDao;
import com.stardust.express.app.entity.HistoryRecordEntity;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.request.ArchiveRequest;
import com.stardust.express.app.request.LeaderLogonRequest;
import com.stardust.express.app.response.ArchiveResponse;
import com.stardust.express.app.response.LeaderLogonResponse;
import com.stardust.express.app.utils.*;

/**
 * Created by Sylar on 15/5/14.
 */
public class ViewInformationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView carFrontImage;
    private ImageView carBodyImage;
    private ImageView carBackImage;
    private ImageView goodsImage;

    private TextView stationName;
    private TextView dateTime;
    private TextView carNumber;
    private TextView carType;
    private TextView totalAmount;
    private TextView stationChannel;
    private TextView goodsName;
    private TextView comment;
    private TextView operator;
    private TextView videoPath;
    private TextView isGreenTextView;
    private TextView tollCollectorTextView;
    private TextView adjustAmount;
//    private TextView channelTypeTextView;
    private TextView reasonTextView;
    private LinearLayout adjustAmountLayout;
    private LinearLayout goodsNameLayout;
    private LinearLayout reasonLayout;
    private Button submitButton;
    private HistoryRecordEntity historyRecordEntity;
    private HistoryRecordDao historyRecordDao;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_view_information;
    }

    @Override
    protected void initViews() {
        historyRecordDao = new HistoryRecordDao(this);

        carFrontImage = (ImageView) findViewById(R.id.car_front_image);
        carBodyImage = (ImageView) findViewById(R.id.car_body_image);
        carBackImage = (ImageView) findViewById(R.id.car_back_image);
        goodsImage = (ImageView) findViewById(R.id.goods_image);
        stationName = (TextView) findViewById(R.id.station_name);
        dateTime = (TextView) findViewById(R.id.date_time);
        carNumber = (TextView) findViewById(R.id.car_number);
        carType = (TextView) findViewById(R.id.car_type);
        totalAmount = (TextView) findViewById(R.id.total_amount);
        stationChannel = (TextView) findViewById(R.id.station_channel);
        goodsName = (TextView) findViewById(R.id.goods_name);
        comment = (TextView) findViewById(R.id.comment);
        operator = (TextView) findViewById(R.id.operator);
        videoPath = (TextView) findViewById(R.id.video_path);
        isGreenTextView = (TextView) findViewById(R.id.is_green);
        tollCollectorTextView = (TextView) findViewById(R.id.toll_collector);
        adjustAmount = (TextView) findViewById(R.id.adjust_amount);
        adjustAmountLayout = (LinearLayout) findViewById(R.id.adjust_amount_layout);
        goodsNameLayout = (LinearLayout) findViewById(R.id.goods_name_layout);
//        channelTypeTextView = (TextView) findViewById(R.id.channel_type);
        reasonTextView = (TextView) findViewById(R.id.reason);
        reasonLayout = (LinearLayout) findViewById(R.id.reason_layout);
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    @Override
    protected void fillData() {
        historyRecordEntity = (HistoryRecordEntity) getIntent().getSerializableExtra("HistoryRecord");
        if (historyRecordEntity.isCommit) {
            submitButton.setVisibility(View.GONE);
        } else {
            submitButton.setVisibility(View.VISIBLE);
        }

        carFrontImage.setImageBitmap(BitmapUtils.getBitmapWithNewSize(historyRecordEntity.carFrontImage,
                ScreenUtils.dp2px(this, 70), ScreenUtils.dp2px(this, 70)));
        carBodyImage.setImageBitmap(BitmapUtils.getBitmapWithNewSize(historyRecordEntity.carBodyImage,
                ScreenUtils.dp2px(this, 70), ScreenUtils.dp2px(this, 70)));
        carBackImage.setImageBitmap(BitmapUtils.getBitmapWithNewSize(historyRecordEntity.carBackImage,
                ScreenUtils.dp2px(this, 70), ScreenUtils.dp2px(this, 70)));
        goodsImage.setImageBitmap(BitmapUtils.getBitmapWithNewSize(historyRecordEntity.goodsImage,
                ScreenUtils.dp2px(this, 70), ScreenUtils.dp2px(this, 70)));

        setTitle(historyRecordEntity.exitName + "-" + SharedUtil.getString(this, "DeviceNO"));
        stationName.setText(historyRecordEntity.entranceName);
        dateTime.setText(historyRecordEntity.recordDate);
        carNumber.setText(historyRecordEntity.vehicleNumber);
        carType.setText(historyRecordEntity.vehicleType);
        totalAmount.setText(historyRecordEntity.amount);
        stationChannel.setText(historyRecordEntity.channelNumber);
        goodsName.setText(historyRecordEntity.merchandiseType);
        comment.setText(historyRecordEntity.comment);
        operator.setText(historyRecordEntity.operatorName);
        videoPath.setText(historyRecordEntity.video);
        isGreenTextView.setText(historyRecordEntity.isGreen ? "是" : "否");
        tollCollectorTextView.setText(historyRecordEntity.tollCollector);
        adjustAmount.setText(historyRecordEntity.adjustAmount);
//        channelTypeTextView.setText(historyRecordEntity.channelType);
        reasonTextView.setText(getResources().getStringArray(R.array.reason_array)[(historyRecordEntity.reason - 1)]);

        if (historyRecordEntity.isGreen) {
            adjustAmountLayout.setVisibility(View.GONE);
            goodsNameLayout.setVisibility(View.VISIBLE);
            reasonLayout.setVisibility(View.GONE);
        } else {
            adjustAmountLayout.setVisibility(View.VISIBLE);
            goodsNameLayout.setVisibility(View.GONE);
            reasonLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_button:
                if (historyRecordEntity.leaderId == -1) {
                    leaderLogon();
                } else {
                    sendInformation();
                }
                break;
        }
    }

    private void leaderLogon() {
        final View dialogView = LayoutInflater.from(ViewInformationActivity.this).inflate(R.layout.dialog_validate, null);
        final EditText userName = (EditText) dialogView.findViewById(R.id.username);
        final EditText password = (EditText) dialogView.findViewById(R.id.password);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("提交验证")
                .setPositiveButton("验证", null).setNegativeButton("取消", null)
                .create();
        alertDialog.setView(dialogView);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = userName.getText().toString();
                String strPassword = password.getText().toString();
                if (!StringUtils.isNotNull(strUserName)) {
                    ToastUtils.showToastAtCenter(ViewInformationActivity.this, "用户名不能为空!");
                    return;
                }

                if (!StringUtils.isNotNull(strPassword)) {
                    ToastUtils.showToastAtCenter(ViewInformationActivity.this, "密码不能为空!");
                    return;
                }

                showProgressDialog("验证中...");
                new LeaderLogonRequest(ViewInformationActivity.this, strUserName, strPassword, new StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        dismissProgressDialog();
                        alertDialog.dismiss();
                        LeaderLogonResponse logonResponse = new LeaderLogonResponse(response);
                        if (logonResponse.success) {
                            historyRecordEntity.leaderId = logonResponse.data.id;
                            sendInformation();
                        } else {
                            ToastUtils.showToastAtCenter(ViewInformationActivity.this, logonResponse.message);
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        dismissProgressDialog();
                        ToastUtils.showToastAtCenter(ViewInformationActivity.this, "网络链接失败，请稍后重试");
                    }
                }).execute();
            }
        });
    }

    private void sendInformation() {
        if (NetworkUtils.isNetworkConnected(ViewInformationActivity.this)) {
            showProgressDialog("提交中...");
            new ArchiveRequest(this, historyRecordEntity, new StringResponseListener() {
                @Override
                public void onResponse(String response) {
                    dismissProgressDialog();
                    final ArchiveResponse archiveResponse = new ArchiveResponse(response);
                    if (archiveResponse.success) {
                        historyRecordEntity.isCommit = true;
                        historyRecordDao.update(historyRecordEntity);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                submitButton.setVisibility(View.GONE);
                            }
                        });

                    }
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ToastUtils.showToastAtCenter(ViewInformationActivity.this, archiveResponse.message);
                        }
                    });
                }

                @Override
                public void onError(String errorMessage) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissProgressDialog();
                            ToastUtils.showToastAtCenter(ViewInformationActivity.this, "网络链接失败，请稍后重试");
                        }
                    });
                }
            }).execute();
        } else {
            ToastUtils.showToastAtCenter(ViewInformationActivity.this, "网络链接失败，请稍后重试");
        }
    }

}
