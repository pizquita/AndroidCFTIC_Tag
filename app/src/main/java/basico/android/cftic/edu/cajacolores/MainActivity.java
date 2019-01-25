package basico.android.cftic.edu.cajacolores;

import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private int color_tocado;
    private int nveces;
    private long tinicial;
    private long tfinal;
    private String nombre_usuario;

    public static final int NCAJAS = 12;

    private String obtenerNombre ()
    {
        String nombre = null;

        if (getIntent().getExtras()!=null)
        {
            Log.d("MIAPP", "Trae el nombre del intent");
            nombre = getIntent().getStringExtra("NOMBRE_USUARIO");
            Preferencias.guardarNombre(nombre, this);
        } else {
            nombre = Preferencias.leerNombre(this);
        }


        return nombre;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.nombre_usuario = obtenerNombre();//obtengo el nombre
        this.nveces = 0;//inicio el contador de toques
        this.color_tocado = ResourcesCompat.getColor(getResources(), R.color.tocado, null);//obtengo el color

}

    private void initCrono ()
    {
        Chronometer c = findViewById(R.id.crono);
        c.setBase(SystemClock.elapsedRealtime());
        c.start();
    }

    /**
     * Iniciamos el juego: quitamos el botón e iniciamos el crono
     * @param v
     */
    public void ocultarBoton (View v)
    {
        Log.d("MIAPP", "Tocó el botón");
        v.setVisibility(View.GONE);
        this.tinicial = System.currentTimeMillis();
        initCrono ();

    }


    //Informamos del tiempo con un toast y salimos
    private void cerrar (long tiempo_total, String nombre)
    {
        long segundos = tiempo_total/1000;
        Toast toast = Toast.makeText(this, "Nombre = " + nombre + " "+segundos+" segundos", Toast.LENGTH_SHORT);
        toast.show();//informo
        finishAffinity();//cierro
    }


    /**
     * Ha tocado una caja la cambio de color y actualizo la cuenta
     * @param v
     */
    public void cambiaColor(View v)
    {
        Log.d("MIAPP", "TOCÓ CAJA");
        Object tag = v.getTag();//obtengo la info asociada
        if (tag==null) //la caja ha sido tocada si se cumple esta condición
        {
                this.nveces++;
                v.setTag(true);//le hago una marquita a la caja tocada
                v.setBackgroundColor(this.color_tocado);
                if (this.nveces==NCAJAS) //si ocurre esto, ya ha tocado todas las cajas
                {
                    this.tfinal = System.currentTimeMillis();//obtengo el tiempo actual
                    long total = tfinal-tinicial;//calculo el tiempo transcurrido
                    Puntacion p = new Puntacion(this.nombre_usuario, total);//creo la puntuación obtenida
                    Preferencias.guardarRecord(p,this);//la guardo
                    cerrar(total, this.nombre_usuario);//salgo e informo

                }//no ha llegado al final, luego sigo, no hago nada

        }//el caso contrario la caja ya habría sido tocada, luego no hago nada





    }
}
