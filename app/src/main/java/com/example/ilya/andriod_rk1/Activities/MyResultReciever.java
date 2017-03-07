package com.example.ilya.andriod_rk1.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.example.ilya.andriod_rk1.Activities.Services.ServiceHelper;

/**
 * Created by ilya on 3/7/17.
 */

public class MyResultReciever extends ResultReceiver {

    private ServiceHelper.Callback mCallback;

    public MyResultReciever(final Handler handler){
        super(handler);
    }

    public void setCallback (ServiceHelper.Callback callback){
        this.mCallback = callback;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (mCallback != null) {
            mCallback.onNewsLoaded(resultCode);
        }
    }
}
