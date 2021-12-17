package com.example.chatprogram;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    EditText chat;
    public String msg;
    TextView clientTv;
    TextView serverTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void SubmitClick(View v){
        chat = (EditText) findViewById(R.id.chat);
        msg = chat.getText().toString();
        chat.setText("");
        if(msg != null){
            Server s = new Server();
            Client c = new Client();
            s.server();
            c.client();
        }
    }
}
