package com.example.android.spacequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class SolarSystemActivity extends AppCompatActivity {
    private int totalPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.space_question_recycler_view);

        /*LayoutManger to manage position of the different elements*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<String[]> questions = new ArrayList<>();
        int spaceQuestionNumber = Integer.parseInt(getResources().getString(R.string.space_question_number));

        for(int i=0; i < spaceQuestionNumber; i++)
            questions.add(getResources().getStringArray(getResources().getIdentifier("space_question_" + (i+1), "array", getPackageName())));

//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(questions);
//        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void increasePoint() {
        totalPoint++;
        Toast.makeText(this, totalPoint + "", Toast.LENGTH_SHORT).show();
    }

    public void decreasePoint() {
        totalPoint--;
        Toast.makeText(this, totalPoint + "", Toast.LENGTH_SHORT).show();
    }
}

