package com.example.DiseaseTrackingApp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfluenzaMainActivity extends AppCompatActivity {
    private int currentPage = 1;
    private final int PAGE_SIZE = 100;
    private int totalPages = 1;

    private String country;

    private TextView mtotal, mrecovered, mdeaths, mregion, mweek;
    private Spinner countrySpinner, regionSpinner, filterSpinner;
    private PieChart mpiechart;
    private RecyclerView recyclerView;

    private List<InfluenzaModelClass> modelClassList2;
    private InfluenzaAdapter adapter;
    private InfluenzaAPIInterface apiService;
    private final Map<String, List<String>> regionCountryMap = new HashMap<String, List<String>>() {{
        put("Asia", Arrays.asList("Afghanistan", "India", "Pakistan", "China"));
        put("Europe", Arrays.asList("Germany", "France", "Italy"));
        put("Africa", Arrays.asList("Nigeria", "Kenya", "South Africa"));
        // Add more regions and countries as per your dataset
    }};


    private final String[] countries = {"India", "Pakistan", "United States", "UK", "Germany"};
    private final String[] regionNames = {
            "National", "HHS Region 1", "HHS Region 2", "HHS Region 3", "HHS Region 4",
            "HHS Region 5", "HHS Region 6", "HHS Region 7", "HHS Region 8", "HHS Region 9", "HHS Region 10"
    };
    private final String[] regionCodes = {
            "nat", "hhs1", "hhs2", "hhs3", "hhs4",
            "hhs5", "hhs6", "hhs7", "hhs8", "hhs9", "hhs10"
    };
    private final String[] filterTypes = {"cases", "deaths", "recovered", "active"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.influenza);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        mtotal = findViewById(R.id.totalcase);
        mrecovered = findViewById(R.id.recoveredcase);
        mdeaths = findViewById(R.id.deathcase);
        mregion = findViewById(R.id.selected_region);
        mweek = findViewById(R.id.latest_week);
        mpiechart = findViewById(R.id.piechart);

        countrySpinner = findViewById(R.id.spinner_country);
        regionSpinner = findViewById(R.id.spinnerRegion);  // FIXED: assign to field, NOT local variable
        filterSpinner = findViewById(R.id.spinner_filter); // You need to add this Spinner in your layout!

        // Set adapters
        ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, regionNames);
        regionSpinner.setAdapter(regionAdapter);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, countries); // Use countries array here
        countrySpinner.setAdapter(countryAdapter);



        regionSpinner.setVisibility(View.GONE); // Hide regionSpinner by default

        // RecyclerView setup (unchanged)
        recyclerView = findViewById(R.id.recycler_view);
        modelClassList2 = new ArrayList<>();
        adapter = new InfluenzaAdapter(this, modelClassList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
                LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
                if (lm != null && lm.findLastVisibleItemPosition() == modelClassList2.size() - 1 && currentPage < totalPages) {
                    currentPage++;
                    fetchDataFromApi(country);
                }
            }
        });

        apiService = InfluenzaApiUtilities.getAPIInterface();

        // Listeners
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
                currentPage = 1;
                fetchDataFromApi(country);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRegion = parent.getItemAtPosition(position).toString();
                List<String> countriesInRegion = regionCountryMap.get(selectedRegion);

                if (countriesInRegion != null) {
                    ArrayAdapter<String> newCountryAdapter = new ArrayAdapter<>(InfluenzaMainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, countriesInRegion);
                    countrySpinner.setAdapter(newCountryAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.filter(filterTypes[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


    apiService = InfluenzaApiUtilities.getAPIInterface();


        regionSpinner.setAdapter(regionAdapter);
        regionSpinner.setVisibility(View.GONE);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
                currentPage = 1;
                fetchDataFromApi(country); // your existing method
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRegion = parent.getItemAtPosition(position).toString();
                List<String> countries = regionCountryMap.get(selectedRegion);

                if (countries != null) {
                    ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(InfluenzaMainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, countries);
                    countrySpinner.setAdapter(countryAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, filterTypes);
        filterSpinner.setAdapter(filterAdapter);
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.filter(filterTypes[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void fetchDataFromApi(String country) {
        Log.d("InfluenzaMainActivity", "Fetching data for country: " + country);
        Toast.makeText(InfluenzaMainActivity.this, "Fetching data for: " + country, Toast.LENGTH_SHORT).show();

        apiService.getFluData(currentPage, PAGE_SIZE, country)
                .enqueue(new Callback<PagedResponse<InfluenzaModelClass>>() {
                    @Override
                    public void onResponse(Call<PagedResponse<InfluenzaModelClass>> call,
                                           Response<PagedResponse<InfluenzaModelClass>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            PagedResponse<InfluenzaModelClass> page = response.body();
                            if (currentPage == 1) {
                                modelClassList2.clear(); // Clear old data if loading fresh
                            }
                            if (page.getData() != null && !page.getData().isEmpty()) {
                                modelClassList2.addAll(page.getData());
                                adapter.notifyDataSetChanged();

                                InfluenzaModelClass firstItem = page.getData().get(0);
                                mtotal.setText("Total cases: " + firstItem.getCases());
                                mweek.setText("Date: " + firstItem.getDateString());
                            } else {
                                Toast.makeText(InfluenzaMainActivity.this, "No data found for selected region.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(InfluenzaMainActivity.this, "Failed: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PagedResponse<InfluenzaModelClass>> call, Throwable t) {
                        Toast.makeText(InfluenzaMainActivity.this,
                                "Something went wrong: " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e("InfluenzaMainActivity", "Failure: ", t);
                    }
                });
    }
    private String epiweekToMonth(String epiweekStr) {
        if (epiweekStr == null || epiweekStr.length() < 6) return "N/A";
        int week = Integer.parseInt(epiweekStr.substring(4));
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int monthIndex = (week - 1) / 4;  // Approximate conversion
        if (monthIndex >= 0 && monthIndex < months.length) {
            return months[monthIndex];
        }
        return "Unknown";
    }

    private int safeParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    private void updateGraph(int total, int active, int recovered, int deaths) {
        mpiechart.clearChart();
        mpiechart.addPieSlice(new PieModel("Confirmed", total, android.graphics.Color.parseColor("#FFB701")));
        mpiechart.addPieSlice(new PieModel("Active", active, android.graphics.Color.parseColor("#FF4caf50")));
        mpiechart.addPieSlice(new PieModel("Recovered", recovered, android.graphics.Color.parseColor("#FF38ACCD")));
        mpiechart.addPieSlice(new PieModel("Deaths", deaths, android.graphics.Color.parseColor("#F55C47")));
        mpiechart.startAnimation();
    }
}
