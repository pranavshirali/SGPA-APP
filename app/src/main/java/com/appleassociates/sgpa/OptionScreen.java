package com.appleassociates.sgpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected options from the Spinners
                String option1 = field1Spinner.getSelectedItem().toString();
                String option2 = field2Spinner.getSelectedItem().toString();
                String option3 = field3Spinner.getSelectedItem().toString();

                // Check if the selected options match the desired values
                if (option1.equals("2021 Scheme") &&
                        (option2.equals("Computer Science") || option2.equals("AIML") || option2.equals("IOT") || option2.equals("Information Science")) &&
                        option3.equals("SEM 3")) {
                    // Redirect to the SGPA calculation view
                    Intent intent = new Intent(OptionScreen.this, activity_3it21.class);
                    startActivity(intent);
                } else if (option1.equals("2021 Scheme") &&
                        (option2.equals("Computer Science") || option2.equals("AIML") || option2.equals("IOT") || option2.equals("Information Science")) &&
                        option3.equals("SEM 4")) {
                    Intent intent = new Intent(OptionScreen.this, activity_4it21.class);
                    startActivity(intent);
                } else if (option1.equals("2021 Scheme") &&
                        option2.equals("Aeronautical") &&
                        option3.equals("SEM 3")) {
                    Intent intent = new Intent(OptionScreen.this, activity_3it21.class);
                    startActivity(intent);
                } else {
                    // Display a message indicating that the selected options are not valid
                    Toast.makeText(OptionScreen.this, "Please select the correct options", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
