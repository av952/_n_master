package siono.game.android.av.siono;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level_2 extends AppCompatActivity  implements Comunicacion_niveles,View.OnClickListener,
Frag_home.OnFragmentInteractionListener,Frag_levels.OnFragmentInteractionListener{

    private int p,op,cantidad,ran,bien,mal,cuantasvidas,pre;
    private ImageView vida,btn_si,btn_no, img_level_2,img_preg;
    public TextView mi_crono;
    private Random random = new Random();
    private long tiempoprimerclick;//para el metodo onbackpresed
    //llamando a calificacion
    Calificacion calificacion;
    //soundpoll
    private SoundPool ok,no;
    private int flujoDeMusica;

    //PARA INSTANCIAR EL CRONOMETRO
    private Cronometro cronometro = null;

    //BOTTON QUE MUESTRA LA PREGUNTA
    public Button btn_preguntado;
    //OPCION ALEATORIA PARA LA RPEGUNTA ECHO CON STRINGS



    //creando array para selecion de preguntas alaeatorias
    //String[] array_preguntas_cambiantes;
    String cambio;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_2);

        btn_no =(ImageView)findViewById(R.id.btnno);
        btn_no.setOnClickListener(this);
        btn_si =(ImageView)findViewById(R.id.btnsi);
        btn_si.setOnClickListener(this);
        //VIDA
        vida = (ImageView)findViewById(R.id.vidas);
        //IMAGENES DEL CENTRO
        img_level_2 = (ImageView)findViewById(R.id.imagenes_level_2);
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
        flujoDeMusica = ok.load(this,R.raw.oknino,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);


        //SOUNDPOOL infantil no
        no = new SoundPool(0, AudioManager.STREAM_MUSIC,0);//numero de veces,el flujo del sonido,calidad
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//para poder usar los botones de audio fisicos
        flujoDeMusica = no.load(this,R.raw.nonino,1);//[objeto_Spoundpool].load (Context context, int resId, int priority);


        //boton de la pregunta
        btn_preguntado = (Button)findViewById(R.id.elbotonquepregunta);



    }

    @Override
    public void evaluacion() {
        if(p<=7 && op==1 && cantidad<=imagenesfruver.length-1&& pre==1){//granja/si/granja=si
            ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p<=7 && op==2 && cantidad<=imagenesfruver.length-1&& pre==1){//granja/no/granja=no
           no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=7 && op==1 && cantidad<=imagenesfruver.length-1&& (pre==0 || pre ==2)){//granja/si/hogar/salvaje=no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=7 && op==2 && cantidad<=imagenesfruver.length-1&& (pre==0 ||pre==2)){//granja/no/hogar/salvaje=si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }else if(p>6 &&p<=16 && op==1 && cantidad<=imagenesfruver.length-1&& pre==0){//hogar/si/hogar=si
            ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p>6 &&p<=16 && op==2 && cantidad<=imagenesfruver.length-1&& pre==0){//hogar/no/hogar=no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>6 &&p<=16 && op==1 && cantidad<=imagenesfruver.length-1&& (pre==1 ||pre==2)){//hogar/si/granja/salvaje=no
            no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>6 &&p<=16 && op==2 && cantidad<=imagenesfruver.length-1&& (pre==1 ||pre==2)){//hogar/no/no es hogar = si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }

        else if(p>=17 && op==1 && cantidad<=imagenesfruver.length-1&& pre==2){//salvaje/si/salvaje = si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }
        else if(p>=17 && op==2 && cantidad<=imagenesfruver.length-1&& pre==2){//salvaje/no/salvaje = no
            no.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }
        else if(p>=17 && op==1 && cantidad<=imagenesfruver.length-1&& (pre==0||pre==1)){//salvaje/si/salvaje = no
            no.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }
        else if(p>=17 && op==2 && cantidad<=imagenesfruver.length-1&& (pre==0||pre==1)){//salvaje/no/salvaje = si
            ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }

        //SI PERDE SE EJECUTA ESTO------------------------------------------------------------------
        if(mal==3){
            fin_juego_set(2);
        }


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
    }

    @Override
    /*ESTE METODO QUE ES LLAMADO DE LA INTERFAZ COMUNICACIÓN NIVELES
    * ME ASIGNA UNA CALIFICACIÓN DEPENDIENDO EL NUMERO DE MALAS O BUENAS QUE TENGA*/
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
    /*ESTE METODO GENERAL UNA IMAGEN AL AZAR PARA SER MOSTRADA EN PANTALLA
    * GENERA TANTO LA PREGUNTA COMO LA IMAGEN CENTRAL*/
    public void azar() {

        //GENERA UN NUMERO ALEATORIO PARA LA PREGUNTA
        pre= random.nextInt(probandoestootro.length);

        p = random.nextInt(imagenes_animales.length);//da una imagen random
        ran = random.nextInt(array_pregunta.length);//pregunta random

        if(cantidad<= imagenes_animales.length-1){
            //btn_preguntado.setText(cambio);
            btn_preguntado = (Button)findViewById(R.id.elbotonquepregunta);
            btn_preguntado.setText(probandoestootro[pre]);


            //img_preg.setImageResource(array_pregunta[pre]);//aleatorio para la pregunta
            //btn_preguntado.setText("ahora si se le da la regalada gana de funcionar");
            img_level_2.setImageResource(imagenes_animales[p]);//aleatorio para la imagen
        }else {
            respuestaFinal(bien);
        }

    }

    @Override
    public void fin_juego_set(int i) {

        Intent intent = new Intent(this,Calificacion.class);
        /*PARA PODER TRANSFERIR INFORMACIÓN DE UNA VARIABLE A OTRA ES IMPORTANTE COLOCAR
        * PUTEXTRA (EL QUE LA RECIBE COLOCA PUT GET Y OTRO METODO DONDE ALMACENA
        * TODO LO QUE SE LE ESTA ENVIANDO*/

        intent.putExtra("respuesta",i);

        //SE PAUSA EL CRONOMETRO DEL NUEVO ILO CREADO
        cronometro.pause();

        //SE INICA LA ACTIVIDAD
        startActivity(intent);
        /*SE FINALIZA ESTA ACTIVIDAD CON EL FIN DE QUE QUE NO SE VUELVA A MOSTRAR
        * AL PRECIONAR EL BOTON ATRAS*/
        finish();

    }

    @Override
    public void tiempo() {
        if(cronometro==null){
            cronometro = new Cronometro("cronometro",mi_crono);
            new Thread(cronometro).start();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsi:
                cronometro.reiniciar();
                op=1;
                cantidad++;
                evaluacion();
                azar();

                break;
            case R.id.btnno:
                cronometro.reiniciar();
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
}
