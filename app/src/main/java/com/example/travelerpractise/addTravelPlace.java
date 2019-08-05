package com.example.travelerpractise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class addTravelPlace extends AppCompatActivity {

    private String addcontinent;
    private Spinner addCountry;
    private TextView addCity;
    private TextView addTravelDate;
    private TextView addReturnDate;

    private Button checklist;
    private Button saveTravel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_place);

        addcontinent = "Asia";
        addCountry = findViewById(R.id.country_spinner);
        addCity = findViewById(R.id.add_city);
        addTravelDate = findViewById(R.id.travel_date);
        addReturnDate = findViewById(R.id.return_Date);

        checklist = findViewById(R.id.checkList);
        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addTravelPlace.this,CreateDiaryActivity.class);
                startActivity(intent);
            }
        });
        saveTravel = findViewById(R.id.update);



        saveTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Travel travel = new Travel();
                travel.setContinent(addcontinent.toString());
                travel.setCountry(addCountry.getSelectedItem().toString());
                travel.setCity(addCity.getText().toString());
                travel.setTravelDate(addTravelDate.getText().toString());
                travel.setReturnDate(addReturnDate.getText().toString());
                new FirebaseDatabaseHelper().addTravel(travel, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Travel> travels, List<String> keys) {
                        
                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(addTravelPlace.this, "This traval have created", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

    }
}
