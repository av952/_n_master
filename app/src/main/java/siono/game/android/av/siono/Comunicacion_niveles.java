package siono.game.android.av.siono;

import android.text.GetChars;

/**
 * Created by Usuario on 11/01/2017.
 */

public interface Comunicacion_niveles {

    int intervalo =2000;//para el metodo onbackpresed
    int tiempototal = 10000;


    //IMAGENES RANDOM-------------------------------------LEVEL_1-----------------------------------
     int[] imagenesfruver ={
            R.drawable.aasparagus256, R.drawable.aaubergine256,
            R.drawable.abeans256, R.drawable.abroccoli256,R.drawable.acabbage256,
            R.drawable.acarrot256,R.drawable.acauliflower256,
            R.drawable.achives256, R.drawable.aradish256,R.drawable.aonion256,R.drawable.apeas256,
            R.drawable.apumpkin256,R.drawable.asalad256,
            R.drawable.bapple256,R.drawable.bavocado256,R.drawable.bbanana256,
            R.drawable.bblueberries256, R.drawable.bcherries256,R.drawable.bcoconut256,
            R.drawable.bfig256,R.drawable.bgrapes256,
            R.drawable.blemon256,R.drawable.borange256,R.drawable.bpeach256,R.drawable.bpear256,
            R.drawable.bpineapple256,R.drawable.braspberry256,
            R.drawable.bstrawberry256,R.drawable.btomato256, R.drawable.bwatermelon256
    };


    //IMAGENES RANDOM-------------------------------------LEVEL_2-----------------------------------

    int[]imagenes_animales={
      R.drawable.farms_webp_1,R.drawable.farms_webp_2,R.drawable.farms_webp_3,R.drawable.farms_webp_4,
            R.drawable.farms_webp_5,R.drawable.farms_webp_6,R.drawable.farms_webp_7,R.drawable.home_webp_1,
            R.drawable.home_webp_2,R.drawable.home_webp_3,R.drawable.home_webp_4,R.drawable.home_webp_5,
            R.drawable.home_webp_6,R.drawable.home_webp_7,R.drawable.home_webp_8,R.drawable.home_webp_9,
            R.drawable.home_webp_10,R.drawable.jungle_web_1,R.drawable.jungle_web_2,R.drawable.jungle_web_3,
            R.drawable.jungle_web_4,R.drawable.jungle_web_5,R.drawable.jungle_web_6,R.drawable.jungle_web_7,
            R.drawable.jungle_web_8,R.drawable.jungle_web_9,R.drawable.jungle_web_10,R.drawable.jungle_web_11,
            R.drawable.jungle_web_12,R.drawable.jungle_web_13
    };
    //IMAGENES RANDOM-------------------------------------LEVEL_23
    int[]imagenes_animales_level3={
            R.drawable.ocean_webp_1,R.drawable.ocean_webp_2,R.drawable.ocean_webp_4,
            R.drawable.ocean_webp_5,R.drawable.ocean_webp_6,R.drawable.ocean_webp_7,R.drawable.ocean_webp_8,
            R.drawable.ocean_webp_9,
            R.drawable.home_webp_1,
            R.drawable.home_webp_2,R.drawable.home_webp_3,R.drawable.home_webp_4,R.drawable.home_webp_5,
            R.drawable.home_webp_6,R.drawable.home_webp_7,R.drawable.jungle_web_1,R.drawable.jungle_web_2,R.drawable.jungle_web_3,
            R.drawable.jungle_web_4,R.drawable.jungle_web_5,R.drawable.jungle_web_6,R.drawable.jungle_web_7,

            R.drawable.fly_1,R.drawable.fly_2,R.drawable.fly_3,R.drawable.fly_4,R.drawable.fly_5,
            R.drawable.home_webp_8,R.drawable.home_webp_9, R.drawable.home_webp_10

    };

    //preguntas
    int[]probandoestootro={
            R.string.casero,
            R.string.granja,
            R.string.salvaje,
    };

    int[]preguntas_level_3={
            R.string.terrestre,
            R.string.maritimo,
            R.string.aereo,
    };


    //ARRAY VIDAS----------------------------------------------------------------------------------
     int[] arrayvidas={
            R.drawable.vidas2_1,
            R.drawable.vidas2_2,
            R.drawable.vidas2_3,
    };

     int[]array_pregunta={
            R.string.fruta,
             R.string.verdura
    };



    void evaluacion();//evalua si la respuesta es correcta o no
    void respuestaFinal(int cantbuenas);//muestra imagen segun cantidad de buenas o malas
    void azar();//calcula al azar la imagen que se muestra
    void fin_juego_set(int i);
    void onBackPressed();
    void tiempo();
    void quitavidas();
    void cuentaatras();


}
