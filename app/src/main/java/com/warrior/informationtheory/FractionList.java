package com.warrior.informationtheory;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warrior on 18.12.14.
 */
public class FractionList extends LinearLayout {

    private final String defaultNumeratorText;
    private final String defaultDenominatorText;

    private List<FractionView> fractionViews = new ArrayList<>();

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            addNewFractionView();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public FractionList(Context context) {
        this(context, null);
    }

    public FractionList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FractionList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FractionList);
        defaultNumeratorText = a.getString(R.styleable.FractionList_default_numerator);
        defaultDenominatorText = a.getString(R.styleable.FractionList_default_denominator);
        setOrientation(HORIZONTAL);
        setDividerDrawable(getResources().getDrawable(R.drawable.divider));
        setShowDividers(SHOW_DIVIDER_MIDDLE);

        addNewFractionView();
    }

    public void addNewFractionView() {
        if (!fractionViews.isEmpty()) {
            FractionView last = fractionViews.get(fractionViews.size() - 1);
            last.getNumerator().removeTextChangedListener(textWatcher);
            last.getDenominator().removeTextChangedListener(textWatcher);
        }
        FractionView view = (FractionView) View.inflate(getContext(), R.layout.fraction, null);
        view.getNumerator().setText(defaultNumeratorText);
        view.getDenominator().setText(defaultDenominatorText);
        view.getNumerator().addTextChangedListener(textWatcher);
        view.getDenominator().addTextChangedListener(textWatcher);
        fractionViews.add(view);
        addView(view);
    }

    public List<Fraction> getFractions() {
        List<Fraction> fractions = new ArrayList<>();
        for (FractionView fractionView : fractionViews) {
            Fraction f = fractionView.getFraction();
            if (f != null) {
                fractions.add(f);
            }
        }
        return fractions;
    }

}
