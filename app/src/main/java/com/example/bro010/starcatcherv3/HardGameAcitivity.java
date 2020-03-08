package com.example.bro010.starcatcherv3;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class HardGameAcitivity extends AppCompatActivity {

    PlayerOpenHelper poh2;
    String playername;
    boolean added=true;
    private TextView scoretxt,starttxt;
    private ImageView  deathleft, deathright,charcter,smallpoints,bigpoints;

    private int screenwidth,screenheight;

    // Placement
    private float charaY;
    private int smallpointY1;
    private int smallpointX1;
    private Float charaY1;
    private Float charaX1;
    private int bigpointX1;
    private int bigpointY1;

    private Float deathleftX1;
    private Float deathleftY1;
    private Float deathrightX1;
    private Float deathrightY1;

    private int frameCap,charaSize;

    private boolean action_flg=false;
    private boolean starter_flg=false;

    private int score=0;

    //yesom calss
    private Handler handler=new Handler();
    private Timer timer =new Timer();

    MediaPlayer mp;
    String music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game_acitivity);
        scoretxt = (TextView) findViewById(R.id.scoretxt);
        starttxt = (TextView) findViewById(R.id.starttxt);
        poh2=new PlayerOpenHelper(this);
        charcter = (ImageView) findViewById(R.id.charcter);

        deathleft = (ImageView) findViewById(R.id.deathleft);
        deathright = (ImageView) findViewById(R.id.deathright);
        scoretxt = (TextView) findViewById(R.id.scoretxt);
        bigpoints = (ImageView) findViewById(R.id.bigpoints);
        smallpoints = (ImageView) findViewById(R.id.smallpoints);

        charaY=0;
        charaX1=0.0f;

            mp = MediaPlayer.create(getApplicationContext(), R.raw.gamemusic);

            mp.setLooping(true);
            mp.start();


        playername=getIntent().getExtras().getString("nameOfThePlayerHard");

        smallpoints.setX(-80);
        smallpoints.setY(-80);
        bigpoints.setX(-80);
        bigpoints.setY(-80);




        scoretxt.setText("Score : "+0);



        deathrightX1=80.0f;
        deathrightY1=80.0f;
        deathleftX1=80.0f;
        deathleftY1=80.0f;
        WindowManager wm=getWindowManager();
        Display disp=wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenwidth=size.x;
        screenheight=size.y;

        charcter.setX(screenwidth%2);

        deathright.setX(screenwidth+80.0f);
        deathright.setY(-80.0f);
        deathleft.setX(screenwidth-80.0f);
        deathleft.setY(-80.0f);


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

    }

    protected void onPause()

    {
        super.onPause();
        mp.stop();
        mp.release();

    }



    public void changePos()
    {
        hitCheck();

        smallpointX1-=12;
        if(smallpointX1<0)
        {
            smallpointX1=screenwidth+20;
            smallpointY1=(int)Math.floor(Math.random()*(screenheight-smallpoints.getHeight()));
        }
        smallpoints.setY(smallpointY1);
        smallpoints.setX(smallpointX1);

        bigpointX1-=20;
        if(bigpointX1<0)
        {
            bigpointX1=screenwidth+80;
            bigpointY1=(int)Math.floor(Math.random()*(screenheight-bigpoints.getHeight()));
        }
        bigpoints.setY(bigpointY1);
        bigpoints.setX(bigpointX1);


        if(action_flg==true)
        {
            //holding
            charaY+=10.0f;
            charaX1=(float)(screenwidth/2)-40.0f;
            charaY1=charaY;
            charaX1=charaX1;
        }
        else
        {
            //leaveing
            charaY-=10.0f;
            charaX1=(float)(screenwidth/2)-40.0f;
            charaY1=charaY;
            charaX1=charaX1;
        }
        if(charaY<0) charaY=0;
        if(charaX1<0) charaX1=(float)0;
        if(charaY>frameCap-charaSize) charaY=frameCap-charaSize;
        if(charaX1>screenwidth/2) charaX1=(float)screenwidth/2;
        charcter.setY(charaY);
        charcter.setX(charaX1);
        scoretxt.setText("Score : "+score);







        deathrightX1 += 10.0f;
        if(deathright.getX()>screenwidth)
        {
            deathrightX1 = -100.0f;
            deathrightY1 = (float)Math.floor(Math.random()*(screenwidth-deathright.getWidth()));

        }
        deathright.setY(deathrightY1);
        deathright.setX(deathrightX1);

        deathleftX1 -= 10.0f;
        if(deathleft.getX()+deathleft.getWidth()<0)
        {
            deathleftX1 = screenwidth+100.0f;
            deathleftY1 = (float)Math.floor(Math.random()*(screenwidth-deathleft.getWidth()));

        }
        deathleft.setY(deathleftY1);
        deathleft.setX(deathleftX1);
    }

    public void hitCheck() {


        // only when the charcter hitsthe middle of the items points get collected

        int smallpointsCenterX = smallpointX1 + smallpoints.getWidth() / 2;
        int smallpointsCenterY = smallpointY1 + smallpoints.getHeight() / 2;

        //0 X of the charcter <= ceneter of the item <= charcter width

        if (charaX1 <= smallpointsCenterX && smallpointsCenterX <= charaX1 + charaSize &&
                charaY <= smallpointsCenterY && smallpointsCenterY <= charaY + charaSize) {
            score += 100;
            smallpointX1 = -10;
        }

        int bigpointsCenterX = bigpointX1 + bigpoints.getWidth() / 2;
        int bigpointsCentery = bigpointY1 + bigpoints.getHeight() / 2;


        if (charaX1 <= bigpointsCenterX && bigpointsCenterX <= charaX1 + charaSize &&
                charaY <= bigpointsCentery && bigpointsCentery <= charaY + charaSize) {
            score += 150;
            bigpointX1 = -10;
        }





        float deathrightCenterX1=deathrightX1+deathright.getWidth()/2;
        float deathrightCenterY1=deathrightY1+deathright.getHeight()/2;


        float deathleftCenterX1=deathleftX1+deathleft.getWidth()/2;
        float deathleftCenterY1=deathleftY1+deathleft.getHeight()/2;


        if(charaX1 <= deathrightCenterX1 && deathrightCenterX1 <= charaX1 + charaSize &&
                charaY <= deathrightCenterY1 && deathrightCenterY1 <= charaY + charaSize) {
           if(added=true)
           {poh2.open();
            Player x1 =new Player(playername,score+"",0);
            x1=poh2.createUser(x1);
            poh2.close();
            added=false;}


            //ending the game
            timer.cancel();
            //timer = null;
            //show results
            try {
                Intent i = new Intent(HardGameAcitivity.this, ResultActivity.class);
                startActivity(i);
            }
            catch(Exception e) {
                Toast.makeText(this, "Caught IOException: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            finally{finish();}
            }
            else{}
        if(charaX1 <= deathleftCenterX1 && deathleftCenterX1 <= charaX1 + charaSize &&
                charaY <= deathleftCenterY1 && deathleftCenterY1 <= charaY + charaSize) {
           if(added=true)
           {
            poh2.open();
            Player x1 =new Player(playername,score+"",0);
            x1=poh2.createUser(x1);
            poh2.close();
            added=false;}

            //ending the game
            timer.cancel();
          //  timer = null;
            //show results

            try {
                Intent i = new Intent(HardGameAcitivity.this, ResultActivity.class);
                startActivity(i);
            }
            catch(Exception e) {
                Toast.makeText(this, "Caught IOException: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            finally{finish();}

        }
            else{}

    }


    public boolean onTouchEvent(MotionEvent me) {

        if (starter_flg == false) {

            starter_flg = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameCap=frame.getHeight();

            starttxt.setVisibility(View.GONE);
            charaY=(int)charcter.getY();
            charaX1=charcter.getX();
            charaSize=charcter.getHeight();




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
