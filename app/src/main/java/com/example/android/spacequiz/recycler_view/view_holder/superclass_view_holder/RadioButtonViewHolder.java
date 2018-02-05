package com.example.android.spacequiz.recycler_view.view_holder.superclass_view_holder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.android.spacequiz.parse_data.ParseData;
import com.example.android.spacequiz.observer.Observer;

import java.util.ArrayList;

/**
 * @author Alessandro
 */

public abstract class RadioButtonViewHolder extends ViewHolder implements CompoundButton.OnCheckedChangeListener {
    private final int maxAnswers;
    private final RadioButton[] answers;

    protected RadioButtonViewHolder(CardView cardView, ParseData parseData, Observer obs, int maxAnswers) {
        super(cardView, parseData, obs);

        this.maxAnswers = maxAnswers;
        answers = new RadioButton[maxAnswers];

        for(int i=0; i < maxAnswers; i++)
            answers[i] = cardView.findViewById(((AppCompatActivity) obs).getResources().getIdentifier("question_answer_" + (i + 1), "id", ((AppCompatActivity) obs).getPackageName()));

        int correctAnswerPosition = parseData.getCorrectAnswerPosition().get(0);
        answers[correctAnswerPosition].setOnCheckedChangeListener(this);
    }

    @Override
    public void loadViewData() {
        super.loadViewData();

        ArrayList<String> answers = parseData.getAnswers();
        for (int i = 0; i < maxAnswers; i++)
            this.answers[i].setText(answers.get(i));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        isCorrect = isChecked;
        notifyObserver();
    }
}
