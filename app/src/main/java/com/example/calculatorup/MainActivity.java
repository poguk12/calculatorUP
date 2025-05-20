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

        Button button0 = findViewById(R.id.btn_0);
        Button button1 = findViewById(R.id.btn_1);
        Button button2 = findViewById(R.id.btn_2);
        Button button3 = findViewById(R.id.btn_3);
        Button button4 = findViewById(R.id.btn_4);
        Button button5 = findViewById(R.id.btn_5);
        Button button6 = findViewById(R.id.btn_6);
        Button button7 = findViewById(R.id.btn_7);
        Button button8 = findViewById(R.id.btn_8);
        Button button9 = findViewById(R.id.btn_9);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "0";
                    isNewInput = false;
                } else {
                    vvod = vvod + "0";
                }
                updateDisplay();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "1";
                    isNewInput = false;
                } else {
                    vvod = vvod + "1";
                }
                updateDisplay();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "2";
                    isNewInput = false;
                } else {
                    vvod = vvod + "2";
                }
                updateDisplay();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "3";
                    isNewInput = false;
                } else {
                    vvod = vvod + "3";
                }
                updateDisplay();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "4";
                    isNewInput = false;
                } else {
                    vvod = vvod + "4";
                }
                updateDisplay();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "5";
                    isNewInput = false;
                } else {
                    vvod = vvod + "5";
                }
                updateDisplay();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "6";
                    isNewInput = false;
                } else {
                    vvod = vvod + "6";
                }
                updateDisplay();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "7";
                    isNewInput = false;
                } else {
                    vvod = vvod + "7";
                }
                updateDisplay();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "8";
                    isNewInput = false;
                } else {
                    vvod = vvod + "8";
                }
                updateDisplay();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "9";
                    isNewInput = false;
                } else {
                    vvod = vvod + "9";
                }
                updateDisplay();
            }
        });

        Button addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.equals("") == false) {
                    firstOperand = Double.parseDouble(vvod);
                    currentOperator = "+";
                    isNewInput = true;
                }
            }
        });

        Button subtractButton = findViewById(R.id.btn_subtract);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.equals("") == false) {
                    firstOperand = Double.parseDouble(vvod);
                    currentOperator = "-";
                    isNewInput = true;
                }
            }
        });

        Button multiplyButton = findViewById(R.id.btn_multiply);
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.equals("") == false) {
                    firstOperand = Double.parseDouble(vvod);
                    currentOperator = "*";
                    isNewInput = true;
                }
            }
        });

        Button divideButton = findViewById(R.id.btn_divide);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.equals("") == false) {
                    firstOperand = Double.parseDouble(vvod);
                    currentOperator = "/";
                    isNewInput = true;
                }
            }
        });

        Button equalsButton = findViewById(R.id.btn_equals);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentOperator.equals("") == false && isNewInput == false) {
                    double secondNumber = Double.parseDouble(vvod);
                    double result = 0;

                    if (currentOperator.equals("+")) {
                        result = firstOperand + secondNumber;
                    } else if (currentOperator.equals("-")) {
                        result = firstOperand - secondNumber;
                    } else if (currentOperator.equals("*")) {
                        result = firstOperand * secondNumber;
                    } else if (currentOperator.equals("/")) {
                        if (secondNumber == 0) {
                            display.setText("Ошибка");
                            return;
                        } else {
                            result = firstOperand / secondNumber;
                        }
                    }

                    vvod = Double.toString(result);
                    currentOperator = "";
                    isNewInput = true;
                    updateDisplay();
                }
            }
        });

        Button clearButton = findViewById(R.id.btn_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod = "";
                firstOperand = 0;
                currentOperator = "";
                isNewInput = true;
                display.setText("0");
            }
        });

        Button backspaceButton = findViewById(R.id.btn_backspace);
        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.length() > 0) {
                    vvod = vvod.substring(0, vvod.length() - 1);
                    if (vvod.length() == 0) {
                        vvod = "0";
                        isNewInput = true;
                    }
                    updateDisplay();
                }
            }
        });

        Button decimalButton = findViewById(R.id.btn_decimal);
        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput == true) {
                    vvod = "0.";
                    isNewInput = false;
                } else if (vvod.contains(".") == false) {
                    vvod = vvod + ".";
                }
                updateDisplay();
            }
        });

        Button mcButton = findViewById(R.id.btn_memory_clear);
        mcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryValue = 0;
            }
        });

        Button mrButton = findViewById(R.id.btn_memory_recall);
        mrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvod = Double.toString(memoryValue);
                isNewInput = true;
                updateDisplay();
            }
        });

        Button mPlusButton = findViewById(R.id.btn_memory_add);
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.length() > 0) {
                    memoryValue = memoryValue + Double.parseDouble(vvod);
                }
            }
        });

        Button mMinusButton = findViewById(R.id.btn_memory_subtract);
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.length() > 0) {
                    memoryValue = memoryValue - Double.parseDouble(vvod);
                }
            }
        });

        Button sqrtButton = findViewById(R.id.btn_sqrt);
        sqrtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.length() > 0) {
                    double num = Double.parseDouble(vvod);
                    if (num >= 0) {
                        vvod = Double.toString(Math.sqrt(num));
                        isNewInput = true;
                        updateDisplay();
                    } else {
                        display.setText("Ошибка");
                    }
                }
            }
        });

        Button percentButton = findViewById(R.id.btn_percent);
        percentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.length() > 0) {
                    double num = Double.parseDouble(vvod);
                    vvod = Double.toString(num / 100);
                    isNewInput = true;
                    updateDisplay();
                }
            }
        });

        Button reciprocalButton = findViewById(R.id.btn_reciprocal);
        reciprocalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vvod.length() > 0) {
                    double num = Double.parseDouble(vvod);
                    if (num != 0) {
                        vvod = Double.toString(1 / num);
                        isNewInput = true;
                        updateDisplay();
                    } else {
                        display.setText("Ошибка");
                    }
                }
            }
        });
    }

    private void updateDisplay() {
        if (vvod.length() == 0) {
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