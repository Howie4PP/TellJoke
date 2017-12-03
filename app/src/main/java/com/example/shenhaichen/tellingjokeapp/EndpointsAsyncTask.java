package com.example.shenhaichen.tellingjokeapp;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.myapplication.backend.jokeapi.Jokeapi;

import java.io.IOException;

/**
 * Created by shenhaichen on 29/11/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<String, Void, JokeBean> {

    private static Jokeapi jokeApi  = null;
    private OnJokesListener onJokesListener = null;

    public void setOnJokesListener(OnJokesListener onJokesListener) {
        this.onJokesListener = onJokesListener;
    }

    @Override
    protected JokeBean doInBackground(String... strings) {
        JokeBean bean = new JokeBean();
        if (jokeApi == null){

            Jokeapi.Builder builder = new Jokeapi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokeApi = builder.build();
        }

        try {
            bean.setMessage(jokeApi.getJoke().execute().getData());
        } catch (IOException e) {
             e.printStackTrace();
             bean.setError(true);
        }

        return bean;

    }

    @Override
    protected void onPostExecute(JokeBean result) {
        if (onJokesListener != null){
            onJokesListener.getMessage(result.getMessage());
            onJokesListener.getError(result.isError());
        }
    }

    interface OnJokesListener{
        void getMessage(String message);
        void getError(boolean error);
    }

}
