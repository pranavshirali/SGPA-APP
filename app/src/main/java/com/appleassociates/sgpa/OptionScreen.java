package com.appleassociates.sgpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class OptionScreen extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView field1AutoCompleteTextView;
    AutoCompleteTextView field2AutoCompleteTextView;
    AutoCompleteTextView field3AutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

        field1AutoCompleteTextView = findViewById(R.id.field1AutoComplete);
        field2AutoCompleteTextView = findViewById(R.id.field2AutoComplete);
        field3AutoCompleteTextView = findViewById(R.id.field3AutoComplete);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        String[] field1Options = getResources().getStringArray(R.array.field1_options);
        ArrayAdapter<String> field1Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, field1Options);
        field1AutoCompleteTextView.setAdapter(field1Adapter);

        // Populate options for field2AutoCompleteTextView
        String[] field2Options = getResources().getStringArray(R.array.field2_options);
        ArrayAdapter<String> field2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, field2Options);
        field2AutoCompleteTextView.setAdapter(field2Adapter);

        // Populate options for field3AutoCompleteTextView
        String[] field3Options = getResources().getStringArray(R.array.field3_options);
        ArrayAdapter<String> field3Adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, field3Options);
        field3AutoCompleteTextView.setAdapter(field3Adapter);

    }

    @Override
    public void onClick(View v) {

        // Get the selected options from the AutoCompleteTextViews
        String option1 = field1AutoCompleteTextView.getText().toString();
        String option2 = field2AutoCompleteTextView.getText().toString();
        String option3 = field3AutoCompleteTextView.getText().toString();

        //  LOGIC FOR 3RD SEM 2021 SCHEME
        boolean juniors = option2.equals("Computer Science") || option2.equals("AIML") || option2.equals("IOT") || option2.equals("Information Science") ||
                option2.equals("Aeronautical") || option2.equals("Civil") || option2.equals("Electronics and Communication") ||
                option2.equals("Mechatronics") || option2.equals("Mechanical") || option2.equals("Computer Science(AIML)") || option2.equals("Computer Science(IoT)");
        boolean seniors = option2.equals("IOT") || option2.equals("Aeronautical") || option2.equals("Civil") || option2.equals("Electronics and Communication") ||
                option2.equals("Mechatronics") || option2.equals("Mechanical");
        boolean seniors2 = option2.equals("Computer Science") || option2.equals("AIML") || option2.equals("Information Science");



        if (option1.equals("2021 Scheme")){
            scheme2021(seniors, seniors2, option3, option2);
        }
        if (option1.equals("2018 Scheme")){
            scheme2018(seniors, seniors2, option3, option2);
        }
        if(option1.equals("2022 Scheme")){
            scheme2022(juniors, option3, option2);
        }
    }

    //LOGIC FOR 2018 SCHEME
    public void scheme2018(boolean seniors, boolean seniors2, String option3, String option2){
        if((seniors || seniors2) && option3.equals("SEM 5")){
            Intent intent = new Intent(OptionScreen.this, activity_5eng18.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if ((seniors || seniors2) && option3.equals("SEM 6")) {
            Intent intent = new Intent(OptionScreen.this, activity_6eng18.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if (option3.equals("SEM 7")) {
            if(seniors){
                Intent intent = new Intent(OptionScreen.this, activity_7eng18.class);
                intent.putExtra("selectedOption", option2);
                startActivity(intent);
            } else if (seniors2) {
                Intent intent = new Intent(OptionScreen.this, activity_7itonly18.class);
                intent.putExtra("selectedOption", option2);
                startActivity(intent);
            }
        } else if ((seniors || seniors2) && option3.equals("SEM 8")) {
            Intent intent = new Intent(OptionScreen.this, activity_8eng18.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else {
            Toast.makeText(OptionScreen.this, "Selected Option not allowed", Toast.LENGTH_SHORT).show();
        }
    }

    //LOGIC FOR 2021 SCHEME
    public void scheme2021(boolean seniors, boolean seniors2, String option3, String option2){
        if((seniors || seniors2) && option3.equals("SEM 3")){
            Intent intent = new Intent(OptionScreen.this, activity_3eng21.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if((seniors || seniors2) && option3.equals("SEM 4")) {
            Intent intent = new Intent(OptionScreen.this, activity_4eng21.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if ((seniors || seniors2) && option3.equals("SEM 5")) {
            Intent intent = new Intent(OptionScreen.this, activity_5eng21.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if ((seniors || seniors2)&& option3.equals("SEM 6")) {
            Intent intent = new Intent(OptionScreen.this, activity_6eng21.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if ((seniors || seniors2)&& option3.equals("SEM 7")) {
            Intent intent = new Intent(OptionScreen.this, activity_7eng21.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if ((seniors || seniors2) && option3.equals("SEM 8")) {
            Intent intent = new Intent(OptionScreen.this, activity_8eng21.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else {
            Toast.makeText(OptionScreen.this, "Selected Option not allowed", Toast.LENGTH_SHORT).show();
        }
    }

    //LOGIC FOR 2022 SCHEME
    public void scheme2022(boolean juniors, String option3, String option2){
        if(juniors && option3.equals("SEM 1")){
            Intent intent = new Intent(OptionScreen.this, activity_1eng22.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if(juniors && option3.equals("SEM 2")){
            Intent intent = new Intent(OptionScreen.this, activity_2eng22.class);
            intent.putExtra("selectedOption", option2);
            startActivity(intent);
        } else if(juniors && option3.equals("SEM 3")){
            Toast.makeText(OptionScreen.this, "Selected Option will be updated soon", Toast.LENGTH_SHORT).show();
        } else if(juniors && option3.equals("SEM 4")){
            Toast.makeText(OptionScreen.this, "Selected Option will be updated soon", Toast.LENGTH_SHORT).show();
        } else if(juniors && option3.equals("SEM 5")){
            Toast.makeText(OptionScreen.this, "Selected Option will be updated soon", Toast.LENGTH_SHORT).show();
        } else if(juniors && option3.equals("SEM 6")){
            Toast.makeText(OptionScreen.this, "Selected Option will be updated soon", Toast.LENGTH_SHORT).show();
        } else if(juniors && option3.equals("SEM 7")){
            Toast.makeText(OptionScreen.this, "Selected Option will be updated soon", Toast.LENGTH_SHORT).show();
        } else if(juniors && option3.equals("SEM 8")){
            Toast.makeText(OptionScreen.this, "Selected Option will be updated soon", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(OptionScreen.this, "Selected Option isn't available", Toast.LENGTH_SHORT).show();
        }
    }

}
