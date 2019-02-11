package com.vaultinnovation.androidcodetest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private static String KEY_LIST_VALUES = "KEY_LIST_VALUES";

    private TextView title;
    private EditText editText;
    private Button button;
    private ListView listView;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initTitle();
        initButton();
        initListView(savedInstanceState);
    }

    private void initViews() {
        title = (TextView) findViewById(R.id.title);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void initTitle() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            title.setText(getString(R.string.main_activity_the_other_side));
        } else {
            title.setText(getString(R.string.main_activity_one_side));
        }
    }

    private void initButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(editText.getText().toString());
                adapter.sort(new Comparator<String>() {
                    @Override
                    public int compare(String arg1, String arg0) {
                        return arg1.compareTo(arg0);
                    }
                });
                editText.setText("");
            }});
    }

    private void initListView(Bundle savedInstanceState) {

        ArrayList<String> entries = new ArrayList<String>();

        if(savedInstanceState != null) {
            entries = savedInstanceState.getStringArrayList(KEY_LIST_VALUES);
        }

        Collections.sort(entries);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entries);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TextActivity.class);
                String value = (String) parent.getItemAtPosition(position);
                intent.putExtra(TextActivity.EXTRA_VALUE, value);
                startActivity(intent);
            }
        });
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList(KEY_LIST_VALUES, getAdapterValues());
    }

    public ArrayList<String> getAdapterValues() {

        ArrayList<String> result = new ArrayList<String>();
        for(int i=0 ; i < adapter.getCount() ; i++){
            result.add(adapter.getItem(i));
        }

        return result;
    }

}
