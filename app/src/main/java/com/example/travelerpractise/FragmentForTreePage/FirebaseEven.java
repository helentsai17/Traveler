package com.example.travelerpractise.FragmentForTreePage;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseEven {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceEven;
    private List<Even> evens = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Even> evens, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseEven() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceEven = mDatabase.getReference("EvenPlanner");
    }
    public void readEven(final DataStatus dataStatus){
        mReferenceEven.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String>keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Even even = keyNode.getValue(Even.class);
                    evens.add(even);

                }
                dataStatus.DataIsLoaded(evens,keys);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
