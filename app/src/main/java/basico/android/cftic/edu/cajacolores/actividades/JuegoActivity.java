package basico.android.cftic.edu.cajacolores.actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import basico.android.cftic.edu.cajacolores.util.Preferencias;
import basico.android.cftic.edu.cajacolores.dto.Puntacion;
import basico.android.cftic.edu.cajacolores.R;

public class JuegoActivity extends AppCompatActivity {


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

    private void ocultarActionBar ()
    {
        getSupportActionBar().hide();
    }

    private void quitarTituloBarra ()
    {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void volverAJugar (View v)
    {
        //Intent replay = new Intent(this, JuegoActivity.class);
        //startActivity(replay);
        //finish();
        recreate();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juego);

        //así dibujo la flecha de navegación estandar atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //probar a decomentar si se quiere
        //ocultarActionBar();
        //quitarTituloBarra();



        this.nombre_usuario = obtenerNombre();//obtengo el nombre
        getSupportActionBar().setSubtitle(this.nombre_usuario);
        this.nveces = 0;//inicio el contador de toques
        this.color_tocado = ResourcesCompat.getColor(getResources(), R.color.tocado, null);//obtengo el color

}

    private void initCrono ()
    {
        Chronometer c = findViewById(R.id.crono);
        c.setBase(SystemClock.elapsedRealtime());
        c.start();
    }

    private void pararCrono ()
    {
        Chronometer c = findViewById(R.id.crono);
        c.stop();
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


    private void informarConToast (long tiempo_total, String nombre)
    {
        Toast toast = Toast.makeText(this, "Nombre = " + nombre + " "+tiempo_total+" segundos", Toast.LENGTH_SHORT);
        toast.show();//informo
    }

    private void informarConSnackBar (long tiempo_total, String nombre)
    {
        View v = findViewById(R.id.fab);
        Snackbar s =  Snackbar.make(v, "USUARIO " +this.nombre_usuario, Snackbar.LENGTH_LONG);
        s.setAction("TIEMPO " + tiempo_total, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MIAPP", "Esto se ejecuta al tocar el snack");


            }
        });
        s.show();
    }

    private void reproducirFin() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.fin);
        mp.start();
    }

    private void mostrarReplay ()
    {
        ImageView iv = findViewById(R.id.img_volver);
        iv.setClickable(true);
        iv.setVisibility(View.VISIBLE);

    }

    //Informamos del tiempo con un toast y salimos
    private void cerrar (long tiempo_total, String nombre)
    {
        long segundos = tiempo_total/1000;
        pararCrono();
        //informarConToast(tiempo_total, nombre);
        informarConSnackBar(tiempo_total, nombre);
        reproducirFin();
        mostrarReplay();




        //finishAffinity();//cierro
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
                    Bitmap mybit = null;
                    Puntacion p = new Puntacion(this.nombre_usuario, total, mybit);//creo la puntuación obtenida
                    Preferencias.guardarRecord(p,this);//la guardo
                    cerrar(total, this.nombre_usuario);//salgo e informo


                }//no ha llegado al final, luego sigo, no hago nada

        }//el caso contrario la caja ya habría sido tocada, luego no hago nada

    }


    /**
     * Método que sirve para definir mi propio menú superior
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sup, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Recibimos el evento sobre una opcion del menú superior
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO permitir cambiar de nombre al usuario

        switch (item.getItemId())
        {

            case R.id.cambiar_usuario:
                Log.d("MIAPP", "Tocó cambiar de nombre");
                Intent i = new Intent(this, NombreActivity.class);
                i.putExtra("DEVUELTA", true);
                startActivityForResult(i, 305);
                break;
            case R.id.hacer_foto:
                Log.d("MIAPP", "Tocó hacer foto");
                Intent i_foto = new Intent(this, FotoActivity.class);
                startActivity(i_foto);
                break;
            case android.R.id.home:
                Log.d("MIAPP", "Tocó ir hacia atrás");
                super.onBackPressed();
                break;
            case R.id.records:
                Log.d("MIAPP", "Tocó hacer foto");
                Intent i_record = new Intent(this, MostrarRecords.class);
                startActivity(i_record);
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Log.d("MIAPP", "Ha vuelto");
        if (requestCode == 305)
        {
            if (resultCode == RESULT_OK)
            {
                String nombre_nuevo = data.getStringExtra("NOMBRE_NUEVO");
                Preferencias.guardarNombre(nombre_nuevo, this);
                getSupportActionBar().setSubtitle(nombre_nuevo);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
