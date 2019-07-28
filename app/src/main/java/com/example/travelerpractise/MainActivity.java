package com.example.travelerpractise;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        new FirebaseDatabaseHelper().readTravel(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Travel> travels, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(recyclerView,MainActivity.this,travels,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.go_travel_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.adddTravel:
                startActivity(new Intent(this,addTravelPlace.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
