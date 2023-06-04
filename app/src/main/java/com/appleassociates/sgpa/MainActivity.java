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

public class MainActivity extends AppCompatActivity {

    EditText m31 ,m32, m33, m34, m35, m36, m37, m381;
    TextView result;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m31 = findViewById(R.id.editTextNumberDecimal);
        m32 = findViewById(R.id.editTextNumberDecimal2);
        m33 = findViewById(R.id.editTextNumberDecimal3);
        m34 = findViewById(R.id.editTextNumberDecimal4);
        m35 = findViewById(R.id.editTextNumberDecimal5);
        m36 = findViewById(R.id.editTextNumberDecimal6);
        m37 = findViewById(R.id.editTextNumberDecimal7);
        m381 = findViewById(R.id.editTextNumberDecimal8);
        result = findViewById(R.id.resultview);
        show = findViewById(R.id.button);
        show.setOnClickListener(v -> {
            calculate();
            try {
                if (validateFields()) {
                    hideKeyboard();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void calculate() {
        try {
            int math = Integer.parseInt(m31.getText().toString());
            int dsa = Integer.parseInt(m32.getText().toString());
            int ade = Integer.parseInt(m33.getText().toString());
            int coa = Integer.parseInt(m34.getText().toString());
            int java = Integer.parseInt(m35.getText().toString());
            int scr = Integer.parseInt(m36.getText().toString());
            int kbk = Integer.parseInt(m37.getText().toString());
            int mso = Integer.parseInt(m381.getText().toString());

            if (math >= 0 && math <= 100 &&
                    dsa >= 0 && dsa <= 100 &&
                    ade >= 0 && ade <= 100 &&
                    coa >= 0 && coa <= 100 &&
                    java >= 0 && java <= 100 &&
                    scr >= 0 && scr <= 100 &&
                    kbk >= 0 && kbk <= 100 &&
                    mso >= 0 && mso <= 100) {
                double total = (math + dsa + ade + coa + java + scr + kbk + mso) / 8.0;
                result.setText("SGPA: " + total);
            } else {
                Toast.makeText(MainActivity.this, "Please enter valid marks (0-100)", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
            Log.e("NumberFormatException", e.getMessage());
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
    private boolean validateFields() {
        return !m31.getText().toString().isEmpty() &&
                !m32.getText().toString().isEmpty() &&
                !m33.getText().toString().isEmpty() &&
                !m34.getText().toString().isEmpty() &&
                !m35.getText().toString().isEmpty() &&
                !m36.getText().toString().isEmpty() &&
                !m37.getText().toString().isEmpty() &&
                !m381.getText().toString().isEmpty();
    }
}

