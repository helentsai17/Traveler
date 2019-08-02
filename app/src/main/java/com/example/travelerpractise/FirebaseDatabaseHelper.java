package com.example.travelerpractise;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Travel> travels = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Travel> travels, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("TravelWorld");
    }
    public void readTravel(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                travels.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    Travel travel = keynode.getValue(Travel.class);
                    travels.add(travel);
                }
                dataStatus.DataIsLoaded(travels,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addTravel(Travel travel, final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(travel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }
    public void updateTravel(String key,Travel travel,final DataStatus dataStatus){
        mReference.child(key).setValue(travel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteTravel(String key,final DataStatus dataStatus){
        mReference.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
