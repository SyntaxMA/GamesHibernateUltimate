package controller;

import model.Genero;
import model.Plataforma;
import model.Videojuego;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;
import view.Menu;

/**
 * Esta clase sirve para controlar la tabla Videojuegos, la principal
 */
public class VideogamesController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;

    Scanner scanner;
    List<Videojuego> videojuegos;
    Menu menu = new Menu();

    /**
     * Aqui tenemos el constructor de la clase
     * @param connection recibe la conexión hacia la base de datos postgres
     */
    public VideogamesController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.scanner = new Scanner(System.in);
        videojuegos = new ArrayList<>();
    }

    public List<Videojuego> readVideojuegoFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while ((linea = br.readLine()) != null) {
            List<String> listToken = getTokenList(linea, ";");
            try {
                StringBuilder titulo = new StringBuilder(listToken.get(0));
                titulo = titulo.deleteCharAt(0);
                titulo = new StringBuilder(titulo.substring(0, titulo.length() - 1));
                String titulos = titulo.toString();

                StringBuilder imagen = new StringBuilder(listToken.get(2));
                imagen = imagen.deleteCharAt(0);
                imagen = new StringBuilder(imagen.substring(0, imagen.length() - 1));
                String imagenes = imagen.toString();

                StringBuilder fecha = new StringBuilder(listToken.get(3));
                fecha = fecha.deleteCharAt(0);
                fecha = new StringBuilder(fecha.substring(0, fecha.length() - 1));
                String fechas = fecha.toString();

                StringBuilder plataforma = new StringBuilder(listToken.get(4));
                plataforma = plataforma.deleteCharAt(0);
                plataforma = new StringBuilder(plataforma.substring(0, plataforma.length() - 1));
                String plataformas = plataforma.toString();

                StringBuilder descripcion = new StringBuilder(listToken.get(5));
                descripcion = descripcion.deleteCharAt(0);
                descripcion = new StringBuilder(descripcion.substring(0, descripcion.length() - 1));
                String descripciones = descripcion.toString();

                StringBuilder genero = new StringBuilder(listToken.get(6));
                genero = genero.deleteCharAt(0);
                genero = new StringBuilder(genero.substring(0, genero.length() - 1));
                String generos = genero.toString();

                videojuegos.add(new Videojuego(titulos, imagenes, fechas, new Plataforma(plataformas), descripciones, new Genero(generos)));

            } catch (Exception e) {
            }

        }

        return videojuegos;
    }

    /**
     * Este método sirve para crear un videojuego
     * */
    public void createVideojuego(Videojuego videojuego) {

            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.merge(videojuego);
            em.getTransaction().commit();
            em.close();
    }

    /**
     * Este metodo sirve para mostrar videojuegos
     */

    public void showVideojuego(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery("from Videojuego ", Videojuego.class).getResultList();
        for (Videojuego pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pokemons con un texto especificado
     */
    public void showVideojuegoCon(){
        System.out.println("Escribe el texto a contener: ");
        String letra = scanner.nextLine().toUpperCase(Locale.ROOT);

        String sql = "from Videojuego where titulo like '%" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql, Videojuego.class).getResultList();
        for (Videojuego pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar videojuegos que empiezan por una letra concreta
     */
    public void showVideojuegoPor(){
        System.out.println("Escribe la letra de inicio: ");
        String letra = scanner.nextLine();

        String sql = "from Videojuego where titulo like '" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql, Videojuego.class).getResultList();
        for (Videojuego pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar las id y los ordenar alfabeticamente los videojuegos por su nombre
     */
    public void showVideojuegoNom(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery("from Videojuego order by titulo", Videojuego.class).getResultList();
        for (Videojuego videojuego : result) {
            System.out.println(videojuego.getId_videojuego() + " " + videojuego.getTitulo());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para modificar el nombre de un videojuego
     */
    public void modificarVideojuegos() {
        int id = menu.NomMenu(connection,entityManagerFactory);
        System.out.println("Escribe el nuevo nombre: ");
        String newNom = scanner.nextLine();

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Videojuego videojuego = (Videojuego) em.find(Videojuego.class, id);
        videojuego.setTitulo(newNom);
        em.merge(videojuego);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar un videojuego
     */
    public void borrarVideojuegos() {
        int id = menu.NomMenu(connection,entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Videojuego videojuego = (Videojuego) em.find(Videojuego.class, id);
        em.remove(videojuego);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar videojuegos por su genero
     */
    public void borrarVideojuegosPorGenero() {
        String genero = menu.GeneroMenu(connection,entityManagerFactory);
        String sql = "from Videojuego where genero='" + genero + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql,Videojuego.class).getResultList();
        for (Videojuego videojuego : result) {
            em.remove(videojuego);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar videojuegos por su plataforma
     */
    public void borrarVideojuegosPorPlataforma() {
        String plataforma = menu.PlataformaMenu(connection,entityManagerFactory);
        String sql = "from Videojuego where plataforma='" + plataforma + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql,Videojuego.class).getResultList();
        for (Videojuego videojuego : result) {
            em.remove(videojuego);
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