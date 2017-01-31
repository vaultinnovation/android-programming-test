package com.vaultinnovation.androidcodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        TextView tv = (TextView) findViewById(R.id.message);

        Intent intent = this.getIntent();
        String strText = intent.getStringExtra("text");
        tv.setText(strText);
    }

}
