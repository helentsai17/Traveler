package com.example.travelerpractise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TravelChangeActivity extends AppCompatActivity {

    private String cContinent;
    private Spinner cCountry;
    private TextView cCity;
    private TextView cTravelDate;
    private TextView cReturnDate;

    private Button cUpdate;
    private Button cDelete;


    private String key;
    private String continent;
    private String country;
    private String city;
    private String travelDate;
    private String returnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_change);

        key = getIntent().getStringExtra("key");
        continent = getIntent().getStringExtra("continent");
        country = getIntent().getStringExtra("country");
        city = getIntent().getStringExtra("city");
        travelDate = getIntent().getStringExtra("travelDate");
        returnDate = getIntent().getStringExtra("returnDate");

        cContinent = "Asia";
        cCountry = findViewById(R.id.country_spinner);
        cCountry.setSelection(getIndex_SpinnerItem(cCountry,country));
        cCity = findViewById(R.id.add_city);
        cCity.setText(city);
        cTravelDate = findViewById(R.id.travel_date);
        cTravelDate.setText(travelDate);
        cReturnDate = findViewById(R.id.return_Date);
        cReturnDate.setText(returnDate);

        cUpdate = findViewById(R.id.update);
        cDelete = findViewById(R.id.delete);

        cUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Travel travel = new Travel();
                travel.setContinent(cContinent);
                travel.setCountry(cCountry.getSelectedItem().toString());
                travel.setCity(cCity.getText().toString());
                travel.setTravelDate(cReturnDate.getText().toString());
                travel.setReturnDate(cReturnDate.getText().toString());
                new FirebaseDatabaseHelper().updateTravel(key, travel, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Travel> travels, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(TravelChangeActivity.this, "Book record has been updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        cDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteTravel(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Travel> travels, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(TravelChangeActivity.this, "Book record has been deleted", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
            }
        });



    }

    private int getIndex_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for(int i = 0 ; i <spinner.getCount();i++){
         if(spinner.getItemAtPosition(i).equals(item)){
             index = i;
         }
        }
        return index;
    }

}
