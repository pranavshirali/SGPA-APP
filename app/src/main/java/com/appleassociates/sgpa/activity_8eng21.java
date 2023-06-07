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

public class activity_8eng21 extends AppCompatActivity {

    EditText btn81 ,btn82, btn83;
    TextView result7;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8eng21);

        btn81 = findViewById(R.id.editTextNumberDecimal);
        btn82 = findViewById(R.id.editTextNumberDecimal2);
        btn83 = findViewById(R.id.editTextNumberDecimal3);
        show = findViewById(R.id.button6);
        result7 = findViewById(R.id.resultview6);
        show.setOnClickListener(v -> {
            calculate();
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_8eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_8eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m81 = Integer.parseInt(btn81.getText().toString());
            int m82 = Integer.parseInt(btn82.getText().toString());
            int m83 = Integer.parseInt(btn83.getText().toString());

            if (m81 >= 0 && m81 <= 100 &&
                    m82 >= 0 && m82 <= 100 &&
                    m83 >= 0 && m83 <= 100) {

                // Convert marks to grades
                int m81Grade = getGradeFromMarks(m81);
                int m82Grade = getGradeFromMarks(m82);
                int m83Grade = getGradeFromMarks(m83);

                double total = calculateSGPAGrades(m81Grade, m82Grade, m83Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedTotal = decimalFormat.format(total);

                result7.setText("SGPA : " + formattedTotal);
            } else {
                Toast.makeText(activity_8eng21.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_8eng21.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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

    private double calculateSGPAGrades(int m81Grade, int m82Grade, int m83Grade) {
        // Define the credit points for each subject
        int m81Credits = 1;
        int m82Credits = 15;
        int m83Credits = 0;

        // Calculate the credit points for each subject
        int m81CP = m81Credits * m81Grade;
        int m82CP = m82Credits * m82Grade;
        int m83CP = m83Credits * m83Grade;

        // Calculate the total credit points and total credits
        int totalCP = m81CP + m82CP + m83CP ;
        int totalCredits = m81Credits + m82Credits + m83Credits;

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
        return !btn81.getText().toString().isEmpty() &&
                !btn82.getText().toString().isEmpty() &&
                !btn83.getText().toString().isEmpty();
    }
}