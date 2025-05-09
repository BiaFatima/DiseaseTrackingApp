package com.example.DiseaseTrackingApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    MaterialCardView cardCovid, cardInfluenza, cardNorovirus, cardDengue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // ✅ Must come first
        setContentView(R.layout.activity_home);

        // ✅ Now it's safe to access views
        TextView title = findViewById(R.id.title);
        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        title.startAnimation(slideIn);

        // Card view references
        cardCovid = findViewById(R.id.cardCovid);
        cardInfluenza = findViewById(R.id.cardInfluenza);
        cardNorovirus = findViewById(R.id.cardNorovirus);
        cardDengue = findViewById(R.id.cardDengue);

        // Click listeners
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
