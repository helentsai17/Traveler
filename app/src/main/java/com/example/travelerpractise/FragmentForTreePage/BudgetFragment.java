package com.example.travelerpractise.FragmentForTreePage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Budget> budgets;
    private View budgetView;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;


    public BudgetFragment() {
        // Required empty public constructor
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("ItemCost");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        budgets = new ArrayList<>();
        budgets.add(new Budget("lunch","12","12"));
        budgets.add(new Budget("hotel","40","40"));
        budgets.add(new Budget("dinner","10","13"));
        budgets.add(new Budget("train","9","9"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        budgetView = inflater.inflate(R.layout.fragment_budget, container, false);

        recyclerView = budgetView.findViewById(R.id.budgetRecycler);
        BudgetRecycler budgetRecycler = new BudgetRecycler(getContext(),budgets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(budgetRecycler);


        return budgetView;
    }

}
