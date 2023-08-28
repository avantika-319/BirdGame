package com.example.birdgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView bird, e1, e2, e3, coin, vol;
    private Button start;
    private Animation animation;
    private MediaPlayer mediaPlayer;

    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bird = findViewById(R.id.bird);
        e1 = findViewById(R.id.E1);
        e2 = findViewById(R.id.E2);
        e3 = findViewById(R.id.E3);
        coin = findViewById(R.id.gc);
        vol = findViewById(R.id.volume);
        start = findViewById(R.id.start_btn);

        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
        bird.setAnimation(animation);
        e1.setAnimation(animation);
        e2.setAnimation(animation);
        e3.setAnimation(animation);
        coin.setAnimation(animation);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.audio);
        mediaPlayer.start();

        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!status)
                {
                    mediaPlayer.setVolume(0,0);
                    vol.setImageResource(R.drawable.soff);
                    status = true;
                }
                else
                {
                    mediaPlayer.setVolume(1,1);
                    vol.setImageResource(R.drawable.son);
                    status = false;
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                vol.setImageResource(R.drawable.son);

                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
    }
}