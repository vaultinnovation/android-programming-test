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

        showSelectedText();
    }

    /**
     * Get the selected text from MainActivity and show it in the center of screen
     */
    private void showSelectedText() {
        TextView tvSelectedText = (TextView) findViewById(R.id.message);

        /**
         * Get passed text, then show it
         */
        Intent intent = getIntent();
        if (intent != null) {
            String selectedText = intent.getStringExtra(MainActivity.KEY_SELECTED_TEXT);
            tvSelectedText.setText(selectedText);
        }
    }
}
