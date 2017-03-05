package siono.game.android.av.siono.gratis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import siono.game.android.av.siono.R;



public class Menu extends AppCompatActivity implements View.OnClickListener,
Frag_creditos.OnFragmentInteractionListener,Frag_levels.OnFragmentInteractionListener,
Frag_home.OnFragmentInteractionListener{

    ImageView btnbegin,resetear,btnlevels;
    public SoundPool sp;
    public int flujoDeMusica;
    private final int intervalo =2000;
    private long tiempoprimerclick;

    FragmentManager fragmentManager;
    FragmentTransaction fragTran_Creditos;
    //Frac_htp frac1;
    Frag_creditos frag_creditos;


    //para resetear
      SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //se crea la interfas grafica
        btnbegin =(ImageView) findViewById(R.id.btnBegin);
        //btnhtp =(ImageView) findViewById(R.id.btnHtp);
        //btnlevels =(ImageView) findViewById(R.id.btncreditos);
        btnbegin.setOnClickListener(this);
        //btnhtp.setOnClickListener(this);
        //btnlevels.setOnClickListener(this);

        //SOUNDPOOL BUTTONS
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujoDeMusica = sp.load(this,R.raw.click,1);

        //FRAGMENT

        fragmentManager = getSupportFragmentManager();
        fragTran_Creditos = fragmentManager.beginTransaction();
        frag_creditos = new Frag_creditos();//relaiona el fragmento
        fragTran_Creditos.replace(R.id.contenedorenmain,frag_creditos);



        //para resetear valores guardados

        resetear = (ImageView)findViewById(R.id.creditos);
        resetear.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("guardadodeniveles", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.creditos:
                sp.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                //PARE RESETEAR LA APP
                /*SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("activacion_level2",false);
                editor.putBoolean("activacion_level3",false);
                editor.putBoolean("activacion_level4",false);
                editor.putInt("cuanta_estrella",0);
                editor.putFloat("opacidad2",0.5f);
                editor.putFloat("opacidad3",0.5f);e
                editor.putFloat("opacidad4",0.5f);
                editor.putInt("tiempomaximo",120);

                editor.commit();*/
                fragTran_Creditos.commit();
                //fragmentTransaction.commit();
                break;
            case R.id.btnBegin:
                sp.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                Intent i = new Intent(this,Levels_all.class);
                startActivity(i);
                finish();
                break;
          /* case R.id.btncreditos:
                sp.play(flujoDeMusica,0.5f,0.5f,0,0,1);
                Intent ir3 = new Intent(this,Frag_creditos.class);
                startActivity(ir3);

                break;*/

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
