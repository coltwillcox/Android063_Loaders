package com.colt.android063_loaders;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

// https://youtu.be/ssHrIS4B6YY
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<String>> {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        getLoaderManager().initLoader(7, null, this);
    }

    public void reloadStrings(View view) {
        Intent intent = new Intent();
        intent.setAction(RandomStringLoader.STRINGLOADER_RELOAD);
        sendBroadcast(intent); // Send message to BroadcastReceiver in RandomStringLoader.
    }

    @Override
    public Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {
        RandomStringLoader loader = new RandomStringLoader(this);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
        textView.setText("");
        for (String s : data)
            textView.append("\n" + s);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<String>> loader) {

    }

}