package controller;

import model.Genero;
import model.Plataforma;
import model.Videojuego;
import view.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Esta clase sirve para controlar lo relacionado con la tabla Plataforma
 */
public class PlataformaController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    Menu menu = new Menu();
    List<Plataforma> plataformas;

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la conexion hacia postgres
     */
    public PlataformaController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        plataformas = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de los generos
     * @throws IOException
     */
    public List<Plataforma> readPlataformaFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, ";");
            try {
                StringBuilder plataforma = new StringBuilder(listToken.get(4));
                plataforma = plataforma.deleteCharAt(0);
                plataforma = new StringBuilder(plataforma.substring(0, plataforma.length() - 1));
                String plataformas = plataforma.toString();

                if (!plataformas.equals("")){
                    this.plataformas.add(new Plataforma(plataformas));
                }
            } catch (Exception e) {
            }

        }
        return plataformas;
    }
    /**
     * Sirve para añadir una genero a la base de datos
     * @param plataforma genero a añadir
     */
    public void addPlataforma(Plataforma plataforma) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(plataforma);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar las plataformas
     */
    public void showPlataforma(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Plataforma> result = em.createQuery("from Plataforma ", Plataforma.class).getResultList();
        for (Plataforma plataforma : result) {
            System.out.println(plataforma.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para dividir una frase en trozos depediendo del separador
     * @param string recibe una frase
     * @param delimiters recibe cual es el separador
     * @return devuelve un array de palabras separadas.
     */
    private static List<String> getTokenList(String string, String delimiters) {

        List<String> listTokens = new ArrayList<String>();

        try {
            StringTokenizer st = new StringTokenizer(string, delimiters);
            while( st.hasMoreTokens() ) {
                //add token to the list
                listTokens.add( st.nextToken() );
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return listTokens;
    }
}
