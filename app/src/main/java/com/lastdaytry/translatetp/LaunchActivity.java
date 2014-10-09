package com.lastdaytry.translatetp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        intentTranslateService.putExtra("name", "list");
        startService(intentTranslateService);

        if (isOnline()) {
            Log.v(LOG_TAG, "online");

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LaunchActivity.this, ListLangActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3 * 1000);

        } else {
            Log.v(LOG_TAG, "not connection");

            TextView textView = (TextView) findViewById(R.id.launch_label_textView);
            textView.setText("Error not internet connection");

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
