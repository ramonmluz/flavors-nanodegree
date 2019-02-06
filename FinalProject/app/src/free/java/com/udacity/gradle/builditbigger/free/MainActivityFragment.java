package com.udacity.gradle.builditbigger.free;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import library.java.create.udacity.jokeactivity.JokeActivity;

public class MainActivityFragment extends Fragment {

    private EndPointsAsyncTask.EndPointResultCallback callback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initCallBanck();
        tellJoke(view);
    }

    public void initCallBanck(){
        callback = new EndPointsAsyncTask.EndPointResultCallback() {
            @Override
            public void onTaskResultSuccess(String result) {
                callJokeActivity(result);
            }
        };
    }

    public void tellJoke(View view) {
       Button bt = view.findViewById(R.id.tellJokeBt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndPointsAsyncTask endPointsAsyncTask = new EndPointsAsyncTask(" Udacity - Free");
                endPointsAsyncTask.execute(new Pair<Context, EndPointsAsyncTask.EndPointResultCallback>(getActivity(), callback));
            }
        });
    }

    public void callJokeActivity(String result) {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, result);
        startActivity(intent);
    }
}
