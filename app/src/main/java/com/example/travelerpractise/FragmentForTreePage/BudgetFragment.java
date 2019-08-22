package com.example.travelerpractise.FragmentForTreePage;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

        FloatingActionButton fab = (FloatingActionButton) budgetView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Replace with your own action", Toast.LENGTH_LONG)
                        .show();

                AlertDialog.Builder mBilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.budget_add_aialog,null);

                final EditText itemBudget = mView.findViewById(R.id.budget_Item_Name);
                final EditText itemExpense = mView.findViewById(R.id.budget_expence);
                Button uploadBudget = mView.findViewById(R.id.budget_upload_expenxe);
                Button budgetcancel = mView.findViewById(R.id.budget_cancel);




                uploadBudget.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!itemBudget.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),"add to firebase",Toast.LENGTH_LONG).show();
                            Budget budget = new Budget();
                            budget.setItemName(itemBudget.getText().toString());
                            budget.setCost(itemExpense.getText().toString());
                            budget.setCostChage(itemExpense.getText().toString());

                            String key = mReference.push().getKey();
                            mReference.child(key).setValue(budget).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(),"add to firebase done",Toast.LENGTH_LONG).show();
                                }
                            });

                        }else {
                            Toast.makeText(getContext(),"plase set a object name",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                mBilder.setView(mView);
                final AlertDialog dialog = mBilder.create();
                dialog.show();

                budgetcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


        return budgetView;
    }

}
