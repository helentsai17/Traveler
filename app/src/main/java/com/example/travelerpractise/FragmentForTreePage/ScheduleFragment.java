package com.example.travelerpractise.FragmentForTreePage;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";


    private RecyclerView sRecyclerView;
    private List<Even> evens;
    private View scheduleView;

    private DatabaseReference mDatabase;




    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        scheduleView = inflater.inflate(R.layout.fragment_schedule, container, false);
        sRecyclerView = scheduleView.findViewById(R.id.schduelr_recycler);
       // ScheduleRecycler scheduleRecycler = new ScheduleRecycler(getContext(),evens);
        sRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      //  sRecyclerView.setAdapter(scheduleRecycler);

        mDatabase = FirebaseDatabase.getInstance().getReference("EvenPlanner");


        return scheduleView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Even>()
                .setQuery(mDatabase,Even.class)
                .build();

        FirebaseRecyclerAdapter<Even,ScheduleHolder> adapter
                = new FirebaseRecyclerAdapter<Even, ScheduleHolder>(options) {
            private Dialog dialog;
            @Override
            protected void onBindViewHolder(@NonNull final ScheduleHolder scheduleHolder, int i, @NonNull Even even) {

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if (dataSnapshot.hasChild("image")){
                            String EventName = dataSnapshot.child("even").getValue().toString();
                            String EventAddress = dataSnapshot.child("address").getValue().toString();
                            String EventImage = dataSnapshot.child("image").getValue().toString();

                            scheduleHolder.Even_name.setText(EventName);
                            scheduleHolder.Address.setText(EventAddress);
                            Picasso.with(getContext())
                                    .load(EventImage)
                                    .fit()
                                    .centerCrop()
                                    .into(scheduleHolder.Image);

                       }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_day_to_day,parent,false);
                ScheduleHolder scheduleHolder = new ScheduleHolder(view);

                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.pop_up_even);



                return scheduleHolder;
            }
        };
        sRecyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    public class ScheduleHolder extends RecyclerView.ViewHolder{

        private LinearLayout evenItem;
        private TextView Even_name;
        private TextView Address;
        private ImageView Image;




        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            evenItem = itemView.findViewById(R.id.Even_Item);
            Even_name = itemView.findViewById(R.id.Even_Name);
            Address = itemView.findViewById(R.id.Even_Address);
            Image = itemView.findViewById(R.id.photoImage);

        }
    }


//      @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        evens = new ArrayList<>();
//        evens.add(new Even("National Meusum of Math","11 W 53rd St New York, NY 10019",
//                "1 (212) 708-9400","10am-5pm","25","https://www.moma.org","R.drawable.moma"));
//
//        evens.add(new Even("The Morgan Library Museum","225 Madison Ave, New York, NY 10016",
//                "(212) 685-0008","10am-5pm","13","themorgan.org","R.drawable.morgan_library"));
//
//        evens.add(new Even("Museum of Sex","233 5th Ave, New York, NY 10016",
//                "(212) 689-6337","10am-12pm","25","https://www.museumofsex.com","R.drawable.moma"));
//
//        evens.add(new Even("Museum of Sex","233 5th Ave, New York, NY 10016",
//                "(212) 689-6337","10am-12pm","25","https://www.museumofsex.com","R.drawable.moma"));
//
//    }

}
