package com.ajsherrell.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActiviyFragment extends Fragment {
    public MainActiviyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_activiy, container, false);
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(MainActivity.JOKE_KEY);
        TextView jokeTextView = (TextView) root.findViewById(R.id.joke_textview);
        if (joke != null & joke.length() != 0) {
            jokeTextView.setText(joke);
        }
        return root;
    }
}
