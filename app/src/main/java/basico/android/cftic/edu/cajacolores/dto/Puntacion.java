package basico.android.cftic.edu.cajacolores.dto;

public class Puntacion {

    private String nombre;
    private long tiempo;

    public Puntacion(String nombre, long tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
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


    @Override
    public String toString() {
        return "Puntacion{" +
                "nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo +
                '}';
    }
}
