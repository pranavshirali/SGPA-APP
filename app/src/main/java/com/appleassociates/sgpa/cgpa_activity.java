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

public class cgpa_activity extends AppCompatActivity {

    EditText[] sgpaEditTexts;
    TextView resultTextView;
    Button calculateButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        sgpaEditTexts = new EditText[]{
                findViewById(R.id.editTextNumberDecimal),
                findViewById(R.id.editTextNumberDecimal2),
                findViewById(R.id.editTextNumberDecimal3),
                findViewById(R.id.editTextNumberDecimal4),
                findViewById(R.id.editTextNumberDecimal5),
                findViewById(R.id.editTextNumberDecimal6),
                findViewById(R.id.editTextNumberDecimal7),
                findViewById(R.id.editTextNumberDecimal8)
        };
        resultTextView = findViewById(R.id.resultview6);
        calculateButton = findViewById(R.id.button6);

        calculateButton.setOnClickListener(v -> {
            hideKeyboard();
            calculateCGPA();
            removeHints();
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculateCGPA() {
        int numOfSGPA = 0;
        double totalSGPA = 0.0;

        for (EditText editText : sgpaEditTexts) {
            String sgpaStr = editText.getText().toString().trim();
            if (!sgpaStr.isEmpty()) {
                try {
                    double sgpa = Double.parseDouble(sgpaStr);
                    if (sgpa >= 1 && sgpa <= 10) {
                        totalSGPA += sgpa;
                        numOfSGPA++;
                    } else {
                        Toast.makeText(cgpa_activity.this, "Please enter SGPA between 1 and 10", Toast.LENGTH_SHORT).show();
                        return; // Stop calculation if invalid SGPA is entered
                    }
                } catch (NumberFormatException e) {
                    Log.e("NumberFormatException", e.getMessage());
                }
            }
        }

        if (numOfSGPA > 0) {
            double cgpa = totalSGPA / numOfSGPA;

            // Rounds off (cgpa) to 2 decimal places into formattedCGPA
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedCGPA = decimalFormat.format(cgpa);

            resultTextView.setText("CGPA : " + formattedCGPA);
        } else {
            Toast.makeText(cgpa_activity.this, "Please enter at least one SGPA", Toast.LENGTH_SHORT).show();
        }
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

    private void removeHints() {
        for (EditText editText : sgpaEditTexts) {
            editText.setHint("");
        }
    }
}
