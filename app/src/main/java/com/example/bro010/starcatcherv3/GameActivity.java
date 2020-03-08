package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    PlayerOpenHelper poh;
    String music;
    boolean added=true;
    private TextView scoretxt, starttxt,txtLive;
    private ImageView charcter, death, bigpoints, smallpoints,death2,death3;

    // Placement
    private int charaY;
    private int smallpointY1;
    private int smallpointX1;
    private int bigpointX1;
    private int bigpointY1;
    private int deathX1;
    private int deathY1;
    private int death2X1;
    private int death2Y1;
    private int death3X1;
    private int death3Y1;



    //yeshom thread
    private Handler handler=new Handler();
    private Timer timer =new Timer();

    //Status check
    private boolean action_flg=false;
    private boolean starter_flg=false;
   // private boolean Hard_flg=false;



    //framelimiter
    private int frameCap,charaSize;
    private int framewidth,framehight;
    String playername;
    private int score=0;
    private int score2=0;

     MediaPlayer    mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        poh=new PlayerOpenHelper(this);
        scoretxt = (TextView) findViewById(R.id.scoretxt);
        starttxt = (TextView) findViewById(R.id.starttxt);
        txtLive = (TextView) findViewById(R.id.txtLive);
        charcter = (ImageView) findViewById(R.id.character);
        death = (ImageView) findViewById(R.id.death);
        death2 = (ImageView) findViewById(R.id.death2);
        death3 = (ImageView) findViewById(R.id.death3);
        bigpoints = (ImageView) findViewById(R.id.bigpoints);
        smallpoints = (ImageView) findViewById(R.id.smallpoints);

        playername=getIntent().getExtras().getString("nameOfThePlayer");
        music=getIntent().getExtras().getString("music");

        mp = MediaPlayer.create(getApplicationContext(), R.raw.gamemusic);

        mp.setLooping(true);
        mp.start();




        //geting screen sizes
        WindowManager wm=getWindowManager();
        Display disp=wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        framewidth=size.x;
        framehight=size.y;

        //moveing items out of the screen

        smallpoints.setX(-80);
        smallpoints.setY(-80);
        bigpoints.setX(-80);
        bigpoints.setY(-80);
        death.setX(-80);
        death.setY(-80);
        death2.setX(-80);
        death2.setY(-80);
        death3.setX(-80);
        death3.setY(-80);


        scoretxt.setText("Score : "+0); //masho + "masho"



    }

    @Override
    protected void onPause()

    {
        super.onPause();
        mp.stop();
        mp.release();

    }
    public void changePos()
    {
        hitCheck();
        //placeing all the items out of screen and crateing a random number of Y they will span in

        smallpointX1-=12;
        if(smallpointX1<0)
        {
            smallpointX1=framewidth+20;
            smallpointY1=(int)Math.floor(Math.random()*(framehight-smallpoints.getHeight()));
        }
        smallpoints.setY(smallpointY1);
        smallpoints.setX(smallpointX1);


        deathX1-=16;
        if(deathX1<0)
        {
            deathX1=framewidth+10;
            deathY1=(int)Math.floor(Math.random()*(framehight-death.getHeight()));
        }
        death.setY(deathY1);
        death.setX(deathX1);

        if(score>=2000) {

            death2X1 -= 16;
            if (death2X1 < 0) {
                death2X1 = framewidth + 10;
                death2Y1 = (int) Math.floor(Math.random() * (framehight - death2.getHeight()));
            }
            death2.setY(death2Y1);
            death2.setX(death2X1);
        }


        if(score>=3000)
        {

            death3Y1-=16;
        if(death3Y1<0)
        {
            death3X1=framewidth+10;
            death3Y1=(int)Math.floor(Math.random()*(framehight-death3.getHeight()));
        }
        death3.setY(death3Y1);
        death3.setX(death3X1);}

        bigpointX1-=20;
        if(bigpointX1<0)
        {
            bigpointX1=framewidth+80;
            bigpointY1=(int)Math.floor(Math.random()*(framehight-bigpoints.getHeight()));
        }
        bigpoints.setY(bigpointY1);
        bigpoints.setX(bigpointX1);



        if(action_flg==true)
        {
            //holding
            charaY+=15;

        } else
        {
            //leaveing
            charaY-=15;
        }
            if(charaY<0) charaY=0;

            if(charaY>frameCap-charaSize) charaY=frameCap-charaSize;

        charcter.setY(charaY);

        scoretxt.setText("Score : "+score);

    }


    public void hitCheck() {




        // only when the charcter hitsthe middle of the items points get collected

        int smallpointsCenterX=smallpointX1+smallpoints.getWidth()/2;
        int smallpointsCenterY=smallpointY1+smallpoints.getHeight()/2;

        //0 X of the charcter <= ceneter of the item <= charcter width

        if(0<=smallpointsCenterX&&smallpointsCenterX<=charaSize&&charaY<=smallpointsCenterY&&smallpointsCenterY<=charaY+charaSize)
        {
            score+=100;
            smallpointX1=-10;
        }

        int bigpointsCenterX=bigpointX1+bigpoints.getWidth()/2;
        int bigpointsCentery=bigpointY1+bigpoints.getHeight()/2;


        if(0<=bigpointsCenterX&&bigpointsCenterX<=charaSize&&charaY<=bigpointsCentery&&bigpointsCentery<=charaY+charaSize)
        {
            score+=150;
            bigpointX1=-10;
        }

        int deathCenterX1=deathX1+death.getWidth()/2;
        int deathCenterY1=deathY1+death.getHeight()/2;

        int death2CenterX1=death2X1+death.getWidth()/2;
        int death2CenterY1=death2Y1+death.getHeight()/2;
        int death3CenterX1=death3X1+death.getWidth()/2;
        int death3CenterY1=death3Y1+death.getHeight()/2;

        if(0<=deathCenterX1&&deathCenterX1<=charaSize&&charaY<=deathCenterY1&&deathCenterY1<=charaY+charaSize) {
        if(added=true)
        {poh.open();
            Player x =new Player(playername,score+"",0);
            x=poh.createUser(x);
            poh.close();
           added=false;}
            //ending the game
            timer.cancel();
            Intent i =new Intent(GameActivity.this,ResultActivity.class);
            startActivity(i);}

        if(0<=death2CenterX1&&death2CenterX1<=charaSize&&charaY<=death2CenterY1&&death2CenterY1<=charaY+charaSize&&score>2000) {
            if(added=true)
            {poh.open();
            Player x =new Player(playername,score+"",0);
            x=poh.createUser(x);
            poh.close();
            added=false;}
            //ending the game
            timer.cancel();
            //timer = null;
            //show results

            Intent i =new Intent(GameActivity.this,ResultActivity.class);
            startActivity(i);
        }
        if(0<=death3CenterX1&&death3CenterX1<=charaSize&&charaY<=death3CenterY1&&death3CenterY1<=charaY+charaSize&&score>3000) {
           if(added=true)
           {poh.open();
            Player x =new Player(playername,score+"",0);
            x=poh.createUser(x);
            poh.close();
            added=false;}
            //ending the game
            timer.cancel();
            //timer = null;
            //show results

            Intent i =new Intent(GameActivity.this,ResultActivity.class);
            startActivity(i);
        }
        }

    public boolean onTouchEvent(MotionEvent me) {

        if (starter_flg == false) {

            starter_flg = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameCap=frame.getHeight();

            charaY=(int)charcter.getY();

            charaSize=charcter.getWidth();


            starttxt.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);


        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;

            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;

            }
        }

        return true;
    }





}
