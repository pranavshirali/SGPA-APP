package com.appleassociates.sgpa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        show.setOnClickListener(view -> calculate());
    }
    @SuppressLint("SetTextI18n")
    private void calculate(){
        int math = Integer.parseInt(m31.getText().toString());
        int dsa =  Integer.parseInt(m32.getText().toString());
        int ade =  Integer.parseInt(m33.getText().toString());
        int coa =  Integer.parseInt(m34.getText().toString());
        int java =  Integer.parseInt(m35.getText().toString());
        int scr = Integer.parseInt(m36.getText().toString());
        int kbk = Integer.parseInt(m37.getText().toString());
        int mso = Integer.parseInt(m381.getText().toString());
        int g31, g32, g33, g34, g35, g36, g37, g381;
        float total;


    }
}