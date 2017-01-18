package siono.game.android.av.siono;

import android.content.Intent;
import android.database.CrossProcessCursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;

import java.util.Random;

//esto es para la publicidad
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Level_1 extends AppCompatActivity  implements View.OnClickListener,
        Frag_home.OnFragmentInteractionListener,Frag_levels.OnFragmentInteractionListener,
Comunicacion_niveles{

    //variable creda para comunicar
    public int intcaptado;

    public Level_1(){
        intcaptado =0;
    }


    public ImageView vida,btn_si,btn_no,img_level_1,img_preg;
    public TextView mi_crono;
    private Random random = new Random();

    private int op, cantidad;//op=opcion escogida(button),cantidad=cantidad de clicks
    private int mal,bien,cuantasvidas,p,ran;

    private long tiempoprimerclick;//para el metodo onbackpresed


    public int c;//para enviar cantidad
    private int tam_array;


    public int tam,can;

    private Cronometro cronometro = null;
    private Cronometro_2 cronometro_2= null;



    //instanciar
    Evaluacion evaluacion;


    //llamando a calificacion
    Calificacion calificacion;

    //soundpoll
    private SoundPool ok,no,click_tiempo;
    private int flujoDeMusica;

    //BUTON PARA PREGUNTA ALEATORIA
    private Button btn_preguntas;



    int traedato;


    //timer
    CountDownTimer countDownTimer;


    //interruptor para detener la cuenta atras
    private boolean interruptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_1);

        //BUTON PARA PREGUNTA ALEATORIA
        btn_preguntas = (Button)findViewById(R.id.elbotonquepregunta);


        //BOTONES
        btn_no =(ImageView)findViewById(R.id.btnno);
        btn_no.setOnClickListener(this);
        btn_si =(ImageView)findViewById(R.id.btnsi);
        btn_si.setOnClickListener(this);
        //VIDA
        vida = (ImageView)findViewById(R.id.vidas);
        //IMAGENES DEL CENTRO
        img_level_1 = (ImageView)findViewById(R.id.imagenes_level_1);
        //IMG PREGUNTA
        //img_preg = (ImageView)findViewById(R.id.preg_fv);

        //MI CRONO LO UTILIZO PARA QUE SEA UTILIZADO POR EL NUEVO ILO QUE LLEVA EL TIEMPO
        mi_crono =  (TextView)findViewById(R.id.microno);//es importante hacer el llamado

        //iniciar
        azar();

        //crear nuevo hilo
        tiempo();

        //cuenta atras
        cuentaatras();
        interruptor=true;


        //instanciar
        calificacion = new Calificacion();

        //SOUNDPOOL infantil ok
        ok = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//para poder usar los botones de audio fisicos
        flujoDeMusica = ok.load(this,R.raw.oknino,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);

        //SOUNDPOOL infantil no
        no = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//para poder usar los botones de audio fisicos
        flujoDeMusica = no.load(this,R.raw.nonino,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);

        //SOUNDPOOL SONIDO TIEMPO
        click_tiempo = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//para poder usar los botones de audio fisicos
        flujoDeMusica = click_tiempo.load(this,R.raw.sonido_tiempo,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);


        //se crea esto para la publicidad
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnsi:
                //cronometro.reiniciar()
                countDownTimer.cancel();
                cuentaatras();
                op=1;
                cantidad++;
                evaluacion();
                azar();

                break;
            case R.id.btnno:
                countDownTimer.cancel();
                cuentaatras();
                //cronometro.reiniciar();
                op=2;
                cantidad++;
                evaluacion();
                azar();

                break;
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void evaluacion() {
        //int p = random.nextInt(imagenesfruver.length);//da una imagen random
       // int ran = random.nextInt(array_pregunta.length);//pregunta random

        /*P ME MUESTRA UN NUMERO RANDOMPARA EVALUAR QUE FRUTA O VERDIURA SE ESTA MOSTRANDO
        * OP ME MUESTRA LA OPCIÓN QUE SE OPREIMIO (SI O NO)
        * CANTIDAD = LA CANTIDAD DE CLICKS QUE LLEVAN PARA SABER CUANDO ACABA EL JUEGO
        * IMAGENESFRUVER ES DONDE SE ENCUENTRAN ALOJADAS LAS IMAGENES Y .LENGTH ME DA EL TAMAÑO
        * -1 = LE GUITA UNA UNIDAD A EL ARRAY
        * RAN ES UN ENTERO QUE ME DEVUELVE EL NÚMERO RAMDOM CON EL FIN DE SABER QUE SE PREGUNTA SI FRUTA O VERDURA*/

        if(p<=12 && op==1 && cantidad<=imagenesfruver.length-1&& ran==1){//verdura/si/?verdura = bien
           ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p<=12 && op==2 && cantidad<=imagenesfruver.length-1&& ran==1){//verdura/no verdura/ = mal
           no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=12 && op==1 && cantidad<=imagenesfruver.length-1&& ran==0){//verdura/si/fruta = mal
          no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=12 && op==2 && cantidad<=imagenesfruver.length-1&& ran==0){//verdura/no/fruta =bien
           ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }else if(p>=13&& op==1 && cantidad<=imagenesfruver.length-1&& ran==0){
           ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p>=13 && op==2 && cantidad<=imagenesfruver.length-1&& ran==0){
           no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>=13 && op==1 && cantidad<=imagenesfruver.length-1&& ran==1){
          no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>=13 && op==2 && cantidad<=imagenesfruver.length-1&& ran==1){
          ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }
        //SI PERDE SE EJECUTA ESTO------------------------------------------------------------------

        quitavidas();
    }

    //como que este no sirbe para nada
    @Override
    public void respuestaFinal(int cantbuenas) {

        int calif=0;

        if(cantbuenas==imagenesfruver.length-1){
            calif=0;

        }else if(cantbuenas<=imagenesfruver.length-2){
            calif=1;

        }

        fin_juego_set(calif);

    }

    @Override
    public void azar() {
        p = random.nextInt(imagenesfruver.length);//da una imagen random
        ran = random.nextInt(array_pregunta.length);//pregunta random

        if(cantidad<= imagenesfruver.length-1){
            //img_preg.setImageResource(array_pregunta[ran]);//aleatorio para la pregunta

            btn_preguntas.setText(array_pregunta[ran]);
            img_level_1.setImageResource(imagenesfruver[p]);//aleatorio para la imagen
        }else {
            respuestaFinal(bien);
        }
    }

    @Override
    public void fin_juego_set(int i) {
        finish();
        interruptor=false;
        countDownTimer.cancel();
        intcaptado = i;
        Intent intent = new Intent(this,Calificacion.class);

        traedato  = cronometro_2.get_seconds();


        intent.putExtra("respuesta",i);
        intent.putExtra("cronometro2",traedato);

        //cronometro.pause();
        cronometro_2.pause();
        startActivity(intent);

    }


    @Override
    public void onBackPressed(){
        if(tiempoprimerclick+intervalo>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(this,"vuelve a aprecionar para salir",Toast.LENGTH_SHORT).show();
        }
        tiempoprimerclick = System.currentTimeMillis();

    }

    @Override
    public void tiempo() {// llama el hilo
        /*if(cronometro==null){
            cronometro = new Cronometro("cronometro",mi_crono);
            new Thread(cronometro).start();
        }*/
        if(cronometro_2==null){
            cronometro_2 = new Cronometro_2("cronometro_2");
            new Thread(cronometro_2).start();
        }
    }

    public void cuentaatras(){

           countDownTimer= new CountDownTimer(3000,1000){

                @Override
                public void onTick(long millisUntilFinished) {

                        mi_crono.setText(" "+(millisUntilFinished/1000));
                        click_tiempo.play(flujoDeMusica,1,1,0,0,1);
                }

                @Override
                public void onFinish() {
                    mal++;
                    quitavidas();
                    azar();
                    if (interruptor==true){
                        cuentaatras();
                    }
                }
            }.start();
    }

    public void quitavidas(){
                /*EVALUO LA CANTIDAD DE VIDAS QUE VA PERDIENDO CON EL FIN DE PASAR EL VALOR DE CUANTAS VIDAS
        * EN  EL ARRAY DE SET_IMAGERESOURSE Y CAMBIAR LA IMAGEN QUE SE MUESTRA*/
        switch (mal){
            case 0:
                cuantasvidas=2;
                break;
            case 1:
                cuantasvidas=1;
                break;
            case 2:
                cuantasvidas=0;
                break;
        }
        vida.setImageResource(arrayvidas[cuantasvidas]);
        if(mal==3){
            fin_juego_set(2);
        }
    }
}
