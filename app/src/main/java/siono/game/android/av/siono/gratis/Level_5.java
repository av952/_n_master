package siono.game.android.av.siono.gratis;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

import siono.game.android.av.siono.Comunicacion_niveles;
import siono.game.android.av.siono.Cronometro;
import siono.game.android.av.siono.Cronometro_2;
import siono.game.android.av.siono.R;

public class Level_5 extends AppCompatActivity  implements Comunicacion_niveles, Frag_home.OnFragmentInteractionListener,
        Frag_levels.OnFragmentInteractionListener,View.OnClickListener{

    private int p,op,cantidad,ran,bien,mal,cuantasvidas,pre;
    private ImageView vida,btn_si,btn_no, img_level_2,img_preg;
    public TextView mi_crono;
    private Random random = new Random();
    private long tiempoprimerclick;//para el metodo onbackpresed
    //llamando a calificacion
    Calificacion calificacion;
    //soundpoll
    private SoundPool ok,no,click_tiempo;
    private int flujoDeMusica;

    //PARA INSTANCIAR EL CRONOMETRO
    private Cronometro cronometro = null;
    private Cronometro_2 cronometro_2= null;

    //BOTTON QUE MUESTRA LA PREGUNTA
    public Button btn_preguntado;
    //OPCION ALEATORIA PARA LA RPEGUNTA ECHO CON STRINGS

    //creando array para selecion de preguntas alaeatorias
    //String[] array_preguntas_cambiantes;
    String cambio;

    //timer
    CountDownTimer countDownTimer;

    private boolean interruptor;

    private int cant_imagenes;

    //con esto obtengo la suma entre los dos arrays de iamgenes para la valuacion
    int sumaimagenes= imagenes_level_5_salado.length+imagenes_level_5_dulce.length;

    //imagen random
    int ran_img;

    //animacion
    Animation animation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("nivel","on create nivel 5");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_5);

        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        interruptor = true;


        btn_no =(ImageView)findViewById(R.id.btnno);
        btn_no.setOnClickListener(this);
        btn_si =(ImageView)findViewById(R.id.btnsi);
        btn_si.setOnClickListener(this);
        //VIDA
        vida = (ImageView)findViewById(R.id.vidas);
        //IMAGENES DEL CENTRO
        img_level_2 = (ImageView)findViewById(R.id.imagenes_level_1);
        //IMG PREGUNTA
        //img_preg = (ImageView)findViewById(R.id.preg_animal);
        //MI CRONO LO UTILIZO PARA QUE SEA UTILIZADO POR EL NUEVO ILO QUE LLEVA EL TIEMPO
        mi_crono =  (TextView)findViewById(R.id.microno);//es importante hacer el llamado



        //instanciar
        calificacion = new Calificacion();

        //SOUNDPOOL infantil ok
        ok = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//para poder usar los botones de audio fisicos
        flujoDeMusica = ok.load(this,R.raw.ok_nuevo,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);


        //SOUNDPOOL infantil no
        no = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//para poder usar los botones de audio fisicos
        flujoDeMusica = no.load(this,R.raw.no_nuevo,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);

        //SOUNDPOOL SONIDO TIEMPO
        click_tiempo = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//para poder usar los botones de audio fisicos
        flujoDeMusica = click_tiempo.load(this,R.raw.sonido_tiempo,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);


        //boton de la pregunta
        btn_preguntado = (Button)findViewById(R.id.elbotonquepregunta);

        //se crea esto para la publicidad
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //guardo la cantidad de imagenes es tes variable para utilizarlo en la logica
        cant_imagenes = imagenes_level_4.length;


        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anima_boton_sino);
        btn_no.startAnimation(animation);
        btn_si.startAnimation(animation);




        //medos llamados*****************************
        cuentaatras();
        //PARA QUE LA PRIMERA IMAGEN SEA RANDOM
        azar();
        //LLAMA AL METODO DENTRO DE ESTA CLASE QUE CREA EL NUEVO HILO
        tiempo();
        //********************************************

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsi:
                countDownTimer.cancel();
                cuentaatras();
                //cronometro.reiniciar();
                op=1;
                cantidad++;
                evaluacion();
                azar();
                btn_si.clearAnimation();
                btn_no.clearAnimation();

                break;
            case R.id.btnno:
                countDownTimer.cancel();
                cuentaatras();
                //cronometro.reiniciar();
                op=2;
                cantidad++;
                evaluacion();
                azar();
                btn_si.clearAnimation();
                btn_no.clearAnimation();
                break;
        }

    }

    @Override
    public void evaluacion() {
        if (pre==0){
            Log.i("evaluacion","p=0");
            evaluacion_dulce();
        }else if (pre==1){
            Log.i("evaluacion","p=1");
            evaluacion_salado();
        }

        //SI PERDE SE EJECUTA ESTO------------------------------------------------------------------

        quitavidas();

    }

    private void evaluacion_salado() {

        switch (op){
            case 1:

                if (ran_img==1){
                    Log.i("evaluacion","salado case 1");
                    ok.play(flujoDeMusica,1,1,0,0,1);
                    bien++;
                }else if (ran_img==0){
                    Log.i("evaluacion","salado case 2");
                    no.play(flujoDeMusica,1,1,0,0,1);
                    mal++;
                }
                break;
            case 2:
                if (ran_img==0){
                    Log.i("evaluacion","salado case 1");
                    ok.play(flujoDeMusica,1,1,0,0,1);
                    bien++;
                }else if (ran_img==1){
                    Log.i("evaluacion","salado case 2");
                    no.play(flujoDeMusica,1,1,0,0,1);
                    mal++;
                }
                break;
        }

    }
    private void evaluacion_dulce() {

        switch (op) {
            case 1:

                if (ran_img==0){
                    Log.i("evaluacion","salado case 1");
                    ok.play(flujoDeMusica,1,1,0,0,1);
                    bien++;
                }else if (ran_img==1){
                    Log.i("evaluacion","salado case 2");
                    no.play(flujoDeMusica,1,1,0,0,1);
                    mal++;
                }
                break;
            case 2:
                if (ran_img==1){
                    Log.i("evaluacion","salado case 1");
                    ok.play(flujoDeMusica,1,1,0,0,1);
                    bien++;
                }else if (ran_img==0){
                    Log.i("evaluacion","salado case 2");
                    no.play(flujoDeMusica,1,1,0,0,1);
                    mal++;
                }
                break;
        }
    }

    @Override
    public void respuestaFinal(int cant) {
        int calif=0;
        //evaluo la cantidad de malas
        if(cant==0){
            calif=0;
        }else if(cant==1){
            calif=1;
        }else if(cant==2){
            calif=2;
        }else {
            calif=3;

        }


       /* int calif=0;

        if(cantbuenas==imagenes_level_4.length-1){
            calif=0;

        }else if(cantbuenas<=imagenes_level_4.length-2){
            calif=1;

        }*/

        fin_juego_set(calif);
    }

    @Override
    public void azar() {

        if (cantidad<=sumaimagenes) {
            btn_preguntado = (Button)findViewById(R.id.elbotonquepregunta);
            //GENERA UN NUMERO ALEATORIO PARA LA PREGUNTA
            pre= random.nextInt(preguntas_level_5.length);
            btn_preguntado.setText(preguntas_level_5[pre]);

            //Doy un numero random para poder determinar si es dulce o salado
            Random random_imagenes = new Random();
            ran_img= random_imagenes.nextInt(2);
            Log.i("random","random img: "+ran_img);

            if (ran_img==0){
                p = random.nextInt(imagenes_level_5_dulce.length);//da una imagen random
                img_level_2.setImageResource(imagenes_level_5_dulce[p]);//aleatorio para la imagen
            }else if (ran_img==1){
                p = random.nextInt(imagenes_level_5_salado.length);//da una imagen random
                img_level_2.setImageResource(imagenes_level_5_salado[p]);//aleatorio para la imagen
            }
            //endregion

            ran = random.nextInt(preguntas_level_5.length);//pregunta random
        }else {
            respuestaFinal(mal);
        }



    }

    @Override
    public void fin_juego_set(int i) {

        finish();
        interruptor=false;
        countDownTimer.cancel();

        Intent intent = new Intent(this,Calificacion.class);
        /*PARA PODER TRANSFERIR INFORMACIÓN DE UNA VARIABLE A OTRA ES IMPORTANTE COLOCAR
        * PUTEXTRA (EL QUE LA RECIBE COLOCA PUT GET Y OTRO METODO DONDE ALMACENA
        * TODO LO QUE SE LE ESTA ENVIANDO*/

        //SE ASIGNA A UNA VARIABLE DE TIPO ENTERO EL VALOR OBTENIDO DE LO SEGUNDOS EN CRONOMETRO 2
        int traedato  = cronometro_2.get_seconds();

        int nivelactual =4;


        intent.putExtra("respuesta",i);
        intent.putExtra("cronometro2",traedato);
        //con esta linea le paso a la califiacion el nivel en el que me encuentro para tener records diferentes por nivel
        intent.putExtra("queniveles",nivelactual);

        //SE PAUSA EL CRONOMETRO DEL NUEVO ILO CREADO
        //cronometro.pause();
        //cronometro.reiniciar();
        cronometro_2.pause();
        cronometro_2.reiniciar();
        //SE INICA LA ACTIVIDAD
        startActivity(intent);
        /*SE FINALIZA ESTA ACTIVIDAD CON EL FIN DE QUE QUE NO SE VUELVA A MOSTRAR
        * AL PRECIONAR EL BOTON ATRAS*/

    }

    @Override
    public void tiempo() {
               /* if(cronometro==null){
            cronometro = new Cronometro("cronometro",mi_crono);
            new Thread(cronometro).start();
        }*/
        if(cronometro_2==null){
            cronometro_2 = new Cronometro_2("cronometro_2");
            new Thread(cronometro_2).start();
        }

    }

    @Override
    public void quitavidas() {
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
            fin_juego_set(3);
        }

    }

    @Override
    public void cuentaatras() {
        countDownTimer= new CountDownTimer(tiempototal,1000){ //tiempototal sale de comunicacion niveles

            @Override
            public void onTick(long millisUntilFinished) {

                mi_crono.setText(" "+(millisUntilFinished/1000));
                click_tiempo.play(flujoDeMusica,0.2f,0.2f,0,0,1);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void onStop(){

        super.onStop();
        countDownTimer.cancel();
        //finish();

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
}

