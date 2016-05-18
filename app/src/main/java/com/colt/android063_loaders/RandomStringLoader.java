package com.colt.android063_loaders;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by colt on 18.05.2016.
 */

public class RandomStringLoader extends AsyncTaskLoader<ArrayList<String>> {

    public static final String STRINGLOADER_RELOAD = "RandomStringLoader.RELOAD";
    private Receiver receiver;

    // Constructor.
    public RandomStringLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        receiver = new Receiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(STRINGLOADER_RELOAD);
        getContext().registerReceiver(receiver, filter); // NEVER store a reference to Context (in Loader)!

        forceLoad();
        super.onStartLoading();
    }

    @Override
    public ArrayList<String> loadInBackground() {
        // Simulate slow operation.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> arrayList = new ArrayList<>();
        // Quick and dirty way to random chars. :)
        char[] chars = "lčsjdf89ysd9fyh98asfdihasd98fw3k4bkafiohasjdiyq2g349y9asgfkjba98".toCharArray();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            String randomString = "";
            for (int j = 0; j < 20; j++) {
                randomString += chars[random.nextInt(chars.length)];
            }
            arrayList.add(randomString);
        }

        return arrayList;
    }

    @Override
    public void deliverResult(ArrayList<String> data) {
        if (isStarted())
            super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        getContext().unregisterReceiver(receiver);
        stopLoading();
        super.onReset();
    }

    // Inner class.
    public class Receiver extends BroadcastReceiver {
        private RandomStringLoader loader;

        // Constructor.
        public Receiver(RandomStringLoader loader) {
            this.loader = loader;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            loader.onContentChanged(); // loadInBackground() will be (again) executed when Button clicks (reloadStrings()).
        }
    }

}