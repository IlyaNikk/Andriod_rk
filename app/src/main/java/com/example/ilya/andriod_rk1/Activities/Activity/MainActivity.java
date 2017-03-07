package com.example.ilya.andriod_rk1.Activities.Activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilya.andriod_rk1.Activities.Services.NewsIntentService;
import com.example.ilya.andriod_rk1.Activities.Services.ServiceHelper;
import com.example.ilya.andriod_rk1.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.mail.weather.lib.News;
import ru.mail.weather.lib.Scheduler;
import ru.mail.weather.lib.Storage;

public class MainActivity extends AppCompatActivity implements ServiceHelper.Callback{

    private Button mRefresh;
    private Button mChange;
    private Button mInBack;
    private Button mNotInBack;
    private TextView mTitle;
    private TextView mStat;
    private TextView mDate;
    private TextView mContent;

    private Storage mStorage;

    private final ServiceHelper mServiceHelper = ServiceHelper.getInstance(this);

    static {
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .penaltyLog()
                .penaltyDeath()
                .build()
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorage = Storage.getInstance(this);

        mTitle = (TextView)findViewById(R.id.title);
        mStat = (TextView)findViewById(R.id.stat);
        mStat.setText(R.string.date);
        mDate = (TextView)findViewById(R.id.date);
        mContent = (TextView)findViewById(R.id.content);

        mRefresh = (Button)findViewById(R.id.refresh);
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = mStorage.loadCurrentTopic();
                ServiceHelper serviceHelper = ServiceHelper.getInstance(MainActivity.this);
                serviceHelper.requestNews(MainActivity.this);
            }
        });

        mChange = (Button)findViewById(R.id.category);
        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class );
                startActivity(intent);
            }
        });

        mInBack = (Button)findViewById(R.id.background);
        mInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInBackground(false);
            }
        });

        mNotInBack = (Button)findViewById(R.id.no_background);
        mNotInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInBackground(true);
            }
        });

        mServiceHelper.setCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String category = mStorage.loadCurrentTopic();
        if(category.length() == 0){
            mStorage.saveCurrentTopic("it");
        }

        ServiceHelper serviceHelper = ServiceHelper.getInstance(this);
        serviceHelper.requestNews(this);

    }

    @Override
    public void onNewsLoaded(int resultCode){
        if(resultCode == NewsIntentService.RESULT_ERROR){
            Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
        }
        News news = Storage.getInstance(this).getLastSavedNews();
        mTitle.setText(news.getTitle());
        mDate.setText(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).format(new Date(news.getDate())));
        mContent.setText(news.getBody());
    }

    public void updateInBackground(boolean update){
        Intent intent = new Intent();
        if(update){
             Scheduler.getInstance().schedule(this, ServiceHelper.getInstance(this).getIntent(), 60000);
        } else {
             Scheduler.getInstance().unschedule(this, ServiceHelper.getInstance(this).getIntent());
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mServiceHelper.setCallback(null);
    }
}
