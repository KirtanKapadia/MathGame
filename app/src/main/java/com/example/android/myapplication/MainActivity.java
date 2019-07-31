package com.example.android.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void startGame(View view){
        view.setVisibility(View.GONE);


        final TextView Timer = findViewById(R.id.timer);
        Timer.setVisibility(View.VISIBLE);
//        TextView Score = findViewById(R.id.Score);
//        Score.setVisibility(View.VISIBLE);


        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                 Timer.setText(String.valueOf((int)l/1000) + "s");
            }

            @Override
            public void onFinish() {
                Timer.setText("30s");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startButton = findViewById(R.id.startButton);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(view);
            }
        });






    }
}
