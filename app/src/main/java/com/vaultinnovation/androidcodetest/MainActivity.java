package com.vaultinnovation.androidcodetest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Main Activity controls activity main layout.
 *
 * Generally, this should be a fragment, but when the scope is this small, it doesn't make a lot of
 * sense to extend the application to split into fragments, since we will never actually reuse any
 * elements. Just wanted to make sure that generally, fragments are best practice and would be used
 * had there been the possibility of the scope being changed in the future. This is generally the
 * case for most apps and most apps are large enough to warrant the invest of using fragments.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Keep track of added strings.
     *
     * This is not persistent.
     */
    private final static String STORE_STATE_KEY = "store";

    /**
     * This is used to store the submitted strings.
     *
     * This is restored when orientation changes.
     */
    ArrayList<String> store = new ArrayList<>();

    /**
     * Keep reference to ListView.
     */
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey(STORE_STATE_KEY)) {
            store = savedInstanceState.getStringArrayList(STORE_STATE_KEY);
        }

        setupListView();
        setLayoutTitle(isLandscape());
        handleAddText();
    }

    /**
     * @see AppCompatActivity
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(STORE_STATE_KEY, store);
    }

    /**
     * Setup the layout list view for displaying added contents.
     *
     * This configures the list adapter and on item click listener.
     */
    private void setupListView() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, store);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, TextActivity.class);
                // Generally, this should be done in a thread safe way. Having everything on the
                // main UI thread is definitely easier.
                intent.putExtra(TextActivity.STORE_STATE_CONTENT, store.get(i));
                MainActivity.this.startActivity(intent);
            }
        });
    }

    /**
     * Add on click listener to add text button.
     */
    private void handleAddText() {
        Button addTextButton = (Button) findViewById(R.id.button);
        addTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addText(getEditText());
            }
        });
    }

    /**
     * Add text to array list store.
     *
     * This is public to allow for inner class to access method.
     *
     * @param content
     *   Content to add to the text store.
     */
    public void addText(String content) {
        store.add(content);
        // Technically, since sorting is such a heavy task, it should be done lazily. Doing it now
        // because ListAdapter is unlikely
        Collections.sort(store);
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    /**
     * Get EditText value.
     *
     * @param clear
     *   Whether to keep contents or clear it after retrieval.
     * @return
     *   EditText contents
     */
    public String getEditText(boolean clear) {
        EditText text = (EditText) findViewById(R.id.editText);
        String value = text.getText().toString();
        if (clear) {
            text.getText().clear();
        }
        return value;
    }

    /**
     * Clear contents after retrieving.
     * @return
     *   Edit Text contents
     */
    public String getEditText() {
        return getEditText(true);
    }

    /**
     * Change the layout title text.
     *
     * The method setTitle may conflict with Activity setTitle method, using setLayoutTitle for more
     * context and prevent conflict.
     *
     * @param useLarger
     *   The title displays a message making reference to the orientation.
     */
    private void setLayoutTitle(boolean useLarger) {
        // Generally, the reference would be member of the class it is contained.
        // We will not be reusing the reference, so we let the reference go out of scope in the
        // hopes that the memory will be restored.
        TextView title = (TextView) findViewById(R.id.title);

        // Set title.
        if (useLarger) {
            title.setText(R.string.title_landscape);
        } else {
            title.setText(R.string.title);
        }
    }

    /**
     * Check if we are in landscape orientation.
     * @return
     *   True if landscape or false if portrait.
     */
    private boolean isLandscape() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        return (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270);
    }
}
