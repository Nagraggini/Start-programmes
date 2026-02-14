package com.example.myfirstapplication12;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private int score = 0;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);  // Az új layout használata


       // View elemek azonosítása
       TextView scoreText = findViewById(R.id.scoreText);
       Button myButton3 = findViewById(R.id.myButton3);

       // Gomb kattintás eseménykezelése
        //myButton3.setOnClickListener(new View.OnClickListener() {
           //@Override
           //public void onButtonClick3(View view) {
               // Pontszám növelése és frissítése
               //score++;
               //scoreText.setText("Score: " + score);
          // }
      // });

   }
}