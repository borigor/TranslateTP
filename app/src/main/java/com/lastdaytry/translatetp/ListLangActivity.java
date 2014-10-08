package com.lastdaytry.translatetp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * Created by igor on 07.10.2014.
 */
public class ListLangActivity extends ListActivity {

    private static final String LOG_TAG = "ListLangActivity";

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<String>(ListLangActivity.this, android.R.layout.simple_list_item_1,
                TranslateService.getLangList());
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intentMainActivity = new Intent(ListLangActivity.this, MainActivity.class);
        List<String> langList = TranslateService.getLangList();
        String langsToFrom = langList.get(position);
        intentMainActivity.putExtra("langsToFrom", langsToFrom);
//        Log.v(LOG_TAG, langsToFrom);
        startActivity(intentMainActivity);
    }
}
