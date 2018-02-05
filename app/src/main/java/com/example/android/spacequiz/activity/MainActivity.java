package com.example.android.spacequiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.android.spacequiz.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String USERNAME = "Username extra message";

    private EditText usernameEditText;
    private Map<Integer, Class<? extends AppCompatActivity>> strategy = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username_edit_text);

        Intent showScoreActivityCall = getIntent();
        usernameEditText.setText(showScoreActivityCall.getStringExtra(ShowScoreActivity.USERNAME));

        strategy.put(R.id.question_category_1, SolarSystemActivity.class);
        strategy.put(R.id.question_category_2, SpaceMissionsActivity.class);
        strategy.put(R.id.question_category_3, SolarSystemActivity.class);

        findViewById(R.id.question_category_1).setOnClickListener(this);
        findViewById(R.id.question_category_2).setOnClickListener(this);
        findViewById(R.id.question_category_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent newQuizActivityCall = new Intent(this, strategy.get(view.getId()));
        newQuizActivityCall.putExtra(USERNAME, usernameEditText.getText().toString());
        startActivity(newQuizActivityCall);
    }
}
