package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mainBillValueTextInputEditText;
    private TextView mainTipPercentageTextView, mainValueTipTextView, mainValueTotalTextView;
    private SeekBar mainSeekBar;
    private static final int TOTAL_PERCENTAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();
        setupSeekBar();
    }

    private void findIds() {
        mainBillValueTextInputEditText = findViewById(R.id.mainBillValueTextInputEditText);
        mainTipPercentageTextView = findViewById(R.id.mainTipPercentageTextView);
        mainValueTipTextView = findViewById(R.id.mainValueTipTextView);
        mainValueTotalTextView = findViewById(R.id.mainValueTotalTextView);
        mainSeekBar = findViewById(R.id.mainSeekBar);
    }

    private void setupSeekBar() {
        mainSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mainTipPercentageTextView.setText(getString(R.string.percentage, progress));
                calculateAmountToPay(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void calculateAmountToPay(int percentage) {
        String accountRecoveredAmount = mainBillValueTextInputEditText.getText().toString();
        if (accountRecoveredAmount.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.enter_a_value), Toast.LENGTH_LONG).show();
        } else {
            float accountAmount = Float.parseFloat(accountRecoveredAmount);
            float tip = calculateValueTip(accountAmount, percentage);
            calculateValueTotal(tip, accountAmount);
        }
    }

    private void calculateValueTotal(float tip, float accountAmount) {
        float total = tip + accountAmount;
        mainValueTotalTextView.setText(getString(R.string.money_result, total));
    }

    private float calculateValueTip(float accountAmount, int percentage) {
        float tip = accountAmount * ((float) percentage / TOTAL_PERCENTAGE);
        mainValueTipTextView.setText(getString(R.string.money_result, tip));
        return tip;
    }
}