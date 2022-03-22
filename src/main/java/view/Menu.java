package view;

import controller.GeneroController;
import controller.PlataformaController;
import controller.VideogamesController;

import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
    private int option;
    private String opciones;
    Scanner sc = new Scanner(System.in);

    /**
     * Este es un constructor y llama a la clase
     */
    public Menu() {
        super();
    }

    /**
     * Este metodo sirve para mostrar un menu
     * @return devuelte la opcion que elegiste en numero
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("********************************************************");
            System.out.println("*                    MENU PRINCIPAL                    *");
            System.out.println("********************************************************");
            System.out.println("* 1. Borrar Tablas                                     *");
            System.out.println("* 2. Mostrar todos los videojuegos                     *");
            System.out.println("* 3. Mostrar todos los generos                         *");
            System.out.println("* 4. Mostrar todas las plataformas                     *");
            System.out.println("* 5. Ordenar los videojuegos por nombre                *");
            System.out.println("* 6. Mostrar los videojuegos que contengan...          *");
            System.out.println("* 7. Mostrar los videojuegos que empiecen por...       *");
            System.out.println("* 8. Modificar un videojuego                           *");
            System.out.println("* 9. Borrar un videojuego                              *");
            System.out.println("* 10. Borrar videojuegos de un genero                  *");
            System.out.println("* 11. Borrar videojuegos de una plataforma             *");
            System.out.println("* 12. Crear genero de videojuego                       *");
            System.out.println("* 13. Crear tu propio videojuego                       *");
            System.out.println("* 14. Salir                                            *");
            System.out.println("********************************************************\n");

            System.out.println("Escoge una opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlido");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13 && option != 14);
        return option;
    }

    /**
     * Este metodo sirve para mostrar un menu de generos
     * @param c recibe la coneccion
     * @return devuelve el rol que elegiste
     */

    public String GeneroMenu(Connection c, EntityManagerFactory entityManagerFactory){
        GeneroController generoController = new GeneroController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            generoController.showGeneros();
            System.out.println("Elige el genero: ");
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }

    /**
     * Este metodo sirve para mostrar un menu de plataformas
     * @param c recibe la coneccion
     * @return devuelve el rol que elegiste
     */

    public String PlataformaMenu(Connection c, EntityManagerFactory entityManagerFactory){
        PlataformaController plataformaController = new PlataformaController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            plataformaController.showPlataforma();
            System.out.println("Elige la plataforma: ");
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }

    /**
     * Este metodo sirve para mostrar un menu de id y nombre de videojuegos
     * @param c recibe la coneccion
     * @return devuelve el nombre que elegiste
     */
    public int NomMenu(Connection c, EntityManagerFactory entityManagerFactory){
        VideogamesController videogamesController = new VideogamesController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n" + "Campeones: ");
        for(;;){
            videogamesController.showVideojuegoNom();
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return option;
        }
    }
}