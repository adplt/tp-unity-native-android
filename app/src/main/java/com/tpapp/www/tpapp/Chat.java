package com.tpapp.www.tpapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.HashMap;

public class Chat extends AppCompatActivity{

    private TextView tv;
    private Session sm;
    private HashMap<String,String> user,data;
    private EditText et;
    private RelativeLayout rl;
    private Firebase fb;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_24);
        toolbar.setLogo(R.drawable.ic_logo_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        sm = new Session(Chat.this);
        user = sm.getLogin();
        data = sm.getTPList();

        Firebase.setAndroidContext(Chat.this);

        String url = "https://tpunity.firebaseio.com/";
        url = url.replaceAll(" ","%20");
        System.out.println(url);

        fb = new Firebase("https://tpunity.firebaseio.com/");
        fb.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                String new_condition = (String) dataSnapshot.getValue();
                //reply.setText(new_condition);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError){}
        });

        et = (EditText) findViewById(R.id.message);
        rl = (RelativeLayout) findViewById(R.id.send);
        tv = (TextView) findViewById(R.id.chat_name);

        et.setTextColor(getResources().getColor(R.color.black_background));
        tv.setText(data.get(Session.TP_NAME));
        rl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String message = String.valueOf(et.getText());
                fb.setValue(message);
                et.setText(null);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        et = (EditText) findViewById(R.id.message);
        rl = (RelativeLayout) findViewById(R.id.send);
        tv = (TextView) findViewById(R.id.chat_name);
    }

}