package com.warrior.informationtheory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by warrior on 19.12.14.
 */
public class ArithmeticDecodeActivity extends Activity {

    private FractionList fractionList;
    private EditText fValue;
    private TextView decodingResult;
    private EditText len;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arithmetic_decode);

        fractionList = (FractionList) findViewById(R.id.fractions);
        fValue = (EditText) findViewById(R.id.fValue);
        len = (EditText) findViewById(R.id.len);
        decodingResult = (TextView) findViewById(R.id.decoding_result);

        findViewById(R.id.calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Fraction> fractions = fractionList.getFractions();
                List<String> symbols = new ArrayList<>(fractions.size());
                LinkedHashMap<String, Fraction> probabilities = new LinkedHashMap<>();
                for (int i = 0; i < fractions.size(); i++) {
                    String symbol = String.valueOf((char) (i + 'a'));
                    symbols.add(symbol);
                    probabilities.put(symbol, fractions.get(i));
                }

                String str = fValue.getText().toString();
                int lenValue = Integer.parseInt(len.getText().toString());
                String result = null;
                if (str != null) {
                    try {
                        result = Utils.arithmeticDecoding(probabilities, str, lenValue);
                    } catch (Exception e) {
                    }
                }
                if (result != null) {
                    decodingResult.setText(result);
                } else {
                    decodingResult.setText(R.string.error);
                }
            }
        });
    }
}
