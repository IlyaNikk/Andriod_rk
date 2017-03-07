package com.example.ilya.andriod_rk1.Activities.Services;

import java.io.IOException;

import ru.mail.weather.lib.News;
import ru.mail.weather.lib.NewsLoader;

/**
 * Created by ilya on 3/7/17.
 */

public class RestMethod {

    public static News Rest(String Category){
        try{
            return new NewsLoader().loadNews(Category);
        } catch (IOException exception){
            return null;
        }
    }
}
