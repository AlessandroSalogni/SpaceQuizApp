package com.example.android.spacequiz.recycler_view.view_holder.instantiable_view_holder;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.spacequiz.parse_data.ParseData;
import com.example.android.spacequiz.R;
import com.example.android.spacequiz.observer.Observer;
import com.example.android.spacequiz.recycler_view.view_holder.superclass_view_holder.CheckBoxViewHolder;

/**
 * @author Alessandro
 */
public class CheckBoxTableViewHolder extends CheckBoxViewHolder {
    private static final int CORRECT_ANSWER_POINTS = 3;
    private static final int PARTIAL_CORRECT_ANSWER_POINTS = 1;

    public CheckBoxTableViewHolder(ViewGroup parent, ParseData parseData, Observer obs) {
        super((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_check_box_table, parent, false), parseData, obs);
    }

    @Override
    public int getAnswerPoints() {
        return (isCorrect) ? CORRECT_ANSWER_POINTS : ((isPartialCorrect) ? PARTIAL_CORRECT_ANSWER_POINTS : 0);
    }

    @Override
    public int getMaxPoint() {
        return CORRECT_ANSWER_POINTS;
    }
}
