package com.example.shenhaichen.tellingjokeapp;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;

/**
 * Created by shenhaichen on 29/11/2017.
 */

public class JokeAsyncTaskTest extends AndroidTestCase implements EndpointsAsyncTask.OnJokesListener {
    public void test() {

        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute();

        JokeBean result = null;
        try {
            result = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertNotNull(result.getMessage());
    }



    @Override
    public void getMessage(String message) {

    }

    @Override
    public void getError(boolean error) {

    }
}
