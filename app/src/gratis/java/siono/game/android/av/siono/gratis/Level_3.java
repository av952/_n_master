package siono.game.android.av.siono.gratis;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
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

public class Level_3 extends AppCompatActivity  implements Comunicacion_niveles, Frag_home.OnFragmentInteractionListener,
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

    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_3);

        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        interruptor = true;
        cuentaatras();

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

        //PARA QUE LA PRIMERA IMAGEN SEA RANDOM
        azar();
        //LLAMA AL METODO DENTRO DE ESTA CLASE QUE CREA EL NUEVO HILO
        tiempo();

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


        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anima_boton_sino);
        btn_no.startAnimation(animation);
        btn_si.startAnimation(animation);
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

                btn_no.clearAnimation();
                btn_si.clearAnimation();

                break;
            case R.id.btnno:
                countDownTimer.cancel();
                cuentaatras();
                //cronometro.reiniciar();
                op=2;
                cantidad++;
                evaluacion();
                azar();

                btn_no.clearAnimation();
                btn_si.clearAnimation();

                break;
        }

    }

    @Override
    public void evaluacion() {
        if(p<=7 && op==1 && cantidad<=imagenesfruver.length-1&& pre==1){//maritimo/si/maritomo=si
            ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p<=7 && op==2 && cantidad<=imagenesfruver.length-1&& pre==1){//maritimo/no/maritimo=no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=7 && op==1 && cantidad<=imagenesfruver.length-1&& (pre==0 || pre ==2)){//maritimo/si/terrestre/aereo=no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=7 && op==2 && cantidad<=imagenesfruver.length-1&& (pre==0 ||pre==2)){//maritimo/no/terrestre/aereo=si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }else if(p>7 &&p<=22 && op==1 && cantidad<=imagenesfruver.length-1&& pre==0){//terrestre/si/terrestre=si
            ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p>7 &&p<=21 && op==2 && cantidad<=imagenesfruver.length-1&& pre==0){//terrestre/no/terrestre=no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>7 &&p<=21 && op==1 && cantidad<=imagenesfruver.length-1&& (pre==1 ||pre==2)){//terrestre/si/maritmo/aereo=no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>7 &&p<=21 && op==2 && cantidad<=imagenesfruver.length-1&& (pre==1 ||pre==2)){//terrestre/no/maritimo/aereo = si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }

        else if(p>=21 && op==1 && cantidad<=imagenesfruver.length-1&& pre==2){//aereo/si/aereo= si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }
        else if(p>=21 && op==2 && cantidad<=imagenesfruver.length-1&& pre==2){//aereo/no/aereo = no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }
        else if(p>=21 && op==1 && cantidad<=imagenesfruver.length-1&& (pre==0||pre==1)){//aereo/si/terrestre/maritomo = no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }
        else if(p>=21 && op==2 && cantidad<=imagenesfruver.length-1&& (pre==0||pre==1)){//aereo/no/terrestre/maritimo = si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }

        //SI PERDE SE EJECUTA ESTO------------------------------------------------------------------
        quitavidas();

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

        if(cantbuenas==imagenes_animales_level3.length-1){
            calif=0;

        }else if(cantbuenas<=imagenes_animales_level3.length-2){
            calif=1;

        }*/

        fin_juego_set(calif);

    }

    @Override
    public void azar() {
        //GENERA UN NUMERO ALEATORIO PARA LA PREGUNTA
        pre= random.nextInt(preguntas_level_3.length);

        p = random.nextInt(imagenes_animales_level3.length);//da una imagen random
        ran = random.nextInt(preguntas_level_3.length);//pregunta random

        if(cantidad<= imagenes_animales_level3.length-1){
            //btn_preguntado.setText(cambio);
            btn_preguntado = (Button)findViewById(R.id.elbotonquepregunta);
            btn_preguntado.setText(preguntas_level_3[pre]);


            //img_preg.setImageResource(array_pregunta[pre]);//aleatorio para la pregunta
            //btn_preguntado.setText("ahora si se le da la regalada gana de funcionar");
            img_level_2.setImageResource(imagenes_animales_level3[p]);//aleatorio para la imagen
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
        int nivelactual =3;


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
