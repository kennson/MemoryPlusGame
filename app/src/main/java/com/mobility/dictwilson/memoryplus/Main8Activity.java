package com.mobility.dictwilson.memoryplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main8Activity extends AppCompatActivity {

    SmsManager smsManager;
    TextView textView19, textView20, textView21, textView22, textView23;
    EditText MO;
    Button send, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        textView19 = (TextView)findViewById(R.id.textView19);
        textView20 = (TextView)findViewById(R.id.textView20);
        textView21 = (TextView)findViewById(R.id.textView21);
        textView22 = (TextView)findViewById(R.id.textView22);
        textView23 = (TextView)findViewById(R.id.textView23);
        MO = (EditText)findViewById(R.id.editText6);
        send = (Button)findViewById(R.id.button23);
        back = (Button)findViewById(R.id.button24);

        Intent intent = getIntent();

        String lname = intent.getStringExtra("LName");
        textView19.setText(lname);
        String score1 = intent.getStringExtra("Score1");
        textView20.setText(score1);
        String score2 = intent.getStringExtra("Score2");
        textView21.setText(score2);
        String score3 = intent.getStringExtra("Score3");
        textView22.setText(score3);
        String score4 = intent.getStringExtra("Score4");
        textView23.setText(score4);

        final String all_data = lname + score1 + score2 + score3 + score4;

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(MO.getText().toString(), all_data);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main8Activity.this,Main6Activity.class));
            }
        });
    }

    private void sendMessage(String mo, String message){
        smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(mo, null, message,null,null);
        Toast.makeText(getApplicationContext(), "SMS send", Toast.LENGTH_LONG).show();
    }
}
