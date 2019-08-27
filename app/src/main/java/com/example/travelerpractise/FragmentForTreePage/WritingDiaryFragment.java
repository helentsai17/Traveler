package com.example.travelerpractise.FragmentForTreePage;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.travelerpractise.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WritingDiaryFragment extends Fragment {

    DatabaseReference mDataRef;
    TextView DiaryText;
    Fragment ImagePageView;


    public WritingDiaryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View writeDiary = inflater.inflate(R.layout.fragment_writing_diary, container, false);

        mDataRef = FirebaseDatabase.getInstance().getReference("ImageFolder");
        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return writeDiary;


    }

    @Override
    public void onStart() {
        super.onStart();


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
