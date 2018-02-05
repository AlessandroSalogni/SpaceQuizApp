package com.example.android.spacequiz.recycler_view.view_holder.superclass_view_holder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.android.spacequiz.parse_data.ParseData;
import com.example.android.spacequiz.observer.Observer;

import java.util.ArrayList;

/**
 * @author Alessandro
 */
public abstract class CheckBoxViewHolder extends ViewHolder implements CompoundButton.OnCheckedChangeListener {
    private static final int MAX_ANSWER = 4;

    private final ArrayList<Integer> correctAnswersPosition;
    private final CheckBox[] answers = new CheckBox[MAX_ANSWER];

    protected boolean isPartialCorrect = false;

    protected CheckBoxViewHolder(CardView cardView, ParseData parseData, Observer obs) {
        super(cardView, parseData, obs);

        for(int i=0; i < MAX_ANSWER; i++) {
            answers[i] = cardView.findViewById(((AppCompatActivity) obs).getResources().getIdentifier("question_answer_" + (i + 1), "id", ((AppCompatActivity) obs).getPackageName()));
            answers[i].setOnCheckedChangeListener(this);
        }

        correctAnswersPosition = parseData.getCorrectAnswerPosition();
    }

    @Override
    public void loadViewData() {
        super.loadViewData();

        ArrayList<String> answers = parseData.getAnswers();
        for(int i=0; i < MAX_ANSWER; i++)
            this.answers[i].setText(answers.get(i));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        boolean allCorrect = true;
        boolean partialCorrect = false;

        for(int i=0; i < correctAnswersPosition.size(); i++)
            if(!answers[correctAnswersPosition.get(i)].isChecked())
                allCorrect = false;
            else
                partialCorrect = true;

        for(int i=0; i < MAX_ANSWER; i++)
            if(!correctAnswersPosition.contains(i) && answers[i].isChecked())
                if(allCorrect) {
                    allCorrect = false;
                    partialCorrect = true;
                }


        isCorrect = allCorrect;
        isPartialCorrect = (!allCorrect && partialCorrect);
        notifyObserver();
    }
}
