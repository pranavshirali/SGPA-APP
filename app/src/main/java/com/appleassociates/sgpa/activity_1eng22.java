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

public class activity_1eng22 extends AppCompatActivity {

    EditText btn101 , btn102, btn103, btn104, btn105, btn106, btn107, btn108;
    TextView result4;
    Button show4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1eng22);


        btn101 = findViewById(R.id.editTextNumberDecimal);
        btn102 = findViewById(R.id.editTextNumberDecimal2);
        btn103 = findViewById(R.id.editTextNumberDecimal3);
        btn104 = findViewById(R.id.editTextNumberDecimal4);
        btn105 = findViewById(R.id.editTextNumberDecimal5);
        btn106 = findViewById(R.id.editTextNumberDecimal6);
        btn107 = findViewById(R.id.editTextNumberDecimal7);
        btn108 = findViewById(R.id.editTextNumberDecimal8);
        result4 = findViewById(R.id.resultview4);
        show4 = findViewById(R.id.button4);
        show4.setOnClickListener(v -> {
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_1eng22.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_1eng22.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            calculate();

        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m101 = Integer.parseInt(btn101.getText().toString());
            int m102 = Integer.parseInt(btn102.getText().toString());
            int m103 = Integer.parseInt(btn103.getText().toString());
            int m104 = Integer.parseInt(btn104.getText().toString());
            int m105 = Integer.parseInt(btn105.getText().toString());
            int m106 = Integer.parseInt(btn106.getText().toString());
            int m107 = Integer.parseInt(btn107.getText().toString());
            int m108 = Integer.parseInt(btn108.getText().toString());

            if (m101 >= 0 && m101 <= 100 &&
                    m102 >= 0 && m102 <= 100 &&
                    m103 >= 0 && m103 <= 100 &&
                    m104 >= 0 && m104 <= 100 &&
                    m105 >= 0 && m105 <= 100 &&
                    m106 >= 0 && m106 <= 100 &&
                    m107 >= 0 && m107 <= 100 &&
                    m108 >= 0 && m108 <= 100) {

                // Convert marks to grades
                int m101Grade = getGradeFromMarks(m101);
                int m102Grade = getGradeFromMarks(m102);
                int m103Grade = getGradeFromMarks(m103);
                int m104Grade = getGradeFromMarks(m104);
                int m105Grade = getGradeFromMarks(m105);
                int m106Grade = getGradeFromMarks(m106);
                int m107Grade = getGradeFromMarks(m107);
                int m108Grade = getGradeFromMarks(m108);

                double total = calculateSGPAGrades(m101Grade, m102Grade, m103Grade, m104Grade, m105Grade, m106Grade, m107Grade, m108Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat4 = new DecimalFormat("#.##");
                String formattedTotal4 = decimalFormat4.format(total);

                result4.setText("SGPA : " + formattedTotal4);
            } else {
                Toast.makeText(activity_1eng22.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_1eng22.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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

    private double calculateSGPAGrades(int m101Grade, int m102Grade, int m103Grade, int m104Grade, int m105Grade, int m106Grade, int m107Grade, int m108Grade) {
        // Define the credit points for each subject
        int m101Credits = 4;
        int m102Credits = 4;
        int m103Credits = 3;
        int m104Credits = 3;
        int m105Credits = 3;
        int m106Credits = 1;
        int m107Credits = 1;
        int m108Credits = 1;

        // Calculate the credit points for each subject
        int m101CP = m101Credits * m101Grade;
        int m102CP = m102Credits * m102Grade;
        int m103CP = m103Credits * m103Grade;
        int m104CP = m104Credits * m104Grade;
        int m105CP = m105Credits * m105Grade;
        int m106CP = m106Credits * m106Grade;
        int m107CP = m106Credits * m107Grade;
        int m108CP = m108Credits * m108Grade;
        // Calculate the total credit points and total credits
        int totalCP = m101CP + m102CP + m103CP + m104CP + m105CP + m106CP + m107CP + m108CP ;
        int totalCredits = m101Credits + m102Credits + m103Credits + m104Credits + m105Credits + m106Credits + m107Credits + m108Credits;

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
        return !btn101.getText().toString().isEmpty() &&
                !btn102.getText().toString().isEmpty() &&
                !btn103.getText().toString().isEmpty() &&
                !btn104.getText().toString().isEmpty() &&
                !btn105.getText().toString().isEmpty() &&
                !btn106.getText().toString().isEmpty() &&
                !btn107.getText().toString().isEmpty() &&
                !btn108.getText().toString().isEmpty();
    }
}