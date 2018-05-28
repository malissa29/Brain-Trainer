package com.example.malissafiger.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    int locationofcorrectanswers;
    int score=0;
    TextView ResulttextView;
    TextView PointstextView;
    int numberofQuestions=0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView SumtextView;
    TextView TimertextView;
    Button playagainbutton;
    RelativeLayout gamerelativelayout;

    public void playagain(View view){

        score= 0;
        numberofQuestions= 0;
        TimertextView.setText("30s");
        PointstextView.setText("0/0");
        ResulttextView.setText("");
        playagainbutton.setVisibility(View.INVISIBLE);

        generateQuestions();

        new CountDownTimer(10100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                TimertextView.setText(String.valueOf(millisUntilFinished/1000) + "s");


            }

            @Override
            public void onFinish() {
                playagainbutton.setVisibility(View.VISIBLE);
                TimertextView.setText("0s");
                ResulttextView.setText("Your Score is :" + Integer.toString(score) + "/" + Integer.toString(numberofQuestions));

            }
        }.start();

    }

    public void generateQuestions()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        SumtextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationofcorrectanswers = rand.nextInt(4);

        answers.clear();

        int incorrectanswer;
        for (int i = 0; i < 4; i++) {
            if(i == locationofcorrectanswers)
            {
                answers.add(a + b);
            }
            else {
                incorrectanswer = rand.nextInt(41);
                while (incorrectanswer== a + b){
                    incorrectanswer = rand.nextInt(41);
                }
                answers.add(incorrectanswer);
            }


        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswers))){
            score++;
            ResulttextView.setText("Correct!!");
        }else{
            ResulttextView.setText("Wrong!!");
        }

        numberofQuestions++;
        PointstextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofQuestions));
        generateQuestions();
    }

    public void buttonPlay(View view) {

        startbutton.setVisibility(View.INVISIBLE);
        gamerelativelayout.setVisibility(RelativeLayout.VISIBLE);
        playagain(findViewById(R.id.playagainbutton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton = (Button) findViewById(R.id.startbutton);
        SumtextView = (TextView) findViewById(R.id.SumtextView);
        button0= (Button)findViewById(R.id.button0);
        button1= (Button)findViewById(R.id.button1);
        button2= (Button)findViewById(R.id.button2);
        button3= (Button)findViewById(R.id.button3);
        ResulttextView= (TextView)findViewById(R.id.ResulttextView);
        PointstextView=(TextView)findViewById(R.id.PointstextView);
        TimertextView= (TextView)findViewById(R.id.TimertextView);
        playagainbutton= (Button)findViewById(R.id.playagainbutton);
        gamerelativelayout= (RelativeLayout)findViewById(R.id.gamerelativelayout);
        // Generating Random Numbers






    }
}