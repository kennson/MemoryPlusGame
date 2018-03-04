package com.mobility.dictwilson.memoryplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main6Activity extends AppCompatActivity {

    Button button18, button20, button21, button22, button23;
    TextView textView2, textView4, textView6, textView8, textView16;

    private FirebaseAuth mAuth;
    DatabaseReference root1Ref, root2Ref, root3Ref, root4Ref, root5Ref, root16Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        button18 = (Button) findViewById(R.id.button18);
        button20 = (Button) findViewById(R.id.button20);
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        button23 = (Button) findViewById(R.id.button23);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView8 = (TextView)findViewById(R.id.textView8);
        textView16 = (TextView)findViewById(R.id.textView16);

        root1Ref = FirebaseDatabase.getInstance().getReference().child("scores1");
        root2Ref = FirebaseDatabase.getInstance().getReference().child("scores2");
        root3Ref = FirebaseDatabase.getInstance().getReference().child("scores3");
        root4Ref = FirebaseDatabase.getInstance().getReference().child("scores4");
        root5Ref = FirebaseDatabase.getInstance().getReference().child("scores4");
        root16Ref = FirebaseDatabase.getInstance().getReference().child("lastname");

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lname = textView16.getText().toString();
                String score1 = textView2.getText().toString();
                String score2 = textView4.getText().toString();
                String score3 = textView6.getText().toString();
                String score4 = textView8.getText().toString();

                Intent intent = new Intent(Main6Activity.this, Main8Activity.class);
                intent.putExtra("LName",lname);
                intent.putExtra("Score1",score1);
                intent.putExtra("Score2",score2);
                intent.putExtra("Score3",score3);
                intent.putExtra("Score4",score4);
                startActivity(intent);
            }
        });

        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root1Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                            String s1 = postSnapshot.getValue(String.class);
                            String score1 = s1.toString();
                            textView2.setText(score1);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                root2Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                            String s2 = postSnapshot.getValue(String.class);
                            String score2 = s2.toString();
                            textView4.setText(score2);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                root3Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                            String s3 = postSnapshot.getValue(String.class);
                            String score3 = s3.toString();
                            textView6.setText(score3);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                root4Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                            String s4 = postSnapshot.getValue(String.class);
                            String score4 = s4.toString();
                            textView8.setText(score4);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                root16Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()) {
                            String s16 = postSnapshot.getValue(String.class);
                            String lastname = s16.toString();
                            textView16.setText(lastname);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finishAffinity();
            }
        });

        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main6Activity.this,MainActivity.class));
            }
        });

        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main6Activity.this,Main9Activity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

}
