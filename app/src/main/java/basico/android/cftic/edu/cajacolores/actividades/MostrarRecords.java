package basico.android.cftic.edu.cajacolores.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        recView = (RecyclerView) findViewById(R.id.myrecycview);
        //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar



        recView.setAdapter(adaptador);//mostrando la lista

        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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

    }
}
