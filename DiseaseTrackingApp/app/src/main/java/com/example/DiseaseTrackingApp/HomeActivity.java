package com.example.DiseaseTrackingApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    LinearLayout cardCovid, cardInfluenza, cardNorovirus, cardDengue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardCovid = findViewById(R.id.cardCovid);
        cardInfluenza = findViewById(R.id.cardInfluenza);
        cardNorovirus = findViewById(R.id.cardNorovirus);
        cardDengue = findViewById(R.id.cardDengue);

        cardCovid.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, CovidMainActivity.class))
        );

        cardInfluenza.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, InfluenzaMainActivity.class))
        );

        cardNorovirus.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, NorovirusMainActivity.class))
        );

        cardDengue.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, DengueMainActivity.class))
        );
    }
}
