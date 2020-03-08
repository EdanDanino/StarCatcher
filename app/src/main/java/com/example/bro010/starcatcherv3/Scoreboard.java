package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Scoreboard extends AppCompatActivity implements View.OnClickListener {
    Button imBtn;
    PlayerOpenHelper usOpHlp;
    ArrayList<Player> listOfUsers;
    ListView lv;
    PlayerAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        imBtn=(Button)findViewById(R.id.btn);
        imBtn.setOnClickListener(this);
        lv=(ListView)findViewById(R.id.lv);

        usOpHlp=new PlayerOpenHelper(this);
        listOfUsers=new ArrayList<Player>();

        usOpHlp.open();
        listOfUsers=usOpHlp.getAllUsers();
        usOpHlp.close();

        userAdapter=new PlayerAdapter(this,0,listOfUsers);
        lv.setAdapter(userAdapter);

       // lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   // TODO Auto-generated method stub
              //  Player c = userAdapter.getItem(position);
              //  Toast.makeText(getBaseContext(), c.getPlayerName() + " touched", Toast.LENGTH_SHORT).show();

              //  Intent i = new Intent(Scoreboard.this, UpdateUser.class);
              //  i.putExtra("rowId", c.getPlayerid());
             //   startActivityForResult(i, 0);//0--->going to updatescreen

           // }
        }


    @Override
    public void onClick(View v) {

        if(v==imBtn)
        {
            Intent homeActiv=new Intent(Scoreboard.this,MainActivity.class);
            startActivity(homeActiv);
        }

    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
    public void refreshMyAdapter()
    {

        userAdapter=new PlayerAdapter(this,0,listOfUsers);
        lv.setAdapter(userAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            usOpHlp.open();
            listOfUsers = usOpHlp.getAllUsers();
            refreshMyAdapter();
            usOpHlp.close();

            if (requestCode == 0) {
                Toast.makeText(getBaseContext(), "Database updated", Toast.LENGTH_SHORT).show();

            } else if (requestCode == 1) {
                Toast.makeText(getBaseContext(), "New Customer add to database", Toast.LENGTH_SHORT).show();

            }


        }
    }
}
