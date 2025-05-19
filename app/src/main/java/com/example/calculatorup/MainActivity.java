package com.example.calculatorup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String vvod = "";
    private double memoryValue = 0;
    private String currentOperator = "";
    private double firstOperand = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        Button btn0 = findViewById(R.id.btn_0);
        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Button btn7 = findViewById(R.id.btn_7);
        Button btn8 = findViewById(R.id.btn_8);
        Button btn9 = findViewById(R.id.btn_9);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "0";
                updateDisplay();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "1";
                updateDisplay();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "2";
                updateDisplay();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "3";
                updateDisplay();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "4";
                updateDisplay();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "5";
                updateDisplay();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "6";
                updateDisplay();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "7";
                updateDisplay();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "8";
                updateDisplay();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod += "9";
                updateDisplay();
            }
        });



        findViewById(R.id.btn_add).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> onOperatorClick("*"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> onOperatorClick("/"));

        findViewById(R.id.btn_equals).setOnClickListener(v -> onEqualsClick());

        findViewById(R.id.btn_clear).setOnClickListener(v -> onClearClick());

        findViewById(R.id.btn_backspace).setOnClickListener(v -> onBackspaceClick());

        findViewById(R.id.btn_decimal).setOnClickListener(v -> onDecimalClick());

        findViewById(R.id.btn_memory_clear).setOnClickListener(v -> onMemoryClear());
        findViewById(R.id.btn_memory_recall).setOnClickListener(v -> onMemoryRecall());
        findViewById(R.id.btn_memory_add).setOnClickListener(v -> onMemoryAdd());
        findViewById(R.id.btn_memory_subtract).setOnClickListener(v -> onMemorySubtract());

        findViewById(R.id.btn_sqrt).setOnClickListener(v -> onSqrtClick());
        findViewById(R.id.btn_percent).setOnClickListener(v -> onPercentClick());
        findViewById(R.id.btn_reciprocal).setOnClickListener(v -> onReciprocalClick());
    }

    private void onDigitClick(String digit) {
        if (isNewInput) {
            vvod = digit;
            isNewInput = false;
        } else {
            vvod += digit;
        }
        updateDisplay();
    }

    private void onOperatorClick(String operator) {
        if (!vvod.isEmpty()) {
            firstOperand = Double.parseDouble(vvod);
            currentOperator = operator;
            isNewInput = true;
        }
    }

    private void onEqualsClick() {
        if (!currentOperator.isEmpty() && !isNewInput) {
            double secondOperand = Double.parseDouble(vvod);
            double resultat = 0;

            switch (currentOperator) {
                case "+":
                    resultat = firstOperand + secondOperand;
                    break;
                case "-":
                    resultat = firstOperand - secondOperand;
                    break;
                case "*":
                    resultat = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        resultat = firstOperand / secondOperand;
                    } else {
                        display.setText("Ошибка");
                        return;
                    }
                    break;
            }

            vvod = String.valueOf(resultat);
            currentOperator = "";
            isNewInput = true;
            updateDisplay();
        }
    }

    private void onClearClick() {
        vvod = "";
        firstOperand = 0;
        currentOperator = "";
        isNewInput = true;
        display.setText("0");
    }

    private void onBackspaceClick() {
        if (!vvod.isEmpty()) {
            vvod = vvod.substring(0, vvod.length() - 1);
            if (vvod.isEmpty()) {
                vvod = "0";
                isNewInput = true;
            }
            updateDisplay();
        }
    }

    private void onDecimalClick() {
        if (isNewInput) {
            vvod = "0.";
            isNewInput = false;
        } else if (!vvod.contains(".")) {
            vvod += ".";
        }
        updateDisplay();
    }

    private void onMemoryClear() {
        memoryValue = 0;
    }

    private void onMemoryRecall() {
        vvod = String.valueOf(memoryValue);
        isNewInput = true;
        updateDisplay();
    }

    private void onMemoryAdd() {
        if (!vvod.isEmpty()) {
            memoryValue += Double.parseDouble(vvod);
        }
    }

    private void onMemorySubtract() {
        if (!vvod.isEmpty()) {
            memoryValue -= Double.parseDouble(vvod);
        }
    }

    private void onSqrtClick() {
        if (!vvod.isEmpty()) {
            double value = Double.parseDouble(vvod);
            if (value >= 0) {
                vvod = String.valueOf(Math.sqrt(value));
                isNewInput = true;
                updateDisplay();
            } else {
                display.setText("Ошибка");
            }
        }
    }

    private void onPercentClick() {
        if (!vvod.isEmpty()) {
            double value = Double.parseDouble(vvod);
            vvod = String.valueOf(value / 100);
            isNewInput = true;
            updateDisplay();
        }
    }

    private void onReciprocalClick() {
        if (!vvod.isEmpty()) {
            double value = Double.parseDouble(vvod);
            if (value != 0) {
                vvod = String.valueOf(1 / value);
                isNewInput = true;
                updateDisplay();
            } else {
                display.setText("Ошибка");
            }
        }
    }

    private void updateDisplay() {
        if (vvod.isEmpty()) {
            display.setText("0");
        } else {
            if (vvod.endsWith(".0")) {
                display.setText(vvod.substring(0, vvod.length() - 2));
            } else {
                display.setText(vvod);
            }
        }
    }
}