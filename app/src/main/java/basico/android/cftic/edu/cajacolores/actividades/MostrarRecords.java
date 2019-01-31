package basico.android.cftic.edu.cajacolores.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import basico.android.cftic.edu.cajacolores.AdapterPuntuaciones;
import basico.android.cftic.edu.cajacolores.R;
import basico.android.cftic.edu.cajacolores.dto.Puntacion;
import basico.android.cftic.edu.cajacolores.util.Preferencias;

public class MostrarRecords extends AppCompatActivity {
    private RecyclerView recView;

    private List<Puntacion> datos;

    private AdapterPuntuaciones adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_records);

        datos = Preferencias.cargarPuntuaciones(this);
        adaptador = new AdapterPuntuaciones(datos);

        int records = adaptador.getItemCount();

        if(records == 0){
            Toast.makeText(getApplicationContext(), "No hay registros aun.", Toast.LENGTH_SHORT).show();
            finish();
        }else {

            recView = (RecyclerView) findViewById(R.id.myrecycview);
            //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar


            recView.setAdapter(adaptador);//mostrando la lista
            recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recView.setHasFixedSize(true);// se adapta a la pantalla
            //recView.setLayoutManager(new GridLayoutManager(this,3));
            //StaggeredGridLayoutManager para celdas de tamaño variable
            //recView.setLayoutManager(new StaggeredGridLayoutManager());


            //ITEM DECORATOR --> OPCIONAL

            recView.addItemDecoration(
                    new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

            //
            //recView.setItemAnimator(new DefaultItemAnimator());

            //registerForContextMenu(recView);

            // recView.setContextClickable(true);

            mostrarFlecha();

        }
    }

    private void mostrarFlecha()
    {
        //así dibujo la flecha de navegación estandar atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
            case android.R.id.home:
                Log.d("MIAPP", "Tocó ir hacia atrás");
                super.onBackPressed();
                break;
        }
        return true;
    }
}
