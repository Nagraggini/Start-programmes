package com.example.tapgame;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View tapTarget;
    private TextView scoreText;
    private int score = 0;
    private final Random random = new Random();
    private final Handler handler = new Handler();
    private Runnable moveTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tapTarget = findViewById(R.id.tapTarget);
        scoreText = findViewById(R.id.scoreText);

        // Kattintási esemény
        tapTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                scoreText.setText("Score: " + score);
            }
        });

        // Célpont mozgatása
        moveTarget = new Runnable() {
            @Override
            public void run() {
                int maxX = findViewById(R.id.tapTarget).getWidth();
                int maxY = findViewById(R.id.tapTarget).getHeight();
                int x = random.nextInt(findViewById(R.id.tapTarget).getWidth());
                int y = random.nextInt(findViewById(R.id.tapTarget).getHeight());
                tapTarget.setX(x);
                tapTarget.setY(y);
                handler.postDelayed(this, 1000);  // Célpont mozgatása minden másodpercben
            }
        };

        handler.post(moveTarget);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(moveTarget);  // Mozgás leállítása, amikor az Activity megsemmisül
    }
}
