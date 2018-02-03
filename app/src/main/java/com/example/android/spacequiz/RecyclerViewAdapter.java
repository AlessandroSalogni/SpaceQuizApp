package com.example.android.spacequiz;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.android.spacequiz.observer.Observer;
import com.example.android.spacequiz.view_holder.superclass_view_holder.ViewHolder;

import java.util.ArrayList;

/**
 * @author Alessandro
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<String[]> questions;
    private final Observer observer;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(ArrayList<String[]> questions, Observer obs) {
        this.questions = questions;
        observer = obs;
    }

    /* Set the viewType value in the onCreateViewHolder*/
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new ViewHolder
        return (new ParseData(questions.get(viewType), parent, observer)).getViewHolder();
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof ViewHolder)
            ((ViewHolder) viewHolder).loadViewData();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return questions.size();
    }
}