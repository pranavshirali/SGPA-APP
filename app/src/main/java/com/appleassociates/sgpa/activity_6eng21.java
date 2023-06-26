package com.appleassociates.sgpa;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;

public class activity_6eng21 extends AppCompatActivity {

    EditText btn61 ,btn62, btn63, btn64, btn65, btn66, btn67, btn68;
    TextView result6;
    Toolbar startingTextView;
    Button show;
    ImageView deleteIcon;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6eng21);


        btn61 = findViewById(R.id.editTextNumberDecimal);
        btn62 = findViewById(R.id.editTextNumberDecimal2);
        btn63 = findViewById(R.id.editTextNumberDecimal3);
        btn64 = findViewById(R.id.editTextNumberDecimal4);
        btn65 = findViewById(R.id.editTextNumberDecimal5);
        btn66 = findViewById(R.id.editTextNumberDecimal6);
        btn67 = findViewById(R.id.editTextNumberDecimal7);
        btn68 = findViewById(R.id.editTextNumberDecimal8);
        show = findViewById(R.id.button6);
        result6 = findViewById(R.id.resultview6);
        deleteIcon = findViewById(R.id.deleteicon);

        startingTextView = findViewById(R.id.textView);
        startingTextView.setTitleTextAppearance(this, R.style.ToolbarTitleTextAppearance);
        startingTextView.setTitleTextColor(ContextCompat.getColor(startingTextView.getContext(), R.color.white));
        Intent intent = getIntent();
        String selectOption = intent.getStringExtra("selectedOption");
        startingTextView.setTitle("2021 SCHEME / 7TH SEM / " + getString(R.string.selected_option) + selectOption);
        deleteIcon.setOnClickListener(v -> {
            btn61.setText("");
            btn62.setText("");
            btn63.setText("");
            btn64.setText("");
            btn65.setText("");
            btn66.setText("");
            btn67.setText("");
            btn68.setText("");
            result6.setText("");
        });

        show.setOnClickListener(v -> {
            hideKeyboard();
            calculate();
/*        try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(activity_6eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                // Reset text color and hint text color to default black
            }
            catch (NumberFormatException e) {
                Toast.makeText(activity_6eng21.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }*/
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int m61 = 0, m62 = 0, m63 = 0, m64 = 0, m65 = 0, m66 = 0, m67 = 0, m68 = 0;
            if (!TextUtils.isEmpty(btn61.getText().toString()))
                m61 = Integer.parseInt(btn61.getText().toString());

            if (!TextUtils.isEmpty(btn62.getText().toString()))
                m62 = Integer.parseInt(btn62.getText().toString());

            if (!TextUtils.isEmpty(btn63.getText().toString()))
                m63 = Integer.parseInt(btn63.getText().toString());

            if (!TextUtils.isEmpty(btn64.getText().toString()))
                m64 = Integer.parseInt(btn64.getText().toString());

            if (!TextUtils.isEmpty(btn65.getText().toString()))
                m65 = Integer.parseInt(btn65.getText().toString());

            if (!TextUtils.isEmpty(btn66.getText().toString()))
                m66 = Integer.parseInt(btn66.getText().toString());

            if (!TextUtils.isEmpty(btn67.getText().toString()))
                m67 = Integer.parseInt(btn67.getText().toString());

            if (!TextUtils.isEmpty(btn68.getText().toString()))
                m68 = Integer.parseInt(btn68.getText().toString());

            boolean isValid = true; // Flag to track if all marks are valid

            if (m61 < 0 || m61 > 100 || TextUtils.isEmpty(btn61.getText().toString())) {
                setEditTextError(btn61);
                isValid = false;
            }
            if (m62 < 0 || m62 > 100 || TextUtils.isEmpty(btn62.getText().toString())) {
                setEditTextError(btn62);
                isValid = false;
            }
            if (m63 < 0 || m63 > 100 || TextUtils.isEmpty(btn63.getText().toString())) {
                setEditTextError(btn63);
                isValid = false;
            }
            if (m64 < 0 || m64 > 100 || btn64.getText().toString().isEmpty()) {
                setEditTextError(btn64);
                isValid = false;
            }
            if (m65 < 0 || m65 > 100 || btn65.getText().toString().isEmpty()) {
                setEditTextError(btn65);
                isValid = false;
            }
            if (m66 < 0 || m66 > 100 || btn66.getText().toString().isEmpty()) {
                setEditTextError(btn66);
                isValid = false;
            }
            if (m67 < 0 || m67 > 100 || btn67.getText().toString().isEmpty()) {
                setEditTextError(btn67);
                isValid = false;
            }
            if (m68 < 0 || m68 > 100 || btn68.getText().toString().isEmpty()) {
                setEditTextError(btn68);
                isValid = false;
            }

            if (isValid) {
                // All marks are valid, continue with the calculation
                // ...
                int m61Grade = getGradeFromMarks(m61);
                int m62Grade = getGradeFromMarks(m62);
                int m63Grade = getGradeFromMarks(m63);
                int m64Grade = getGradeFromMarks(m64);
                int m65Grade = getGradeFromMarks(m65);
                int m66Grade = getGradeFromMarks(m66);
                int m67Grade = getGradeFromMarks(m67);
                int m68Grade = getGradeFromMarks(m68);

                double total = calculateSGPAGrades(m61Grade, m62Grade, m63Grade, m64Grade, m65Grade, m66Grade, m67Grade, m68Grade);

                // Rounds off (total) to 2 decimal places into formattedTotal
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedTotal = decimalFormat.format(total);

                result6.setText("SGPA : " + formattedTotal);
            } else {
                Toast.makeText(activity_6eng21.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
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

    private double calculateSGPAGrades(int m61Grade, int m62Grade, int m63Grade, int m64Grade, int m65Grade, int m66Grade, int m67Grade, int m68Grade) {
        // Define the credit points for each subject
        int m61Credits = 3;
        int m62Credits = 4;
        int m63Credits = 3;
        int m64Credits = 3;
        int m65Credits = 3;
        int m66Credits = 2;
        int m67Credits = 2;
        int m68Credits = 3;

        // Calculate the credit points for each subject
        int m61CP = m61Credits * m61Grade;
        int m62CP = m62Credits * m62Grade;
        int m63CP = m63Credits * m63Grade;
        int m64CP = m64Credits * m64Grade;
        int m65CP = m65Credits * m65Grade;
        int m66CP = m66Credits * m66Grade;
        int m67CP = m66Credits * m67Grade;
        int m68CP = m68Credits * m68Grade;

        // Calculate the total credit points and total credits
        int totalCP = m61CP + m62CP + m63CP + m64CP + m65CP + m66CP + m67CP + m68CP ;
        int totalCredits = m61Credits + m62Credits + m63Credits + m64Credits + m65Credits + m66Credits + m67Credits + m68Credits;

        // Calculate the SGPA
        return (double) totalCP / totalCredits;
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm;
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /*    private boolean validateFields() {
            return !btn61.getText().toString().isEmpty() &&
                    !btn62.getText().toString().isEmpty() &&
                    !btn63.getText().toString().isEmpty() &&
                    !btn64.getText().toString().isEmpty() &&
                    !btn65.getText().toString().isEmpty() &&
                    !btn66.getText().toString().isEmpty() &&
                    !btn67.getText().toString().isEmpty() &&
                    !btn68.getText().toString().isEmpty();
        }*/
    private void setEditTextError(EditText editText) {
        /* editText.setTextColor(getResources().getColor(R.color.red));*/
        ObjectAnimator animator = ObjectAnimator.ofFloat(editText, "translationX", -20, 20, -20, 20, 0);
        animator.setDuration(500);
        animator.start();
    }

}