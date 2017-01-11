package siono.game.android.av.siono;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by francis on 21/06/15.
 */
public class Cronometro implements Runnable
{
    // Atributos privados de la clase
    private TextView etiq;                  // Etiqueta para mostrar la información
    private String nombrecronometro;        // Nombre del cronómetro
    private int segundos, minutos, horas;   // Segundos, minutos y horas que lleva activo el cronómetro
    private Handler escribirenUI;           // Necesario para modificar la UI
    private Boolean pausado;                // Para pausar el cronómetro
    private String salida;                  // Salida formateada de los datos del cronómetro

    private Comunicacion_niveles comunicacion_niveles;

    /**
     * Constructor de la clase
     * @param nombre Nombre del cronómetro
     * @param etiqueta Etiqueta para mostrar información
     */
    public Cronometro(String nombre, TextView etiqueta)
    {
        etiq = etiqueta;
        salida = "";
        segundos = 10;
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
                    segundos--;
                    salida += segundos;
                    // Modifico la UI
                    try
                    {
                        escribirenUI.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if (salida.equals("-5")){

                                }
                                etiq.setText(salida);

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

}