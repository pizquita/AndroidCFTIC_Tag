package basico.android.cftic.edu.cajacolores;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Clase que sirve para almacenar las prefrencias de usuario
 * (de momento sólo el último tiempo)
 */
public class Preferencias {

    private static final String NOMBRE_FICHERO="record";
    private static final String CLAVE_TIEMPO="tiempo";

    public static long leerRecord (Context context)
    {
        long record=0;

            SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
            record = sp.getLong(CLAVE_TIEMPO, 0);

        return record;
    }

    public static void guardarRecord (long nuevo_record, Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putLong(CLAVE_TIEMPO, nuevo_record);
        ed.commit();
    }

}
