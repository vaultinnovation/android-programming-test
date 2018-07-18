package com.vaultinnovation.androidcodetest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vaultinnovation.androidcodetest.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_LIST_ITEMS = "KEY_LIST_ITEMS";
    private ActivityMainBinding binding;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(binding.editText.getText().toString());
                binding.editText.setText("");
            }
        });
        initList(savedInstanceState);

    }

    private void initList(Bundle savedInstanceState) {
        final ArrayList<String> items;
        if (savedInstanceState != null) {
            items = savedInstanceState.getStringArrayList(KEY_LIST_ITEMS);
        } else {
            items = new ArrayList<>();
        }
        adapter = new ListAdapter(this, items);
        final ListView listView = binding.listView;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final String item = adapter.getItem(position);
                openTextActivity(item);
            }
        });

    }

    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);

        ArrayList<String> items = adapter.getItems();
        savedState.putStringArrayList(KEY_LIST_ITEMS, items);

    }

    private void openTextActivity(String item) {
        TextActivity.startActivity(item, this);
    }

}
