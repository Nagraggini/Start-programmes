package com.example.myfirstapplication12;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Layout beállítása
    }



    public void onButtonClick(View view) {
        // Push up értesítés
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();

        int[] numbers = new int[6]; // Egy 10 elemű tömb létrehozása

        //Feltöltjük a tömböt
        for (int i = 0; i <= 5; i++) {
            numbers[i]=i;
        }

        // Megkeressük a TextView-t az XML alapján
        TextView myTextView = findViewById(R.id.textView2);

                // Szöveg felépítése a tömb elemeiből
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }

        // Szöveg beállítása a TextView-ba
        myTextView.setText("For ciklus: "+sb.toString());

        //TextView-nek új háttérszín beállítása. A # utáni szám határozza meg, hogy mennyire legyen áttetsző.
        myTextView.setBackgroundColor(Color.parseColor("#90ffe6ff"));

    }

    public void onButtonClick2(View view) {
        //activity_second layout beállítása
        setContentView(R.layout.activity_second);
        // Push up értesítés
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_LONG).show();

    }
}