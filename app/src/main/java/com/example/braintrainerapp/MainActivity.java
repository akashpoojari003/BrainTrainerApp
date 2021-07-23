package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.mtp.MtpConstants;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView seconds;
    TextView score;
    TextView question;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;
    int locationOfCorrectAns;
    TextView ans ;
    Button playAgainButton;
    ConstraintLayout game;
    boolean check = true;

    int scored = 0;
    int answered = 0;
    // If user pressed play again
    // It resets everything
    public void playAgain(View view){
        scored = 0;
        answered = 0;
        check = true;
        score.setText("0/0");
        seconds.setText("30s");
        ans.setText("LETS GOO!!!");
        playAgainButton.setVisibility(View.INVISIBLE);
        //Here we will generate question and answer
        generate();
        //Setting time
        new CountDownTimer(30100 , 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                seconds.setText(String.valueOf((millisUntilFinished/1000))+"s");
            }

            @Override
            public void onFinish() {
                check = false;
                seconds.setText("0s");
                //Final score
                ans.setText("Time's Up Your Score was "+Integer.toString(scored)+ " And your attempts are "+Integer.toString(answered));
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    //To check whether user had answered it correctly or not
    public void MyAns(View view){
        if(check) {
            if (Integer.toString(locationOfCorrectAns).equals(view.getTag().toString())) {
                ans.setText("CORRECT!!");
                scored++;
            } else {
                ans.setText("INCORRECT!!!");
            }
            answered++;
            score.setText(Integer.toString(scored) + "/" + Integer.toString(answered));
            generate();
        }
    }

    // This is to generate questions and answers too.
    public void generate(){

            Random random = new Random();
            ArrayList<Integer> arr = new ArrayList<>();
            int a = random.nextInt(21);
            int b = random.nextInt(21);
            question.setText(Integer.toString(a) + " + " + Integer.toString(b));
            locationOfCorrectAns = random.nextInt(4);
            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAns) {
                    arr.add(a + b);
                } else {
                    int wrongAns = random.nextInt(41);
                    while (wrongAns == a + b) {
                        wrongAns = random.nextInt(41);
                    }
                    arr.add(wrongAns);
                }
            }
            ans1.setText(Integer.toString(arr.get(0)));
            ans2.setText(Integer.toString(arr.get(1)));
            ans3.setText(Integer.toString(arr.get(2)));
            ans4.setText(Integer.toString(arr.get(3)));

    }

    public void ClickFun(View view){
        button.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.score));
        game.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        seconds = findViewById(R.id.seconds);
        ans = findViewById(R.id.ansStatus);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        ans1 = findViewById(R.id.button3);
        ans2 = findViewById(R.id.button4);
        ans3 = findViewById(R.id.button5);
        ans4 = findViewById(R.id.button2);
        game = findViewById(R.id.MyConstraint);
        playAgainButton = findViewById(R.id.PlayButton);
        game.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
    }
}