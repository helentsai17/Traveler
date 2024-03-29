package com.example.travelerpractise;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.FragmentForTreePage.AddCalAndBudgetActivity;
import com.example.travelerpractise.FragmentForTreePage.TreeFragmentContenerActivity;
import com.google.android.gms.maps.MapFragment;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_LONG;

public class TravelDaySetActivity extends AppCompatActivity implements DayCountAdapter.OnNoteListener{


    private String cContinent;
    private Spinner cCountry;
    private TextView cCity;
    private TextView cTravelDate;
    private TextView cReturnDate;
    private Button checklist;
    private TextView tryDate;

    private String key;
    private String continent;
    private String country;
    private String city;
    private String travelDate;
    private String returnDate;
    private long diff;
    private String days;

    public double cityLa;
    public double cityLo;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_day_set);

        MapFragment mMapFragment = new MapFragment();
        mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.my_container, mMapFragment);
        fragmentTransaction.commit();



        key = getIntent().getStringExtra("key");
        continent = getIntent().getStringExtra("continent");
        country = getIntent().getStringExtra("country");
        city = getIntent().getStringExtra("city");
        travelDate = getIntent().getStringExtra("travelDate");
        returnDate = getIntent().getStringExtra("returnDate");

        ArrayList<DaysCard> daysCards = new ArrayList<>();
        daysCards.add(new DaysCard("03/03/2019","day 1"));
        daysCards.add(new DaysCard("03/04/2019","day 2"));
        daysCards.add(new DaysCard("03/05/2019","day 3"));
        daysCards.add(new DaysCard("03/06/2019","day 4"));
        daysCards.add(new DaysCard("03/07/2019","day 5"));
        daysCards.add(new DaysCard("03/08/2019","day 6"));



        recyclerView = findViewById(R.id.daySetRecycler);
        layoutManager = new LinearLayoutManager(this);
        adapter = new DayCountAdapter(daysCards,this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        //Todo: move to method;
        checklist = findViewById(R.id.checklist1);
        tryDate = findViewById(R.id.textView2);

        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TravelDaySetActivity.this, AddCalAndBudgetActivity.class);
                startActivity(intent);
            }
        });

        String dateStr = travelDate;
        String dateEnd = returnDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = sdf.parse(dateStr);
            Date date2 = sdf.parse(dateEnd);

            diff = (date2.getTime() - date1.getTime());
            days = "Day " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            tryDate.setText(days);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void passingGeo(){
        Geocoder geocoder = new Geocoder(TravelDaySetActivity.this);
        List<Address> list = new ArrayList<>();

        try {
            list = geocoder.getFromLocationName(city,1);
        } catch (IOException e) {
            Toast.makeText(this,"e.printStackTrace()", LENGTH_LONG).show();
        }

        if(list.size()>0) {
            Address address = list.get(0);
            Toast.makeText(this, "find Location", LENGTH_LONG).show();
            Log.d("travelcity", "geoLocation = find a location = " + address.toString());
            cityLa = address.getLatitude();
            cityLo = address.getLongitude();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.go_and_edit,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.editTravel:
                Intent intent = new Intent(this,TravelChangeActivity.class);
                intent.putExtra("key",key);
                intent.putExtra("continent",continent);
                intent.putExtra("country",country);
                intent.putExtra("city",city);
                intent.putExtra("travelDate",travelDate);
                intent.putExtra("returnDate",returnDate);

                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onNotClick(int position) {
        Intent intent = new Intent(this, TreeFragmentContenerActivity.class);
        startActivity(intent);
    }
}
