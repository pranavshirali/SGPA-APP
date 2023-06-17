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

public class activity_7eng18 extends AppCompatActivity {

    EditText btn71 , btn72, btn73, btn74, btn75, btn76, btn77, btn78;
    TextView result4, startingTextView;
    Button show4;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7eng18);


        btn71 = findViewById(R.id.editTextNumberDecimal);
        btn72 = findViewById(R.id.editTextNumberDecimal2);
        btn73 = findViewById(R.id.editTextNumberDecimal3);
        btn74 = findViewById(R.id.editTextNumberDecimal4);
        btn75 = findViewById(R.id.editTextNumberDecimal5);
        btn76 = findViewById(R.id.editTextNumberDecimal6);
        btn77 = findViewById(R.id.editTextNumberDecimal7);
        btn78 = findViewById(R.id.editTextNumberDecimal8);
        result4 = findViewById(R.id.resultview7);
        show4 = findViewById(R.id.button7);

        startingTextView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String selectOption = intent.getStringExtra("selectedOption");
        startingTextView.setText("2018 SCHEME / 7TH SEM / " + getString(R.string.selected_option) + selectOption);

        show4.setOnClickListener(v -> {
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_7eng18.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_7eng18.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            calculate();

        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m71 = Integer.parseInt(btn71.getText().toString());
            int m72 = Integer.parseInt(btn72.getText().toString());
            int m73 = Integer.parseInt(btn73.getText().toString());
            int m74 = Integer.parseInt(btn74.getText().toString());
            int m75 = Integer.parseInt(btn75.getText().toString());
            int m76 = Integer.parseInt(btn76.getText().toString());
            int m77 = Integer.parseInt(btn77.getText().toString());
            int m78 = Integer.parseInt(btn78.getText().toString());

            if (m71 >= 0 && m71 <= 100 &&
                    m72 >= 0 && m72 <= 100 &&
                    m73 >= 0 && m73 <= 100 &&
                    m74 >= 0 && m74 <= 100 &&
                    m75 >= 0 && m75 <= 100 &&
                    m76 >= 0 && m76 <= 100 &&
                    m77 >= 0 && m77 <= 100 &&
                    m78 >= 0 && m78 <= 100) {

                // Convert marks to grades
                int m71Grade = getGradeFromMarks(m71);
                int m72Grade = getGradeFromMarks(m72);
                int m73Grade = getGradeFromMarks(m73);
                int m74Grade = getGradeFromMarks(m74);
                int m75Grade = getGradeFromMarks(m75);
                int m76Grade = getGradeFromMarks(m76);
                int m77Grade = getGradeFromMarks(m77);
                int m78Grade = getGradeFromMarks(m78);

                double total = calculateSGPAGrades(m71Grade, m72Grade, m73Grade, m74Grade, m75Grade, m76Grade, m77Grade, m78Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat4 = new DecimalFormat("#.##");
                String formattedTotal4 = decimalFormat4.format(total);

                result4.setText("SGPA : " + formattedTotal4);
            } else {
                Toast.makeText(activity_7eng18.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_7eng18.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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

    private double calculateSGPAGrades(int m71Grade, int m72Grade, int m73Grade, int m74Grade, int m75Grade, int m76Grade, int m77Grade, int m78Grade) {
        // Define the credit points for each subject
        int m71Credits = 3;
        int m72Credits = 3;
        int m73Credits = 3;
        int m74Credits = 3;
        int m75Credits = 3;
        int m76Credits = 2;
        int m77Credits = 2;
        int m78Credits = 1;

        // Calculate the credit points for each subject
        int m71CP = m71Credits * m71Grade;
        int m72CP = m72Credits * m72Grade;
        int m73CP = m73Credits * m73Grade;
        int m74CP = m74Credits * m74Grade;
        int m75CP = m75Credits * m75Grade;
        int m76CP = m76Credits * m76Grade;
        int m77CP = m77Credits * m77Grade;
        int m78CP = m78Credits * m78Grade;
        // Calculate the total credit points and total credits
        int totalCP = m71CP + m72CP + m73CP + m74CP + m75CP + m76CP + m77CP + m78CP ;
        int totalCredits = m71Credits + m72Credits + m73Credits + m74Credits + m75Credits + m76Credits + m77Credits + m78Credits;

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
        return !btn71.getText().toString().isEmpty() &&
                !btn72.getText().toString().isEmpty() &&
                !btn73.getText().toString().isEmpty() &&
                !btn74.getText().toString().isEmpty() &&
                !btn75.getText().toString().isEmpty() &&
                !btn76.getText().toString().isEmpty() &&
                !btn77.getText().toString().isEmpty() &&
                !btn78.getText().toString().isEmpty();
    }
}