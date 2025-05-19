package com.example.calculatorup;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.calculatorup.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;

    Button btnMplus;
    Button btnMminus;
    Button btnMC;
    Button btnMR;

    Button btnProsent;
    Button btnDelete;
    Button btnC;
    Button btnAC;

    Button btnKvadrat;
    Button btnProsent1;
    Button btnKoren;
    Button btnDelenie;


    Button btnPlusMinus;
    Button btnDote;
    Button btnRavno;

    Button btnAdd;
    Button btnSubtract;
    Button btnMultiply;

    private EditText display;
    private TextView memoryIndicator;
    private String currentInput = "";
    private double memoryValue = 0;
    private boolean hasMemory = false;
    private String currentOperator = "";
    private double firstOperand = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 =  findViewById(R.id.btn_zero);
        btn1 =  findViewById(R.id.btn_one);
        btn2 =  findViewById(R.id.btn_two);
        btn3 =  findViewById(R.id.btn_three);
        btn4 =  findViewById(R.id.btn_four);
        btn5 =  findViewById(R.id.btn_five);
        btn6 =  findViewById(R.id.btn_six);
        btn7 =  findViewById(R.id.btn_seven);
        btn8 =  findViewById(R.id.btn_eight);
        btn9 =  findViewById(R.id.btn_nine);

        btnMplus = findViewById(R.id.btn_memory_add);
        btnMminus = findViewById(R.id.btn_memory_subtract);
        btnMC = findViewById(R.id.btn_memory_clear);
        btnMR = findViewById(R.id.btn_memory_recall);

        btnProsent = findViewById(R.id.btn_percent);
        btnDelete = findViewById(R.id.btn_backspace);
        btnC = findViewById(R.id.btn_clear);
        btnAC = findViewById(R.id.btn_clear_entry);

        btnKvadrat = findViewById(R.id.btn_power);
        btnProsent1 = findViewById(R.id.btn_square_root);
        btnKoren = findViewById(R.id.btn_reciprocal);
        btnDelenie = findViewById(R.id.btn_divide);

        btnPlusMinus = findViewById(R.id.btn_negate);
        btnDote = findViewById(R.id.btn_decimal);
        btnRavno = findViewById(R.id.btn_equals);

        // Initialize additional buttons from layout
        btnAdd = findViewById(R.id.btn_add);
        btnSubtract = findViewById(R.id.btn_subtract);
        btnMultiply = findViewById(R.id.btn_multiply);

        // Decimal point
        btnDote.setOnClickListener(v -> {
            if (isNewInput) {
                currentInput = "0.";
                isNewInput = false;
            } else if (!currentInput.contains(".")) {
                currentInput += ".";
            }
            updateDisplay();
        });

        // Basic operations
        View.OnClickListener operationListener = v -> {
            Button button = (Button) v;
            if (!currentInput.isEmpty()) {
                firstOperand = Double.parseDouble(currentInput);
            }
            currentOperator = button.getText().toString();
            isNewInput = true;
        };

        btnAdd.setOnClickListener(operationListener);
        btnSubtract.setOnClickListener(operationListener);
        btnMultiply.setOnClickListener(operationListener);
        btnDelenie.setOnClickListener(operationListener);

        // Equals button
        btnRavno.setOnClickListener(v -> {
            if (!currentOperator.isEmpty() && !isNewInput) {
                double secondOperand = Double.parseDouble(currentInput);
                double result = 0;

                switch (currentOperator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        result = firstOperand / secondOperand;
                        break;
                }

                currentInput = String.valueOf(result);
                updateDisplay();
                isNewInput = true;
                currentOperator = "";
            }
        });

        // Clear buttons
        btnC.setOnClickListener(v -> {
            currentInput = "";
            updateDisplay();
        });

        btnAC.setOnClickListener(v -> {
            currentInput = "";
            firstOperand = 0;
            currentOperator = "";
            updateDisplay();
        });

        // Memory functions
        btnMplus.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                memoryValue += Double.parseDouble(currentInput);
                hasMemory = true;
                updateMemoryIndicator();
            }
        });

        btnMminus.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                memoryValue -= Double.parseDouble(currentInput);
                hasMemory = true;
                updateMemoryIndicator();
            }
        });

        btnMC.setOnClickListener(v -> {
            memoryValue = 0;
            hasMemory = false;
            updateMemoryIndicator();
        });

        btnMR.setOnClickListener(v -> {
            if (hasMemory) {
                currentInput = String.valueOf(memoryValue);
                updateDisplay();
                isNewInput = true;
            }
        });

        // Additional functions
        btnPlusMinus.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                value = -value;
                currentInput = String.valueOf(value);
                updateDisplay();
            }
        });

        btnKvadrat.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                value = value * value;
                currentInput = String.valueOf(value);
                updateDisplay();
                isNewInput = true;
            }
        });

        btnProsent1.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                value = Math.sqrt(value);
                currentInput = String.valueOf(value);
                updateDisplay();
                isNewInput = true;
            }
        });

        btnKoren.setOnClickListener(v -> {
            if (!currentInput.isEmpty() && !currentInput.equals("0")) {
                double value = Double.parseDouble(currentInput);
                value = 1 / value;
                currentInput = String.valueOf(value);
                updateDisplay();
                isNewInput = true;
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (currentInput.length() > 0) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                if (currentInput.isEmpty()) {
                    currentInput = "0";
                    isNewInput = true;
                }
                updateDisplay();
            }
        });

        btnProsent.setOnClickListener(v -> {
            if (!currentInput.isEmpty() && firstOperand != 0) {
                double value = Double.parseDouble(currentInput);
                value = firstOperand * value / 100;
                currentInput = String.valueOf(value);
                updateDisplay();
                isNewInput = true;
            }
        });
    }

    private void updateDisplay() {
        display.setText(currentInput);
    }

    private void updateMemoryIndicator() {
        memoryIndicator.setText(hasMemory ? "M" : "");
    }
}