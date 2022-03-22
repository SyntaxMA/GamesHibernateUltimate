package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para crear el objeto del genero
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "generos")
public class Genero implements Serializable {
    @Id
    @Column(name = "genero", length = 30)
    String genero;

    /**
     * Constructor de la genero
     * @param genero
     */
    public Genero(String genero) {
        this.genero = genero;
    }

    /**
     * Constructor vacio del genero
     */
    public Genero() {}

    /**
     * Pemite obtener el genero
     * @return
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Permite cambiar el genero
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * To string del objeto del genero
     * @return
     */
    @Override
    public String toString() {
        return "Generos{" +
                "genero='" + genero + '\'' +
                '}';
    }
}
