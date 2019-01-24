package basico.android.cftic.edu.cajacolores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    public void entrar (View v)
    {
        irAMain();
    }

    private void irAMain ()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USUARIO", "Juan");
        startActivity(intent);
    }
}
