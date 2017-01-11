package siono.game.android.av.siono;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Calificacion extends AppCompatActivity implements Frag_levels.OnFragmentInteractionListener,
Frag_home.OnFragmentInteractionListener{

    private int calificacion_int;
    private ImageView img_calif;
    private int getrespuesta;

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
}
