package basico.android.cftic.edu.cajacolores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * ACTIVIDAD PRINCIPAL (Main Intent Filter Mainfest)
 */
public class InicioActivity extends AppCompatActivity {


    private boolean devuelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        if (Preferencias.primeraVez(this)) //si entra la primera vez
        {
            Preferencias.marcarPrimeraVez(this); //lo marco
        } else {
            this.devuelta = getIntent().getBooleanExtra("DEVUELTA", false);
            if (!this.devuelta) {
                irAMain(); //si no, le paso directo a jugar
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
            //Intent intent_de_vuelta = new Intent();
            //intent_de_vuelta.putExtra("NOMBRE_NUEVO", nombre);
            Intent i = new Intent();
            i.putExtra("NOMBRE_NUEVO", nombre);
            setResult(RESULT_OK, i);//, intent_de_vuelta);
            finish();
        }

        irAMain(nombre);//voy a la pantalla del juego, pasándole el nombre
    }


    private void irAMain(String nombre) {
        Intent intent = new Intent(this, MainActivity.class);//creo el intent
        intent.putExtra("NOMBRE_USUARIO", nombre);//le introduzco el nombre
        startActivity(intent);//lo lanzo
        finish();
    }

    private void irAMain() {
        Intent intent = new Intent(this, MainActivity.class);//preparo el intent explícito
        startActivity(intent);//lo lanzo
    }
}
