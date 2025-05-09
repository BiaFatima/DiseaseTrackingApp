package com.example.DiseaseTrackingApp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CountryCodePicker countryCodePicker;
    TextView mtodaytotal, mtotal, mactive, mtodayactive, mrecovered, mtodayrecovered, mdeaths, mtodaydeaths;
    String country;
    TextView mfilter;
    Spinner spinner;
    String[] types = {"cases", "deaths", "recovered", "active"};
    private List<CovidModelClass> modelClassList;
    private List<CovidModelClass> modelClassList2;
    PieChart mpiechart;
    private RecyclerView recyclerView;
    CovidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.covid19);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        countryCodePicker = findViewById(R.id.ccp);
        mtodayactive = findViewById(R.id.todayactive);
        mactive = findViewById(R.id.activecase);
        mdeaths = findViewById(R.id.deathcase);
        mtodaydeaths = findViewById(R.id.todaydeaths);
        mrecovered = findViewById(R.id.recoveredcase);
        mtodayrecovered = findViewById(R.id.todayrecover);
        mtotal = findViewById(R.id.totalcase);
        mtodaytotal = findViewById(R.id.todaytotal);
        mpiechart = findViewById(R.id.piechart);
        spinner = findViewById(R.id.spinner);
        mfilter = findViewById(R.id.filter);
        recyclerView = findViewById(R.id.recycler_view);

        modelClassList = new ArrayList<>();
        modelClassList2 = new ArrayList<>();

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        CovidApiUtilities.getAPIInterface().getCountryData().enqueue(new Callback<List<CovidModelClass>>() {
            @Override
            public void onResponse(Call<List<CovidModelClass>> call, Response<List<CovidModelClass>> response) {
                //  modelClassList2.addAll(response.body());
                //adapter.notifyDataSetChanged();
                modelClassList2.clear();
                for (CovidModelClass model : response.body()) {
                    if (model.getCountry().equals(country)) {
                        modelClassList2.add(model);
                        break; // found match, no need to continue
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<CovidModelClass>> call, Throwable t) {
                // handle failure
            }
        });

        adapter = new CovidAdapter(this, modelClassList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        countryCodePicker.setAutoDetectedCountry(true);
        country = countryCodePicker.getSelectedCountryName();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = countryCodePicker.getSelectedCountryName();
                fetchdata();

            }
        });
        fetchdata();

    }

    private void fetchdata() {
        CovidApiUtilities.getAPIInterface().getCountryData().enqueue(new Callback<List<CovidModelClass>>() {
            @Override
            public void onResponse(Call<List<CovidModelClass>> call, Response<List<CovidModelClass>> response) {
                if (response.body() == null || response.body().isEmpty()) {
                    Toast.makeText(CovidMainActivity.this, "No data received from server", Toast.LENGTH_SHORT).show();
                    return;
                }

                modelClassList.clear();
                modelClassList.addAll(response.body());

                modelClassList2.clear(); // Clear old data every time country is changed

                for (CovidModelClass model : modelClassList) {
                    if (model.getCountry().equals(country)) {
                        modelClassList2.add(model); // Add current selected country only

                        mactive.setText(model.getActive());
                        mtodaydeaths.setText(model.getDeaths());
                        mtodayrecovered.setText(model.getTodayRecovered());
                        mtodaytotal.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getTodayCases())));
                        mtotal.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getCases())));
                        mdeaths.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getDeaths())));
                        mrecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getRecovered())));

                        int active = Integer.parseInt(model.getActive());
                        int total = Integer.parseInt(model.getCases());
                        int recovered = Integer.parseInt(model.getRecovered());
                        int deaths = Integer.parseInt(model.getDeaths());

                        updategraph(total, active, recovered, deaths);
                        break;
                    }
                }

                adapter.notifyDataSetChanged(); // Refresh RecyclerView with new country data
            }

            @Override
            public void onFailure(Call<List<CovidModelClass>> call, Throwable t) {
                Toast.makeText(CovidMainActivity.this, "Failed to load data: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updategraph(int total, int active, int recovered, int deaths) {
        mpiechart.clearChart();
        mpiechart.addPieSlice(new PieModel("Confirm", total, android.graphics.Color.parseColor("#FFB701")));
        mpiechart.addPieSlice(new PieModel("Active", active, android.graphics.Color.parseColor("#FF4caf50")));
        mpiechart.addPieSlice(new PieModel("Recovered", recovered, android.graphics.Color.parseColor("#FF38ACCD")));
        mpiechart.addPieSlice(new PieModel("Deaths", deaths, android.graphics.Color.parseColor("#F55C47")));
        mpiechart.startAnimation();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      String item=types[position];
      mfilter.setText(item);
adapter.filter(item);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // optional
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
