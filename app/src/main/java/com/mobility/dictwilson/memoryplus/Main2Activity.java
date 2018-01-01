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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    TextView textview_info, textview_word;
    EditText edittext_guess;
    Button button_check, button_news, button_nexts, button_quits;
    Random r;
    String currentWord;
    String[] dictionary = {
            "one","two","three","four","five","six","seven","eight","nine","ten"};
    private FirebaseAuth mAuth;
    DatabaseReference rootRef,scoresRef;
    int score = 100, tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textview_info = (TextView) findViewById(R.id.textview_info);
        textview_word = (TextView) findViewById(R.id.textview_word);
        edittext_guess = (EditText) findViewById(R.id.edittext_guess);
        button_check = (Button) findViewById(R.id.button_check);
        button_news = (Button) findViewById(R.id.button_news);
        button_nexts = (Button)findViewById(R.id.button_nexts);
        button_quits = (Button)findViewById(R.id.button_quits);
        r = new Random();

        rootRef = FirebaseDatabase.getInstance().getReference();
        scoresRef = rootRef.child("scores2");

        newsGame();

        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittext_guess.getText().toString().equalsIgnoreCase(currentWord)){
                    textview_info.setText("Awesome!");
                    button_check.setEnabled(false);
                    button_news.setEnabled(true);
                    tries++;
                    Toast.makeText(Main2Activity.this, "Success! You got it in " + tries + " moves! Score: " + score , Toast.LENGTH_SHORT).show();

                    String s2 = Integer.toString(score);
                    scoresRef.push().setValue(s2);

                }else{
                    tries++;
                    score--;
                    textview_info.setText("Try Again!");
                    Toast.makeText(Main2Activity.this, "Try Again!", Toast.LENGTH_SHORT).show();

                    String s2 = Integer.toString(score);
                    scoresRef.push().setValue(s2);
                }
            }
        });

        button_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsGame();
            }
        });

        button_nexts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
            }
        });

        button_quits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();
                finishAffinity();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private String shuffleWord(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

    private void newsGame(){
        currentWord = dictionary[r.nextInt(dictionary.length)];
        textview_word.setText(shuffleWord(currentWord));
        edittext_guess.setText("");
        button_news.setEnabled(false);
        button_check.setEnabled(true);
        textview_info.setText("Guess the Word");
    }
}

