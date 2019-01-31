package basico.android.cftic.edu.cajacolores.pruebas;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import basico.android.cftic.edu.cajacolores.dto.Puntacion;

public class MainPruebas {

    public static void main (String [] argumentos)
    {
        Puntacion puntacion1 = new Puntacion("Vale", 1,null);
        Puntacion puntacion2 = new Puntacion("Vale", 50,null);
        Puntacion puntacion3 = new Puntacion("Vale", 22,null);

        List<Puntacion> lp = new ArrayList<Puntacion>(3);
        lp.add(puntacion1);
        lp.add(puntacion2);
        lp.add(puntacion3);

        System.out.println("ANTES "+lp);

        Collections.sort(lp);

        System.out.println("despues "+lp);


        /*System.out.println(puntacion.toString());


        //VAMOS A PASAR DE objeto puntacion A JSON --> SERIALIZAR
        Gson gson = new Gson();
        String str_puntacion = gson.toJson(puntacion);
        System.out.println(str_puntacion);
        //VAMOS A PASAR DE JSON a objeto puntación --> DESERIALIZAR
        Puntacion p2 = gson.fromJson(str_puntacion, Puntacion.class);
        System.out.println("p2 = " + p2.toString());
    */
    }
}
