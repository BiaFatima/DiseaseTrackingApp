package com.example.DiseaseTrackingApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfluenzaAdapter extends RecyclerView.Adapter<InfluenzaAdapter.ViewHolder> {

    private Context context;
    private List<InfluenzaModelClass> modelList;
    private List<InfluenzaModelClass> filteredList;

    public InfluenzaAdapter(Context context, List<InfluenzaModelClass> list) {
        this.context = context;
        this.modelList = list;
        this.filteredList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public InfluenzaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.influenza, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfluenzaAdapter.ViewHolder holder, int position) {
        InfluenzaModelClass item = filteredList.get(position);
        holder.region.setText(item.getRegion());
        holder.week.setText(item.getWeek());
        holder.cases.setText(item.getCases());
        holder.deaths.setText(item.getDeaths());
        holder.recovered.setText(item.getRecovered());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    // Simple filter example based on the selected type
    public void filter(String filterType) {
        filteredList.clear();
        for (InfluenzaModelClass item : modelList) {
            switch (filterType) {
                case "cases":
                    if (safeParseInt(item.getCases()) > 0) filteredList.add(item);
                    break;
                case "deaths":
                    if (safeParseInt(item.getDeaths()) > 0) filteredList.add(item);
                    break;
                case "recovered":
                    if (safeParseInt(item.getRecovered()) > 0) filteredList.add(item);
                    break;
                case "active":
                    if (safeParseInt(item.getActive()) > 0) filteredList.add(item);
                    break;
                default:
                    filteredList.add(item);
            }
        }
        notifyDataSetChanged();
    }

    private int safeParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView region, week, cases, deaths, recovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            region = itemView.findViewById(R.id.region);
            week = itemView.findViewById(R.id.week);
            cases = itemView.findViewById(R.id.cases);
            deaths = itemView.findViewById(R.id.deaths);
            recovered = itemView.findViewById(R.id.recovered);
        }
    }
}
