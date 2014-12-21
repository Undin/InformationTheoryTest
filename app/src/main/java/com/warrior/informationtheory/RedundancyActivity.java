package com.warrior.informationtheory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by warrior on 18.12.14.
 */
public class RedundancyActivity extends Activity {

    private FractionList fractionList;

    private TextView redundancy;
    private TextView entropyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redundancy);

        fractionList = (FractionList) findViewById(R.id.fractions);
        entropyResult = (TextView) findViewById(R.id.entropy);
        redundancy = (TextView) findViewById(R.id.redundancy);

        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Fraction> fractions = fractionList.getFractions();
                List<String> symbols = new ArrayList<>(fractions.size());
                Map<String, Fraction> probabilities = new HashMap<>();
                for (int i = 0; i < fractions.size(); i++) {
                    String symbol = String.valueOf((char) (i + 'a'));
                    symbols.add(symbol);
                    probabilities.put(symbol, fractions.get(i));
                }
                Map<String, String> code = Utils.huffman(probabilities);
                Fraction avgLength = Utils.averageLength(probabilities, code);
                Fraction entropy = null;
                try {
                    entropy = Utils.binaryEntropy(fractions);
                } catch (IllegalArgumentException e) {
                }
                if (entropy == null) {
                    entropyResult.setText("entropy: " + getResources().getString(R.string.error));
                    redundancy.setText("redundancy: " + getResources().getString(R.string.error));
                } else {
                    entropyResult.setText("entropy: " + Utils.fractionToString(entropy));
                    redundancy.setText("redundancy: " + Utils.fractionToString(avgLength.subtract(entropy)));
                }
            }
        });
    }
}
