package com.example.ilya.andriod_rk1.Activities.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;

import java.io.IOException;

public class NewsIntentService extends IntentService {

    public static String NEWS_CATEGORY = "news_category";
    public static String NEWS_RESULTRECIEVER = "news_ResultReciever";
    public final static int RESULT_SUCCESS = 1;
    public final static int RESULT_ERROR = 2;
    public NewsIntentService() {
        super("NewsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver reciever = intent.getParcelableExtra(NEWS_RESULTRECIEVER);
        int resultCode = NewsProcessor.processor(this);

        if(resultCode == RESULT_SUCCESS){
            reciever.send(RESULT_SUCCESS, null);
        } else {
            reciever.send(RESULT_ERROR, null);
        }

    }
}
