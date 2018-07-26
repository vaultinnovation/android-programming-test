package com.vaultinnovation.androidcodetest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button addButton;
    private EditText entryEditText;
    private TextView titleText;

    ArrayAdapter<String> listViewAdapter;
    private Comparator<String> alphabeticalComparator = new Comparator<String>() {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setUpListView();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEntryToListView(entryEditText.getText().toString());
                resetEntryEditText();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitleTextForRequestedOrientation();
    }

    private void bindViews() {
        listView = (ListView) findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.button);
        entryEditText = (EditText) findViewById(R.id.editText);
        titleText = (TextView) findViewById(R.id.title);
    }

    private void setUpListView() {
        listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedEntry = listViewAdapter.getItem(position);

                startTextActivityForEntry(selectedEntry);
            }
        });
    }

    private void startTextActivityForEntry(String selectedEntry) {
        Intent intent = new Intent(MainActivity.this, TextActivity.class);
        intent.putExtra(TextActivity.SELECTED_ENTRY, selectedEntry);
        startActivity(intent);
    }

    private void addEntryToListView(String entry) {
        listViewAdapter.add(entry);
        listViewAdapter.sort(alphabeticalComparator);
        listViewAdapter.notifyDataSetChanged();
    }

    private void resetEntryEditText() {
        entryEditText.setText("");
        entryEditText.clearFocus();
    }

    private void setTitleTextForRequestedOrientation() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            titleText.setText(R.string.landscape_title);
        } else {
            titleText.setText(R.string.portrait_title);
        }
    }
}