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

public class GameActivitylvl3 extends AppCompatActivity {

    private TextView scoretxt, starttxt,txtLive;
    private ImageView charcter, death, bigpoints, smallpoints,death2,death3;

    // Placement
    private int charaX;
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
    private int charaY=0;



    //yesom calss
    private Handler handler=new Handler();
    private Timer timer =new Timer();

    //Status check
    private boolean action_flg=false;
    private boolean starter_flg=false;
    private boolean pause_flg=false;
    private boolean play_time=true;
    private int life = 3;

    //framelimiter
    private int frameCap,charaSize;
    private int framewidth,framehight;

    private int score=0;


    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activitylvl2);
        scoretxt = (TextView) findViewById(R.id.scoretxt);
        starttxt = (TextView) findViewById(R.id.starttxt);
        txtLive = (TextView) findViewById(R.id.txtLive);
        charcter = (ImageView) findViewById(R.id.character);
        death = (ImageView) findViewById(R.id.death);
        death2 = (ImageView) findViewById(R.id.death2);
        death3 = (ImageView) findViewById(R.id.death3);
        bigpoints = (ImageView) findViewById(R.id.bigpoints);
        smallpoints = (ImageView) findViewById(R.id.smallpoints);


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


        scoretxt.setText("Score : "+0);




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









        //placeing all the items out of screen and crateing a random number of Y they will spwan in

        smallpointY1-=12;
        if(smallpointY1<0)
        {

            smallpointX1=(int)Math.floor(Math.random()*(framewidth-smallpoints.getWidth()));
            smallpointY1=-12;
        }
        smallpoints.setY(smallpointY1);
        smallpoints.setX(smallpointX1);


        bigpointY1-=20;
        if(bigpointY1<0) {
            bigpointX1 = (int) Math.floor(Math.random() * (framehight - bigpoints.getHeight()));
            bigpointY1 =-20;
        }

        bigpoints.setY(bigpointY1);
        bigpoints.setX(bigpointX1);



        deathY1-=16;
        if(deathY1<0)
        {
            deathX1=(int)Math.floor(Math.random()*(framehight-death.getHeight()));
            deathY1=-16;
        }
        death.setY(deathY1);
        death.setX(deathX1);

        if(score>=2000) {
            Toast.makeText(this, "Game got harder keep on catching!", Toast.LENGTH_SHORT).show();
            death2Y1 -= 16;
            if (death2Y1 <0) {
                death2X1=(int)Math.floor(Math.random()*(framehight-death2.getHeight()));
                death2Y1=16;
            }
            death2.setY(death2Y1);
            death2.setX(death2X1);
        }

        if(score>=3000)
        {
            death3Y1-=16;
            if(death3Y1<0)
            {
                death3X1=(int)Math.floor(Math.random()*(framehight-death3.getHeight()));
                death3Y1=0;
            }
            death3.setY(death3Y1);
            death3.setX(death3X1);}


        if(action_flg==true)
        {
            //holding
            charaX+=15;
            charaY=frameCap-50;
        } else
        {
            //leaveing
            charaX-=15;
            charaY=frameCap-50;
        }
        if(charaX<0) charaX=0;
        if(charaY<0) charaY=0;
        if(charaY>frameCap-50) charaY=frameCap-50;
        if(charaX>frameCap-charaSize) charaX=frameCap-charaSize;
        charcter.setY(charaY);
        charcter.setX(charaX);

        scoretxt.setText("Score : "+score);

    }


    public void hitCheck() {




        // only when the charcter hitsthe middle of the items points get collected

        int smallpointsCenterX=smallpointX1+smallpoints.getWidth()/2;
        int smallpointsCenterY=smallpointY1+smallpoints.getHeight()/2;

        //0 X of the charcter <= ceneter of the item <= charcter width

        if (charaX <= smallpointsCenterX && smallpointsCenterX <= charaX+charaSize && charaY <= smallpointsCenterY && smallpointsCenterY <= charaY+charaSize)
        {
            score+=100;
            smallpointY1=-10;
        }

        int bigpointsCenterX=bigpointX1+bigpoints.getWidth()/2;
        int bigpointsCentery=bigpointY1+bigpoints.getHeight()/2;


        if(charaX <= bigpointsCenterX && bigpointsCenterX <= charaX+charaSize && charaY <= bigpointsCentery && bigpointsCentery <= charaY+charaSize)
        {
            score+=150;
            bigpointY1=-10;
        }

        int deathCenterX1=deathX1+death.getWidth()/2;
        int deathCenterY1=deathY1+death.getHeight()/2;

        int death2CenterX1=death2X1+death.getWidth()/2;
        int death2CenterY1=death2Y1+death.getHeight()/2;
        int death3CenterX1=death3X1+death.getWidth()/2;
        int death3CenterY1=death3Y1+death.getHeight()/2;

        if(charaX <= deathCenterX1 && deathCenterX1 <= charaX+charaSize && charaY <= deathCenterY1 && deathCenterY1 <= charaY+charaSize) {
            life=life-1;
            if(life==0)
            {
                Intent takeit=getIntent();
                String name=takeit.getStringExtra("key");
                String tempscore=String.valueOf(score);


                //ending the game
                timer.cancel();
                timer = null;
                //show results

                Intent i =new Intent(GameActivitylvl3.this,ResultActivity.class);
                startActivity(i);}
        }
        if(charaX <= death2CenterX1 && death2CenterX1 <= charaX+charaSize && charaY <= death2CenterY1 && death2CenterY1 <= charaY+charaSize&&score>2000) {
            Intent takeit=getIntent();
            String name=takeit.getStringExtra("key");
            String tempscore=String.valueOf(score);



            //ending the game
            timer.cancel();
            timer = null;
            //show results

            Intent i =new Intent(GameActivitylvl3.this,ResultActivity.class);
            startActivity(i);
        }
        if (charaX <= death3CenterX1 && death3CenterX1 <= charaX+charaSize && charaY <= death3CenterY1 && death3CenterY1 <= charaY+charaSize&&score>3000) {
            Intent takeit=getIntent();
            String name=takeit.getStringExtra("key");
            String tempscore=String.valueOf(score);



            //ending the game
            timer.cancel();
            timer = null;
            //show results

            Intent i =new Intent(GameActivitylvl3.this,ResultActivity.class);
            startActivity(i);
        }
    }












    public boolean onTouchEvent(MotionEvent me) {

        if (starter_flg == false) {

            starter_flg = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameCap=frame.getWidth();

            charaX=(int)charcter.getY();

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