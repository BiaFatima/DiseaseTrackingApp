package com.example.DiseaseTrackingApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.List;

public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.ViewHolder> {

    private int m1 = 1; // Default to show "cases"
    private final Context context;
    private final List<CovidModelClass> countryList;

    public CovidAdapter(Context context, List<CovidModelClass> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CovidModelClass modelClass = countryList.get(position);

        switch (m1) {
            case 1:
                holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getCases())));
                break;
            case 2:
                holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getRecovered())));
                break;
            case 3:
                holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getDeaths())));
                break;
            default:
                holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getActive())));
                break;
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
        switch (charText) {
            case "cases":
                m1 = 1;
                break;
            case "recovered":
                m1 = 2;
                break;
            case "deaths":
                m1 = 3;
                break;
            default:
                m1 = 4;
        }

        notifyDataSetChanged();
    }
}
