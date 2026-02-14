package com.example.taptheball;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View elemek azonosítása
        TextView scoreText = findViewById(R.id.scoreText);
        Button myButton = findViewById(R.id.myButton);

        // Gomb kattintás eseménykezelése
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pontszám növelése és frissítése
                score++;
                scoreText.setText("Score: " + score);
            }
        });
    }
}
