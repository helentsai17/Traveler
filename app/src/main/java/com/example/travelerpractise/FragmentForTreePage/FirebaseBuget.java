package com.example.travelerpractise.FragmentForTreePage;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseBuget {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mBugetReference;
    private List<Budget> budgets = new ArrayList<>();

    public FirebaseBuget(){
        mDatabase = FirebaseDatabase.getInstance();
        mBugetReference = mDatabase.getReference("ItemCost");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Budget> travels, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readBudget(DataStatus dataStatus){
        mBugetReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Budget budget = keyNode.getValue(Budget.class);
                    budgets.add(budget);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
