package com.colt.android063_loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by colt on 18.05.2016.
 */

public class RandomStringLoader extends AsyncTaskLoader<ArrayList<String>> {

    // Constructor.
    public RandomStringLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
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

}