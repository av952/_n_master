package siono.game.android.av.siono;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Levels_all extends AppCompatActivity implements View.OnClickListener,Frag_home.OnFragmentInteractionListener{

    private ImageView btn_1,btn_2,btn_3;
    private final int intervalo =2000;
    private long tiempoprimerclick;
    private SoundPool click;
    private int flujoDeMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_all);


        btn_1 =(ImageView)findViewById(R.id.btn_level_1);
        btn_1.setOnClickListener(this);
        btn_2 = (ImageView)findViewById(R.id.btn_level_2);
        btn_2.setOnClickListener(this);
        btn_3 = (ImageView)findViewById(R.id.btn_level_3);
        btn_3.setOnClickListener(this);


        click = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoDeMusica = click.load(this,R.raw.click,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_level_1:
                click.play(flujoDeMusica,1,1,0,0,1);
                Intent i = new Intent(this,Level_1.class);
                startActivity(i);
                finish();
                break;
            case R.id.btn_level_2:
                click.play(flujoDeMusica,1,1,0,0,1);
                Intent i2 = new Intent(this,Level_2.class);
                startActivity(i2);
                finish();
                break;
            case R.id.btn_level_3:
                click.play(flujoDeMusica,1,1,0,0,1);
                Intent i3 = new Intent(this,Level_3.class);
                startActivity(i3);
                finish();
        }

    }
    //ACCION REALIZADA CUANDO SE PRECIONA EL BOTON DE REGRESAR
    public void onBackPressed(){
        if(tiempoprimerclick+intervalo>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(this,"Vuelve a aprecionar para salir",Toast.LENGTH_SHORT).show();
        }
        tiempoprimerclick = System.currentTimeMillis();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
