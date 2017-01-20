package siono.game.android.av.siono;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.net.Uri;
import android.service.quicksettings.Tile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.XMLReader;

import java.net.URLClassLoader;

public class Calificacion extends AppCompatActivity implements Frag_levels.OnFragmentInteractionListener,
Frag_home.OnFragmentInteractionListener,View.OnClickListener{

    private int calificacion_int;
    private ImageView img_calif;
    private int getrespuesta;
    //lo de abajo es para el backonpresed
    private final int intervalo =2000;
    private long tiempoprimerclick;

    private int estrellas;


    //VARIABLES PARA GURDAR PUNTUACION
    private int  max =0;


    private int res,res2;

    private Level_1 level_1 = new Level_1();

    private int[] respuestacalifica2={
            R.drawable.resp_pref_3_700x516,
            R.drawable.resp_bien_3_700x516,
            R.drawable.resp_fallo_3_700x516};

    private int[] array_respuesta_txt={
      R.string.perfecto,
            R.string.bien_echo,
            R.string.fallaste

    };

    private TextView tv_califica,tv_tiempo;

    SharedPreferences share;

    Tile tile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);

        Intent intent = new Intent(this,Level_1.class);

        //toma el paquete de datos de la actividad que la llamo
        Bundle datos = this.getIntent().getExtras();
        //se asigna los datos tomados a nuevas cariables
        res = datos.getInt("respuesta");

        //recogiendo datos de cronometro2
        Bundle datos2 = this.getIntent().getExtras();
        res2 = datos2.getInt("cronometro2");


        //cargarpreferencias();

        img_calif = (ImageView)findViewById(R.id.img_calificada);
        tv_califica = (TextView)findViewById(R.id.txt_respuesta);

        //TOMA EL TIEMPO TOTAL DEL JUEGO
        tv_tiempo = (TextView)findViewById(R.id.txt_cant_time);
        tv_tiempo.setText(Integer.toString(res2)+" segundos");

        tv_califica.setText(array_respuesta_txt[res]);

        img_calif.setImageResource(respuestacalifica2[res]);

        img_calif.setOnClickListener(this);


        share = getSharedPreferences("guardadodeniveles",Context.MODE_PRIVATE);
        colocandoestrellas();


    }

    private void colocandoestrellas() {

        estrellas = share.getInt("cuanta_estrella",0);

        switch (res){
            case 0:
                estrellas=estrellas+3;
                break;
            case 1:
                estrellas=estrellas+1;
                break;
            case 2:
                estrellas=estrellas+0;

        }

        SharedPreferences.Editor editor = share.edit();
        editor.putInt("cuanta_estrella",estrellas);
        editor.commit();

    }


    /*public void cargarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        tv_tiempo = (TextView)findViewById(R.id.txt_cant_time);
        (preferences.getString("tiempo"," "));

        if(!preferences.getString("tiempo"," ").equals(Integer.toString(res2))){
            guardarpreferencias();
        }

    }
    public void guardarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        // con este empezamos a editar nuestras preferencias
        SharedPreferences.Editor editor = preferences.edit();
        //colocamos la llave "tiempo" y el valor que queremos asinar
        editor.putInt("tiempo",res2);
        //con comit hacemos que los cambios sean guardados
        editor.commit();
        //tv_tiempo = (TextView)findViewById(R.id.txt_cant_time);
        //tv_tiempo.setText(Integer.toString(res2));
    }*/

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
                finish();
                break;

        }
    }
}
