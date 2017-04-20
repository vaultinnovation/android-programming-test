package com.vaultinnovation.androidcodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    public final static String STORE_STATE_CONTENT = "contents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Intent intent = getIntent();
        String contents = intent.getStringExtra(STORE_STATE_CONTENT);
        TextView message = (TextView) findViewById(R.id.message);
        message.setText(contents);
    }

}
