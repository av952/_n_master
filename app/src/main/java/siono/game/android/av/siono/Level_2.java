package siono.game.android.av.siono;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Level_2 extends AppCompatActivity  implements Comunicacion_niveles {

    private int p,op,cantidad,ran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_2);
    }

    @Override
    public void evaluacion() {
        if(p<=12 && op==1 && cantidad<=imagenesfruver.length-1&& ran==1){//verdura/si/?verdura = bien
            //ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p<=12 && op==2 && cantidad<=imagenesfruver.length-1&& ran==1){//verdura/no verdura/ = mal
           // no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=12 && op==1 && cantidad<=imagenesfruver.length-1&& ran==0){//verdura/si/fruta = mal
            //no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p<=12 && op==2 && cantidad<=imagenesfruver.length-1&& ran==0){//verdura/no/fruta =bien
            //ok.play(flujoDeMusica,1,1,0,0,1);
            bien++;
        }else if(p>=13&& op==1 && cantidad<=imagenesfruver.length-1&& ran==0){
            //ok.play(flujoDeMusica,1,1,0,0,1);//sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
            bien++;
        }else if(p>=13 && op==2 && cantidad<=imagenesfruver.length-1&& ran==0){
            //no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>=13 && op==1 && cantidad<=imagenesfruver.length-1&& ran==1){
            //no.play(flujoDeMusica,1,1,0,0,1);
            mal++;
        }else if(p>=13 && op==2 && cantidad<=imagenesfruver.length-1&& ran==1){
            //ok.play(flujoDeMusica,1,1,0,0,1);
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
    public void respuestaFinal(int cantbuenas) {

    }

    @Override
    public void azar() {

    }

    @Override
    public void fin_juego_set(int i) {

    }

    @Override
    public void tiempo() {

    }
}
