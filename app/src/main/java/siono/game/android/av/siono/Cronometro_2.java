package siono.game.android.av.siono;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;


public class Cronometro_2 implements Runnable
{
    // Atributos privados de la clase
    //private TextView etiq;            // Etiqueta para mostrar la información
    private String nombrecronometro;        // Nombre del cronómetro
    public int segundos, minutos, horas;   // Segundos, minutos y horas que lleva activo el cronómetro
    private Handler escribirenUI;           // Necesario para modificar la UI
    private Boolean pausado;                // Para pausar el cronómetro
    public String salida;                  // Salida formateada de los datos del cronómetro


    public Cronometro_2(String nombre)
    {
        //etiq = etiqueta;
        salida = "";
        segundos = 0;
        minutos = 0;
        horas = 0;
        nombrecronometro = nombre;
        escribirenUI = new Handler();
        pausado = Boolean.FALSE;


    }

    @Override
    /**
     * Acción del cronómetro, contar tiempo en segundo plano
     */
    public void run()
    {
        try
        {
            while(Boolean.TRUE)
            {
                Thread.sleep(1000);
                salida = "";
                if( !pausado )
                {
                    segundos++;
                    set_seconds(segundos);
                    //salida += segundos;
                    // Modifico la UI
                    try
                    {
                        escribirenUI.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                               // set_seconds(segundos);
                            }
                        });
                    }
                    catch (Exception e)
                    {
                        Log.i("Cronometro", "Error en el cronometro " + nombrecronometro + " al escribir en la UI: " + e.toString());
                    }
                }
            }
        }
        catch (InterruptedException e)
        {
            Log.i("Cronometro", "Error en el cronometro " + nombrecronometro + ": " + e.toString());
        }
    }


    /**
     * Reinicia el cronómetro
     */
    public void reiniciar()
    {
        segundos = 10;
        /*minutos = 0;
        horas = 0;*/
        pausado = Boolean.FALSE;
    }

    /**
     * Pausa/Continua el cronómetro
     */
    public void pause()
    {
        pausado = !pausado;
    }

    public int get_seconds(){
        ///set_seconds();
        return segundos;
    }

    public void set_seconds(int seg){

        this.segundos = seg;

    }


}
