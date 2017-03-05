package siono.game.android.av.siono;

import android.text.GetChars;

import java.util.ArrayList;

/**
 * Created by Usuario on 11/01/2017.
 */

public interface Comunicacion_niveles {

    int intervalo =2000;//para el metodo onbackpresed
    int tiempototal = 5000;


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
    //IMAGENES RANDOM-------------------------------------LEVEL_3
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
    //IMAGENES RANDOM-------------------------------------LEVEL_4

    int[] imagenes_level_4={
            R.drawable.aire_1,R.drawable.aire_2,R.drawable.aire_3,R.drawable.aire_4,
            R.drawable.aire_5,R.drawable.aire_6,R.drawable.aire_7,R.drawable.aire_8,

            R.drawable.tierra_1,R.drawable.tierra_2,R.drawable.tierra_3,R.drawable.tierra_4,
            R.drawable.tierra_5,R.drawable.tierra_6,R.drawable.tierra_7,R.drawable.tierra_8,
            R.drawable.tierra_9,R.drawable.tierra_10,

            R.drawable.agua_1,R.drawable.agua_2,R.drawable.agua_3,R.drawable.agua_4,R.drawable.agua_5,
            R.drawable.agua_6,R.drawable.agua_7,R.drawable.agua_8
    };

    int[] imagenes_level_5_dulce={
      R.drawable.dulce01,
            R.drawable.dulce02,
            R.drawable.dulce03,
            R.drawable.dulce04,
            R.drawable.dulce05,
            R.drawable.dulce06,
            R.drawable.dulce07,
            R.drawable.dulce08,
            R.drawable.dulce09,
            R.drawable.dulce10,
            R.drawable.dulce11,
            R.drawable.dulce12,
            R.drawable.dulce13,
            R.drawable.dulce14,
            R.drawable.dulce15,
            R.drawable.dulce15,
            R.drawable.dulce17,
            R.drawable.dulce18,
            R.drawable.dulce19,
            R.drawable.dulce21,
            R.drawable.dulce22,
            R.drawable.dulce23,
            R.drawable.dulce24,
            R.drawable.dulce25,

    };

    int[] imagenes_level_5_salado={
      R.drawable.salado01,
            R.drawable.salado03,
            R.drawable.salado04,
            R.drawable.salado05,
            R.drawable.salado06,
            R.drawable.salado07,
            R.drawable.salado08,
            R.drawable.salado09,
            R.drawable.salado10,
            R.drawable.salado11,
            R.drawable.salado12
    };


    //preguntas level 4
    int[]preguntas_level_4={
            R.string.aire,
            R.string.tierra,
            R.string.agua
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

    int[] preguntas_level_5={
            R.string.dulce,
            R.string.salado
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
