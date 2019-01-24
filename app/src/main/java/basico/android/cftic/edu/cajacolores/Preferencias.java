package basico.android.cftic.edu.cajacolores;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Clase que sirve para almacenar las prefrencias de usuario
 * (de momento sólo el último tiempo)
 */
public class Preferencias {

    private static final String NOMBRE_FICHERO="record";
    private static final String CLAVE_TIEMPO="tiempo";
    private static final String CLAVE_PRIMERA_VEZ="primera";
    private static final String CLAVE_NOMBRE_USUARIO="nombre";

    private static final String CLAVE_PUNTAUCION="puntuacion";



    public static String leerNombre (Context context)
    {
        String nombre = null;

        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        nombre = sp.getString(CLAVE_NOMBRE_USUARIO, null);

        return nombre;
    }

    public static void guardarNombre (String nombre, Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(CLAVE_NOMBRE_USUARIO, nombre);
        ed.commit();
    }
    public static long leerRecord (Context context)
    {
        long record=0;

            SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
            record = sp.getLong(CLAVE_TIEMPO, 0);

        return record;
    }

    public static void guardarRecord (String nombre,  long nuevo_record, Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putLong(CLAVE_TIEMPO, nuevo_record);
        ed.putString(CLAVE_NOMBRE_USUARIO, nombre);
        ed.commit();
    }

    public static void guardarRecord (Puntacion p, Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        Gson gson = new Gson();
        String puntuacion_json = gson.toJson(p);
        ed.putString(CLAVE_PUNTAUCION, puntuacion_json);
        ed.commit();
    }

    public static boolean primeraVez (Context context)
    {
        boolean primera_vez = false;

        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        primera_vez = sp.getBoolean(CLAVE_PRIMERA_VEZ, true);

        return primera_vez;
    }

    public static void marcarPrimeraVez (Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(CLAVE_PRIMERA_VEZ, false);
        ed.commit();
    }

}
