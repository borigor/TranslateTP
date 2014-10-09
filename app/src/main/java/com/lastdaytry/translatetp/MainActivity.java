package com.lastdaytry.translatetp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by igor on 06.10.2014.
 */
public class MainActivity extends Activity{

    private static final String LOG_TAG = "MainActivity";

    public static final String BROADCAST_ACTION = "broadcastAction";

    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        br = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.v(LOG_TAG, "onReceive");
//
//                String result = intent.getStringExtra("result");
//                resultText.setText(result);
//
//
//            }
//        };
//
//        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
//        registerReceiver(br, intentFilter);
    }


}
