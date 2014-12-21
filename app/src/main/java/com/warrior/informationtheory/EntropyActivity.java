package com.warrior.informationtheory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.math3.fraction.Fraction;

import java.util.List;


public class EntropyActivity extends Activity {

    private FractionList fractionList;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entropy);

        fractionList = (FractionList) findViewById(R.id.fractions);
        Button calculate = (Button) findViewById(R.id.calculate);
        result = (TextView) findViewById(R.id.result);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Fraction> fractions = fractionList.getFractions();

                Fraction entropy = null;
                try {
                    entropy = Utils.binaryEntropy(fractions);
                } catch (IllegalArgumentException e) {
                }
                if (entropy != null) {
                    String res = Utils.fractionToString(entropy);
                    result.setText("entropy: " + res);
                } else {
                    try {
                        result.setText("entropy: " + Utils.entropy(fractions));
                    } catch (IllegalArgumentException e) {
                        result.setText("entropy: " + getResources().getString(R.string.error));
                    }
                }
            }
        });
    }
}
