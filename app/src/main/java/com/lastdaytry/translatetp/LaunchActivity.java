package com.lastdaytry.translatetp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by igor on 06.10.2014.
 */
public class LaunchActivity extends Activity {

    private static final String LOG_TAG = "LaunchActivity";

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        Intent intentTranslateService = new Intent(LaunchActivity.this, TranslateService.class);

        startService(intentTranslateService);


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this, ListLangActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3 * 1000);
    }
}
