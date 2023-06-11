package com.appleassociates.sgpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class OptionScreen extends AppCompatActivity {

    Spinner field1Spinner;
    Spinner field2Spinner;
    Spinner field3Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

        field1Spinner = findViewById(R.id.field1Spinner);
        field2Spinner = findViewById(R.id.field2Spinner);
        field3Spinner = findViewById(R.id.field3Spinner);

        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            // Get the selected options from the Spinners
            String option1 = field1Spinner.getSelectedItem().toString();
            String option2 = field2Spinner.getSelectedItem().toString();
            String option3 = field3Spinner.getSelectedItem().toString();

            //  LOGIC FOR 3RD SEM 2021 SCHEME
            boolean b = option2.equals("Computer Science") || option2.equals("AIML") || option2.equals("IOT") || option2.equals("Information Science") ||
                    option2.equals("Aeronautical") || option2.equals("Civil") || option2.equals("Electronics and Communication") ||
                    option2.equals("Mechatronics") || option2.equals("Mechanical") || option2.equals("Computer Science(AIML)") || option2.equals("Computer Science(IoT)");


            if (option1.equals("2021 Scheme")){
                if(b && option3.equals("SEM 3")){
                    Intent intent = new Intent(OptionScreen.this, activity_3eng21.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 4")) {
                    Intent intent = new Intent(OptionScreen.this, activity_4eng21.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 5")) {
                    Intent intent = new Intent(OptionScreen.this, activity_5eng21.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 6")) {
                    Intent intent = new Intent(OptionScreen.this, activity_6eng21.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 7")) {
                    Intent intent = new Intent(OptionScreen.this, activity_7eng21.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 8")) {
                    Intent intent = new Intent(OptionScreen.this, activity_8eng21.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(OptionScreen.this, "Selected Option not allowed", Toast.LENGTH_SHORT).show();
                }
            }

            if (option1.equals("2018 Scheme")){
                if(b && option3.equals("SEM 5")){
                    Intent intent = new Intent(OptionScreen.this, activity_5eng18.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 6")) {
                    Intent intent = new Intent(OptionScreen.this, activity_6eng18.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 7")) {
                    Intent intent = new Intent(OptionScreen.this, activity_7eng18.class);
                    startActivity(intent);
                } else if (b && option3.equals("SEM 8")) {
                    Intent intent = new Intent(OptionScreen.this, activity_8eng18.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(OptionScreen.this, "Selected Option not allowed", Toast.LENGTH_SHORT).show();
                }
            }

            if(option1.equals("2022 Scheme")){
                if(b && option3.equals("SEM 1")){
                    Intent intent = new Intent(OptionScreen.this, activity_1eng22.class);
                    startActivity(intent);
                } else if(b && option3.equals("SEM 2")){
                    Intent intent = new Intent(OptionScreen.this, activity_2eng22.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(OptionScreen.this, "Selected Option isn't available", Toast.LENGTH_SHORT).show();
                }
            }



        });
    }
}