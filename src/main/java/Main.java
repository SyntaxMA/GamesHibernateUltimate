import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import controller.*;
import database.ConnectionFactory;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Esta clase es la principal donde inicializas tu programa y muestra un menu
 */
public class Main {

    /**
     * Este metodo sirve para crear el Manager de Entity que esta Anotado en las clase.
     * @return
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("JPAMagazines");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();
        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

        VideogamesController videogamesController = new VideogamesController(c, entityManagerFactory);
        GeneroController generoController = new GeneroController(c, entityManagerFactory);
        PlataformaController plataformaController = new PlataformaController(c, entityManagerFactory);

        Menu menu = new Menu();
        int option = menu.mainMenu();
        while (option > 0 && option < 16) {
            switch (option) {
                case 1:
                    System.out.println("1!!");
                    try{
                        List<Plataforma> plataformas = plataformaController.readPlataformaFile("src/main/resources/pokemons.csv");
                        for (Plataforma r : plataformas) {
                            try {
                                plataformaController.addPlataforma(r);
                            } catch (Exception e) {
                            }
                        }
                        List<Genero> generos = generoController.readGeneroFile("src/main/resources/pokemons.csv");
                        for (Genero r : generos) {
                            try {
                                generoController.addGenero(r);
                            } catch (Exception e) {
                            }

                        }
                        List<Videojuego> videojuegos = videogamesController.readVideojuegoFile("src/main/resources/pokemons.csv");
                        for (Videojuego r : videojuegos) {
                            try {
                                videogamesController.createVideojuego(r);
                            } catch (Exception e) {
                            }

                        }

                    }catch (NumberFormatException | IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    videogamesController.showVideojuego();
                    break;
                case 3:
                    generoController.showGeneros();
                    break;
                case 4:
                    plataformaController.showPlataforma();
                    break;
                case 5:
                    videogamesController.showVideojuegoNom();
                    break;
                case 6:
                    videogamesController.showVideojuegoCon();
                    break;
                case 7:
                    videogamesController.showVideojuegoPor();
                    break;
                case 8:
                    videogamesController.modificarVideojuegos();
                    break;
                case 9:
                    videogamesController.borrarVideojuegos();
                    break;
                case 10:
                    videogamesController.borrarVideojuegosPorGenero();
                    break;
                case 11:
                    videogamesController.borrarVideojuegosPorPlataforma();
                    break;
                case 12:
                    System.out.println("**********************");
                    System.out.println("*    Crear Genero    *");
                    System.out.println("**********************");

                    System.out.println("Genero:");
                    String tipe = sc.nextLine().toUpperCase(Locale.ROOT);

                    generoController.addGenero(new Genero(tipe));

                    break;
                case 13:
                    System.out.println("**********************");
                    System.out.println("*  Crear videojuego  *");
                    System.out.println("**********************");

                    System.out.println("Titulo:");
                    String titulos = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Adjunta enlace de una imagen:");
                    String imagenes = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Fecha en la que sale el juego:");
                    String fechas = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Elige una primera habilidad:");
                    String plataformas = menu.PlataformaMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);

                    System.out.println("Elige una segunda habilidad:");
                    String descripciones = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Elige una segunda habilidad:");
                    String generos = menu.GeneroMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);

                    videogamesController.createVideojuego(new Videojuego(titulos, imagenes, fechas, new Plataforma(plataformas), descripciones, new Genero(generos)));

                    break;
                case 14:
                    System.exit(1);
                    break;

                default:
                    System.out.println("Adeu!!");
                    System.exit(1);
                    break;
            }
            option = menu.mainMenu();
        }
    }
}