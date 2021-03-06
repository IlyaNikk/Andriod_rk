package com.example.ilya.andriod_rk1.Activities.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.ilya.andriod_rk1.R;

import ru.mail.weather.lib.Storage;
import ru.mail.weather.lib.Topics;

public class CategoryActivity extends AppCompatActivity {

    private Storage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mStorage = Storage.getInstance(this);

        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_category);
        for(int i = 0; i < Topics.ALL_TOPICS.length; ++i){
            final Button button = new Button(this);

            ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );

            button.setLayoutParams(params);
            button.setText(Topics.ALL_TOPICS[i]);

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String category = button.getText().toString();
                    mStorage.saveCurrentTopic(category);
                    finish();
                }
            });

            layout.addView(button);
        }
    }
}
