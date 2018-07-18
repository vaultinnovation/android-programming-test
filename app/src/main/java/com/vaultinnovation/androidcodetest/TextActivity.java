package com.vaultinnovation.androidcodetest;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vaultinnovation.androidcodetest.databinding.ActivityTextBinding;

public class TextActivity extends AppCompatActivity {
    private static final String KEY_ITEM = "KEY_ITEM";
    private ActivityTextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text);
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String item = extras.getString(KEY_ITEM);
            binding.message.setText(item);
        }else {
            finish();
        }
    }

    public static void startActivity(String item, Activity activity) {
        Intent intent = new Intent(activity, TextActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ITEM, item);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
