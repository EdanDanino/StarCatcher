package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerSignInlvl2 extends AppCompatActivity implements View.OnClickListener {

    EditText etPlayerName;
    Button btnEnterGame,backToMain;

    String nameOfThePlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_sign_in);

        etPlayerName=(EditText)findViewById(R.id.etPlayerName2);
        btnEnterGame=(Button)findViewById(R.id.btnEnterGame2);
        backToMain=(Button)findViewById(R.id.backToMain2);
        btnEnterGame.setOnClickListener(this);
        backToMain.setOnClickListener(this);


        nameOfThePlayer=etPlayerName.getText().toString();


    }
    @Override
    public void onClick(View v) {
        if(backToMain==v)
        {
            Intent i = new Intent(PlayerSignInlvl2.this,MainActivity.class);
            startActivity(i);

        }
        if(etPlayerName.getText().toString().equals("")||etPlayerName.getText().toString().equals(null))
        {
            Toast.makeText( this, "Write your name to proceed", Toast.LENGTH_SHORT).show();
        }
        else {
            nameOfThePlayer=etPlayerName.getText().toString()+"(LVL2)";
            Intent i = new Intent(PlayerSignInlvl2.this,GameActivitylvl2.class);

            i.putExtra("nameOfThePlayerLvl2",nameOfThePlayer);
            startActivity(i);}




    }
}
