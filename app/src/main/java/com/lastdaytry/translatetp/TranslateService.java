package com.lastdaytry.translatetp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
    private static HashMap<String, String> langShortNameMap = new HashMap<String, String>();


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
        Log.v(LOG_TAG, intent.getStringExtra("name"));

        if ("list".equals(intent.getStringExtra("name"))) {
            apiGetLangs();
        } else if ("translate".equals(intent.getStringExtra("name"))) {

            String text = intent.getStringExtra("text");
            String lang = intent.getStringExtra("lang");

            apiTranslate(text, lang);
        }

    }

    public static List<String> getLangList() {
        return langList;
    }

    public static HashMap<String, String> getLangFullNameMap() {
        return langFullNameMap;
    }

    public static HashMap<String, String> getLangShortNameMap() {
        return langShortNameMap;
    }

    public void apiGetLangs() {

        Log.v(LOG_TAG, "get Api lang list");

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
                langShortNameMap.put(langFull, lang);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void apiTranslate(String text, String lang) {

        HttpURLConnection connection = null;
        try {

            String textUTF8 = URLEncoder.encode(text, "UTF-8");
            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key="  + apiKey
                    + "&text=" + textUTF8 + "&lang=" + lang);
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
            String dataAsString1 = sb.toString();
            String dataAsString = new String(dataAsString1.getBytes("UTF-8"));
            JSONObject jsonObject = new JSONObject(dataAsString);
            dataAsString = jsonObject.getString("text");
            int size = dataAsString.length() - 2;
            dataAsString = dataAsString.substring(2, size);

            Log.v(LOG_TAG, dataAsString);

            Log.v(LOG_TAG, "sendBroadcast");
            Intent intent = new Intent(FragmentForms.BROADCAST_ACTION);
            intent.putExtra("result", dataAsString);
            sendBroadcast(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
