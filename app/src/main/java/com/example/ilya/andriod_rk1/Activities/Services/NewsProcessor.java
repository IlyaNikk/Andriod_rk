package com.example.ilya.andriod_rk1.Activities.Services;

import android.content.Context;

import ru.mail.weather.lib.News;
import ru.mail.weather.lib.NewsLoader;
import ru.mail.weather.lib.Storage;

/**
 * Created by ilya on 3/7/17.
 */

public class NewsProcessor {

    public static int processor(Context context){
        String category = Storage.getInstance(context).loadCurrentTopic();
        News news = RestMethod.Rest(category);
        if(news != null) {
            Storage.getInstance(context).saveNews(news);
            return NewsIntentService.RESULT_SUCCESS;
        } else {
            return NewsIntentService.RESULT_ERROR;
        }

    }
}
