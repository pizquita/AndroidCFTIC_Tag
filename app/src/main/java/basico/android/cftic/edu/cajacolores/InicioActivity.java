package basico.android.cftic.edu.cajacolores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InicioActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        if (Preferencias.primeraVez(this))
        {
            Preferencias.marcarPrimeraVez(this);
        } else
            {
               irAMain();
            }
    }


    private String obtenerNombre ()
    {
        String nombre = null;

            EditText editText = (EditText)findViewById(R.id.cajaNombre);
            nombre = editText.getText().toString();

        return nombre;
    }
    public void entrar (View v)
    {
        String nombre = obtenerNombre();
        irAMain(nombre);
    }

    private void irAMain ()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void irAMain (String nombre)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("NOMBRE_USUARIO", nombre);
        startActivity(intent);
    }
}
