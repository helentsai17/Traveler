package com.example.travelerpractise.FragmentForTreePage;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {

    private View budgetView;
    private RecyclerView bRecyclerView;
    private List<Budget> budgets;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private DatabaseReference budgetRef;
    FirebaseRecyclerAdapter<Budget,ViewHolder> Newadapter;


    public BudgetFragment() {
        // Required empty public constructor
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("ItemCost");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        budgetView = inflater.inflate(R.layout.fragment_budget, container, false);

        bRecyclerView = (RecyclerView) budgetView.findViewById(R.id.budgetRecycler);
        BudgetRecycler budgetRecycler = new BudgetRecycler(getContext(),budgets);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bRecyclerView.setAdapter(budgetRecycler);

        budgetRef = FirebaseDatabase.getInstance().getReference().child("ItemCost");

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

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Budget>()
                .setQuery(budgetRef,Budget.class)
                .build();


         Newadapter = new FirebaseRecyclerAdapter<Budget, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder budgetViewHolder, int i, @NonNull final Budget budget) {

                String userID = getRef(i).getKey();
                budgetRef.child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("cost")){
                            String buyItemName = dataSnapshot.child("itemName").getValue().toString();
                            String buyItemCost = dataSnapshot.child("cost").getValue().toString();
                            String buyItemChange = dataSnapshot.child("costChage").getValue().toString();

                                 budgetViewHolder.ItemName.setText(buyItemName);
                                 budgetViewHolder.ItemCost.setText(buyItemCost);
                                 budgetViewHolder.ItemChange.setText(buyItemChange);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.budget_card,parent,false);
                ViewHolder viewHolder = new ViewHolder(view);



                return viewHolder;
            }
        };

        bRecyclerView.setAdapter(Newadapter);
        Newadapter.startListening();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ItemName, ItemCost, ItemChange;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ItemName = itemView.findViewById(R.id.budget_name);
            ItemCost = itemView.findViewById(R.id.budget_cost);
            ItemChange = itemView.findViewById(R.id.budget_change);
        }
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        budgets = new ArrayList<>();
//        budgets.add(new Budget("lunch","12","12"));
//        budgets.add(new Budget("hotel","40","40"));
//        budgets.add(new Budget("dinner","10","13"));
//        budgets.add(new Budget("train","9","9"));
//
//    }
}
