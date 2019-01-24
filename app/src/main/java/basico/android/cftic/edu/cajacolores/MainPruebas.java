package basico.android.cftic.edu.cajacolores;

import com.google.gson.Gson;

public class MainPruebas {

    public static void main (String [] argumentos)
    {
        Puntacion puntacion = new Puntacion("Vale", 33);
        System.out.println(puntacion.toString());
        //VAMOS A PASAR DE objeto puntacion A JSON --> SERIALIZAR
        Gson gson = new Gson();
        String str_puntacion = gson.toJson(puntacion);
        System.out.println(str_puntacion);
        //VAMOS A PASAR DE JSON a objeto puntación --> DESERIALIZAR
        Puntacion p2 = gson.fromJson(str_puntacion, Puntacion.class);
        System.out.println("p2 = " + p2.toString());
    }
}
