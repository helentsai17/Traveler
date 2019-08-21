package com.example.travelerpractise.FragmentForTreePage;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ScheduleRecycler extends RecyclerView.Adapter<ScheduleRecycler.ScheduleHolder> {

    private Context mContext;
    private List<Even> evens;
    private Dialog dialog;

    public ScheduleRecycler(Context mContext, List<Even> evens) {
        this.mContext = mContext;
        this.evens = evens;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContext).inflate(R.layout.travel_day_to_day,parent,false);
        final ScheduleHolder scheduleHolder = new ScheduleHolder(view);

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.pop_up_even);

        scheduleHolder.evenItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Test Click"+String.valueOf(scheduleHolder.getAdapterPosition()),Toast.LENGTH_LONG).show();
                TextView EvenName = dialog.findViewById(R.id.evenName);
                TextView EvenAddress = dialog.findViewById(R.id.EvenAddress);
                TextView EvenNumber = dialog.findViewById(R.id.EvenNumber);
                TextView EvenWeb = dialog.findViewById(R.id.EvenWeb);
                TextView EvenCost = dialog.findViewById(R.id.EvenCost);
                TextView EvenHours = dialog.findViewById(R.id.EvenHours);
  //             ImageView EvenView = dialog.findViewById(R.id.EvenPic);

                EvenName.setText(evens.get(scheduleHolder.getAdapterPosition()).getEven());
                EvenAddress.setText(evens.get(scheduleHolder.getAdapterPosition()).getAddress());
                EvenNumber.setText(evens.get(scheduleHolder.getAdapterPosition()).getPhoneNumber());
                EvenWeb.setText(evens.get(scheduleHolder.getAdapterPosition()).getWeb());
                EvenCost.setText(evens.get(scheduleHolder.getAdapterPosition()).getCost());
                EvenHours.setText(evens.get(scheduleHolder.getAdapterPosition()).getOpenHour());
//                EvenView.setImageResource(evens.get(scheduleHolder.getAdapterPosition()).getImage());
                dialog.show();


            }
        });


        return scheduleHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {

        Even uploadCurrent = evens.get(position);
        holder.Even_name.setText(uploadCurrent.getEven());
        holder.Address.setText(uploadCurrent.getAddress());
        Picasso.with(mContext)
                .load(uploadCurrent.getImage())
                .fit()
                .centerCrop()
                .into(holder.Image);


    }

    @Override
    public int getItemCount() {
        return evens.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder{

        private LinearLayout evenItem;
        private TextView Even_name;
        private TextView Address;
        private ImageView Image;




        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            evenItem = itemView.findViewById(R.id.Even_Item);
            Even_name = itemView.findViewById(R.id.Even_Name);
            Address = itemView.findViewById(R.id.Even_Address);
            Image = itemView.findViewById(R.id.photoImage);

        }
    }


}
