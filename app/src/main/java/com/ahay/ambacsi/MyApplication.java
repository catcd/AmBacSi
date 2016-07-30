package com.ahay.ambacsi;

import android.app.Application;

import com.ahay.ambacsi.helper.ConnectivityReceiver;

/**
 * Created by SONY on 02-Jul-16.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
