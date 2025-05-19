package com.example.calculatorup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentInput = "";
    private double memoryValue = 0;
    private String currentOperator = "";
    private double firstOperand = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Цифровые кнопки
        int[] digitButtons = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};

        for (int id : digitButtons) {
            findViewById(id).setOnClickListener(v -> onDigitClick(((Button) v).getText().toString()));
        }

        // Операторы
        findViewById(R.id.btn_add).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> onOperatorClick("×"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> onOperatorClick("÷"));

        // Кнопка равно
        findViewById(R.id.btn_equals).setOnClickListener(v -> onEqualsClick());

        // Кнопка очистки
        findViewById(R.id.btn_clear).setOnClickListener(v -> onClearClick());

        // Кнопка удаления последнего символа
        findViewById(R.id.btn_backspace).setOnClickListener(v -> onBackspaceClick());

        // Кнопка точки
        findViewById(R.id.btn_decimal).setOnClickListener(v -> onDecimalClick());

        // Операции с памятью
        findViewById(R.id.btn_memory_clear).setOnClickListener(v -> onMemoryClear());
        findViewById(R.id.btn_memory_recall).setOnClickListener(v -> onMemoryRecall());
        findViewById(R.id.btn_memory_add).setOnClickListener(v -> onMemoryAdd());
        findViewById(R.id.btn_memory_subtract).setOnClickListener(v -> onMemorySubtract());

        // Специальные операции
        findViewById(R.id.btn_sqrt).setOnClickListener(v -> onSqrtClick());
        findViewById(R.id.btn_percent).setOnClickListener(v -> onPercentClick());
        findViewById(R.id.btn_reciprocal).setOnClickListener(v -> onReciprocalClick());
    }

    private void onDigitClick(String digit) {
        if (isNewInput) {
            currentInput = digit;
            isNewInput = false;
        } else {
            currentInput += digit;
        }
        updateDisplay();
    }

    private void onOperatorClick(String operator) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            currentOperator = operator;
            isNewInput = true;
        }
    }

    private void onEqualsClick() {
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
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "÷":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            currentInput = String.valueOf(result);
            currentOperator = "";
            isNewInput = true;
            updateDisplay();
        }
    }

    private void onClearClick() {
        currentInput = "";
        firstOperand = 0;
        currentOperator = "";
        isNewInput = true;
        display.setText("0");
    }

    private void onBackspaceClick() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            if (currentInput.isEmpty()) {
                currentInput = "0";
                isNewInput = true;
            }
            updateDisplay();
        }
    }

    private void onDecimalClick() {
        if (isNewInput) {
            currentInput = "0.";
            isNewInput = false;
        } else if (!currentInput.contains(".")) {
            currentInput += ".";
        }
        updateDisplay();
    }

    private void onMemoryClear() {
        memoryValue = 0;
    }

    private void onMemoryRecall() {
        currentInput = String.valueOf(memoryValue);
        isNewInput = true;
        updateDisplay();
    }

    private void onMemoryAdd() {
        if (!currentInput.isEmpty()) {
            memoryValue += Double.parseDouble(currentInput);
        }
    }

    private void onMemorySubtract() {
        if (!currentInput.isEmpty()) {
            memoryValue -= Double.parseDouble(currentInput);
        }
    }

    private void onSqrtClick() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            if (value >= 0) {
                currentInput = String.valueOf(Math.sqrt(value));
                isNewInput = true;
                updateDisplay();
            } else {
                display.setText("Error");
            }
        }
    }

    private void onPercentClick() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            currentInput = String.valueOf(value / 100);
            isNewInput = true;
            updateDisplay();
        }
    }

    private void onReciprocalClick() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            if (value != 0) {
                currentInput = String.valueOf(1 / value);
                isNewInput = true;
                updateDisplay();
            } else {
                display.setText("Error");
            }
        }
    }

    private void updateDisplay() {
        if (currentInput.isEmpty()) {
            display.setText("0");
        } else {
            // Удаляем .0 для целых чисел
            if (currentInput.endsWith(".0")) {
                display.setText(currentInput.substring(0, currentInput.length() - 2));
            } else {
                display.setText(currentInput);
            }
        }
    }
}