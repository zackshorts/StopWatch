package com.example.zshorts.stopwatch;

import android.graphics.Color;
import android.media.Image;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {
    int buttonState = 1;
    long startTime = 0;
    long timeWhenStopped = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final ImageView playButton = (ImageView)findViewById(R.id.playButton);
        ImageView lapButton = (ImageView)findViewById(R.id.lapButton);
        ImageView restartButton = (ImageView)findViewById(R.id.restartButton);
        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonState == 1){
                    startTime = SystemClock.elapsedRealtime();
                    chronometer.setBase(startTime);
                    chronometer.start();
                    playButton.setImageResource(R.drawable.pause);
                    buttonState = 2;
                }else if(buttonState == 2){
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
                    playButton.setImageResource(R.drawable.play);
                    buttonState = 3;
                }
                else if(buttonState == 3){
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    playButton.setImageResource(R.drawable.pause);
                    buttonState = 2;
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                buttonState = 1;
                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;

            }
        });
    }

    public void run(){

    }
}
