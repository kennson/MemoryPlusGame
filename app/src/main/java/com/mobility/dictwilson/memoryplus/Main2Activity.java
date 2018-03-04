package com.mobility.dictwilson.memoryplus;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    TextView textview_info, textview_word;
    EditText edittext_guess;
    Button button_check, button_news, button_nexts, button_shuffle;
    Random r;
    String currentWord;

    //String[] dict1 = {"able","club","does"};
    //String[] dict2 = {"about","below","cause"};
    //String[] dict3 = {"abroad","belong","carbon"};


    private FirebaseAuth mAuth;
    DatabaseReference rootRef,scoresRef, dict1Ref, tvWord, tvWord2, tvWord3;
    int score = 100, tries = 0, round = 3, shuffle = 2;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

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
        button_shuffle = (Button)findViewById(R.id.button_shuffle);
        r = new Random();

        rootRef = FirebaseDatabase.getInstance().getReference();
        scoresRef = rootRef.child("scores2");
        button_nexts.setVisibility(View.INVISIBLE);



        newsGame();

        Intent intent = new Intent(Main2Activity.this, ShakeService.class);
        startService(intent);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        button_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shuffle==0){
                    Toast.makeText(Main2Activity.this, "Shuffle consumed!", Toast.LENGTH_LONG).show();
                }else {
                    textview_word.setText(shuffleWord(currentWord));
                    shuffle--;
                }

            }
        });


        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittext_guess.getText().toString().equalsIgnoreCase(currentWord)){
                    textview_info.setText("Awesome!");
                    button_check.setEnabled(false);
                    button_news.setEnabled(true);
                    tries++;
                    Toast.makeText(Main2Activity.this, "Success! Your score is: " + score , Toast.LENGTH_LONG).show();
                    button_nexts.setVisibility(View.VISIBLE);
                    String s2 = Integer.toString(score);
                    scoresRef.push().setValue(s2);


                    round++;
                    if (round==4) {
                        newsGame2();
                    }else if (round==5){
                        newsGame3();
                    }

                }else{
                    tries++;
                    score--;
                    textview_info.setText("Try Again!");
                    Toast.makeText(Main2Activity.this, "Try Again!", Toast.LENGTH_LONG).show();
                    //button_nexts.setVisibility(View.VISIBLE);
                    button_news.setVisibility(View.VISIBLE);
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
                //score = 0;
                //String s2 = Integer.toString(score);
                //scoresRef.push().setValue(s2);
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
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

        edittext_guess.setText("");
        button_news.setVisibility(View.INVISIBLE);
        button_check.setEnabled(true);
        textview_info.setText("Guess the Word");
        tvWord = FirebaseDatabase.getInstance().getReference().child("dictionary1");
        tvWord.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Random random = new Random();
                    int index = random.nextInt((int) dataSnapshot.getChildrenCount());
                    int count = 0;
                    for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                        if (count == index) {
                        String tvw = postSnapshot.getValue(String.class);
                        String dicts1 = tvw.toString();
                            textview_word.setText(shuffleWord(dicts1));
                            currentWord = dicts1;
                            return;
                        }
                        count++;
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void newsGame2(){
        shuffle=2;
        //currentWord = dict2[r.nextInt(dict2.length)];
        //textview_word.setText(shuffleWord(currentWord));
        edittext_guess.setText("");
        button_news.setVisibility(View.INVISIBLE);
        button_check.setEnabled(true);
        textview_info.setText("Guess the Word");
        tvWord2 = FirebaseDatabase.getInstance().getReference().child("dictionary2");
        tvWord2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Random random = new Random();
                    int index = random.nextInt((int) dataSnapshot.getChildrenCount());
                    int count = 0;
                    for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                        if (count == index) {
                            String tvw = postSnapshot.getValue(String.class);
                            String dicts1 = tvw.toString();
                            textview_word.setText(shuffleWord(dicts1));
                            currentWord = dicts1;
                            return;
                        }
                        count++;
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void newsGame3(){
        shuffle=2;
        //currentWord = dict3[r.nextInt(dict3.length)];
        //textview_word.setText(shuffleWord(currentWord));
        edittext_guess.setText("");
        button_news.setVisibility(View.INVISIBLE);
        button_check.setEnabled(true);
        textview_info.setText("Guess the Word");
        tvWord3 = FirebaseDatabase.getInstance().getReference().child("dictionary3");
        tvWord3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Random random = new Random();
                    int index = random.nextInt((int) dataSnapshot.getChildrenCount());
                    int count = 0;
                    for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                        if (count == index) {
                            String tvw = postSnapshot.getValue(String.class);
                            String dicts1 = tvw.toString();
                            textview_word.setText(shuffleWord(dicts1));
                            currentWord = dicts1;
                            return;
                        }
                        count++;
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}

