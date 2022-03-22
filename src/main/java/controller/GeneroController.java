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
 * Esta clase sirve para controlar lo relacionado con la tabla Genero
 */
public class GeneroController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    Menu menu = new Menu();
    List<Genero> generos;

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la conexion hacia postgres
     */
    public GeneroController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        generos = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de los generos
     * @throws IOException
     */
    public List<Genero> readGeneroFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, ";");
            try {
                StringBuilder genero = new StringBuilder(listToken.get(6));
                genero = genero.deleteCharAt(0);
                genero = new StringBuilder(genero.substring(0, genero.length() - 1));
                String generos = genero.toString();

                if (!generos.equals("")){
                    this.generos.add(new Genero(generos));
                }
            } catch (Exception e) {
            }

        }
        return generos;
    }
    /**
     * Sirve para añadir una genero a la base de datos
     * @param genero genero a añadir
     */
    public void addGenero(Genero genero) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(genero);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar los generos
     */
    public void showGeneros(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Genero> result = em.createQuery("from Genero ", Genero.class).getResultList();
        for (Genero genero : result) {
            System.out.println(genero.toString());
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
