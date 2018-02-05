package com.example.android.spacequiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.android.spacequiz.R;
import com.example.android.spacequiz.recycler_view.RecyclerViewAdapter;
import com.example.android.spacequiz.observer.Observable;
import com.example.android.spacequiz.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SolarSystemActivity extends AppCompatActivity implements Observer, View.OnClickListener {
    private RecyclerViewAdapter recyclerViewAdapter;
    private Map<Observable, Integer> answerPoints = new HashMap<>();
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system);

        Intent mainActivityCall = getIntent();
        username = mainActivityCall.getStringExtra(MainActivity.USERNAME);

        /* Add listener to the send button */
        Button sendButton = findViewById(R.id.solar_system_question_send_button);
        sendButton.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.solar_system_question_recycler_view);

        /*LayoutManger to manage position of the different elements*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        int spaceQuestionNumber = Integer.parseInt(getResources().getString(R.string.solar_system_question_number));
        ArrayList<String[]> questions = new ArrayList<>();

        for(int i = 0; i < spaceQuestionNumber; i++)
            questions.add(getResources().getStringArray(getResources().getIdentifier("solar_system_question_" + (i+1), "array", getPackageName())));

        /* RecyclerViewAdapter to create all elements */
        recyclerViewAdapter = new RecyclerViewAdapter(questions, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void update(Observable observable) {
        answerPoints.put(observable, observable.getAnswerPoints());
    }

    @Override
    public void onClick(View view) {
        int score = 0;

        for(int points : answerPoints.values())
            score += points;

        Intent newShowScoreActivityCall = new Intent(this, ShowScoreActivity.class);
        newShowScoreActivityCall.putExtra(ShowScoreActivity.SCORE, score);
        newShowScoreActivityCall.putExtra(ShowScoreActivity.MAX_SCORE, recyclerViewAdapter.getQuizTotalScore());
        newShowScoreActivityCall.putExtra(ShowScoreActivity.USERNAME, username);
        startActivity(newShowScoreActivityCall);
    }
}

