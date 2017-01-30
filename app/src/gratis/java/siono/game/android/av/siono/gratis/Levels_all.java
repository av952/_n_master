package siono.game.android.av.siono.gratis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import siono.game.android.av.siono.R;

public class Levels_all extends AppCompatActivity implements View.OnClickListener,Frag_home.OnFragmentInteractionListener{

    private ImageView btn_1,btn_2,btn_3,btn_4;
    private final int intervalo =2000;
    private long tiempoprimerclick;
    private SoundPool click;
    private int flujoDeMusica;
    private TextView tv_estrella;

    //boleanos paraa activacion de niveles
    boolean key_level2,key_level3,key_level4;
    //-------------------------------



    //en esta variable guar que niveles se han desbloqueado
    private int nivel_superado =0;
    private  int estrellas;
    public SharedPreferences sharedPreferences;

    //opcines de nivel
    private int op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_all);

        //activacion booleanos
        key_level2=false;
        key_level3=false;
        key_level4=false;

        btn_1 =(ImageView)findViewById(R.id.btn_level_1);
        btn_1.setOnClickListener(this);
        btn_2 = (ImageView)findViewById(R.id.btn_level_2);
        btn_2.setOnClickListener(this);
        btn_3 = (ImageView)findViewById(R.id.btn_level_3);
        btn_3.setOnClickListener(this);
        btn_4 = (ImageView)findViewById(R.id.btn_level_4);
        btn_4.setOnClickListener(this);


        tv_estrella = (TextView)findViewById(R.id.tv_estrellas);


        click = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoDeMusica = click.load(this,R.raw.click,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);


        sharedPreferences = getSharedPreferences("guardadodeniveles",Context.MODE_PRIVATE);
        //nivel_superado = sharedPreferences.getInt("nivel",0);
        estrellas = sharedPreferences.getInt("cuanta_estrella",0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putInt("cuanta_estrella",100);
        editor.commit();

        key_level2 = sharedPreferences.getBoolean("activacion_level2",false);
        key_level3 = sharedPreferences.getBoolean("activacion_level3",false);
        key_level4 = sharedPreferences.getBoolean("activacion_level4",false);

        cantidadestrellas(estrellas);

        cambioopacidad();


        //se crea esto para la publicidad
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
                }else {
                    op=2;
                    entrada_nivel();
                }
                break;
            case R.id.btn_level_3:

                    if(key_level3==true){
                        click.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                        Intent i3 = new Intent(this,Level_3.class);
                        startActivity(i3);
                        finish();
                    }else {
                        op=3;
                        entrada_nivel();
                    }
                break;

            case R.id.btn_level_4:
                if(key_level4==true){
                    click.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                    Intent i4 = new Intent(this,Level_4.class);
                    startActivity(i4);
                    finish();
                }else {
                    op=4;
                    entrada_nivel();
                }


        }

    }
    private void entrada_nivel() {

        //evaluo si tiene 3 o mas estrelas y si la llamada l hizo en boton level2
        if(estrellas>=6 && op==2){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("cuanta_estrella",estrellas-6);
            editor.putBoolean("activacion_level2",true);
            editor.putFloat("opacidad2",1f);
            editor.commit();

            //lo leo para poder identificar que ahora es true y poder entrar al nivel
            key_level2 = sharedPreferences.getBoolean("activacion_level2",false);

            cambioopacidad();

        }else if (estrellas>=9&&op==3){

            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putInt("cuanta_estrella",estrellas-9);
            editor.putBoolean("activacion_level3",true);
            editor.putFloat("opacidad3",1f);
            editor.commit();

            key_level3 = sharedPreferences.getBoolean("activacion_level3",false);
            cambioopacidad();

        }else if (estrellas>=3&&op==4){
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putInt("cuanta_estrella",estrellas-3);
            editor.putBoolean("activacion_level4",true);
            editor.putFloat("opacidad4",1f);
            editor.commit();

            key_level4 = sharedPreferences.getBoolean("activacion_level4",false);
            cambioopacidad();

        } else {
            Toast.makeText(this,R.string.advertencia_nivel,Toast.LENGTH_SHORT).show();
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
    public void cambioopacidad(){

        estrellas = sharedPreferences.getInt("cuanta_estrella",0);
        cantidadestrellas(estrellas);

        //obtiene el valor de la opcaciodad y si no lo encuentra coloca  0.5
        float opaco = sharedPreferences.getFloat("opacidad2",0.5f);
        float opaco2 = sharedPreferences.getFloat("opacidad3",0.5f);
        float opaco3 = sharedPreferences.getFloat("opacidad4",0.5f);

        //asigna opacidad segun el dato que recoge  en las lineas de arriba
        btn_2.setAlpha(opaco);
        btn_3.setAlpha(opaco2);
        btn_4.setAlpha(opaco3);

    }

    public void onStop(){
        super.onStop();
        //finish();
    }

    private void cantidadestrellas(int e) {

        tv_estrella.setText(Integer.toString(e));



    }
}
