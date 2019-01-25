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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        if (Preferencias.primeraVez(this)) //si entra la primera vez
        {
            Preferencias.marcarPrimeraVez(this); //lo marco
        } else
            {
               irAMain(); //si no, le paso directo a jugar
            }
    }


    private String obtenerNombre ()
    {
        String nombre = null;

            EditText editText = (EditText)findViewById(R.id.cajaNombre);
            nombre = editText.getText().toString();

        return nombre;
    }

    /**
     * Callback cuando toca el botón
     * @param v
     */
    public void entrar (View v)
    {
        String nombre = obtenerNombre();//obtengo el nombre introducido TODO posible mejora, validar que introduzca un nombre válido
        irAMain(nombre);//voy a la pantalla del juego, pasándole el nombre
    }


    private void irAMain (String nombre)
    {
        Intent intent = new Intent(this, MainActivity.class);//creo el intent
        intent.putExtra("NOMBRE_USUARIO", nombre);//le introduzco el nombre
        startActivity(intent);//lo lanzo
    }

    private void irAMain ()
    {
        Intent intent = new Intent(this, MainActivity.class);//preparo el intent explícito
        startActivity(intent);//lo lanzo
    }
}
