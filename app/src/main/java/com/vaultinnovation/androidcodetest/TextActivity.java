package com.vaultinnovation.androidcodetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    public static String EXTRA_VALUE = "EXTRA_VALUE";

    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        message = (TextView) findViewById(R.id.message);

        String value = getIntent().getStringExtra(EXTRA_VALUE);
        message.setText(value);
    }

}
