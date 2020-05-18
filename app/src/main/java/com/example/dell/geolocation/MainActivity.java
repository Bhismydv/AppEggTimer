package com.example.dell.geolocation;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Boolean counterIsActive=false;
    Button goBUtton;
    CountDownTimer countDownTimer;

    public void reset(){
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goBUtton.setText("Go");
        counterIsActive = false;
    }

    public void Click(View view) {

        if (counterIsActive) {
           reset();
        } else {


            counterIsActive = true;
            seekBar.setEnabled(false);
            goBUtton.setText("Stop");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mediaPlayer.start();
                    reset();
                }
            }.start();
        }

    }
    public void updateTimer(int secondsleft){
        int minutes =secondsleft/60;
        int seconds = secondsleft - (minutes * 60);

        String secondString=Integer.toString(seconds);

        if (seconds <= 9){
            secondString="0"+secondString;
        }
        //converting int time into string
        textView.setText(Integer.toString(minutes)+ ":"+Integer.toString(seconds));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView);
        goBUtton=findViewById(R.id.click);

        seekBar.setMax(600);
        seekBar.setProgress(30);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
