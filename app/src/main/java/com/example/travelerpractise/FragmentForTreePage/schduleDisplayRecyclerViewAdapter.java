package com.example.travelerpractise.FragmentForTreePage;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.FragmentForTreePage.schduleDisplayFragment.OnListFragmentInteractionListener;
import com.example.travelerpractise.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class schduleDisplayRecyclerViewAdapter extends RecyclerView.Adapter<schduleDisplayRecyclerViewAdapter.ViewHolder> {

    private List<Even> evens;
    private Dialog dialog;
    private final OnListFragmentInteractionListener mListener;

    public schduleDisplayRecyclerViewAdapter(List<Even> evens, OnListFragmentInteractionListener mListener) {
        this.evens = evens;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_day_to_day, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Even uploadCurrent = evens.get(position);
        holder.Even_name.setText(uploadCurrent.getEven());
        holder.Address.setText(uploadCurrent.getAddress());
        Picasso.with((Context) mListener)
                .load(uploadCurrent.getImage())
                .fit()
                .centerCrop()
                .into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return evens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public Even mItem;

        private LinearLayout evenItem;
        private TextView Even_name;
        private TextView Address;
        private ImageView Image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            evenItem = itemView.findViewById(R.id.Even_Item);
            Even_name = itemView.findViewById(R.id.Even_Name);
            Address = itemView.findViewById(R.id.Even_Address);
            Image = itemView.findViewById(R.id.photoImage);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
