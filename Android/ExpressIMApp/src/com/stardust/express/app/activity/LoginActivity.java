package com.stardust.express.app.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.Constants;
import com.stardust.express.app.R;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.request.CargoListRequest;
import com.stardust.express.app.request.OperatorLogonRequest;
import com.stardust.express.app.request.StationListRequest;
import com.stardust.express.app.request.TollCollectorListRequest;
import com.stardust.express.app.response.OperatorLogonResponse;
import com.stardust.express.app.utils.SharedUtil;
import com.stardust.express.app.utils.StringUtils;
import com.stardust.express.app.utils.ToastUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.CountDownLatch;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        userNameEditText = (EditText) findViewById(R.id.login_username);
        passwordEditText = (EditText) findViewById(R.id.login_password);
        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.logo).setOnClickListener(this);
    }

    @Override
    protected void fillData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if (validateInput()) {
                    showProgressDialog("登陆中...");
                    new OperatorLogonRequest(this, userNameEditText.getText().toString(),
                            passwordEditText.getText().toString(), new StringResponseListener() {
                        @Override
                        public void onResponse(String response) {
                            dismissProgressDialog();
                            OperatorLogonResponse logonResponse = new OperatorLogonResponse(response);
                            if (logonResponse.success) {
                                SharedUtil.putLong(LoginActivity.this, Constants.SHARED_KEY.uid, logonResponse.data.id);
                                SharedUtil.putString(LoginActivity.this, Constants.SHARED_KEY.name, logonResponse.data.name);
                                SharedUtil.putString(LoginActivity.this, Constants.SHARED_KEY.account, logonResponse.data.username);
                                Intent intent = new Intent(LoginActivity.this, SendInformationActivity.class);
                                intent.putExtra("Operator", logonResponse.data);
                                startActivity(intent);
                                finish();
                            } else {
                                ToastUtils.showToastAtCenter(LoginActivity.this, logonResponse.message);
                            }
                        }

                        @Override
                        public void onError(String errorMessage) {
                            dismissProgressDialog();
                            ToastUtils.showToastAtCenter(LoginActivity.this, "网络链接超时，请稍后重试！");
                        }
                    }
                    ).execute();
                }
                break;
            case R.id.logo:
                final View dialogView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_init_host, null);
                final EditText userName = (EditText) dialogView.findViewById(R.id.username);
                final EditText password = (EditText) dialogView.findViewById(R.id.password);
                final EditText exitName = (EditText) dialogView.findViewById(R.id.exit_name);
                final EditText host = (EditText) dialogView.findViewById(R.id.host_name);
                final EditText deviceNo = (EditText) dialogView.findViewById(R.id.device_no);
                exitName.setText(SharedUtil.getString(LoginActivity.this, "StationName"));
                host.setText(SharedUtil.getString(LoginActivity.this, "Host"));
                deviceNo.setText(SharedUtil.getString(LoginActivity.this, "DeviceNO"));
                final AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("初始化")
                        .setPositiveButton("确定", null).setNegativeButton("取消", null)
                        .create();
                alertDialog.setView(dialogView);
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String strUsername = userName.getText().toString();
                        String strPassword = password.getText().toString();
                        String strExitName = exitName.getText().toString();
                        String strDeviceNo = deviceNo.getText().toString();
                        String strHost = host.getText().toString();
                        if (!StringUtils.isNotNull(strUsername)) {
                            ToastUtils.showToastAtCenter(LoginActivity.this, "请输入管理员名称");
                            return;
                        }
                        if (!StringUtils.isNotNull(strPassword)) {
                            ToastUtils.showToastAtCenter(LoginActivity.this, "请输入密码");
                            return;
                        }
                        if (!StringUtils.isNotNull(strExitName)) {
                            ToastUtils.showToastAtCenter(LoginActivity.this, "请输入收费站名称");
                            return;
                        }
                        if (!StringUtils.isNotNull(strHost)) {
                            ToastUtils.showToastAtCenter(LoginActivity.this, "请输入服务器地址");
                            return;
                        }
                        if (!StringUtils.isNotNull(strDeviceNo)) {
                            ToastUtils.showToastAtCenter(LoginActivity.this, "请输入设备编号");
                            return;
                        }

                        if (!strUsername.equals("MASTER") || !strPassword.equals("#bluestory-01")) {
                            ToastUtils.showToastAtCenter(LoginActivity.this, "管理员用户名或者密码错误");
                            return;
                        }

                        if (!strHost.contains("http://")) {
                            strHost = "http://" + strHost;
                        }

                        SharedUtil.putString(LoginActivity.this, "StationName", strExitName);
                        SharedUtil.putString(LoginActivity.this, "Host", strHost);
                        SharedUtil.putString(LoginActivity.this, "DeviceNO", strDeviceNo);
                        alertDialog.dismiss();

                        new Thread(new InitBasicDataRunnable()).start();

                    }
                });
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showProgressDialog("正在初始化基础数据");
                    break;
                case 2:
                    dismissProgressDialog();
                    ToastUtils.showToast(LoginActivity.this, "基础数据初始化成功");
                    break;
                case 3:
                    dismissProgressDialog();
                    ToastUtils.showToast(LoginActivity.this, "基础数据初始化失败,错误原因\r\n" + msg.obj.toString());
                    break;
                case 4:
                    dismissProgressDialog();
                    ToastUtils.showToast(LoginActivity.this, "基础数据初始化失败");
                    break;
            }
        }
    };


    private class InitBasicDataRunnable implements Runnable {

        private CountDownLatch latch;
        private Throwable[] throwables;

        public InitBasicDataRunnable() {
            latch = new CountDownLatch(3);
            throwables = new Throwable[3];
        }

        @Override
        public void run() {
            try {
                handler.sendEmptyMessage(1);
                fetchTollerData(latch);
                fetchStationData(latch);
                fetchCargoData(latch);
                latch.await();

                StringBuilder errorMsg = new StringBuilder();
                for (Throwable throwable : throwables) {
                    if (throwable != null) {
                        errorMsg.append(throwable.getMessage());
                    }
                }
                errorMsg.trimToSize();
                if (errorMsg.length() == 0) {
                    handler.sendEmptyMessage(2);
                } else {
                    handler.sendMessage(handler.obtainMessage(3, errorMsg.toString()));

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(4);
            }
        }

        private void fetchCargoData(final CountDownLatch latch) {
            new CargoListRequest(LoginActivity.this, new StringResponseListener() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() == 0) {
                            throwables[2] = new Throwable("货物信息初始化失败,请检查服务器配置信息是否正确");
                        } else {
                            SharedUtil.putString(LoginActivity.this, "Cargo", response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        throwables[2] = new Throwable("货物信息初始化失败,请检查服务器配置信息是否正确");
                    } finally {
                        latch.countDown();
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    latch.countDown();
                    throwables[2] = new Throwable("货物信息初始化失败,请检查网络");
                }
            }).execute();
        }

        private void fetchStationData(final CountDownLatch latch) {
            new StationListRequest(LoginActivity.this, new StringResponseListener() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() == 0) {
                            throwables[1] = new Throwable("收费站信息初始化失败,请检查服务器配置信息是否正确\r\n");
                        } else {
                            SharedUtil.putString(LoginActivity.this, "Station", response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        throwables[1] = new Throwable("收费站信息初始化失败,请检查服务器配置信息是否正确\r\n");
                    } finally {
                        latch.countDown();
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    latch.countDown();
                    throwables[1] = new Throwable("收费站信息初始化失败,请检查网络\r\n");
                }
            }).execute();
        }

        private void fetchTollerData(final CountDownLatch latch) {
            new TollCollectorListRequest(LoginActivity.this, new StringResponseListener() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() == 0) {
                            throwables[0] = new Throwable("收费员信息初始化失败,请检查服务器配置信息是否正确\r\n");
                        } else {
                            SharedUtil.putString(LoginActivity.this, "TollCollector", response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        throwables[0] = new Throwable("收费员信息初始化失败,请检查服务器配置信息是否正确]\r\n");
                    } finally {
                        latch.countDown();
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    latch.countDown();
                    throwables[0] = new Throwable("收费员信息初始化失败,请检查网络\r\n");
                }
            }).execute();
        }
    }

    private boolean validateInput() {
        if (!StringUtils.isNotNull(userNameEditText.getText().toString())) {
            ToastUtils.showToastAtCenter(this, "用户名不能为空！");
            return false;
        }

        if (!StringUtils.isNotNull(passwordEditText.getText().toString())) {
            ToastUtils.showToastAtCenter(this, "密码不能为空！");
            return false;
        }
        return true;
    }
}
