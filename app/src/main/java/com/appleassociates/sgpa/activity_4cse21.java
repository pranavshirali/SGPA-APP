package com.appleassociates.sgpa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class activity_4cse21 extends AppCompatActivity {

    EditText m41 ,m42, m43, m44, m45, m46, m47, m48, m49;
    TextView result;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4cse21);

        m41 = findViewById(R.id.editTextNumberDecimal);
        m42 = findViewById(R.id.editTextNumberDecimal2);
        m43 = findViewById(R.id.editTextNumberDecimal3);
        m44 = findViewById(R.id.editTextNumberDecimal4);
        m45 = findViewById(R.id.editTextNumberDecimal5);
        m46 = findViewById(R.id.editTextNumberDecimal6);
        m47 = findViewById(R.id.editTextNumberDecimal7);
        m48 = findViewById(R.id.editTextNumberDecimal8);
        m49 = findViewById(R.id.editTextNumberDecimal9);
        result = findViewById(R.id.resultview);
        show = findViewById(R.id.button);
        show.setOnClickListener(v -> {
            calculate();
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_4cse21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_4cse21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int cs41 = Integer.parseInt(m41.getText().toString());
            int cs42 = Integer.parseInt(m42.getText().toString());
            int cs43 = Integer.parseInt(m43.getText().toString());
            int cs44 = Integer.parseInt(m44.getText().toString());
            int cs45 = Integer.parseInt(m45.getText().toString());
            int cs46 = Integer.parseInt(m46.getText().toString());
            int cs47 = Integer.parseInt(m47.getText().toString());
            int cs48 = Integer.parseInt(m48.getText().toString());
            int cs49 = Integer.parseInt(m49.getText().toString());


            if (cs41 >= 0 && cs41 <= 100 &&
                    cs42 >= 0 && cs42 <= 100 &&
                    cs43 >= 0 && cs43 <= 100 &&
                    cs44 >= 0 && cs44 <= 100 &&
                    cs45 >= 0 && cs45 <= 100 &&
                    cs46 >= 0 && cs46 <= 100 &&
                    cs47 >= 0 && cs47 <= 100 &&
                    cs48 >= 0 && cs48 <= 100 &&
                    cs49 >= 0 && cs49 <= 100) {

                // Convert marks to grades
                int cs41Grade = getGradeFromMarks(cs41);
                int cs42Grade = getGradeFromMarks(cs42);
                int cs43Grade = getGradeFromMarks(cs43);
                int cs44Grade = getGradeFromMarks(cs44);
                int cs45Grade = getGradeFromMarks(cs45);
                int cs46Grade = getGradeFromMarks(cs46);
                int cs47Grade = getGradeFromMarks(cs47);
                int cs48Grade = getGradeFromMarks(cs48);
                int cs49Grade = getGradeFromMarks(cs49);

                double total = calculateSGPAGrades(cs41Grade, cs42Grade, cs43Grade, cs44Grade, cs45Grade, cs46Grade, cs47Grade, cs48Grade, cs49Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedTotal = decimalFormat.format(total);

                result.setText("SGPA : " + formattedTotal);
            } else {
                Toast.makeText(activity_4cse21.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_4cse21.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
            Log.e("NumberFormatException", e.getMessage());
        }
    }
    private int getGradeFromMarks(int marks) {
        int grade;
        // Define the grade ranges and corresponding grades
        if (marks >= 90 && marks <= 100) {
            grade = 10;
        } else if (marks >= 80 && marks < 90) {
            grade = 9 ;
        } else if (marks >= 70 && marks < 80) {
            grade = 8;
        } else if (marks >= 60 && marks < 70) {
            grade = 7;
        } else if (marks >= 50 && marks < 60) {
            grade = 6;
        } else if (marks >= 45 && marks < 50) {
            grade = 5;
        } else if (marks >= 40 && marks < 45) {
            grade = 4;
        } else {
            grade = 0;
        }
        return grade;
    }

    private double calculateSGPAGrades(int cs41Grade, int cs42Grade, int cs43Grade, int cs44Grade, int cs45Grade, int cs46Grade, int cs47Grade, int cs48Grade, int cs49Grade) {
        // Define the credit points for each subject
        int cs41Credits = 3;
        int cs42Credits = 4;
        int cs43Credits = 4;
        int cs44Credits= 3;
        int cs45Credits = 2;
        int cs46Credits= 1;
        int cs47Credits = 1;
        int cs48Credits = 1;
        int cs49Credits = 1;

        // Calculate the credit points for each subject
        int cs41CP = cs41Credits * cs41Grade;
        int cs42CP = cs42Credits * cs42Grade;
        int cs43CP = cs43Credits * cs43Grade;
        int cs44CP = cs44Credits * cs44Grade;
        int cs45CP = cs45Credits * cs45Grade;
        int cs46CP = cs46Credits * cs46Grade;
        int cs47CP = cs46Credits * cs47Grade;
        int cs48CP = cs48Credits * cs48Grade;
        int cs49CP = cs49Credits * cs49Grade;

        // Calculate the total credit points and total credits
        int totalCP = cs41CP + cs42CP + cs43CP + cs44CP + cs45CP + cs46CP + cs47CP + cs48CP + cs49CP;
        int totalCredits = cs41Credits + cs42Credits + cs43Credits + cs44Credits + cs45Credits + cs46Credits + cs47Credits + cs48Credits + cs49Credits;

        // Calculate the SGPA
        return (double) totalCP / totalCredits;
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean validateFields() {
        return !m41.getText().toString().isEmpty() &&
                !m42.getText().toString().isEmpty() &&
                !m43.getText().toString().isEmpty() &&
                !m44.getText().toString().isEmpty() &&
                !m45.getText().toString().isEmpty() &&
                !m46.getText().toString().isEmpty() &&
                !m47.getText().toString().isEmpty() &&
                !m48.getText().toString().isEmpty() &&
                !m49.getText().toString().isEmpty();
    }
}