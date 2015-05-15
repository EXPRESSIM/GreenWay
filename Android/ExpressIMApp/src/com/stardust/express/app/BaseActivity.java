package com.stardust.express.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Created by Gyb on 2015/4/27.
 */
public abstract class BaseActivity extends ActionBarActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forceShowActionBarOverflowMenu(this);
        setContentView(getLayoutResource());
        initViews();
        fillData();
    }

    public void showProgressDialog(String message) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        if (!isFinishing()) {
            mProgressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    protected abstract int getLayoutResource();

    protected abstract void initViews();

    protected abstract void fillData();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK
                || super.onKeyDown(keyCode, event);
    }

    protected static void forceShowActionBarOverflowMenu(Context context) {
        try {
            ViewConfiguration config = ViewConfiguration.get(context);
            Field[] fields = ViewConfiguration.class.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("sHasPermanentMenuKey")) {
                    field.setAccessible(true);
                    field.setBoolean(config, false);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}