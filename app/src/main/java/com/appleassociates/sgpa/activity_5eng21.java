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

public class activity_5eng21 extends AppCompatActivity {

    EditText btn51 ,btn52, btn53, btn54, btn55, btn56, btn57, btn58;
    TextView result5;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5eng21);

        btn51 = findViewById(R.id.editTextNumberDecimal);
        btn52 = findViewById(R.id.editTextNumberDecimal2);
        btn53 = findViewById(R.id.editTextNumberDecimal3);
        btn54 = findViewById(R.id.editTextNumberDecimal4);
        btn55 = findViewById(R.id.editTextNumberDecimal5);
        btn56 = findViewById(R.id.editTextNumberDecimal6);
        btn57 = findViewById(R.id.editTextNumberDecimal7);
        btn58 = findViewById(R.id.editTextNumberDecimal8);
        result5 = findViewById(R.id.resultview5);
        show = findViewById(R.id.button);
        show.setOnClickListener(v -> {
            calculate();
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_5eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_5eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m51 = Integer.parseInt(btn51.getText().toString());
            int m52 = Integer.parseInt(btn52.getText().toString());
            int m53 = Integer.parseInt(btn53.getText().toString());
            int m54 = Integer.parseInt(btn54.getText().toString());
            int m55 = Integer.parseInt(btn55.getText().toString());
            int m56 = Integer.parseInt(btn56.getText().toString());
            int m57 = Integer.parseInt(btn57.getText().toString());
            int m58 = Integer.parseInt(btn58.getText().toString());

            if (m51 >= 0 && m51 <= 100 &&
                    m52 >= 0 && m52 <= 100 &&
                    m53 >= 0 && m53 <= 100 &&
                    m54 >= 0 && m54 <= 100 &&
                    m55 >= 0 && m55 <= 100 &&
                    m56 >= 0 && m56 <= 100 &&
                    m57 >= 0 && m57 <= 100 &&
                    m58 >= 0 && m58 <= 100) {

                // Convert marks to grades
                int m51Grade = getGradeFromMarks(m51);
                int m52Grade = getGradeFromMarks(m52);
                int m53Grade = getGradeFromMarks(m53);
                int m54Grade = getGradeFromMarks(m54);
                int m55Grade = getGradeFromMarks(m55);
                int m56Grade = getGradeFromMarks(m56);
                int m57Grade = getGradeFromMarks(m57);
                int m58Grade = getGradeFromMarks(m58);

                double total = calculateSGPAGrades(m51Grade, m52Grade, m53Grade, m54Grade, m55Grade, m56Grade, m57Grade, m58Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedTotal = decimalFormat.format(total);

                result5.setText("SGPA : " + formattedTotal);
            } else {
                Toast.makeText(activity_5eng21.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_5eng21.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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

    private double calculateSGPAGrades(int m51Grade, int m52Grade, int m53Grade, int m54Grade, int m55Grade, int m56Grade, int m57Grade, int m58Grade) {
        // Define the credit points for each subject
        int m51Credits = 3;
        int m52Credits = 4;
        int m53Credits = 3;
        int m54Credits= 3;
        int m55Credits = 1;
        int m56Credits= 2;
        int m57Credits = 1;
        int m58Credits = 1;

        // Calculate the credit points for each subject
        int m51CP = m51Credits * m51Grade;
        int m52CP = m52Credits * m52Grade;
        int m53CP = m53Credits * m53Grade;
        int m54CP = m54Credits * m54Grade;
        int m55CP = m55Credits * m55Grade;
        int m56CP = m56Credits * m56Grade;
        int m57CP = m56Credits * m57Grade;
        int m58CP = m58Credits * m58Grade;

        // Calculate the total credit points and total credits
        int totalCP = m51CP + m52CP + m53CP + m54CP + m55CP + m56CP + m57CP + m58CP ;
        int totalCredits = m51Credits + m52Credits + m53Credits + m54Credits + m55Credits + m56Credits + m57Credits + m58Credits;

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
        return !btn51.getText().toString().isEmpty() &&
                !btn52.getText().toString().isEmpty() &&
                !btn53.getText().toString().isEmpty() &&
                !btn54.getText().toString().isEmpty() &&
                !btn55.getText().toString().isEmpty() &&
                !btn56.getText().toString().isEmpty() &&
                !btn57.getText().toString().isEmpty() &&
                !btn58.getText().toString().isEmpty();
    }
}