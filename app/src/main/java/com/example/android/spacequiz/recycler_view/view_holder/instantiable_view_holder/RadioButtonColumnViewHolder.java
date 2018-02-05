package com.example.android.spacequiz.recycler_view.view_holder.instantiable_view_holder;

import android.support.v7.widget.CardView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.spacequiz.parse_data.ParseData;
import com.example.android.spacequiz.R;
import com.example.android.spacequiz.observer.Observer;
import com.example.android.spacequiz.recycler_view.view_holder.superclass_view_holder.RadioButtonViewHolder;

/**
 * @author Alessandro
 */
public class RadioButtonColumnViewHolder extends RadioButtonViewHolder {
    private static final int MAX_ANSWER = 4;
    private static final int CORRECT_ANSWER_POINTS = 3;

    public RadioButtonColumnViewHolder(ViewGroup parent, ParseData parseData, Observer obs) {
        super((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_radio_button_column, parent, false), parseData, obs, MAX_ANSWER);
    }

    @Override
    public int getAnswerPoints() {
        return (isCorrect) ? CORRECT_ANSWER_POINTS : 0;
    }

    @Override
    public int getMaxPoint() {
        return CORRECT_ANSWER_POINTS;
    }
}
