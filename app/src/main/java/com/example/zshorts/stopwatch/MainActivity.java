package com.example.zshorts.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int buttonState = 1;
    long startTime = 0;
    long timeWhenStopped = 0;
    TextView playButtonText;
    ListView list;
    ArrayList<String> lapTimes = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final ImageView playButton = (ImageView)findViewById(R.id.playButton);
        ImageView lapButton = (ImageView)findViewById(R.id.lapButton);
        ImageView restartButton = (ImageView)findViewById(R.id.restartButton);
        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);
        playButtonText = (TextView)findViewById(R.id.startText);
        list = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lapTimes);
        list.setAdapter(adapter);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonState == 1){
                    startTime = SystemClock.elapsedRealtime();
                    chronometer.setBase(startTime);
                    chronometer.start();
                    playButton.setImageResource(R.drawable.pause);
                    playButtonText.setText("Pause");
                    buttonState = 2;
                }else if(buttonState == 2){
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
                    playButton.setImageResource(R.drawable.play);
                    playButtonText.setText("Play");
                    buttonState = 3;
                }
                else if(buttonState == 3){
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    playButton.setImageResource(R.drawable.pause);
                    playButtonText.setText("Pause");
                    buttonState = 2;
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                buttonState = 2;
                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                i = 0;
                lapTimes.clear();
                adapter.notifyDataSetChanged();
            }
        });

        lapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lapTimes.add(chronometer.getText().toString() + " - " + (i+1) );
                adapter.notifyDataSetChanged();
                i++;
            }
        });
    }


}
