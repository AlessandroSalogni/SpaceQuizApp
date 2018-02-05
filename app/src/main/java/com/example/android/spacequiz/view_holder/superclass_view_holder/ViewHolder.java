package com.example.android.spacequiz.view_holder.superclass_view_holder;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.spacequiz.ParseData;
import com.example.android.spacequiz.R;
import com.example.android.spacequiz.observer.Observable;
import com.example.android.spacequiz.observer.Observer;

/**
 * @author Alessandro
 */
public abstract class ViewHolder extends RecyclerView.ViewHolder implements Observable {
    protected final ParseData parseData;
    private final ImageView image;
    private final TextView question;

    private Observer observer;
    protected boolean isCorrect = false;

    protected ViewHolder(CardView cardView, ParseData parseData, Observer obs) {
        super(cardView);
        this.parseData = parseData;

        if(!(obs instanceof AppCompatActivity))
            throw new IllegalArgumentException("Error: Observer also must implements AppCompatActivity");

        addObserver(obs);
        image = cardView.findViewById(R.id.question_photo);
        question = cardView.findViewById(R.id.question_text);
    }

    public void loadViewData() {
        Drawable image = parseData.getImage((AppCompatActivity) observer);
        if((image != null))
            this.image.setImageDrawable(image);

        question.setText(parseData.getQuestion());
    }

    public abstract int getMaxPoint();

    @Override
    public void addObserver(Observer obs) {
        observer = obs;
    }

    @Override
    public void removeObserver(Observer obs) {
        observer = null;
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }
}
