package basico.android.cftic.edu.cajacolores;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import basico.android.cftic.edu.cajacolores.dto.Puntacion;

public class PuntuacionHolder extends RecyclerView.ViewHolder {

    private TextView caja_tiempo_jugador;
    private TextView caja_nombre_jugador;

    public PuntuacionHolder(View itemView) {

        super(itemView);
        caja_nombre_jugador = (TextView) itemView.findViewById(R.id.nombre_jugador);
        caja_tiempo_jugador = (TextView) itemView.findViewById(R.id.tiempo_jugador);
    }


    public void cargarPuntuacion(Puntacion p) {
        caja_tiempo_jugador.setText(p.getTiempo() + "");
        caja_nombre_jugador.setText(p.getNombre());
    }

}