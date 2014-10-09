package com.lastdaytry.translatetp;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by igor on 06.10.2014.
 */
public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";

    public static final String BROADCAST_ACTION = "broadcastAction";

    BroadcastReceiver br;

    private EditText resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = (EditText) getFragmentManager().findFragmentById(R.id.container_forms).getView().findViewById(R.id.resultText);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.v(LOG_TAG, "onReceive");

                String result = intent.getStringExtra("result");
//                while (result.indexOf("\"))
                resultText.setText(result);
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        synchronized (this) {
            if(br != null){
                unregisterReceiver(br);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
