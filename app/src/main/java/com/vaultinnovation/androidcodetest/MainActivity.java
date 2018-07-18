package com.vaultinnovation.androidcodetest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_SELECTED_TEXT = "key_selected_text";
    private TextView tvTitle;
    private Button btnAdd;
    private EditText edtText;
    private ListView lvItems;
    // This list is used to stored all entered texts and we can do sorting on it
    private List<String> items = new ArrayList<>();
    // The adapter to manage items in the list view
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.title);

        btnAdd = (Button) findViewById(R.id.button);
        btnAdd.setOnClickListener(this);

        edtText = (EditText) findViewById(R.id.editText);

        lvItems = (ListView) findViewById(R.id.listView);
        setupListView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Get the new orientation
        int orientation = newConfig.orientation;

        // Landscape
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.tvTitle.setText(R.string.title_landscape);
        }
        // Portrait
        else {
            this.tvTitle.setText(R.string.title);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Click on the button -> Add the entered text to the list
            case R.id.button:
                addTextToList();
                break;
        }
    }

    /**
     * Add entered text to list view
     */
    private void addTextToList() {
        String enteredText = edtText.getText().toString();

        // Only add the text if it is not empty
        if (!TextUtils.isEmpty(enteredText)) {
            // Add entered text to list of items
            items.add(enteredText);

            // Sort items in alphabetical order
            sortItemsInAlphabeticalOrder();

            // Update the list view
            adapter.notifyDataSetChanged();

            // Clear current text
            edtText.setText("");
        }
    }

    /**
     * Sort items in alphabetical order
     */
    private void sortItemsInAlphabeticalOrder() {
        Collections.sort(items, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
    }

    /**
     * Create adapter and connect the list view to this adapter
     */
    private void setupListView() {
        // Create adapter with default list item (simple_list_item_1 is from Android system)
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, 0, items);

        // Assign adapter to ListView
        lvItems.setAdapter(adapter);

        // ListView Item Click Listener
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedText = items.get(position);
                Intent intent = new Intent(MainActivity.this, TextActivity.class);
                // Pass selected text to TextActivity
                intent.putExtra(KEY_SELECTED_TEXT, selectedText);
                startActivity(intent);
            }
        });
    }
}
