package com.example.smeet.alarmcountdown;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
       TextView t;
       SeekBar s;
       MediaPlayer m;
       boolean tr=false;
       CountDownTimer c;
       Button b;

       public void reset(){
           t.setText("0:30");
           s.setProgress(30);
           b.setText("GO");
           s.setEnabled(true);
           c.cancel();
           tr=false;
       }

    public void fun(View view) {
        if (tr) {
            reset();
        }
        else {

            s.setEnabled(false);
            b.setText("STOP");
            tr = true;
           c = new CountDownTimer(s.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    m.start();
                    reset();
                }
            }.start();
        }
    }
    public void updateTimer(int i){
        int mins=i/60;
        int sec=i-(mins*60);

        String secs=Integer.toString(sec);
        if(sec<=9) {
            secs = "0" + secs;
        }
        t.setText(Integer.toString(mins)+":"+secs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=(SeekBar)findViewById(R.id.seekBar);
        t=(TextView)findViewById(R.id.timer);
        b=findViewById(R.id.button);

        m=MediaPlayer.create(getApplicationContext(),R.raw.siren);
        s.setMax(600);
        s.setProgress(30);
         s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 updateTimer(i);

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
