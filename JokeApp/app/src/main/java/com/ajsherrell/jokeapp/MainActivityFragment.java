package com.ajsherrell.jokeapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajsherrell.manualjokes.JokeSmith;
import com.ajsherrell.wizardjokes.JokeWizard;


public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_activity, container, false);

        JokeWizard myJokeWizard = new JokeWizard();
        String wizardJoke = myJokeWizard.tellAWizardJoke();
        TextView wizardTextView = (TextView) rootView.findViewById(R.id.wizardJokeTextView);
        wizardTextView.setText(wizardJoke);

        JokeSmith myJokeSmith = new JokeSmith();
        String handcraftedJoke = myJokeSmith.tellAHandCraftedJoke();
        TextView handcraftedJokeTextView = (TextView) rootView.findViewById(R.id.handcraftedJokeTextView);
        handcraftedJokeTextView.setText(handcraftedJoke);

        return rootView;
    }
}
