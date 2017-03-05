package siono.game.android.av.siono.gratis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.icu.text.AlphabeticIndex;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

import siono.game.android.av.siono.R;

public class Calificacion extends AppCompatActivity implements Frag_levels.OnFragmentInteractionListener,
        Frag_home.OnFragmentInteractionListener, View.OnClickListener {

    private int calificacion_int;
    private ImageView img_calif;
    private int getrespuesta;
    //lo de abajo es para el backonpresed
    private final int intervalo = 2000;
    private long tiempoprimerclick;

    private int estrellas;

    //sonido
    private SoundPool gano,perdio;
    private int flujodemusica;

    private MediaPlayer mpgano,mpperdio;


    //VARIABLES PARA GURDAR PUNTUACION
    private int max = 0;


    private int res, res2,nivelquellama;

    private int[] respuestacalifica2 = {
            R.drawable.resp_pref_3_700x516,
            R.drawable.resp_bien_3_700x516_2estrellas,
            R.drawable.resp_bien_3_700x516,
            R.drawable.resp_fallo_3_700x516
    };

    private int[] array_respuesta_txt = {
            R.string.perfecto,
            R.string.bien_echo,
            R.string.bien,
            R.string.fallaste

    };

    private TextView tv_califica, tv_tiempo, tv_msg;

    SharedPreferences share;

    Tile tile;


    //para el dato de charedpreference
    private int tiempoobtenido = 0;

    //publicidad interticial
    InterstitialAd interstitialAd;


    //*********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //toma el paquete de datos de la actividad que la llamo
        Bundle datos = this.getIntent().getExtras();
        //se asigna los datos tomados a nuevas cariables
        res = datos.getInt("respuesta");

        //recogiendo datos de cronometro2
        Bundle datos2 = this.getIntent().getExtras();
        res2 = datos2.getInt("cronometro2");

        //recojo el nivel que me envio el dato
        Bundle datos3 = this.getIntent().getExtras();
        nivelquellama = datos3.getInt("queniveles");



        //cargarpreferencias();

        img_calif = (ImageView) findViewById(R.id.img_calificada);
        tv_califica = (TextView) findViewById(R.id.txt_respuesta);

        //TOMA EL TIEMPO TOTAL DEL JUEGO
        tv_tiempo = (TextView) findViewById(R.id.txt_cant_time);

        //TEXTO MENSAJE
        tv_msg = (TextView) findViewById(R.id.msg_cant_time);

        tv_califica.setText(array_respuesta_txt[res]);

        img_calif.setImageResource(respuestacalifica2[res]);

        img_calif.setOnClickListener(this);


        share = getSharedPreferences("guardadodeniveles", Context.MODE_PRIVATE);

        //MUSICA

        gano = new SoundPool(0, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujodemusica =gano.load(this,R.raw.sonidogano_2,1);

        perdio = new SoundPool(0,AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujodemusica =perdio.load(this,R.raw.sonidoperdio_2,1);

        //mediaplayer

        gano.play(flujodemusica,1,1,0,0,1);
        //perdio.play(flujodemusica,1,1,0,0,1);

        //INICIA METODOS
        //le paso a record el dato del nivel que lo llamo
        record();
        colocandoestrellas();
        sonidorespuesta(res);


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-7600541404292053/7477090920");
         publicidad_interticial();

    }

    private void publicidad_interticial() {

        AdRequest adrequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adrequest);


        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdLoaded() {

                Random random = new Random();
                int ran  = random.nextInt(3);
                Log.i("publicidad"," #ran"+ran);

                if (ran == 1) {
                    Log.i("publicidad", "onadloaded");
                    interstitialAd.show();
                    super.onAdLoaded();
                }
            }
        });

    }


    //fin del oncreate

    public void record() {

        Log.i("llave", "entro a caso que abajo se emnciona");
        Log.i("1",String.valueOf(nivelquellama));

        /*CREO LA VARIABLE DE SEGUNDOS PARA LUEGO CONVERIR
        EL DATO  DE R.STRING EN UN STRING Y ASIGNARLO A LA RESPUESTA
        DEL TIEMPO
         */
        String segundos;
        //segundos=  String.valueOf(R.string.cuentasegundos);

        segundos =  " ";
        segundos += getString( R.string.cuentasegundos);


        Log.i("prueba",segundos);

        switch (nivelquellama){

            case 1:
                Log.i("2","entro a caso 1");
                //tv_tiempo.setText(Integer.toString(res2)+" segundos");
                tiempoobtenido = share.getInt("tiempomaximo", 120);
                if (res != 3) {


                    //evaluando que tiempoobtenido sea mayor para que el tiempo actual se considere un record
                    if (tiempoobtenido < res2) {

                        tv_msg.setText(R.string.mensaje_cantidad);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);

                    } else if (tiempoobtenido > res2) {

                        tv_msg.setText(R.string.record);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);


                        SharedPreferences.Editor editor = share.edit();
                        editor.putInt("tiempomaximo", res2);
                        editor.commit();
                    }

                } else {
                    //muestro una respuesta en caso de que falle
                    tv_msg.setText(R.string.mensaje_cantidad_2);
                    tv_tiempo.setText(" ");
                }

                break;

            case 2:
                Log.i("3","entro a caso 2");
                //tv_tiempo.setText(Integer.toString(res2)+" segundos");
                tiempoobtenido = share.getInt("tiempomaximo2", 120);
                if (res != 3) {

                    //evaluando que tiempoobtenido sea mayor para que el tiempo actual se considere un record
                    if (tiempoobtenido < res2) {

                        tv_msg.setText(R.string.mensaje_cantidad);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);

                    } else if (tiempoobtenido > res2) {

                        tv_msg.setText(R.string.record);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);


                        SharedPreferences.Editor editor = share.edit();
                        editor.putInt("tiempomaximo2", res2);
                        editor.commit();
                    }

                } else {
                    //muestro una respuesta en caso de que falle
                    tv_msg.setText(R.string.mensaje_cantidad_2);
                    tv_tiempo.setText(" ");
                }

                break;

            case 3:
                Log.i("3","entro a caso 3");
                //tv_tiempo.setText(Integer.toString(res2)+" segundos");
                tiempoobtenido = share.getInt("tiempomaximo3", 120);
                if (res != 3) {


                    //evaluando que tiempoobtenido sea mayor para que el tiempo actual se considere un record
                    if (tiempoobtenido < res2) {

                        tv_msg.setText(R.string.mensaje_cantidad);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);

                    } else if (tiempoobtenido > res2) {

                        tv_msg.setText(R.string.record);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);


                        SharedPreferences.Editor editor = share.edit();
                        editor.putInt("tiempomaximo3", res2);
                        editor.commit();
                    }

                } else {
                    //muestro una respuesta en caso de que falle
                    tv_msg.setText(R.string.mensaje_cantidad_2);
                    tv_tiempo.setText(" ");
                }
                break;

            case 4:
                Log.i("3","entro a caso 2");
                //tv_tiempo.setText(Integer.toString(res2)+" segundos");
                tiempoobtenido = share.getInt("tiempomaximo4", 120);
                if (res != 3) {

                    //evaluando que tiempoobtenido sea mayor para que el tiempo actual se considere un record
                    if (tiempoobtenido < res2) {

                        tv_msg.setText(R.string.mensaje_cantidad);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);

                    } else if (tiempoobtenido > res2) {

                        tv_msg.setText(R.string.record);
                        tv_tiempo.setText(Integer.toString(res2)+segundos);


                        SharedPreferences.Editor editor = share.edit();
                        editor.putInt("tiempomaximo4", res2);
                        editor.commit();
                    }

                } else {
                    //muestro una respuesta en caso de que falle
                    tv_msg.setText(R.string.mensaje_cantidad_2);
                    tv_tiempo.setText(" ");
                }
        }


    }


    public void sonidorespuesta(int r){
        if(r<=2){
            mpgano= MediaPlayer.create(this,R.raw.sonidogano_2);
            mpgano.setLooping(false);
            mpgano.start();

        }else{
            mpperdio =MediaPlayer.create(this,R.raw.sonidoperdio_3);
            mpperdio.setLooping(false);
            mpperdio.start();
        }
    }

    private void colocandoestrellas() {

        estrellas = share.getInt("cuanta_estrella", 0);

        switch (res) {
            case 0:
                estrellas = estrellas + 3;
                break;
            case 1:
                estrellas = estrellas + 2;
                break;
            case 2:
                estrellas = estrellas + 1;

        }

        SharedPreferences.Editor editor = share.edit();
        editor.putInt("cuanta_estrella", estrellas);
        editor.commit();

    }


    /*public void cargarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        tv_tiempo = (TextView)findViewById(R.id.txt_cant_time);
        (preferences.getString("tiempo"," "));

        if(!preferences.getString("tiempo"," ").equals(Integer.toString(res2))){
            guardarpreferencias();
        }

    }
    public void guardarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        // con este empezamos a editar nuestras preferencias
        SharedPreferences.Editor editor = preferences.edit();
        //colocamos la llave "tiempo" y el valor que queremos asinar
        editor.putInt("tiempo",res2);
        //con comit hacemos que los cambios sean guardados
        editor.commit();
        //tv_tiempo = (TextView)findViewById(R.id.txt_cant_time);
        //tv_tiempo.setText(Integer.toString(res2));
    }*/


    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    //ACCION REALIZADA CUANDO SE PRECIONA EL BOTON DE REGRESAR
    public void onBackPressed() {
        if (tiempoprimerclick + intervalo > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Vuelve a aprecionar para salir", Toast.LENGTH_SHORT).show();
        }
        tiempoprimerclick = System.currentTimeMillis();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_calificada:
                Intent i = new Intent(this, Levels_all.class);
                startActivity(i);
                finish();
                break;

        }
    }

}
