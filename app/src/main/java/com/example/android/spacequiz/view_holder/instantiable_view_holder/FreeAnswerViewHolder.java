package com.example.android.spacequiz.view_holder.instantiable_view_holder;

import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.spacequiz.ParseData;
import com.example.android.spacequiz.R;
import com.example.android.spacequiz.observer.Observer;
import com.example.android.spacequiz.view_holder.superclass_view_holder.ViewHolder;

/**
 * @author Alessandro
 */

public class FreeAnswerViewHolder extends ViewHolder implements TextWatcher {
    private static final int CORRECT_ANSWER_POINTS = 4;

    private final String correctAnswer;
    private final EditText answer;

    public FreeAnswerViewHolder(ViewGroup parent, ParseData parseData, Observer obs) {
        this((CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_free_answer, parent, false), parseData, obs);
    }

    private FreeAnswerViewHolder(CardView cardView, ParseData parseData, Observer obs) {
        super(cardView, parseData, obs);

        answer = cardView.findViewById(R.id.question_free_answer);
        answer.addTextChangedListener(this);
        correctAnswer = parseData.getAnswers().get(0);
    }

    @Override
    public int getAnswerPoints() {
        return (isCorrect) ? CORRECT_ANSWER_POINTS : 0;
    }

    @Override
    public int getMaxPoint() {
        return CORRECT_ANSWER_POINTS;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {
        isCorrect = answer.getText().toString().trim().equals(correctAnswer);
        notifyObserver();
    }
}
