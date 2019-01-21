package basico.android.cftic.edu.cajacolores;

import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private int color_tocado;
    private int nveces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nveces = 0;
        this.color_tocado = ResourcesCompat.getColor(getResources(), R.color.tocado, null);

    }

    private void cerrar ()
    {
        Toast toast = Toast.makeText(this, "FIN DE LA APP", Toast.LENGTH_SHORT);
        toast.show();//informo
        finish();//cierro
    }
    public void cambiaColor(View v)
    {
        Log.d("MIAPP", "TOCÓ CAJA");
        Object tag = v.getTag();//obtengo la info asociada
        if (tag==null)
        {
                this.nveces++;
                v.setTag(true);//le hago una marquita a la caja tocada
                v.setBackgroundColor(this.color_tocado);
                if (this.nveces==12) {cerrar();}

        }





    }
}
