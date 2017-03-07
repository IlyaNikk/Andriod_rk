package com.example.ilya.andriod_rk1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_category);
        for(int i = 0; i < 3; ++i){
            final Button button = new Button(this);

            ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );

            button.setLayoutParams(params);
            button.setText("Ololololol");

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String category = button.getText().toString();
                    finish();
                }
            });

            layout.addView(button);
        }
    }
}
