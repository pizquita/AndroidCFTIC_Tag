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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //obtener el USUARIO
        String n = getIntent().getStringExtra("USUARIO");
        Log.d("MIAPP", "Nombre = " +n);

        this.nveces = 0;
        this.color_tocado = ResourcesCompat.getColor(getResources(), R.color.tocado, null);
        //LEO EL RECORD
        long record_actual = Preferencias.leerRecord(this);
        Log.d("MIAPP", "Record actual "+record_actual);
}

    private void initCrono ()
    {
        Chronometer c = findViewById(R.id.crono);
        c.setBase(SystemClock.elapsedRealtime());
        c.start();
    }
    public void ocultarBoton (View v)
    {
        Log.d("MIAPP", "Tocó el botón");
        //TODO quitar el botón u ocultarlo
        v.setVisibility(View.GONE);
        this.tinicial = System.currentTimeMillis();
        initCrono ();

    }

    private void cerrar (long tiempo_total)
    {
        long segundos = tiempo_total/1000;
        Toast toast = Toast.makeText(this, segundos+" segundos", Toast.LENGTH_SHORT);
        toast.show();//informo
        finish();//cierro
    }
    public void cambiaColor(View v)
    {
        Log.d("MIAPP", "TOCÓ CAJA");
        //v.setVisibility(View.GONE);
        Object tag = v.getTag();//obtengo la info asociada
        if (tag==null)
        {
                this.nveces++;
                v.setTag(true);//le hago una marquita a la caja tocada
                v.setBackgroundColor(this.color_tocado);
                if (this.nveces==12)
                {
                    this.tfinal = System.currentTimeMillis();
                    long total = tfinal-tinicial;
                    //GUARDAR RECORD
                    Preferencias.guardarRecord(total, this);
                    cerrar(total);

                }

        }





    }
}
