package com.example.travelerpractise.FragmentForTreePage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {


    private RecyclerView sRecyclerView;
    private List<Even> evens;
    private View scheduleView;




    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        evens = new ArrayList<>();
        evens.add(new Even("National Meusum of Math","11 W 53rd St New York, NY 10019",
                "1 (212) 708-9400","10am-5pm","25","https://www.moma.org","R.drawable.moma"));

        evens.add(new Even("The Morgan Library Museum","225 Madison Ave, New York, NY 10016",
                "(212) 685-0008","10am-5pm","13","themorgan.org","R.drawable.morgan_library"));

        evens.add(new Even("Museum of Sex","233 5th Ave, New York, NY 10016",
                "(212) 689-6337","10am-12pm","25","https://www.museumofsex.com","R.drawable.moma"));

        evens.add(new Even("Museum of Sex","233 5th Ave, New York, NY 10016",
                "(212) 689-6337","10am-12pm","25","https://www.museumofsex.com","R.drawable.moma"));

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        scheduleView = inflater.inflate(R.layout.fragment_schedule, container, false);

        sRecyclerView = scheduleView.findViewById(R.id.schduelr_recycler);
        ScheduleRecycler scheduleRecycler = new ScheduleRecycler(getContext(),evens);
        sRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sRecyclerView.setAdapter(scheduleRecycler);


        return scheduleView;
    }


}
