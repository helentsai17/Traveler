package com.example.travelerpractise.FragmentForTreePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.travelerpractise.R;

public class TreeFragmentContenerActivity extends AppCompatActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_fragment_contener);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter( new TreePagesAdapter(getSupportFragmentManager()));



    }
}
