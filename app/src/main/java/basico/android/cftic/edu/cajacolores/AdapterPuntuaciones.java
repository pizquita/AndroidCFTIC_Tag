package basico.android.cftic.edu.cajacolores;

import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import basico.android.cftic.edu.cajacolores.dto.Puntacion;

public class AdapterPuntuaciones extends RecyclerView.Adapter<PuntuacionHolder> {

    private List<Puntacion> datos;


    /**
     * ESTE MÉTODO CREA LAS CAJAS / ITEMS QUE ALBERGARÁ LA INFORMACIÓN
     * DE CADA REGISTRO / ELEMENTO. CREAMOS LA VISTA (LA CAJA)
     *
     * @param parent la propia lista, el RecyclerView
     * @param viewType
     * @return
     */
    @Override
    public PuntuacionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PuntuacionHolder puntuacionHolder = null;

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.fila_record, parent, false);
            //itemView.setOnClickListener(this);//asociaría el listener
            puntuacionHolder = new PuntuacionHolder(itemView);

        return puntuacionHolder;
    }

    //Al holder, le meto la info de item que toca
    @Override
    public void onBindViewHolder(PuntuacionHolder holder, int position) {

        Puntacion puntacion = datos.get(position);
        holder.cargarPuntuacion(puntacion);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public AdapterPuntuaciones (List<Puntacion> lista_puntuaciones)
    {
        this.datos = lista_puntuaciones;
    }
}
