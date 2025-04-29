package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView outputText;
    private String currentInput = "";
    private double result = 0;
    private String currentOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputText = findViewById(R.id.outputText);

        if (savedInstanceState != null) {
            currentInput = savedInstanceState.getString("currentInput", "");
            result = savedInstanceState.getDouble("result", 0);
            currentOperation = savedInstanceState.getString("currentOperation", "");
            outputText.setText(currentInput.isEmpty() ? "0" : currentInput);
        }

        findViewById(R.id.button0).setOnClickListener(v -> appendDigit("0"));
        findViewById(R.id.button1).setOnClickListener(v -> appendDigit("1"));
        findViewById(R.id.button2).setOnClickListener(v -> appendDigit("2"));
        findViewById(R.id.button3).setOnClickListener(v -> appendDigit("3"));
        findViewById(R.id.button4).setOnClickListener(v -> appendDigit("4"));
        findViewById(R.id.button5).setOnClickListener(v -> appendDigit("5"));
        findViewById(R.id.button6).setOnClickListener(v -> appendDigit("6"));
        findViewById(R.id.button7).setOnClickListener(v -> appendDigit("7"));
        findViewById(R.id.button8).setOnClickListener(v -> appendDigit("8"));
        findViewById(R.id.button9).setOnClickListener(v -> appendDigit("9"));

        findViewById(R.id.buttonPlus).setOnClickListener(v -> setOperation("+"));
        findViewById(R.id.buttonMinus).setOnClickListener(v -> setOperation("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> setOperation("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> setOperation("/"));
        findViewById(R.id.buttonPercent).setOnClickListener(v -> setOperation("%"));

        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculateResult());

        findViewById(R.id.buttonClear).setOnClickListener(v -> clearInput());

        findViewById(R.id.buttonBackspace).setOnClickListener(v -> backspace());

        findViewById(R.id.buttonDecimal).setOnClickListener(v -> appendDecimal());
    }

    private void appendDigit(String digit) {
        currentInput += digit;
        outputText.setText(currentInput);
    }

    private void appendDecimal() {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            outputText.setText(currentInput);
        }
    }

    private void backspace() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            outputText.setText(currentInput.isEmpty() ? "0" : currentInput);
        }
    }

    private void setOperation(String operation) {
        if (!currentInput.isEmpty()) {
            result = Double.parseDouble(currentInput);
            currentOperation = operation;
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            switch (currentOperation) {
                case "+":
                    result += secondOperand;
                    break;
                case "-":
                    result -= secondOperand;
                    break;
                case "*":
                    result *= secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result /= secondOperand;
                    } else {
                        outputText.setText("Ошибка: деление на 0");
                        return;
                    }
                    break;
                case "%":
                    result = result % secondOperand;
                    break;
            }
            outputText.setText(String.valueOf(result));
            currentInput = "";
            currentOperation = "";
        }
    }

    private void clearInput() {
        currentInput = "";
        result = 0;
        currentOperation = "";
        outputText.setText("0");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentInput", currentInput);
        outState.putDouble("result", result);
        outState.putString("currentOperation", currentOperation);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentInput = savedInstanceState.getString("currentInput", "");
        result = savedInstanceState.getDouble("result", 0);
        currentOperation = savedInstanceState.getString("currentOperation", "");
        outputText.setText(currentInput.isEmpty() ? "0" : currentInput);
    }
}
