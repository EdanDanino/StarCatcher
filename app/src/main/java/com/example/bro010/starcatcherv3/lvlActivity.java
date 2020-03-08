package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class lvlActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnNormal,btnHard,btnlvl2,btnlvl3;
    Button rtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl);
        btnlvl2=(ImageButton)findViewById(R.id.btnlvl2);
        btnlvl3=(ImageButton)findViewById(R.id.btnlvl3);
        btnNormal=(ImageButton)findViewById(R.id.btnNormal);
        btnHard=(ImageButton)findViewById(R.id.btnHard);
        rtn=(Button)findViewById(R.id.rtn);
        btnHard.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnlvl2.setOnClickListener(this);
        btnlvl3.setOnClickListener(this);
        rtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(btnNormal==v)
        {   stopService(new Intent(this,MyService.class));
            Intent i = new Intent(lvlActivity.this, PlayerSignIn.class);
            startActivity(i);

        }
        if(btnHard==v)
        {   stopService(new Intent(this,MyService.class));
            Intent i = new Intent(lvlActivity.this, PlayerSignInHard.class);
            startActivity(i);
        }
        if(btnlvl2==v)
        {   stopService(new Intent(this,MyService.class));
            Intent i = new Intent(lvlActivity.this, PlayerSignInlvl2.class);
            startActivity(i);
        }
        if(rtn==v)
        {
            Intent i = new Intent(lvlActivity.this, MainActivity.class);
            startActivity(i);
        }
      //  if(btnlvl3==v)
       // {
      //      Intent i = new Intent(lvlActivity.this, GameActivitylvl3.class);
      //      startActivity(i);
     //   }

    }
}
