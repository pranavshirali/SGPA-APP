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

public class activity_2eng22 extends AppCompatActivity {

    EditText btn201 , btn202, btn203, btn204, btn205, btn206, btn207, btn208;
    TextView result4, startingTextView;
    Button show4;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2eng22);


        btn201 = findViewById(R.id.editTextNumberDecimal);
        btn202 = findViewById(R.id.editTextNumberDecimal2);
        btn203 = findViewById(R.id.editTextNumberDecimal3);
        btn204 = findViewById(R.id.editTextNumberDecimal4);
        btn205 = findViewById(R.id.editTextNumberDecimal5);
        btn206 = findViewById(R.id.editTextNumberDecimal6);
        btn207 = findViewById(R.id.editTextNumberDecimal7);
        btn208 = findViewById(R.id.editTextNumberDecimal8);
        result4 = findViewById(R.id.resultview2);
        show4 = findViewById(R.id.button2);

        startingTextView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String selectOption = intent.getStringExtra("selectedOption");
        startingTextView.setText("2018 SCHEME / 2ND SEM / " + getString(R.string.selected_option) + selectOption);

        show4.setOnClickListener(v -> {
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_2eng22.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_2eng22.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            calculate();

        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m201 = Integer.parseInt(btn201.getText().toString());
            int m202 = Integer.parseInt(btn202.getText().toString());
            int m203 = Integer.parseInt(btn203.getText().toString());
            int m204 = Integer.parseInt(btn204.getText().toString());
            int m205 = Integer.parseInt(btn205.getText().toString());
            int m206 = Integer.parseInt(btn206.getText().toString());
            int m207 = Integer.parseInt(btn207.getText().toString());
            int m208 = Integer.parseInt(btn208.getText().toString());

            if (m201 >= 0 && m201 <= 100 &&
                    m202 >= 0 && m202 <= 100 &&
                    m203 >= 0 && m203 <= 100 &&
                    m204 >= 0 && m204 <= 100 &&
                    m205 >= 0 && m205 <= 100 &&
                    m206 >= 0 && m206 <= 100 &&
                    m207 >= 0 && m207 <= 100 &&
                    m208 >= 0 && m208 <= 100) {

                // Convert marks to grades
                int m201Grade = getGradeFromMarks(m201);
                int m202Grade = getGradeFromMarks(m202);
                int m203Grade = getGradeFromMarks(m203);
                int m204Grade = getGradeFromMarks(m204);
                int m205Grade = getGradeFromMarks(m205);
                int m206Grade = getGradeFromMarks(m206);
                int m207Grade = getGradeFromMarks(m207);
                int m208Grade = getGradeFromMarks(m208);

                double total = calculateSGPAGrades(m201Grade, m202Grade, m203Grade, m204Grade, m205Grade, m206Grade, m207Grade, m208Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat4 = new DecimalFormat("#.##");
                String formattedTotal4 = decimalFormat4.format(total);

                result4.setText("SGPA : " + formattedTotal4);
            } else {
                Toast.makeText(activity_2eng22.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_2eng22.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
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

    private double calculateSGPAGrades(int m201Grade, int m202Grade, int m203Grade, int m204Grade, int m205Grade, int m206Grade, int m207Grade, int m208Grade) {
        // Define the credit points for each subject
        int m201Credits = 4;
        int m202Credits = 4;
        int m203Credits = 3;
        int m204Credits = 3;
        int m205Credits = 3;
        int m206Credits = 1;
        int m207Credits = 1;
        int m208Credits = 1;

        // Calculate the credit points for each subject
        int m201CP = m201Credits * m201Grade;
        int m202CP = m202Credits * m202Grade;
        int m203CP = m203Credits * m203Grade;
        int m204CP = m204Credits * m204Grade;
        int m205CP = m205Credits * m205Grade;
        int m206CP = m206Credits * m206Grade;
        int m207CP = m206Credits * m207Grade;
        int m208CP = m208Credits * m208Grade;
        // Calculate the total credit points and total credits
        int totalCP = m201CP + m202CP + m203CP + m204CP + m205CP + m206CP + m207CP + m208CP ;
        int totalCredits = m201Credits + m202Credits + m203Credits + m204Credits + m205Credits + m206Credits + m207Credits + m208Credits;

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
        return !btn201.getText().toString().isEmpty() &&
                !btn202.getText().toString().isEmpty() &&
                !btn203.getText().toString().isEmpty() &&
                !btn204.getText().toString().isEmpty() &&
                !btn205.getText().toString().isEmpty() &&
                !btn206.getText().toString().isEmpty() &&
                !btn207.getText().toString().isEmpty() &&
                !btn208.getText().toString().isEmpty();
    }
}