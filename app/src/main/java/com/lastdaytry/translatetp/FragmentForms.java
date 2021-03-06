package com.lastdaytry.translatetp;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by igor on 09.10.2014.
 */
public class FragmentForms extends Fragment {

    private static final String LOG_TAG = "FragmentForms";

    private EditText editText;
    private EditText resultText;

    public static final String BROADCAST_ACTION = "broadcastAction";

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forms, null);

        editText = (EditText) view.findViewById(R.id.editText);
        resultText = (EditText) view.findViewById(R.id.resultText);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
