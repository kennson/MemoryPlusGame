package com.mobility.dictwilson.memoryplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Main3Activity extends AppCompatActivity {

    private TextView textview_msg;
    private EditText edittext_try;
    private Button button_submit, button_ok, button_nextss;
    private int numberToFind, numberTries;
    public static final int maxNumber = 100;
    public static final Random random = new Random();

    private FirebaseAuth mAuth;
    DatabaseReference rootRef,scoresRef;
    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        rootRef = FirebaseDatabase.getInstance().getReference();
        scoresRef = rootRef.child("scores3");

        textview_msg = (TextView)findViewById(R.id.textview_infos);
        edittext_try = (EditText)findViewById(R.id.edittext_try);
        edittext_try.setVisibility(View.INVISIBLE);
        button_ok = (Button)findViewById(R.id.button_ok);
        button_submit = (Button)findViewById(R.id.button_submit);
        button_nextss = (Button)findViewById(R.id.button_nextss);
        button_submit.setVisibility(View.INVISIBLE);
        button_nextss.setVisibility(View.INVISIBLE);
        newGame();

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_submit.setVisibility(View.VISIBLE);
                button_ok.setVisibility(View.INVISIBLE);
                button_nextss.setVisibility(View.INVISIBLE);
                edittext_try.setVisibility(View.VISIBLE);

            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    validate();
            }
        });

        button_nextss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //score = 0;
                //String s3 = Integer.toString(score);
                //scoresRef.push().setValue(s3);
                startActivity(new Intent(Main3Activity.this,Main5Activity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

  private void validate(){
        int n = Integer.parseInt(edittext_try.getText().toString());
        numberTries++;
        score--;
            if(n == numberToFind){
                Toast.makeText(this, "Congratulation! Your score is: "+ score, Toast.LENGTH_LONG).show();
                edittext_try.setVisibility(View.INVISIBLE);
                String s3 = Integer.toString(score);
                scoresRef.push().setValue(s3);
                button_submit.setVisibility(View.INVISIBLE);
                button_ok.setVisibility(View.VISIBLE);
                button_nextss.setVisibility(View.VISIBLE);
                newGame();
            }else if (n > numberToFind){
                textview_msg.setText(R.string.too_high);
                button_nextss.setVisibility(View.VISIBLE);
            }else if ( n < numberToFind){
                textview_msg.setText(R.string.too_low);
                button_nextss.setVisibility(View.VISIBLE);
            }
  }

  private void newGame(){
      numberToFind = random.nextInt(maxNumber) + 1;
      textview_msg.setText("Guess a Number from 1 to 100");
      edittext_try.setText("0");
      numberTries = 0;
  }
}
