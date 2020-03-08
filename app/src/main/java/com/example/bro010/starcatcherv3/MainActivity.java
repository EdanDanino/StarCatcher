package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    String flag2;
    RadioGroup music;
    RadioButton musicon,musicoff;
    Button btn,btnScore,btnGuide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
        btnScore=(Button)findViewById(R.id.btnScore);
        btnScore.setOnClickListener(this);
        btnGuide=(Button)findViewById(R.id.btnGuide);
        btnGuide.setOnClickListener(this);
        music=(RadioGroup) findViewById(R.id.musicswitch);
        music.setOnClickListener(this);
        musicoff=(RadioButton) findViewById(R.id.musicoff);
        musicoff.setOnClickListener(this);
        musicon=(RadioButton) findViewById(R.id.musicon);
        musicon.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {

        if(btn==v) {

            Intent i3 = new Intent(MainActivity.this, lvlActivity.class);
            startActivity(i3);


        }


        if(btnGuide==v)
        {
            Intent inte=new Intent(MainActivity.this,GuideActivity.class);
            startActivity(inte);


        }

        if(btnScore==v)
        {
            Intent i =new Intent(MainActivity.this,Scoreboard.class);
            startActivity(i);
            i.putExtra("music",flag2);
        }



    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(musicon.isChecked()){
            startService(new Intent(this,MyService.class));
             flag2="true";
        }
        else
        {
            stopService(new Intent(this,MyService.class));
            flag2="false";
        }
    }
}

