package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerSignInHard extends AppCompatActivity implements View.OnClickListener {

    EditText etPlayerName2;
    Button btnEnterGame2,backToMain2;
    String nameOfThePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_sign_in_hard);

        etPlayerName2=(EditText)findViewById(R.id.etPlayerName2);
        btnEnterGame2=(Button)findViewById(R.id.btnEnterGame2);
        backToMain2=(Button)findViewById(R.id.backToMain2);
        btnEnterGame2.setOnClickListener(this);
        backToMain2.setOnClickListener(this);


        nameOfThePlayer=etPlayerName2.getText().toString();


    }

    @Override
    public void onClick(View v) {
        if(backToMain2==v)
        {
            Intent i = new Intent(PlayerSignInHard.this,MainActivity.class);
            startActivity(i);
        }
        if(btnEnterGame2==v)
        {

            if(btnEnterGame2==v)
            {

                if(etPlayerName2.getText().toString().equals("")||etPlayerName2.getText().toString().equals(null))
                {
                    Toast.makeText( this, "Write your name to proceed", Toast.LENGTH_SHORT).show();
                }
                else {
                    nameOfThePlayer=etPlayerName2.getText().toString()+"(LVL3)";
                    Intent i2 = new Intent(PlayerSignInHard.this,HardGameAcitivity.class);

                    i2.putExtra("nameOfThePlayerHard",nameOfThePlayer);
                    startActivity(i2);}
            }
        }




    }
}