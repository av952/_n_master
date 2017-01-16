package siono.game.android.av.siono;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener,Levels.OnFragmentInteractionListener,
Frag_creditos.OnFragmentInteractionListener,Frag_levels.OnFragmentInteractionListener,
Frag_home.OnFragmentInteractionListener{

    ImageView btnbegin,btnhtp,btnlevels;
    public SoundPool sp;
    public int flujoDeMusica;
    private final int intervalo =2000;
    private long tiempoprimerclick;

    FragmentManager fragmentManager;
    FragmentTransaction fragTran_Creditos;
    //Frac_htp frac1;
    Levels levels;
    Frag_creditos frag_creditos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //se crea la interfas grafica
        btnbegin =(ImageView) findViewById(R.id.btnBegin);
        //btnhtp =(ImageView) findViewById(R.id.btnHtp);
        btnlevels =(ImageView) findViewById(R.id.btncreditos);
        btnbegin.setOnClickListener(this);
        //btnhtp.setOnClickListener(this);
        btnlevels.setOnClickListener(this);

        //SOUNDPOOL BUTTONS
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoDeMusica = sp.load(this,R.raw.click,1);

        //FRAGMENT

        fragmentManager = getSupportFragmentManager();
        fragTran_Creditos = fragmentManager.beginTransaction();
        frag_creditos = new Frag_creditos();//relaiona el fragmento
        fragTran_Creditos.replace(R.id.contenedorenmain,frag_creditos);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.btnHtp:
                sp.play(flujoDeMusica,1,1,0,0,1);
                //fragmentTransaction.commit();
                break;*/
            case R.id.btnBegin:
                sp.play(flujoDeMusica,1,1,0,0,1);
                Intent i = new Intent(this,Levels_all.class);
                startActivity(i);
                finish();
                break;
            case R.id.btncreditos:
                sp.play(flujoDeMusica,1,1,0,0,1);
               /* Intent ir3 = new Intent(this,Frag_creditos.class);
                startActivity(ir3);*/

                fragTran_Creditos.commit();
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
}
