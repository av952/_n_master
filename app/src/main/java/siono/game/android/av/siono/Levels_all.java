package siono.game.android.av.siono;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Levels_all extends AppCompatActivity implements View.OnClickListener,Frag_home.OnFragmentInteractionListener{

    private ImageView btn_1;
    private final int intervalo =2000;
    private long tiempoprimerclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_all);


        btn_1 =(ImageView)findViewById(R.id.btn_level_1);
        btn_1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_level_1:
                Intent i = new Intent(this,Level_1.class);
                startActivity(i);
                finish();
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
