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

public class activity_8eng18 extends AppCompatActivity {

    EditText btn61 , btn62, btn63, btn64, btn65;
    TextView result4, startingTextView;
    Button show4;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8eng18);


        btn61 = findViewById(R.id.editTextNumberDecimal);
        btn62 = findViewById(R.id.editTextNumberDecimal2);
        btn63 = findViewById(R.id.editTextNumberDecimal3);
        btn64 = findViewById(R.id.editTextNumberDecimal4);
        btn65 = findViewById(R.id.editTextNumberDecimal5);
        result4 = findViewById(R.id.resultview8);
        show4 = findViewById(R.id.button8);

        startingTextView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String selectOption = intent.getStringExtra("selectedOption");
        startingTextView.setText("2018 SCHEME / 8TH SEM / " + getString(R.string.selected_option) + selectOption);

        show4.setOnClickListener(v -> {
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_8eng18.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_8eng18.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            calculate();

        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m61 = Integer.parseInt(btn61.getText().toString());
            int m62 = Integer.parseInt(btn62.getText().toString());
            int m63 = Integer.parseInt(btn63.getText().toString());
            int m64 = Integer.parseInt(btn64.getText().toString());
            int m65 = Integer.parseInt(btn65.getText().toString());

            if (m61 >= 0 && m61 <= 100 &&
                    m62 >= 0 && m62 <= 100 &&
                    m63 >= 0 && m63 <= 100 &&
                    m64 >= 0 && m64 <= 100 &&
                    m65 >= 0 && m65 <= 100) {

                // Convert marks to grades
                int m61Grade = getGradeFromMarks(m61);
                int m62Grade = getGradeFromMarks(m62);
                int m63Grade = getGradeFromMarks(m63);
                int m64Grade = getGradeFromMarks(m64);
                int m65Grade = getGradeFromMarks(m65);

                double total = calculateSGPAGrades(m61Grade, m62Grade, m63Grade, m64Grade, m65Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat4 = new DecimalFormat("#.##");
                String formattedTotal4 = decimalFormat4.format(total);

                result4.setText("SGPA : " + formattedTotal4);
            } else {
                Toast.makeText(activity_8eng18.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_8eng18.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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

    private double calculateSGPAGrades(int m61Grade, int m62Grade, int m63Grade, int m64Grade, int m65Grade) {
        // Define the credit points for each subject
        int m61Credits = 3;
        int m62Credits = 3;
        int m63Credits = 8;
        int m64Credits = 1;
        int m65Credits = 3;

        // Calculate the credit points for each subject
        int m61CP = m61Credits * m61Grade;
        int m62CP = m62Credits * m62Grade;
        int m63CP = m63Credits * m63Grade;
        int m64CP = m64Credits * m64Grade;
        int m65CP = m65Credits * m65Grade;
        // Calculate the total credit points and total credits
        int totalCP = m61CP + m62CP + m63CP + m64CP + m65CP;
        int totalCredits = m61Credits + m62Credits + m63Credits + m64Credits + m65Credits;

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
        return !btn61.getText().toString().isEmpty() &&
                !btn62.getText().toString().isEmpty() &&
                !btn63.getText().toString().isEmpty() &&
                !btn64.getText().toString().isEmpty() &&
                !btn65.getText().toString().isEmpty();
    }
}