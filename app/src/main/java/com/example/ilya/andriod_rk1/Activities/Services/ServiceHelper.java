package com.example.ilya.andriod_rk1.Activities.Services;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.ilya.andriod_rk1.Activities.MyResultReciever;

/**
 * Created by ilya on 3/7/17.
 */

public class ServiceHelper {

    private static final ServiceHelper instance = new ServiceHelper();

    private MyResultReciever mReciever = new MyResultReciever(new Handler());

    private ServiceHelper(){}

    public static ServiceHelper getInstance() {
        return instance;
    }

    public MyResultReciever getmReciever(){
        return mReciever;
    }

    public void requestNews(Context context){
        Intent intent = new Intent(context, NewsIntentService.class);
        intent.putExtra(NewsIntentService.NEWS_RESULTRECIEVER, mReciever);
        context.startService(intent);
    }

    public void setCallback (Callback callback){
        mReciever.setCallback(callback);
    }

    public interface Callback{
        void onNewsLoaded(int resultCode);
    }

}
