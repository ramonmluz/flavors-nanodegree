package com.udacity.gradle.builditbigger.paid;

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

import com.udacity.gradle.builditbigger.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import library.java.create.udacity.jokeactivity.JokeActivity;

public class MainActivityFragment extends Fragment {

    private EndPointsAsyncTask.EndPointResultCallback callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
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
                EndPointsAsyncTask endPointsAsyncTask = new EndPointsAsyncTask(" Udacity - Paid");
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
