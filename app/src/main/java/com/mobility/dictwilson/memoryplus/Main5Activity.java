package com.mobility.dictwilson.memoryplus;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Main5Activity extends AppCompatActivity {

    Button button13, button14, button15, button16, button17, button19;

    String buttonColor1, buttonColor2, buttonColor3, buttonColor4;

    Random r;

    int score = 0, scores = 1000;

    private FirebaseAuth mAuth;
    DatabaseReference rootRef,scoresRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        rootRef = FirebaseDatabase.getInstance().getReference();
        scoresRef = rootRef.child("scores4");

        String s4a = Integer.toString(score);
        scoresRef.push().setValue(s4a);

        button13 = (Button)findViewById(R.id.button13);
        button14 = (Button)findViewById(R.id.button14);
        button15 = (Button)findViewById(R.id.button15);
        button16 = (Button)findViewById(R.id.button16);
        button17 = (Button)findViewById(R.id.button17);
        button19 = (Button)findViewById(R.id.button19);

        r = new Random();
        int temp;

        temp = r.nextInt(3) + 1;
        if(temp == 1){
            buttonColor1 = "red";
        }else if (temp == 2){
            buttonColor1 = "green";
         }else if (temp == 3){
            buttonColor1 = "blue";
        }

        temp = r.nextInt(3) + 1;
        if(temp == 1){
            buttonColor2 = "red";
        }else if (temp == 2){
            buttonColor2 = "green";
        }else if (temp == 3){
            buttonColor2 = "blue";
        }

        temp = r.nextInt(3) + 1;
        if(temp == 1){
            buttonColor3 = "red";
        }else if (temp == 2){
            buttonColor3 = "green";
        }else if (temp == 3){
            buttonColor3 = "blue";
        }

        temp = r.nextInt(3) + 1;
        if(temp == 1){
            buttonColor4 = "red";
        }else if (temp == 2){
            buttonColor4 = "green";
        }else if (temp == 3){
            buttonColor4 = "blue";
        }

        applyColor(button13, buttonColor1);
        applyColor(button14, buttonColor2);
        applyColor(button15, buttonColor3);
        applyColor(button16, buttonColor4);

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor1 = changeColor(buttonColor1);
                applyColor(button13, buttonColor1);

                buttonColor2 = changeColor(buttonColor2);
                applyColor(button14, buttonColor2);

                checkForWin();
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor1 = changeColor(buttonColor1);
                applyColor(button13, buttonColor1);

                buttonColor2 = changeColor(buttonColor2);
                applyColor(button14, buttonColor2);

                buttonColor3 = changeColor(buttonColor3);
                applyColor(button15, buttonColor3);

                checkForWin();
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor2 = changeColor(buttonColor2);
                applyColor(button14, buttonColor2);

                buttonColor3 = changeColor(buttonColor3);
                applyColor(button15, buttonColor3);

                buttonColor4 = changeColor(buttonColor4);
                applyColor(button16, buttonColor4);

                checkForWin();
            }
        });

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor3 = changeColor(buttonColor3);
                applyColor(button15, buttonColor3);

                buttonColor4 = changeColor(buttonColor4);
                applyColor(button16, buttonColor4);

                checkForWin();
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
                startActivity(intent);
                finish();
            }
        });

        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //score = 0;
                //String s4c = Integer.toString(score);
                //scoresRef.push().setValue(s4c);
                startActivity(new Intent(Main5Activity.this,Main6Activity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    public void checkForWin(){
        score++;
        scores--;

        int result = Math.round ((scores / 100) * 10);

        if(buttonColor1.equals("green")&&
                buttonColor2.equals("green") &&
                buttonColor3.equals("green") &&
                buttonColor4.equals("green")){
            Toast.makeText(Main5Activity.this, "You got it in " + score + " moves! Score: " + result, Toast.LENGTH_LONG).show();

            String s4b = Integer.toString(scores);
            scoresRef.push().setValue(s4b);

            button13.setEnabled(false);
            button14.setEnabled(false);
            button15.setEnabled(false);
            button16.setEnabled(false);

        } else if(buttonColor1.equals("red")&&
                buttonColor2.equals("red") &&
                buttonColor3.equals("red") &&
                buttonColor4.equals("red")){
            Toast.makeText(Main5Activity.this, "You got it in " + score + " moves! Score: " + result, Toast.LENGTH_LONG).show();

            String s4b = Integer.toString(scores);
            scoresRef.push().setValue(s4b);

            button13.setEnabled(false);
            button14.setEnabled(false);
            button15.setEnabled(false);
            button16.setEnabled(false);

        } else if(buttonColor1.equals("blue")&&
                buttonColor2.equals("blue") &&
                buttonColor3.equals("blue") &&
                buttonColor4.equals("blue")){
            Toast.makeText(Main5Activity.this, "You got it in " + score + " moves! Score: " + result, Toast.LENGTH_LONG).show();

            String s4b = Integer.toString(scores);
            scoresRef.push().setValue(s4b);

            button13.setEnabled(false);
            button14.setEnabled(false);
            button15.setEnabled(false);
            button16.setEnabled(false);

        }
    }

    public String changeColor(String color){
        if(color.equals("red")){
            color = "green";
        }else if(color.equals("green")) {
            color = "blue";
        }else if(color.equals("blue")) {
            color = "red";
        }
        return color;
    }

    public void applyColor(Button button, String colour){
        if(colour.equals("red")){
            button.setBackgroundColor(Color.RED);
        }else if(colour.equals("green")){
            button.setBackgroundColor(Color.GREEN);
        }else if(colour.equals("blue")){
            button.setBackgroundColor(Color.BLUE);
        }
    }
}
