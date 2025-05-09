package com.example.DiseaseTrackingApp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.NumberFormat;
import java.util.List;

public class InfluenzaAdapter extends RecyclerView.Adapter<InfluenzaAdapter.ViewHolder> {

    int m1;
    Context context;
    List<InfluenzaModelClass> countryList;

    public InfluenzaAdapter(Context context, List<InfluenzaModelClass> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public InfluenzaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfluenzaAdapter.ViewHolder holder, int position) {
        InfluenzaModelClass modelClass = countryList.get(position);

        if (m1 == 1) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getCases())));
        } else if (m1 == 2) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getRecovered())));
        } else if (m1 == 3) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getDeaths())));
        } else {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getActive())));
        }

        holder.country.setText(modelClass.getCountry());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cases, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cases = itemView.findViewById(R.id.countrycase);
            country = itemView.findViewById(R.id.textView_countryname);
        }
    }

    public void filter(String charText) {
        if (charText.equals("cases")) {
            m1 = 1;
        } else if (charText.equals("recovered")) {
            m1 = 2;
        } else if (charText.equals("deaths")) {
            m1 = 3;
        } else {
            m1 = 4;
        }

        notifyDataSetChanged();
    }
}
