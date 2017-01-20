package siono.game.android.av.siono;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private static int SPLASH_TIME_OUT =2000;
    private SoundPool soundPool;
    private int flujodemusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,Menu.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

        mediaPlayer = MediaPlayer.create(this,R.raw.avxc);
        mediaPlayer.setLooping(false);
        mediaPlayer.setVolume(0.2f,0.2f);
        mediaPlayer.start();


        //soundpool
        /*undPool = new SoundPool(0, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujodemusica = soundPool.load(this,R.raw.avxc,1);
        soundPool.play(flujodemusica,0.5f,0.5f,0,0,1);*/
    }

}
