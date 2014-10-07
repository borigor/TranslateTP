package com.lastdaytry.translatetp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


/**
 * Created by igor on 07.10.2014.
 */
public class ListLangActivity extends ListActivity {

    private static final String LOG_TAG = "ListLangActivity";

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TranslateService.getLangList());
        setListAdapter(adapter);
    }
}
