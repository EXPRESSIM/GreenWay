package com.stardust.express.app.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.Constants;
import com.stardust.express.app.R;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.request.OperatorLogonRequest;
import com.stardust.express.app.response.OperatorLogonResponse;
import com.stardust.express.app.utils.SharedUtil;
import com.stardust.express.app.utils.StringUtils;
import com.stardust.express.app.utils.ToastUtils;


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
                    }
                });
                break;
            default:
                break;
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
