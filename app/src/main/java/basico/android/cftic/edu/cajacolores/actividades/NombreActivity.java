package basico.android.cftic.edu.cajacolores.actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import basico.android.cftic.edu.cajacolores.util.Preferencias;
import basico.android.cftic.edu.cajacolores.R;


/**
 * ACTIVIDAD PRINCIPAL (Main Intent Filter Mainfest)
 */
public class NombreActivity extends AppCompatActivity {


    private boolean devuelta;

    private void lanzarMostrarRecords ()
    {
        Intent i = new Intent(this, MostrarRecords.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        //lanzarMostrarRecords();

       if (Preferencias.primeraVez(this)) //si entra la primera vez
        {
            Preferencias.marcarPrimeraVez(this); //lo marco
        }
            else
                {
                    this.devuelta = getIntent().getBooleanExtra("DEVUELTA", false);
                    if (!this.devuelta) {
                        aJugar(); //si no, le paso directo a jugar
                }

        }
    }



    private String obtenerNombre() {
        String nombre = null;

        EditText editText = (EditText) findViewById(R.id.cajaNombre);
        nombre = editText.getText().toString();

        return nombre;
    }

    /**
     * Callback cuando toca el botón
     *
     * @param v
     */
    public void entrar(View v) {
        String nombre = obtenerNombre();//obtengo el nombre introducido TODO posible mejora, validar que introduzca un nombre válido

        if (this.devuelta) {
            Intent i = new Intent();
            i.putExtra("NOMBRE_NUEVO", nombre);
            setResult(RESULT_OK, i);//, intent_de_vuelta);
            finish();
        } else {

            aJugar(nombre);//voy a la pantalla del juego, pasándole el nombre
        }

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


    private void aJugar(String nombre) {
        Intent intent = new Intent(this, JuegoActivity.class);//creo el intent
        intent.putExtra("NOMBRE_USUARIO", nombre);//le introduzco el nombre
        startActivity(intent);//lo lanzo
        finish();
    }

    private void aJugar() {
        Intent intent = new Intent(this, JuegoActivity.class);//preparo el intent explícito
        startActivity(intent);//lo lanzo
        finish();
    }
}
