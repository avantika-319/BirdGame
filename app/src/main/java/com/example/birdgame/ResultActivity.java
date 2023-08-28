package com.example.birdgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView result, score, high_score;
    private Button btn_again;
    private int points;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.ResultInfo);
        score = findViewById(R.id.scoreInfo);
        high_score = findViewById(R.id.highscores);
        btn_again = findViewById(R.id.btn_again);

        points = getIntent().getIntExtra("points",0);
        score.setText("Your Score : " + points);

        sharedPreferences = this.getSharedPreferences("points", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("highScore", 0);

        if(points >= 500)
        {
            result.setText("YOU WON!!!");
            high_score.setText("High Score : "+points);
            sharedPreferences.edit().putInt("highScore",points).apply();
        }
        else if(points >= highScore)
        {
            result.setText("SORRY!!! You Lost");
            high_score.setText("High Score : "+points);
            sharedPreferences.edit().putInt("highScore",points).apply();
        }
        else
        {
            result.setText("SORRY!!! You Lost");
            high_score.setText("High Score : "+highScore);
        }

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Help The Bird");
        builder.setMessage("Are you sure you want to QUIT...?");
        builder.setCancelable(false);//so the alert box does not close on screen-touch
        builder.setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.create().show();
    }
}