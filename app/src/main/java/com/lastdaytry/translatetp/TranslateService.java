package com.lastdaytry.translatetp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

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
 * Created by igor on 07.10.2014.
 */
public class TranslateService extends IntentService {

    private static final String LOG_TAG = "TranslateService";

    private static final String apiKey = "trnsl.1.1.20141006T210046Z.6ef88adae03898aa.3ec04c1204024ebfa9e4cc5a75bac6fc1e9cd561";
    private static List<String> langList = new ArrayList<String>();
    private static HashMap<String, String> langFullNameMap = new HashMap<String, String>();


    public TranslateService() {
        super("translateService");
    }

    @Override
    public void onCreate() {
        Log.v(LOG_TAG, "onCreate");
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.v(LOG_TAG, "onHandleIntent");

        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=" + apiKey + "&ui=ru");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream is = connection.getInputStream();

            // Convert the InputStream into a string
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String dataAsString = sb.toString();
            Log.v(LOG_TAG, dataAsString);

            JSONObject jsonObject = new JSONObject(dataAsString);
            JSONArray jsonArray = jsonObject.getJSONArray("dirs");
            for (int i=0; i<jsonArray.length(); i++) {
                langList.add((String) jsonArray.get(i));
            }

            JSONObject langs = jsonObject.getJSONObject("langs");
            Iterator it = langs.keys();
            while (it.hasNext()) {
                String lang = (String) it.next();
                String langFull = (String) langs.get(lang);
                langFullNameMap.put(lang, langFull);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getLangList() {
        return langList;
    }

    public static HashMap<String, String> getLangFullNameMap() {
        return langFullNameMap;
    }
}
