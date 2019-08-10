package com.example.travelerpractise.FragmentForTreePage;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelerpractise.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {


    public BudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View budget = inflater.inflate(R.layout.fragment_budget, container, false);

        return budget;
    }

}
