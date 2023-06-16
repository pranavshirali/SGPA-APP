package com.appleassociates.sgpa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class activity_4eng21 extends AppCompatActivity {

    EditText btn41 ,btn42, btn43, btn44, btn45, btn46, btn47, btn48, btn49, btn10;
    TextView result4, startingTextView;
    Button show4;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4eng21);

        btn41 = findViewById(R.id.editTextNumberDecimal);
        btn42 = findViewById(R.id.editTextNumberDecimal2);
        btn43 = findViewById(R.id.editTextNumberDecimal3);
        btn44 = findViewById(R.id.editTextNumberDecimal4);
        btn45 = findViewById(R.id.editTextNumberDecimal5);
        btn46 = findViewById(R.id.editTextNumberDecimal6);
        btn47 = findViewById(R.id.editTextNumberDecimal7);
        btn48 = findViewById(R.id.editTextNumberDecimal8);
        btn49 = findViewById(R.id.editTextNumberDecimal9);
        btn10 = findViewById(R.id.editTextNumberDecimal10);
        result4 = findViewById(R.id.resultview4);
        startingTextView = findViewById(R.id.textView);
        show4 = findViewById(R.id.button4);

        Intent intent = getIntent();
        String selectedOption = intent.getStringExtra("selectedOption");
        startingTextView.setText("2021 SCHEME / 4TH SEM / " + getString(R.string.selected_option) + selectedOption);
        show4.setOnClickListener(v -> {
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_4eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_4eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            calculate();

        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m41 = Integer.parseInt(btn41.getText().toString());
            int m42 = Integer.parseInt(btn42.getText().toString());
            int m43 = Integer.parseInt(btn43.getText().toString());
            int m44 = Integer.parseInt(btn44.getText().toString());
            int m45 = Integer.parseInt(btn45.getText().toString());
            int m46 = Integer.parseInt(btn46.getText().toString());
            int m47 = Integer.parseInt(btn47.getText().toString());
            int m48 = Integer.parseInt(btn48.getText().toString());
            int m49 = Integer.parseInt(btn49.getText().toString());
            int m10 = Integer.parseInt(btn10.getText().toString());

            if (m41 >= 0 && m41 <= 100 &&
                    m42 >= 0 && m42 <= 100 &&
                    m43 >= 0 && m43 <= 100 &&
                    m44 >= 0 && m44 <= 100 &&
                    m45 >= 0 && m45 <= 100 &&
                    m46 >= 0 && m46 <= 100 &&
                    m47 >= 0 && m47 <= 100 &&
                    m48 >= 0 && m48 <= 100 &&
                    m49 >= 0 && m49 <= 100 &&
                    m10 >= 0 && m10 <= 100) {

                // Convert marks to grades
                int m41Grade = getGradeFromMarks(m41);
                int m42Grade = getGradeFromMarks(m42);
                int m43Grade = getGradeFromMarks(m43);
                int m44Grade = getGradeFromMarks(m44);
                int m45Grade = getGradeFromMarks(m45);
                int m46Grade = getGradeFromMarks(m46);
                int m47Grade = getGradeFromMarks(m47);
                int m48Grade = getGradeFromMarks(m48);
                int m49Grade = getGradeFromMarks(m49);
                int m10Grade = getGradeFromMarks(m10);

                double total = calculateSGPAGrades(m41Grade, m42Grade, m43Grade, m44Grade, m45Grade, m46Grade, m47Grade, m48Grade, m49Grade, m10Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat4 = new DecimalFormat("#.##");
                String formattedTotal4 = decimalFormat4.format(total);

                result4.setText("SGPA : " + formattedTotal4);
            } else {
                Toast.makeText(activity_4eng21.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_4eng21.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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

    private double calculateSGPAGrades(int m41Grade, int m42Grade, int m43Grade, int m44Grade, int m45Grade, int m46Grade, int m47Grade, int m48Grade, int m49Grade, int m10Grade) {
        // Define the credit points for each subject
        int m41Credits = 3;
        int m42Credits = 4;
        int m43Credits = 4;
        int m44Credits= 3;
        int m45Credits = 2;
        int m46Credits= 1;
        int m47Credits = 1;
        int m48Credits = 1;
        int m49Credits = 1;
        int m10Credits = 2;

        // Calculate the credit points for each subject
        int m41CP = m41Credits * m41Grade;
        int m42CP = m42Credits * m42Grade;
        int m43CP = m43Credits * m43Grade;
        int m44CP = m44Credits * m44Grade;
        int m45CP = m45Credits * m45Grade;
        int m46CP = m46Credits * m46Grade;
        int m47CP = m46Credits * m47Grade;
        int m48CP = m48Credits * m48Grade;
        int m49CP = m49Credits * m49Grade;
        int m10CP = m10Credits * m10Grade;

        // Calculate the total credit points and total credits
        int totalCP = m41CP + m42CP + m43CP + m44CP + m45CP + m46CP + m47CP + m48CP + m49CP + m10CP ;
        int totalCredits = m41Credits + m42Credits + m43Credits + m44Credits + m45Credits + m46Credits + m47Credits + m48Credits + m49Credits + m10Credits;

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
        return !btn41.getText().toString().isEmpty() &&
                !btn42.getText().toString().isEmpty() &&
                !btn43.getText().toString().isEmpty() &&
                !btn44.getText().toString().isEmpty() &&
                !btn45.getText().toString().isEmpty() &&
                !btn46.getText().toString().isEmpty() &&
                !btn47.getText().toString().isEmpty() &&
                !btn48.getText().toString().isEmpty() &&
                !btn49.getText().toString().isEmpty() &&
                !btn10.getText().toString().isEmpty();
    }
}