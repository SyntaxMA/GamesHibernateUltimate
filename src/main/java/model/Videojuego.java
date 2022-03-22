package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para crear los videojuegos con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "videojuegos")
public class Videojuego implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "videjuego_id")
    int id_videojuego;

    @Column(name = "titulo",length = 200)
    String titulo;

    @Column(name = "imagen",length = 100)
    String imagen;

    @Column(name = "fecha",length = 100)
    String fecha;

    @ManyToOne
    @JoinColumn(name = "plataforma")
    Plataforma plataforma;

    @Column(name = "descripcion", length = 1000)
    String descripcion;

    @ManyToOne
    @JoinColumn(name = "genero")
    Genero genero;

    /**
     * Este es el constructor de la clase
     * @param titulo recibe un nombre
     *
     * @param fecha recibe un rol
     * @param descripcion recibe una historia
     */

    public Videojuego(String titulo, String imagen, String fecha, Plataforma plataforma,String descripcion, Genero genero){
        super();
        this.titulo = titulo;
        this.imagen = imagen;
        this.fecha = fecha;
        this.plataforma = plataforma;
        this.descripcion = descripcion;
        this.genero = genero;
    }

    /**
     * Es un constructor
     */
    public Videojuego(){
        super();
    }

    /**
     * Esto para pillar la id.
     * @return devuelve la id.
     */
    public int getId_videojuego() {
        return id_videojuego;
    }

    /**
     * Esto para asignar una id.
     * @param Id_videojuego recibe el que le vas a poner a la id.
     */
    public void setId_videojuego(int Id_videojuego ) {
        this.id_videojuego = Id_videojuego;
    }


    /**
     * Esto para pillar el nombre.
     * @return devuelve el nombre.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Esto para asignar un nombre.
     * @param titulo recibe el que le vas a poner a lla nombre.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    /**
     * Esto para pillar historia.
     * @return devuelve la historia.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Esto para asignar una historia.
     * @param imagen recibe el que le vas a poner a la historia.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    /**
     * Esto para pillar historia.
     * @return devuelve la historia.
     */


    public String getFecha() {
        return fecha;
    }

    /**
     * Esto para asignar una historia.
     * @param fecha recibe el que le vas a poner a la historia.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Esto para pillar historia.
     * @return devuelve la historia.
     */

    public Plataforma getPlataforma() {
        return plataforma;
    }

    /**
     * Esto para asignar una historia.
     * @param plataforma recibe el que le vas a poner a la historia.
     */
    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }
    /**
     * Esto para pillar historia.
     * @return devuelve la historia.
     */

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Esto para asignar una historia.
     * @param descripcion recibe el que le vas a poner a la historia.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Genero getGenero() {return genero;}
    /**
     * Esto para asignar una historia.
     * @param genero recibe el que le vas a poner a la historia.
     */

    public void setGenero(Genero genero) {this.genero = genero;}
    /**
     * Esto para pillar historia.
     * @return devuelve la historia.
     */

    /**
     * Este metodo sirve para decir de que formato para mostrar.
     * @return un frase
     */


    @Override
    public String toString() {
        return "Videojuego{" +
                "id_Videojuego=" + id_videojuego +
                ", Titulo='" + titulo + '\'' +
                ", Imagen='" + imagen + '\'' +
                ", Fecha='" + fecha + '\'' +
                ", Plataforma='" + plataforma.toString() + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                ", Genero='" + genero.toString() + '\'' +
                '}';
    }
}
