package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para crear el objeto de la plataforma
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "plataformas")
public class Plataforma implements Serializable {
    @Id
    @Column(name = "plataforma", length = 30)
    String plataforma;

    /**
     * Constructor de la plataforma
     * @param plataforma
     */
    public Plataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * Constructor vacio de la plataforma
     */
    public Plataforma() {}

    /**
     * Pemite obtener la plataforma
     * @return
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Permite cambiar la plataforma
     * @param plataforma
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * To string del objeto de la plataforma
     * @return
     */
    @Override
    public String toString() {
        return "Plataforma{" +
                "plataforma='" + plataforma + '\'' +
                '}';
    }
}
