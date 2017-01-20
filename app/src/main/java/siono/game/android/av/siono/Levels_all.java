package siono.game.android.av.siono;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    //boleanos paraa activacion de niveles
    boolean key_level2;
    boolean key_level3;
    //-------------------------------

    private Clase_comunicadora clase_comunicadora = new Clase_comunicadora();

    //en esta variable guar que niveles se han desbloqueado
    private int nivel_superado =0;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_all);

        //activacion booleanos
        key_level2=false;
        key_level3=false;


        btn_1 =(ImageView)findViewById(R.id.btn_level_1);
        btn_1.setOnClickListener(this);
        btn_2 = (ImageView)findViewById(R.id.btn_level_2);
        btn_2.setOnClickListener(this);
        btn_3 = (ImageView)findViewById(R.id.btn_level_3);
        btn_3.setOnClickListener(this);


        click = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoDeMusica = click.load(this,R.raw.click,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);


        //recibiendo datos de level 1
        /*int tomarecibido = clase_comunicadora.get_recibe();
        if(tomarecibido==2){
            Intent intent = new Intent(this,Level_1.class);
            Bundle bundle = this.getIntent().getExtras();
            int res  = bundle.getInt("nivel_2_desbloqueado");
            cambioopacidad(res);
        }*/


        sharedPreferences = getSharedPreferences("guardadodeniveles",Context.MODE_PRIVATE);
        nivel_superado = sharedPreferences.getInt("nivel",0);

        cambioopacidad(nivel_superado);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_level_1:
                click.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                Intent i = new Intent(this,Level_1.class);
                startActivity(i);
                finish();
                break;
            case R.id.btn_level_2:

                    if(key_level2==true){
                        click.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                        Intent i2 = new Intent(this,Level_2.class);
                        startActivity(i2);
                        finish();
                    }
                break;
            case R.id.btn_level_3:
                    if(key_level3==true){
                        click.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                        Intent i3 = new Intent(this,Level_3.class);
                        startActivity(i3);
                        finish();
                    }
                break;

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
    public void cambioopacidad(int i){

        switch (i){
            case 0:
                btn_2.setAlpha(0.5f);
                btn_3.setAlpha(0.5f);
                key_level2=false;
                key_level3=false;
                break;
            case 2:
                btn_2.setAlpha(1f);
                btn_3.setAlpha(0.5f);
                key_level2=true;
                break;
            case 3:
                btn_3.setAlpha(1f);
                btn_2.setAlpha(1f);
                key_level2=true;
                key_level3=true;

                break;
        }


    }

    public void onStop(){
        super.onStop();
        finish();
    }
}
