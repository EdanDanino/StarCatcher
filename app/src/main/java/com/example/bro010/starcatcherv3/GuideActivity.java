package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgV,imgV2,imgV3,imgV4;
    Button btnRtn3,btnGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        btnRtn3 = (Button) findViewById(R.id.btnRtn3);
        btnRtn3.setOnClickListener(this);
        btnGame = (Button) findViewById(R.id.btnGame);
        btnGame.setOnClickListener(this);
        imgV = (ImageView) findViewById(R.id.imgV);
        imgV2 = (ImageView) findViewById(R.id.imgV2);
        imgV3 = (ImageView) findViewById(R.id.imgV3);
        imgV4 = (ImageView) findViewById(R.id.imgV4);

    }

    @Override
    public void onClick(View v) {
        if (btnRtn3 == v) {
            Intent i = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(i);

        }
        if (btnGame == v) {
            Intent i = new Intent(GuideActivity.this, lvlActivity.class);
            startActivity(i);
            stopService(new Intent(this,MyService.class));
        }
    }
}