package com.example.travelerpractise.FragmentForTreePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelerpractise.R;

import java.util.List;

public class BudgetRecycler extends RecyclerView.Adapter<BudgetRecycler.BudgetViewHolder> {

    private Context mContext;
    private List<Budget> mbudget;

    public BudgetRecycler(Context mContext, List<Budget> mbudget) {
        this.mContext = mContext;
        this.mbudget = mbudget;
    }

    @NonNull
    @Override
    public BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.budget_card,parent,false);
        BudgetViewHolder mBudgetHolder = new BudgetViewHolder(view);

        return mBudgetHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetViewHolder holder, int position) {

        holder.itemName.setText(mbudget.get(position).getItemName());
        holder.changeCost.setText(mbudget.get(position).getCostChage());
        holder.cost.setText(mbudget.get(position).getCost());

    }

    @Override
    public int getItemCount() {
        return mbudget.size();
    }

    public static class BudgetViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        TextView changeCost;
        TextView cost;

        public BudgetViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.budget_name);
            changeCost = itemView.findViewById(R.id.budget_change);
            cost = itemView.findViewById(R.id.budget_cost);

        }
    }




}
