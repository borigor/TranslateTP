package com.lastdaytry.translatetp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by igor on 06.10.2014.
 */
public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinnerFrom = (Spinner)findViewById(R.id.spinner_lang_from);
        Spinner spinnerTo = (Spinner)findViewById(R.id.spinner_lang_to);

        List<String> list = new ArrayList<String>();

        HashMap<String, String> langsMap = TranslateService.getLangFullNameMap();

        for (Object value: langsMap.values()) {
            list.add((String) value);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(dataAdapter);

        spinnerTo.setAdapter(dataAdapter);

        ArrayAdapter spinFromAdapter = (ArrayAdapter) spinnerFrom.getAdapter(); //cast to an ArrayAdapter

        Intent intent = getIntent();
        String langsToFrom = intent.getStringExtra("langsToFrom");
        String langFrom = langsToFrom.substring(0, 2);
        String langTo = langsToFrom.substring(3);
        Log.v(LOG_TAG, langTo);
        Log.v(LOG_TAG, langFrom);
        langFrom = langsMap.get(langFrom);
        langTo = langsMap.get(langTo);

        int spinnerPosition = spinFromAdapter.getPosition(langFrom);
        spinnerFrom.setSelection(spinnerPosition);

        spinnerPosition = spinFromAdapter.getPosition(langTo);
        spinnerTo.setSelection(spinnerPosition);

        // Spinner item selection Listener
        addListenerOnSpinnerItemSelection();

        // Button click Listener
//        addListenerOnButton();


    }

    // Add spinner data

    public void addListenerOnSpinnerItemSelection(){

//        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    //get the selected dropdown list value

//    public void addListenerOnButton() {
//
//        spinner = (Spinner) findViewById(R.id.spinner_lang);
//
//        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//
//        btnSubmit.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(SpinnerExample.this,
//                        "On Button Click : " +
//                                "\n" + String.valueOf(spinner1.getSelectedItem()) ,
//                        Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//    }

}
