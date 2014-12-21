package com.warrior.informationtheory;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.apache.commons.math3.fraction.Fraction;

/**
 * Created by warrior on 18.12.14.
 */
public class FractionView extends LinearLayout {

    private EditText numerator;
    private EditText denominator;

    public FractionView(Context context) {
        this(context, null);
    }

    public FractionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FractionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        numerator = (EditText) findViewById(R.id.numerator);
        denominator = (EditText) findViewById(R.id.denominator);
    }

    public Fraction getFraction() {
        String numText = numerator.getText().toString();
        String denText = denominator.getText().toString();
        try {
            int num = Integer.parseInt(numText);
            int den = Integer.parseInt(denText);
            return new Fraction(num, den);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EditText getNumerator() {
        return numerator;
    }

    public EditText getDenominator() {
        return denominator;
    }
}
