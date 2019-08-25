package com.example.travelerpractise.FragmentForTreePage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.travelerpractise.CreateDiaryActivity;
import com.example.travelerpractise.MainActivity;
import com.example.travelerpractise.MapsActivity;
import com.example.travelerpractise.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class TreeFragmentContenerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FirebaseDatabase mDatabase, setBudgetDatabase;
    private TreePagesAdapter mFragmentAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_fragment_contener);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter( new TreePagesAdapter(getSupportFragmentManager()));


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //initDatabaseEven();
        initSchedule();
        initBudget();



    }

    private void initBudget() {
        setBudgetDatabase = FirebaseDatabase.getInstance();
        DatabaseReference setbudgetRef = setBudgetDatabase.getReference("ItemCost");

        ValueEventListener budgetListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot budgetKey : dataSnapshot.getChildren()){
                    Budget budgetCost = budgetKey.getValue(Budget.class);

                    Log.e("budget",budgetKey.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        setbudgetRef.addValueEventListener(budgetListener);
    }

    private void initSchedule() {

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = mDatabase.getReference("EvenPlanner");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //schduleDisplayFragment schduleDisplay = (schduleDisplayFragment)mFragmentAdapter.getItem(3);

                for(DataSnapshot evenkey : dataSnapshot.getChildren()){
                    Even even = evenkey.getValue(Even.class);
                  //  schduleDisplay.routeSchdule(even);
                    Log.e("event",evenkey.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(listener);
    }

//    private void initDatabaseEven() {
//        mDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference ref = mDatabase.getReference("EvenPlanner");
//
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                ScheduleFragment schedule = (ScheduleFragment) madpter.getItem(1);
//
//                for(DataSnapshot evenkey : dataSnapshot.getChildren()){
//                    Even even = evenkey.getValue(Even.class);
//                    schedule.routevent(even);
//                    Log.e("event",evenkey.toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        ref.addValueEventListener(listener);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adding_and_edit_dirary,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.write_diary:
                startActivity(new Intent(this, CreateDiaryActivity.class));
                return true;
            case R.id.even_creater:
                startActivity(new Intent(this,AddCalAndBudgetActivity.class));
                return true;
            case R.id.gotoMap:
                startActivity(new Intent(this,MapsActivity.class));
                return true;
            case R.id.gotoMain:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
