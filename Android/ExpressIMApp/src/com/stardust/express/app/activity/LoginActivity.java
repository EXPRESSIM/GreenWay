package com.stardust.express.app.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.stardust.express.app.BaseActivity;
import com.stardust.express.app.R;
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
    }

    @Override
    protected void fillData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if (validateInput()) {
                    //TODO 登陆接口调用
                    startActivity(new Intent(this, UserListActivity.class));
                }
                break;
            default:
                break;
        }
    }

    private boolean validateInput() {
        if (!StringUtils.isNotNull(userNameEditText.getText().toString())) {
            ToastUtils.showToast(this, "用户名不能为空！");
            return false;
        }

        if (!StringUtils.isNotNull(passwordEditText.getText().toString())) {
            ToastUtils.showToast(this, "密码不能为空！");
            return false;
        }
        return true;
    }
}
