package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mp;
    Button btnRtn8,button8542651;
    boolean music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btnRtn8=(Button)findViewById(R.id.btnRtn8);
        btnRtn8.setOnClickListener(this);
        button8542651=(Button)findViewById(R.id.button8542651);
        button8542651.setOnClickListener(this);
            mp = MediaPlayer.create(getApplicationContext(), R.raw.over);

            mp.setLooping(true);
            mp.start();
        }

    @Override
    protected void onPause()

    {
        super.onPause();
        mp.stop();
        mp.release();

    }
    @Override
    public void onClick(View v) {
        if(btnRtn8==v)
        {
            Intent i =new Intent(ResultActivity.this,MainActivity.class);
            startActivity(i);

        }


        if(button8542651==v)
        {
            Intent i =new Intent(ResultActivity.this,Scoreboard.class);
            startActivity(i);
        }

    }
}
