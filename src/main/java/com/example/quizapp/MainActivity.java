package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.databinding.ActivityMainBindingImpl;
import com.example.quizapp.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TESTING123";
    private ActivityMainBinding binding;
    private int questionIndex = 0;

    private Question[] questionBank = new Question[]{
        new Question(R.string.question_amendments, false),
        new Question(R.string.question_constitution, true),
        new Question(R.string.question_waterfall, false),
        new Question(R.string.question_capitial, true),
        new Question(R.string.question_declaration, true)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // setup data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.questionBoxTextView.setText(questionBank[questionIndex].getAnswerRedId());

        binding.nextButton.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: " + questionIndex);
            questionIndex += 1;
            if (questionIndex < questionBank.length) {
                binding.questionBoxTextView.setText(questionBank[questionIndex].getAnswerRedId());
            } else {
                Toast.makeText(this, R.string.warning1, Toast.LENGTH_LONG).show();
                questionIndex = questionBank.length-1;
            }


        });

        binding.prevButton.setOnClickListener(v -> {
            questionIndex -= 1;
            if (questionIndex >= 0) {
                binding.questionBoxTextView.setText(questionBank[questionIndex].getAnswerRedId());
            } else {
                Toast.makeText(this, R.string.warning2, Toast.LENGTH_LONG).show();
                questionIndex = 0;
            }
        });

        binding.trueButton.setOnClickListener(this::onClick);
        binding.falseButton.setOnClickListener(this::onClick2);
        
    }

    private void onClick2(View view) {
        if (!questionBank[questionIndex].isAnswerTrue()) {
            Snackbar.make(view, "Correct!", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(view, "Incorrect :(", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void onClick(View v) {
        if (questionBank[questionIndex].isAnswerTrue()) {
            Snackbar.make(v, "Correct!", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(v, "Incorrect :(", Snackbar.LENGTH_SHORT).show();
        }
    }
}