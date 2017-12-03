package com.example.shenhaichen.tellingjokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shenhaichen.androidlib.GetMessageActivity;
import com.example.shenhaichen.androidlib.Tools;

/**
 * Created by shenhaichen on 29/11/2017.
 */

public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.OnJokesListener, View.OnClickListener{
    private Button btn_joke;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        btn_joke = root.findViewById(R.id.btn_joke);
        btn_joke.setOnClickListener(this);
        progressBar = root.findViewById(R.id.progress_bar);

//        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .build();
//        mAdView.loadAd(adRequest);
        return root;
    }


    @Override
    public void getMessage(String message) {
        showLoading(false);
        Intent intent = new Intent(getContext(), GetMessageActivity.class);
        intent.putExtra(Tools.SEND_MESSAGE,message);
        startActivity(intent);

    }

    @Override
    public void getError(boolean error) {
         if(error){
             showLoading(false);
             Toast.makeText(MainActivityFragment.this.getContext(), "Oh, Something wrong!", Toast.LENGTH_SHORT).show();
         }
    }

    @Override
    public void onClick(View v) {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.setOnJokesListener(this);
        endpointsAsyncTask.execute();
        showLoading(true);
    }

    private void showLoading(boolean show) {
        if (show) {
            btn_joke.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            btn_joke.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
