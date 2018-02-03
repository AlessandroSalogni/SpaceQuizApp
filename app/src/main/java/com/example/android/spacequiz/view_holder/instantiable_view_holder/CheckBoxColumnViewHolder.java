package com.example.android.spacequiz.view_holder.instantiable_view_holder;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.spacequiz.ParseData;
import com.example.android.spacequiz.R;
import com.example.android.spacequiz.observer.Observer;
import com.example.android.spacequiz.view_holder.superclass_view_holder.CheckBoxViewHolder;

/**
 * @author Alessandro
 */

public class CheckBoxColumnViewHolder extends CheckBoxViewHolder {
    public CheckBoxColumnViewHolder(ViewGroup parent, ParseData parseData, Observer obs) {
        super((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_check_box_column, parent, false), parseData, obs);
    }
}
