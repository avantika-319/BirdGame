package com.example.birdgame;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView bird, e1, e2, e3, coin1, coin2, hrt1, hrt2, hrt;
    private TextView score, start;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    private boolean beginControl = false;
    private Runnable runnable, runnable2;
    private Handler handler, handler2;

    //Positions
    int birdX, e1X, e2X, e3X, c1X, c2X;
    int birdY, e1Y, e2Y, e3Y, c1Y, c2Y;
    //Remaining Life
    int life = 3;
    //Points
    int points = 0;

    //Screen Dimensions
    int screenWidth;
    int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bird = findViewById(R.id.imageViewBird);
        e1 = findViewById(R.id.ee1);
        e2 = findViewById(R.id.ee2);
        e3 = findViewById(R.id.ee3);
        coin1 = findViewById(R.id.coin);
        coin2 = findViewById(R.id.coin1);
        hrt = findViewById(R.id.hrt);
        hrt1 = findViewById(R.id.hrt1);
        hrt2 = findViewById(R.id.hrt2);
        score = findViewById(R.id.scoreview);
        start = findViewById(R.id.gametxt);
        constraintLayout = findViewById(R.id.cst_layout);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                start.setVisibility(View.INVISIBLE);

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    touchControl = true;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    touchControl = false;
                }

                if (!beginControl) // Screen touched first time
                {
                    beginControl = true;

                    screenWidth = (int) constraintLayout.getWidth();
                    screenHeight = (int) constraintLayout.getHeight();

                    birdX = (int) bird.getX();
                    birdY = (int) bird.getY();

                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {

                            moveToBird();
                            enemyControl();
                            collisionControl();
                        }
                    };
                    handler.post(runnable);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        touchControl = true;
                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        touchControl = false;
                    }
                }


                return true;
            }
        });
    }

    public void moveToBird() {
        if (touchControl) {
            birdY = birdY - (screenHeight / 50);
        } else {
            birdY = birdY + (screenHeight / 50);
        }

        if (birdY <= 0)//Vanishes up
        {
            birdY = 0;
        }
        if (birdY >= (screenHeight - bird.getHeight()))//Vanishes down
        {
            birdY = (screenHeight - bird.getHeight());
        }

        bird.setY(birdY);
    }

    public void enemyControl() {
        e1.setVisibility(View.VISIBLE);
        e2.setVisibility(View.VISIBLE);
        e3.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        e1X = e1X - (screenWidth / 150);
        if(points >= 150 && points < 300)
        {
            e1X = e1X - (screenWidth / 130);
        }
        if(points >= 300 && points < 425)
        {
            e1X = e1X - (screenWidth / 115);
        }
        if(points >= 425)
        {
            e1X = e1X - (screenWidth / 100);
        }
        if (e1X < 0)
        {
            e1X = screenWidth + 200;
            e1Y = (int) Math.floor(Math.random() * screenHeight);

            if (e1Y <= 0)//Vanishes up
            {
                e1Y = 0;
            }
            if (e1Y >= (screenHeight - e1.getHeight()))//Vanishes down
            {
                e1Y = (screenHeight - e1.getHeight());
            }
        }
        e1.setX(e1X);
        e1.setY(e1Y);

        e2X = e2X - (screenWidth / 140);
        if(points >= 150 && points < 300)
        {
            e2X = e2X - (screenWidth / 120);
        }
        if(points >= 300 && points < 425)
        {
            e2X = e2X - (screenWidth / 110);
        }
        if(points >= 425)
        {
            e2X = e2X - (screenWidth / 100);
        }
        if(e2X < 0)
        {
            e2X = screenWidth + 200;
            e2Y = (int)Math.floor(Math.random() * screenHeight);

            if(e2Y <= 0)//Vanishes up
            {
                e2Y = 0;
            }
            if(e2Y >= (screenHeight - e2.getHeight()))//Vanishes down
            {
                e2Y = (screenHeight - e2.getHeight());
            }
        }
        e2.setX(e2X);
        e2.setY(e2Y);

        e3X = e3X - (screenWidth / 130);
        if(points >= 150 && points < 300)
        {
            e3X = e3X - (screenWidth / 120);
        }
        if(points >= 300 && points < 425)
        {
            e3X = e3X - (screenWidth / 110);
        }
        if(points >= 425)
        {
            e3X = e3X - (screenWidth / 100);
        }
        if(e3X < 0)
        {
            e3X = screenWidth + 200;
            e3Y = (int)Math.floor(Math.random() * screenHeight);

            if(e3Y <= 0)//Vanishes up
            {
                e3Y = 0;
            }
            if(e3Y >= (screenHeight - e3.getHeight()))//Vanishes down
            {
                e3Y = (screenHeight - e3.getHeight());
            }
        }
        e3.setX(e3X);
        e3.setY(e3Y);

        c1X = c1X - (screenWidth / 120);
        if(c1X < 0)
        {
            c1X = screenWidth + 200;
            c1Y = (int)Math.floor(Math.random() * screenHeight);

            if(c1Y <= 0)//Vanishes up
            {
                c1Y = 0;
            }
            if(c1Y >= (screenHeight - coin1.getHeight()))//Vanishes down
            {
                c1Y = (screenHeight - coin1.getHeight());
            }
        }
        coin1.setX(c1X);
        coin1.setY(c1Y);

        c2X = c2X - (screenWidth / 110);
        if(c2X < 0)
        {
            c2X = screenWidth + 200;
            c2Y = (int)Math.floor(Math.random() * screenHeight);

            if(c2Y <= 0)//Vanishes up
            {
                c2Y = 0;
            }
            if(c2Y >= (screenHeight - coin2.getHeight()))//Vanishes down
            {
                c2Y = (screenHeight - coin2.getHeight());
            }
        }
        coin2.setX(c2X);
        coin2.setY(c2Y);
    }

    public void collisionControl()
    {
        //To find the center point
        int center_e1X = e1X + e1.getWidth() / 2;
        int center_e1Y = e1Y + e1.getHeight() / 2;
        //Collision
        if(center_e1X >= birdX
          && center_e1X <= (birdX + bird.getWidth())
          && center_e1Y >= birdY
          && center_e1Y <= (birdY + bird.getHeight()))
        {
            e1X = screenWidth + 200;
            life--;
        }

        int center_e2X = e2X + e2.getWidth() / 2;
        int center_e2Y = e2Y + e2.getHeight() / 2;
        //Collision
        if(center_e2X >= birdX
                && center_e2X <= (birdX + bird.getWidth())
                && center_e2Y >= birdY
                && center_e2Y <= (birdY + bird.getHeight()))
        {
            e2X = screenWidth + 200;
            life--;
        }

        int center_e3X = e3X + e3.getWidth() / 2;
        int center_e3Y = e3Y + e3.getHeight() / 2;
        //Collision
        if(center_e3X >= birdX
                && center_e3X <= (birdX + bird.getWidth())
                && center_e3Y >= birdY
                && center_e3Y <= (birdY + bird.getHeight()))
        {
            e3X = screenWidth + 200;
            life--;
        }

        int center_c1X = c1X + coin1.getWidth() / 2;
        int center_c1Y = c1Y + coin1.getHeight() / 2;
        //Collision
        if(center_c1X >= birdX
                && center_c1X <= (birdX + bird.getWidth())
                && center_c1Y >= birdY
                && center_c1Y <= (birdY + bird.getHeight()))
        {
            c1X = screenWidth + 200;
            points += 10;
            score.setText(""+points);
        }

        int center_c2X = c2X + coin2.getWidth() / 2;
        int center_c2Y = c2Y + coin2.getHeight() / 2;
        //Collision
        if(center_c2X >= birdX
                && center_c2X <= (birdX + bird.getWidth())
                && center_c2Y >= birdY
                && center_c2Y <= (birdY + bird.getHeight()))
        {
            c2X = screenWidth + 200;
            points += 10;
            score.setText(""+points);
        }

        if(life > 0 && points < 500)
        {
            if(life == 2)
            {
                hrt.setImageResource(R.drawable.greyy);
            }
            if(life == 1)
            {
                hrt1.setImageResource(R.drawable.greyy);
            }
            handler.postDelayed(runnable,20);
        }
        else if(points >= 500)
        {
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            start.setVisibility(View.VISIBLE);
            start.setText("CONGRATULATIONS!!! YOU WON");
            e1.setVisibility(View.INVISIBLE);
            e2.setVisibility(View.INVISIBLE);
            e3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {
                    birdX = birdX +(screenWidth / 300);
                    bird.setX(birdX);
                    bird.setY(screenHeight/ 2f);
                    if(birdX <= screenWidth)
                    {
                        handler2.postDelayed(runnable2,20);
                    }
                    else
                    {
                        handler2.removeCallbacks(runnable2);
                        Intent intent = new Intent(GameActivity.this,ResultActivity.class);
                        intent.putExtra("points",points);
                        startActivity(intent);
                        finish();
                    }

                }
            };

            handler2.post(runnable2);
        }
        else if(life == 0)
        {
            handler.removeCallbacks(runnable);
            hrt2.setImageResource(R.drawable.greyy);
            Intent intent = new Intent(GameActivity.this,ResultActivity.class);
            intent.putExtra("points",points);
            startActivity(intent);
            finish();
        }
    }


 }
