package basico.android.cftic.edu.cajacolores.dto;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import basico.android.cftic.edu.cajacolores.PuntuacionHolder;

public class Puntacion implements Comparable{

    private String nombre;
    private long tiempo;
    private Bitmap foto;

    public Puntacion(String nombre, long tiempo, Bitmap foto) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.foto = foto;
    }

    public Puntacion() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Puntacion{" +
                "nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo + '\'' +
                ", foto=" + foto +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        //tengo que comparar this con o
        //Si es positivo this es mayor si no es o
        int compara =0;
        Puntacion p1 = null;

        p1 = (Puntacion)o;

        if(this.getTiempo() > p1.getTiempo()){
            compara = 1;
        }else if(this.getTiempo() < p1.getTiempo())
        {
            compara = -1;
        }
        //compara = (int)p1.getTiempo() - (int)this.getTiempo();

        return compara;
    }
}
