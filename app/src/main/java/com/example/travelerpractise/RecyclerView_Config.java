package com.example.travelerpractise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private BooksAdopter mBooksAdopter;
    public void setConfig(RecyclerView recyclerView,Context context, List<Travel> travels, List<String> keys) {
        mContext = context;
        mBooksAdopter = new BooksAdopter(travels,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdopter);
    }

    class BookItemView extends RecyclerView.ViewHolder{
        private TextView goContinent;
        private TextView goCountry;
        private TextView goCity;
        private TextView goTravelDate;
        private TextView goReturnDate;

        private String key;


        public BookItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.travel_list_file,parent,false));
            //TODO:check it should be ViewGroup or View ...also the super ()

            goContinent = itemView.findViewById(R.id.setContient);
            goCountry = itemView.findViewById(R.id.setCountry);
            goCity = itemView.findViewById(R.id.setCity);
            goTravelDate = itemView.findViewById(R.id.setTravelDate);
            goReturnDate = itemView.findViewById(R.id.setReturnDate);

        }
        public void bind(Travel travel, String key){
            goContinent.setText(travel.getContinent());
            goCountry.setText(travel.getCountry());
            goCity.setText(travel.getCity());
            goReturnDate.setText(travel.getReturnDate());
            goTravelDate.setText(travel.getTravelDate());

            this.key = key;
        }

    }
    class BooksAdopter extends RecyclerView.Adapter<BookItemView>{

        private List<Travel> mTravel;
        private List<String> mkey;

        public BooksAdopter(List<Travel> mTravel, List<String> mkey) {
            this.mTravel = mTravel;
            this.mkey = mkey;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mTravel.get(position),mkey.get(position));

        }

        @Override
        public int getItemCount() {
            return mTravel.size();
        }
    }

}
