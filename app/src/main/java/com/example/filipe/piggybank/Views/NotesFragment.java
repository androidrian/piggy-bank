package com.example.filipe.piggybank.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filipe.piggybank.R;



public class NotesFragment extends Fragment {

    private static final String TAG = "NotesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_layout, container, false);

        return view;
    }
}
