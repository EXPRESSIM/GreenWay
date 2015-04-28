package com.stardust.express.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.stardust.express.app.activity.LoginActivity;

/**
 * Created by Gyb on 2015/4/27.
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TAG", "BootCompleteReceiver.onReceive();");
        Intent startIntent = new Intent(context, LoginActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startIntent);
    }
}
