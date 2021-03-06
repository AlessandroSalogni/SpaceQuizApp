package com.example.android.spacequiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.spacequiz.R;
import com.example.android.spacequiz.activity.MainActivity;

public class ShowScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERFECT_SCORE = 90;
    private static final int GOOD_SCORE = 60;
    private static final int OK_SCORE = 30;
    public static final String SCORE = "Score extra message";
    public static final String MAX_SCORE = "Max score extra message";
    public static final String USERNAME = "Username extra message";
    private static final String DESCRIPTION = "Description score extra message";

    private TextView scoreTextView;
    private TextView scoreDescriptionTextView;
    private String username;
    private String score;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(this);

        scoreTextView = findViewById(R.id.score_text);
        scoreDescriptionTextView = findViewById(R.id.score_description_text);

        Intent quizActivityCall = getIntent();
        username = quizActivityCall.getStringExtra(USERNAME);
        int score = quizActivityCall.getIntExtra(SCORE, 0);
        int maxScore = quizActivityCall.getIntExtra(MAX_SCORE, 0);
        float successRate = (float)score/maxScore*100;

        checkScoreValue(score, maxScore);

        scoreTextView.setText(this.score = getResources().getString(R.string.score_text, score, maxScore));
        scoreDescriptionTextView.setText(description = getScoreDescription(username, (int) successRate));

        Toast.makeText(this, "Your score is " + this.score, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence(SCORE, score);
        savedInstanceState.putCharSequence(DESCRIPTION, description);
        savedInstanceState.putCharSequence(USERNAME, username);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreTextView.setText(savedInstanceState.getCharSequence(SCORE));
        scoreDescriptionTextView.setText(savedInstanceState.getCharSequence(DESCRIPTION));
        username = (String) savedInstanceState.getCharSequence(USERNAME);
    }

    private String getScoreDescription(String username, int successRate) {
        if(successRate > PERFECT_SCORE)
           return getResources().getString(R.string.score_description_perfect_result, username);
        else if(successRate > GOOD_SCORE && successRate <= PERFECT_SCORE)
            return getResources().getString(R.string.score_description_good_result, username);
        else if(successRate > OK_SCORE && successRate <= GOOD_SCORE)
            return getResources().getString(R.string.score_description_ok_result, username);
        else
            return getResources().getString(R.string.score_description_bad_result, username);
    }

    private void checkScoreValue(int score, int maxScore) {
        if(maxScore <= 0)
            throw new IllegalArgumentException("Error: Max score must be greater than 0");
        if(maxScore < score)
            throw new IllegalArgumentException("Error: Max score must be greater or equals than score");
        if(score < 0)
            throw new IllegalArgumentException("Error: Score must be greater or equals than 0");
    }

    @Override
    public void onClick(View view) {
        Intent newMainActivityCall = new Intent(this, MainActivity.class);
        newMainActivityCall.putExtra(USERNAME, username);
        startActivity(newMainActivityCall);
    }
}
