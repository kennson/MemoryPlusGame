package com.mobility.dictwilson.memoryplus;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

public class Main8Activity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0 ;
    TextView textView19, textView20, textView21, textView22, textView23;
    EditText MO;
    Button send, back;
    String phoneNo;
    String all_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        textView19 = (TextView) findViewById(R.id.textView19);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView21 = (TextView) findViewById(R.id.textView21);
        textView22 = (TextView) findViewById(R.id.textView22);
        textView23 = (TextView) findViewById(R.id.textView23);
        MO = (EditText) findViewById(R.id.editText6);
        send = (Button) findViewById(R.id.button23);
        back = (Button) findViewById(R.id.button24);

        Intent intent = getIntent();

        String lname = intent.getStringExtra("LName");
        textView19.setText("Lastname: " + lname);
        String score1 = intent.getStringExtra("Score1");
        textView20.setText("Recall the Dots " + score1);
        String score2 = intent.getStringExtra("Score2");
        textView21.setText("Guess the Word" + score2);
        String score3 = intent.getStringExtra("Score3");
        textView22.setText("Guess a Number" + score3);
        String score4 = intent.getStringExtra("Score4");
        textView23.setText("Match the Color" + score4);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMSMessage();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main8Activity.this, Main6Activity.class));
            }
        });
    }

    protected void sendSMSMessage() {
        phoneNo = MO.getText().toString();
        all_data = "Memory Plus Player Name: " + textView19.getText().toString() + " Recall the Dots: " +
                textView20.getText().toString() + " Guess the Word: " +
                textView21.getText().toString()+ " Guess a Number: " +
                textView22.getText().toString() + " Match the Color: " +
                textView23.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, all_data, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS Failed, Please Try Again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}
