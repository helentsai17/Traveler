package com.example.travelerpractise;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DayCountAdapter extends RecyclerView.Adapter<DayCountAdapter.DayCountHolder>{
    private ArrayList<DaysCard> DayList;
    private OnNoteListener mOnNoteListener;

    public DayCountAdapter(ArrayList<DaysCard> dayList, OnNoteListener mOnNoteListener) {
        DayList = dayList;
        this.mOnNoteListener = mOnNoteListener;
    }

    public static class DayCountHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dayItem;
        TextView countdays;
        OnNoteListener onNoteListener;

        public DayCountHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);

            dayItem = itemView.findViewById(R.id.daysItem);
            countdays = itemView.findViewById(R.id.countday);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {

            onNoteListener.onNotClick(getAdapterPosition());
        }
    }

    public DayCountAdapter(ArrayList<DaysCard> DayList){

        this.DayList = DayList;
    }

    @NonNull
    @Override
    public DayCountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_to_day_cardview2,parent,false);
        DayCountHolder viewholder = new DayCountHolder(view,mOnNoteListener);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayCountHolder holder, int position) {
        DaysCard dayCards = DayList.get(position);
        holder.dayItem.setText(dayCards.getDaycard());
        holder.countdays.setText(dayCards.getCountday());

    }

    @Override
    public int getItemCount() {
        return DayList.size();
    }


    public interface OnNoteListener{
        void onNotClick(int position);
    }
}
