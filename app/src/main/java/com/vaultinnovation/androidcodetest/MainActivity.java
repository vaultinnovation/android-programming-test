package com.vaultinnovation.androidcodetest;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etInput;
    private Button btnAdd;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViews();
    }

    private void loadViews() {

        tvTitle     = (TextView) findViewById(R.id.tv_title);
        etInput     = (EditText) findViewById(R.id.et_input);

        listView    = (ListView) findViewById(R.id.listView);
        refreshListAdapter();

        btnAdd      = (Button) findViewById(R.id.bt_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strText = etInput.getText().toString();
                TextManager.sharedInstance().addNewText(strText);
                refreshListAdapter();

                etInput.setText("");
            }
        });
    }

    private void refreshListAdapter() {
        listAdapter = new ListAdapter(this);
        listAdapter.setOnTextListener(new ListAdapter.OnTextListener() {
            @Override
            public void tappedItemCell(int position) {

                showTextDialog(position);
            }
        });

        listView.setAdapter(listAdapter);
    }

    private void showTextDialog(int position) {

        String strText = TextManager.sharedInstance().getTextbyIndex(position);
        if(strText == null)
            return;

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_text);
        dialog.setCancelable(true);

        TextView text = (TextView) dialog.findViewById(R.id.message);

        text.setText(strText);
        dialog.show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        checkOrientation(newConfig);
    }

    private void checkOrientation(Configuration newConfig){

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvTitle.setText(R.string.landscapte_title);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            tvTitle.setText(R.string.portrait_title);
        }
    }
}
