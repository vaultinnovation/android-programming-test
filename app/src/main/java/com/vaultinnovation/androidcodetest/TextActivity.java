package com.vaultinnovation.androidcodetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String value = bundle.getString("item_text");
            if(value != null){
                ((TextView) findViewById(R.id.message)).setText(value);
            }
        }

    }

}
