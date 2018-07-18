package com.vaultinnovation.androidcodetest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private int TEXT_ACTIVITY_ID  = 100;

    private ArrayList<String> textList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onSelectItem(position);
            }
        });

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewItem();
            }
        });

        Configuration orientationConfig = getResources().getConfiguration();
        if(orientationConfig != null){
            performOrientationChange(orientationConfig);
        }
    }

    private void onSelectItem(int position) {
        String item = textList.get(position);
        Intent intent = new Intent(this, TextActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("item_text", item);
        intent.putExtras(bundle);
        startActivityForResult(intent, TEXT_ACTIVITY_ID);
    }

    private void addNewItem() {
        EditText editView = ((EditText)findViewById(R.id.editText));
        String newText = editView.getText().toString();
        if(!newText.isEmpty()) {
            textList.add(newText);
            Collections.sort(textList, String.CASE_INSENSITIVE_ORDER);
            editView.setText("");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfiguration){
        super.onConfigurationChanged(newConfiguration);
        performOrientationChange(newConfiguration);
    }

    private void performOrientationChange (Configuration newConfiguration) {
        if(newConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            ((TextView)findViewById(R.id.title)).setText("One side makes you larger");
        } else if(newConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT){
            ((TextView)findViewById(R.id.title)).setText("The other side makes you small");
        }
    }
}
