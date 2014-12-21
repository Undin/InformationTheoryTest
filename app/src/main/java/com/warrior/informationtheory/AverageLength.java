package com.warrior.informationtheory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by warrior on 19.12.14.
 */
public class AverageLength extends Activity {

    private FractionList fractionList;
    private TextView huffman;
    private TextView shennon;
    private TextView gilbert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.average_length);

        fractionList  = (FractionList) findViewById(R.id.fractions);
        huffman = (TextView) findViewById(R.id.huffman);
        shennon = (TextView) findViewById(R.id.shennon);
        gilbert = (TextView) findViewById(R.id.gilbert);

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
                Map<String, String> huffmanCode = Utils.huffman(probabilities);
                Log.d("huffman", Utils.codeToString(symbols, huffmanCode));
                Fraction avgLength = Utils.averageLength(probabilities, huffmanCode);
                huffman.setText("huffman. AvgLength: " + Utils.fractionToString(avgLength));
                Map<String, String> shennonCode = Utils.shennon(probabilities);
                Log.d("shennon", Utils.codeToString(symbols, shennonCode));
                avgLength = Utils.averageLength(probabilities, shennonCode);
                shennon.setText("shennon. AvgLength: " + Utils.fractionToString(avgLength));
                Map<String, String> gilbertCode = Utils.gilbert(probabilities);
                Log.d("gilbert", Utils.codeToString(symbols, gilbertCode));
                avgLength = Utils.averageLength(probabilities, gilbertCode);
                gilbert.setText("gilbert. AvgLength: " + Utils.fractionToString(avgLength));
            }
        });
    }
}
