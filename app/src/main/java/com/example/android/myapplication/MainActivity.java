package com.example.android.myapplication;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    TextView resultTextview;
    TextView sumTextView;
    TextView pointsTextview;
    TextView timerTextview;
    ConstraintLayout secondLayout;

    public void playAgain(View view){
        playAgain.setVisibility(View.INVISIBLE);
        score = 0;
        final String[] timeLeft = new String[1];
        final int[] conv = new int[1];
        timerTextview.setText("30s");
        pointsTextview.setText("0");
        resultTextview.setText("");
        generateQuestions();

        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                conv[0] = (int) l/1000;

                if (conv[0] < 10){
                    timeLeft[0] = "0" + Integer.toString(conv[0]) + "s";
                }
                else{
                    timeLeft[0] = Integer.toString(conv[0]) + "s";
                }
                timerTextview.setText(timeLeft[0]);
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextview.setText("0s");
                resultTextview.setText("Score: " + Integer.toString(score));


            }
        }.start();

    }

    public void start(View view){
        view.setVisibility(View.INVISIBLE);
        secondLayout.setVisibility(View.VISIBLE);
        playAgain(playAgain);
    }

    public void generateQuestions(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        String question = Integer.toString(a) + "+" + Integer.toString(b);
        sumTextView.setText(question);

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        answer.clear();
        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answer.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answer.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }



    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score ++;
            resultTextview.setText("Correct!!!");
        }
        else{
            resultTextview.setText("Wrong!!!");
        }

        pointsTextview.setText(Integer.toString(score));
        generateQuestions();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextview = findViewById(R.id.resultTextView);
        pointsTextview = findViewById(R.id.pointsTextView);
        timerTextview = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.playAgain);
        secondLayout = findViewById(R.id.secondLayout);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(view);
            }
        });

    }
}
