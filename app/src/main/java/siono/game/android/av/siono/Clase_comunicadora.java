package siono.game.android.av.siono;

/**
 * Created by Usuario on 19/01/2017.
 */

public class Clase_comunicadora {

    public int recibido;


    public Clase_comunicadora() {
        super();

        recibido=0;
    }

    public void set_recibe(int i){
        this.recibido =i;

    }
    public int get_recibe(){
        return this.recibido;

    }
}
