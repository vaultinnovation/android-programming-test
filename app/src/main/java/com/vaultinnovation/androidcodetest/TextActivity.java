package com.vaultinnovation.androidcodetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {
    public static final String SELECTED_ENTRY = "SELECTED_ENTRY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        setMessageText();
    }

    private void setMessageText() {
        String selectedEntry = getIntent().getStringExtra(SELECTED_ENTRY);
        ((TextView) findViewById(R.id.message)).setText(selectedEntry);
    }

}
