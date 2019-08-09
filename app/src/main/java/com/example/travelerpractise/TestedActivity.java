package com.example.travelerpractise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TestedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tested);

        ArrayList<DaysCard> daysCards = new ArrayList<>();
        daysCards.add(new DaysCard("03/03/2019","day 1"));
        daysCards.add(new DaysCard("03/04/2019","day 2"));
        daysCards.add(new DaysCard("03/05/2019","day 3"));
        daysCards.add(new DaysCard("03/06/2019","day 4"));
        daysCards.add(new DaysCard("03/07/2019","day 5"));
        daysCards.add(new DaysCard("03/08/2019","day 6"));
        daysCards.add(new DaysCard("03/08/2019","day 6"));
        daysCards.add(new DaysCard("03/08/2019","day 6"));
        daysCards.add(new DaysCard("03/08/2019","day 6"));
        daysCards.add(new DaysCard("03/08/2019","day 6"));
        daysCards.add(new DaysCard("03/08/2019","day 6"));

        recyclerView = findViewById(R.id.testRecycler);
        layoutManager = new LinearLayoutManager(this);
        adapter = new DayCountAdapter(daysCards);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
