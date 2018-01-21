package com.mobility.dictwilson.memoryplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main7Activity extends AppCompatActivity {

    Button button28, button30;
    EditText lastname, firstname, email, birthdate;
    private FirebaseAuth mAuth;
    DatabaseReference rootRef,lastnameRef, firstRef, firstnameRef, emailRef, emailaddRef, birthRef, birthdateRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        button28 = (Button) findViewById(R.id.button28);
        button30 = (Button) findViewById(R.id.button30);

        lastname = (EditText)findViewById(R.id.editText);
        firstname = (EditText)findViewById(R.id.editText2);
        email = (EditText)findViewById(R.id.editText3);
        birthdate = (EditText)findViewById(R.id.editText5);

        rootRef = FirebaseDatabase.getInstance().getReference();
        lastnameRef = rootRef.child("lastname");
        rootRef = FirebaseDatabase.getInstance().getReference();
        firstnameRef = rootRef.child("firstname");
        rootRef = FirebaseDatabase.getInstance().getReference();
        emailaddRef = rootRef.child("emailaddress");
        rootRef = FirebaseDatabase.getInstance().getReference();
        birthdateRef = rootRef.child("birthdate");

        button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lastnames = lastname.toString();
                lastnameRef.push().setValue(lastnames);
                String firstnames = firstname.toString();
                firstnameRef.push().setValue(firstnames);
                String emails = email.toString();
                emailaddRef.push().setValue(emails);
                String birthdates = birthdate.toString();
                birthdateRef.push().setValue(birthdates);
                Toast.makeText(Main7Activity.this, "Account Registered!", Toast.LENGTH_LONG).show();
            }
        });

        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main7Activity.this,MainActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }


}
