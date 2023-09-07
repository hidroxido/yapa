package com.radioccc.yetanotherpingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private TextView connectivityTextView;
    public NetworkChangeReceiver(TextView textView) {
        this.connectivityTextView = textView;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (connectivityTextView != null) {
            if (NetworkUtils.isWiFiConnected(context)) {
                connectivityTextView.setText(context.getString(R.string.WiFi));
            } else if (NetworkUtils.isMobileDataConnected(context)) {
                connectivityTextView.setText(context.getString(R.string.MobileData));
            } else {
                connectivityTextView.setText(context.getString(R.string.NoNetwork));
            }
        }
    }
}

