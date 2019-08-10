package com.example.travelerpractise.FragmentForTreePage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.travelerpractise.R;
import com.google.android.material.tabs.TabLayout;



public class TreeFragmentContenerActivity extends AppCompatActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_fragment_contener);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter( new TreePagesAdapter(getSupportFragmentManager()));


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);



    }
}
