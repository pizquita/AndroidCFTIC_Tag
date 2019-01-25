package basico.android.cftic.edu.cajacolores;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Clase que sirve para almacenar las prefrencias de usuario
 * (de momento sólo el último tiempo)
 */
public class Preferencias {

    //FICHERO DONDE TENGO EL NOMBRE DEL USUARIO EN CURSO E INFORMACIÓN DE CONTROL
    private static final String NOMBRE_FICHERO_CONTROL ="control";
    //FICHERO DONDE GUARDO LA LISTA DE TIEMPOS
    private static final String NOMBRE_FICHERO_RECORD ="record";

    //PARA SABER SI HA ENTRADO O NO
    private static final String CLAVE_PRIMERA_VEZ="primera";
    //CLAVE PARA SABER CUANTOS RECORDS LLEVO GUARDADOS
    private static final String CLAVE_CONTADOR="num_record";
    //CLAVE PARA SABER EL NOMBRE DE USUARIO EN CURSO
    private static final String CLAVE_NOMBRE_USUARIO="nombre";




    public static String leerNombre (Context context)
    {
        String nombre = null;

        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO_CONTROL, Context.MODE_PRIVATE);
        nombre = sp.getString(CLAVE_NOMBRE_USUARIO, null);

        return nombre;
    }

    public static void guardarNombre (String nombre, Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO_CONTROL, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(CLAVE_NOMBRE_USUARIO, nombre);
        ed.commit();
    }


    public static void guardarRecord (Puntacion p, Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO_RECORD, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        Gson gson = new Gson();
        String puntuacion_json = gson.toJson(p);
        int num_record = leerContador(context);
        ed.putString(num_record+"", puntuacion_json);
        ed.commit();
        incrementarContador(context);

    }

    public static boolean primeraVez (Context context)
    {
        boolean primera_vez = false;

        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO_CONTROL, Context.MODE_PRIVATE);
        primera_vez = sp.getBoolean(CLAVE_PRIMERA_VEZ, true);

        return primera_vez;
    }


    public static void marcarPrimeraVez (Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO_CONTROL, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(CLAVE_PRIMERA_VEZ, false);
        ed.commit();
    }


    public static int leerContador(Context context)
    {
        int nconta=0;

            SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO_CONTROL, Context.MODE_PRIVATE);
            nconta = sp.getInt(CLAVE_CONTADOR, 0);

        return nconta;
    }
    public static void incrementarContador (Context context)
    {
        int n = leerContador(context);
        n = n+1;
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO_CONTROL, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(CLAVE_CONTADOR, n);
        ed.commit();
    }
}
