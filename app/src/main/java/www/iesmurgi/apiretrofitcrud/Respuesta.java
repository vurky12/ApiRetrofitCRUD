package www.iesmurgi.apiretrofitcrud;

public class Respuesta {
    public String titulo;
    public float precio;
    public String descripcion;
    public long metrosConstruidos;
    public double metrosUtiles;
    public String ubicacion;
    public String zona;
    public String fechaPublicacion;
    public long habitaciones;
    public long bannos;
    public long idInmueble;

    public Respuesta(String titulo, float precio, String descripcion, long metrosConstruidos, double metrosUtiles,
                     String ubicacion, String zona, String fechaPublicacion, long habitaciones,
                     long bannos) {
        this.titulo = titulo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.metrosConstruidos = metrosConstruidos;
        this.metrosUtiles = metrosUtiles;
        this.ubicacion = ubicacion;
        this.zona = zona;
        this.fechaPublicacion = fechaPublicacion;
        this.habitaciones = habitaciones;
        this.bannos = bannos;

    }
    public String getTitulo() {
        return titulo;
    }
    public long getIdInmueble() {
        return idInmueble;
    }

}
