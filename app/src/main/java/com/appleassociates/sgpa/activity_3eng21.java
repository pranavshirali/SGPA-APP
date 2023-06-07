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

public class activity_3eng21 extends AppCompatActivity {
    EditText btn31 ,btn32, btn33, btn34, btn35, btn36, btn37, btn381;
    TextView result;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3eng21);

        btn31 = findViewById(R.id.editTextNumberDecimal);
        btn32 = findViewById(R.id.editTextNumberDecimal2);
        btn33 = findViewById(R.id.editTextNumberDecimal3);
        btn34 = findViewById(R.id.editTextNumberDecimal4);
        btn35 = findViewById(R.id.editTextNumberDecimal5);
        btn36 = findViewById(R.id.editTextNumberDecimal6);
        btn37 = findViewById(R.id.editTextNumberDecimal7);
        btn381 = findViewById(R.id.editTextNumberDecimal8);
        result = findViewById(R.id.resultview3);
        show = findViewById(R.id.button3);
        show.setOnClickListener(v -> {
            calculate();
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_3eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_3eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m31 = Integer.parseInt(btn31.getText().toString());
            int m32 = Integer.parseInt(btn32.getText().toString());
            int m33 = Integer.parseInt(btn33.getText().toString());
            int m34 = Integer.parseInt(btn34.getText().toString());
            int m35 = Integer.parseInt(btn35.getText().toString());
            int m36 = Integer.parseInt(btn36.getText().toString());
            int m37 = Integer.parseInt(btn37.getText().toString());
            int m38 = Integer.parseInt(btn381.getText().toString());


            if (m31 >= 0 && m31 <= 100 &&
                    m32 >= 0 && m32 <= 100 &&
                    m33 >= 0 && m33 <= 100 &&
                    m34 >= 0 && m34 <= 100 &&
                    m35 >= 0 && m35 <= 100 &&
                    m36 >= 0 && m36 <= 100 &&
                    m37 >= 0 && m37 <= 100 &&
                    m38 >= 0 && m38 <= 100) {

                // Convert marks to grm33s
                int m31Grade = getGrm33FromMarks(m31);
                int m32Grade = getGrm33FromMarks(m32);
                int m33Grade = getGrm33FromMarks(m33);
                int m34Grade = getGrm33FromMarks(m34);
                int m35Grade = getGrm33FromMarks(m35);
                int m36Grade = getGrm33FromMarks(m36);
                int m37Grade = getGrm33FromMarks(m37);
                int m38Grade = getGrm33FromMarks(m38);

                double total = calculateSGPAGrm33s(m31Grade, m32Grade, m33Grade, m34Grade, m35Grade, m36Grade, m37Grade, m38Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedTotal = decimalFormat.format(total);

                result.setText("SGPA : " + formattedTotal);
            } else {
                Toast.makeText(activity_3eng21.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(activity_3eng21.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
            Log.e("NumberFormatException", e.getMessage());
        }
    }
    private int getGrm33FromMarks(int marks) {
        int grm33;
        // Define the grm33 ranges and corresponding grm33s
        if (marks >= 90 && marks <= 100) {
            grm33 = 10;
        } else if (marks >= 80 && marks < 90) {
            grm33 = 9 ;
        } else if (marks >= 70 && marks < 80) {
            grm33 = 8;
        } else if (marks >= 60 && marks < 70) {
            grm33 = 7;
        } else if (marks >= 50 && marks < 60) {
            grm33 = 6;
        } else if (marks >= 45 && marks < 50) {
            grm33 = 5;
        } else if (marks >= 40 && marks < 45) {
            grm33 = 4;
        } else {
            grm33 = 0;
        }
        return grm33;
    }

    private double calculateSGPAGrm33s(int m31Grade, int m32Grade, int m33Grade, int m34Grade, int m35Grade, int m36Grade, int m37Grade, int m38Grade) {
        // Define the credit points for each subject
        int m31Credits = 3;
        int m32Credits = 4;
        int m33Credits = 4;
        int m34Credits = 3;
        int m35Credits = 1;
        int m36Credits = 1;
        int m37Credits = 1;
        int m38Credits = 1;

        // Calculate the credit points for each subject
        int m31CP = m31Credits * m31Grade;
        int m32CP = m32Credits * m32Grade;
        int m33CP = m33Credits * m33Grade;
        int m34CP = m34Credits * m34Grade;
        int m35CP = m35Credits * m35Grade;
        int m36CP = m36Credits * m36Grade;
        int m37CP = m37Credits * m37Grade;
        int m38CP = m38Credits * m38Grade;

        // Calculate the total credit points and total credits
        int totalCP = m31CP + m32CP + m33CP + m34CP + m35CP + m36CP + m37CP + m38CP;
        int totalCredits = m31Credits + m32Credits + m33Credits + m34Credits + m35Credits + m36Credits + m37Credits + m38Credits;

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
        return !btn31.getText().toString().isEmpty() &&
                !btn32.getText().toString().isEmpty() &&
                !btn33.getText().toString().isEmpty() &&
                !btn34.getText().toString().isEmpty() &&
                !btn35.getText().toString().isEmpty() &&
                !btn36.getText().toString().isEmpty() &&
                !btn37.getText().toString().isEmpty() &&
                !btn381.getText().toString().isEmpty();
    }
}
