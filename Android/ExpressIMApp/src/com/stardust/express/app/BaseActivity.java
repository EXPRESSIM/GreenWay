package com.stardust.express.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Gyb on 2015/4/27.
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initViews();
        fillData();
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
        Log.e("TAG", keyCode + "");
        return keyCode == KeyEvent.KEYCODE_BACK
                || keyCode == KeyEvent.KEYCODE_HOME
                || super.onKeyDown(keyCode, event);
    }
}