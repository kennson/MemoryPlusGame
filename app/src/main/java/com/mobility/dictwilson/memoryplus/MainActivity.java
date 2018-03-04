package com.mobility.dictwilson.memoryplus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button_new, button_next;
    Button button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button10, button11, button12;
    List<Integer> buttons;
    int curLevel, curGuess;
    private FirebaseAuth mAuth;
    DatabaseReference rootRef,scoresRef;
    int tries = 0, score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootRef = FirebaseDatabase.getInstance().getReference();
        scoresRef = rootRef.child("scores1");

        button_new = (Button)findViewById(R.id.button_new);
        button_next = (Button)findViewById(R.id.button_next);
        button_next.setVisibility(View.INVISIBLE);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        button10 = (Button)findViewById(R.id.button10);
        button11 = (Button)findViewById(R.id.button11);
        button12 = (Button)findViewById(R.id.button12);

        button1.setTag(1);
        button2.setTag(2);
        button3.setTag(3);
        button4.setTag(4);
        button5.setTag(5);
        button6.setTag(6);
        button7.setTag(7);
        button8.setTag(8);
        button9.setTag(9);
        button10.setTag(10);
        button11.setTag(11);
        button12.setTag(12);

        disableButtons();

        buttons = new ArrayList<>();
        buttons.add(1);
        buttons.add(2);
        buttons.add(3);
        buttons.add(4);
        buttons.add(5);
        buttons.add(6);
        buttons.add(7);
        buttons.add(8);
        buttons.add(9);
        buttons.add(10);
        buttons.add(11);
        buttons.add(12);

        button_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_new.setVisibility(View.INVISIBLE);
                button_next.setVisibility(View.INVISIBLE);
                curGuess = 0;
                curLevel = 1;
                generateButtons(curLevel);
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //score = 0;
                //String s1 = Integer.toString(score);
                //scoresRef.push().setValue(s1);
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonsLogic(view);
                ((Button) view).setText("O");
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void buttonsLogic(View v){
        List<Integer> tempList = new ArrayList<>();
        for(int i = 0; i < curLevel; i++){
            tempList.add((buttons.get(i)));
        }

        if(tempList.contains(v.getTag())){
            curGuess++;
            checkWin();
        }else {
            lostGame();
        }
    }

    private void checkWin() {
        tries++;

        if(curGuess == curLevel){
            disableButtons();
            if(curLevel == 12){
                Toast.makeText(MainActivity.this, "Wow! Your score is: " + score, Toast.LENGTH_LONG).show();
                button_new.setVisibility(View.VISIBLE);
                button_next.setVisibility(View.VISIBLE);

                String s1 = Integer.toString(score);
                scoresRef.push().setValue(s1);

            } else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        curGuess = 0;
                        curLevel++;
                        generateButtons(curLevel);
                    }
                },1000);
            }
        }
    }

    private void lostGame() {
        score = 90;
        Toast.makeText(MainActivity.this, "Wow! Your score is: " + score, Toast.LENGTH_LONG).show();
        disableButtons();
        button_new.setVisibility(View.VISIBLE);
        button_next.setVisibility(View.VISIBLE);

        String s1 = Integer.toString(score);
        scoresRef.push().setValue(s1);
    }

    private void generateButtons(int number){
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");

        Collections.shuffle(buttons);

        for (int i = 0; i < number; i++){
            showButton(buttons.get(i));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button9.setText("");
                button10.setText("");
                button11.setText("");
                button12.setText("");

                enableButtons();
            }
        }, 1000);
    }

    private void showButton(int number){
        switch (number){
            case 1:
                button1.setText("O");
                break;
            case 2:
                button2.setText("O");
                break;
            case 3:
                button3.setText("O");
                break;
            case 4:
                button4.setText("O");
                break;
            case 5:
                button5.setText("O");
                break;
            case 6:
                button6.setText("O");
                break;
            case 7:
                button7.setText("O");
                break;
            case 8:
                button8.setText("O");
                break;
            case 9:
                button9.setText("O");
                break;
            case 10:
                button10.setText("O");
                break;
            case 11:
                button11.setText("O");
                break;
            case 12:
                button12.setText("O");
                break;
        }
    }

    private void enableButtons(){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        button10.setEnabled(true);
        button11.setEnabled(true);
        button12.setEnabled(true);
    }

    private void disableButtons(){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        button10.setEnabled(false);
        button11.setEnabled(false);
        button12.setEnabled(false);
    }

}
