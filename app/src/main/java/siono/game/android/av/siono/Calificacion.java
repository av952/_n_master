package siono.game.android.av.siono;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Calificacion extends AppCompatActivity implements Frag_levels.OnFragmentInteractionListener,
Frag_home.OnFragmentInteractionListener,View.OnClickListener{

    private int calificacion_int;
    private ImageView img_calif;
    private int getrespuesta;
    //lo de abajo es para el backonpresed
    private final int intervalo =2000;
    private long tiempoprimerclick;

    private Level_1 level_1 = new Level_1();

    private int[] respuestacalifica2={
            R.drawable.resp_pref_2_700x516,
            R.drawable.resp_bien_2_700x516,
            R.drawable.resp_fallo_2_700x516};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);

        Intent intent = new Intent(this,Level_1.class);

        //toma el paquete de datos de la actividad que la llamo
        Bundle datos = this.getIntent().getExtras();
        //se asigna los datos tomados a nuevas cariables
        int res = datos.getInt("respuesta");



        img_calif = (ImageView)findViewById(R.id.img_calificada);
        img_calif.setImageResource(respuestacalifica2[res]);

        img_calif.setOnClickListener(this);


    }

    public void respuestaporpuntuacion(int i){

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public int recivecalifica (int j){

        calificacion_int = j;
        return calificacion_int;
    }
    //ACCION REALIZADA CUANDO SE PRECIONA EL BOTON DE REGRESAR
    public void onBackPressed(){
        if(tiempoprimerclick+intervalo>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(this,"Vuelve a aprecionar para ir a niveles",Toast.LENGTH_SHORT).show();
        }
        tiempoprimerclick = System.currentTimeMillis();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_calificada:
                Intent i = new Intent(this,Levels_all.class);
                startActivity(i);
        }
    }
}
