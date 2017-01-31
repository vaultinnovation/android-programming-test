package com.vaultinnovation.androidcodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<String>(){};
    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
            TextView tv = (TextView) view;

            Bundle params = new Bundle();
            params.putString("text", tv.getText().toString());

            Intent nav = new Intent(MainActivity.this, TextActivity.class);
            nav.putExtras(params);
            MainActivity.this.startActivity(nav);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et = (EditText) findViewById(R.id.editText);
        final ListView lv = (ListView) findViewById(R.id.listView);
        Button button = (Button) findViewById(R.id.button);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items);
        lv.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(items.size(), et.getText().toString());
                fillLV(items);
            }
        });

        lv.setOnItemClickListener(listener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("items", items);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        items = (ArrayList<String>) savedState.getSerializable("items");
        fillLV(items);
    }

    private void fillLV(ArrayList<String> ary) {
        Collections.sort(ary);
        final ListView lv = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ary);
        lv.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        lv.setOnItemClickListener(listener);
    }
}
