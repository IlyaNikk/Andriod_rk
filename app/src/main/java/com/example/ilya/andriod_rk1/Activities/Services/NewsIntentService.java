package com.example.ilya.andriod_rk1.Activities.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;

public class NewsIntentService extends IntentService {

    public static final String NEWS_RESULTRECIEVER = "news_ResultReciever";
    public final static int RESULT_SUCCESS = 1;
    public final static int RESULT_ERROR = 2;
    private ResultReceiver receiver;

    public NewsIntentService() {
        super("NewsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        receiver = intent.getParcelableExtra(NEWS_RESULTRECIEVER);
        int resultCode = NewsProcessor.processor(this);

        if(receiver != null) {
            receiver.send(resultCode, null);
        }

    }
}
