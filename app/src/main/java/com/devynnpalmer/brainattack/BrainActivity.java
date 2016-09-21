package com.devynnpalmer.brainattack;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BrainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int totalQuestions = 0;

    public void playAgain(View view) { //resets everything if the user wants to play again

        score = 0;
        totalQuestions = 0;

        timerTextView.setText("30");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) { //creates the timer

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() { //when the time is up

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0");
                resultTextView.setText("YOUR SCORE: " + Integer.toString(score));

            }
        }.start();
    }

    public void generateQuestion() { //creates random question & answers

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b); //correct answer generation

            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41); //incorrect answer generation

                }

                answers.add(incorrectAnswer);

            }
        }
        
        //attaches answers to buttons
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) { //selects the chosen answer

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("CORRECT"); //WINNER!! :)

        } else {

            resultTextView.setText("INCORRECT"); //LOSER!! :(

        }

        totalQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));
        generateQuestion();
    }

    public void start(View view) { //start button

        startButton.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.playAgainButton));
    }
}
