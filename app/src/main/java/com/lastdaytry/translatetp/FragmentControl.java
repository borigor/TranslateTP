package com.lastdaytry.translatetp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by igor on 09.10.2014.
 */
public class FragmentControl extends Fragment{

    private static final String LOG_TAG = "FragmentControl";

    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Button buttonTranslate;
    private Button buttonChangeLangs;

    ArrayAdapter<String> dataSpinnerAdapter;
    private List<String> listFullNameLangs;
    HashMap<String, String> langFullNameMap;
    HashMap<String, String> langShortNameMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_control, null);

        spinnerFrom = (Spinner)view.findViewById(R.id.spinner_lang_from);
        spinnerTo = (Spinner)view.findViewById(R.id.spinner_lang_to);

        buttonTranslate = (Button) view.findViewById(R.id.button_translate);
        buttonChangeLangs = (Button) view.findViewById(R.id.button_change_lang);

        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(LOG_TAG, "onClick Translate");

                String langFrom = langShortNameMap.get(spinnerFrom.getSelectedItem());
                String langTo = langShortNameMap.get(spinnerTo.getSelectedItem());

//                View formView = getActivity().getFragmentManager().findFragmentById(R.id.container_forms).getView();
//                EditText editText = (EditText) formView.findViewById(R.id.editText);
                EditText editText = (EditText) getActivity().getFragmentManager().findFragmentById(R.id.container_forms).getView().findViewById(R.id.editText);
                String text = String.valueOf(editText.getText());

                Log.v(LOG_TAG, text);
                Log.v(LOG_TAG, langFrom);
                Log.v(LOG_TAG, langTo);

                Intent intentTranslate = new Intent(view.getContext(), TranslateService.class);
                intentTranslate.putExtra("name", "translate");
                intentTranslate.putExtra("text", text);
                intentTranslate.putExtra("lang", langFrom + "-" + langTo);

                view.getContext().startService(intentTranslate);
            }
        });

        listFullNameLangs = new ArrayList<String>();
        langFullNameMap = TranslateService.getLangFullNameMap();
        langShortNameMap = TranslateService.getLangShortNameMap();

        for (String value: langFullNameMap.values()) {
            listFullNameLangs.add(value);
        }

        dataSpinnerAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item, listFullNameLangs);
        dataSpinnerAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(dataSpinnerAdapter);
        spinnerTo.setAdapter(dataSpinnerAdapter);

        Intent intent = getActivity().getIntent();
        String langsToFrom = intent.getStringExtra("langsToFrom");
        String langFrom = langsToFrom.substring(0, 2);
        String langTo = langsToFrom.substring(3);
        Log.v(LOG_TAG, langTo);
        Log.v(LOG_TAG, langFrom);
        langFrom = langFullNameMap.get(langFrom);
        langTo = langFullNameMap.get(langTo);

        int spinnerPosition = dataSpinnerAdapter.getPosition(langFrom);
        spinnerFrom.setSelection(spinnerPosition);

        spinnerPosition = dataSpinnerAdapter.getPosition(langTo);
        spinnerTo.setSelection(spinnerPosition);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }



}
