package view;

import controller.RouteManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que interactúa con el usuario para calcular la ruta más corta entre dos lugares.
 */
public class Main {

    /**
     * Método principal para ejecutar la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RouteManager routeManager = new RouteManager();
        HashMap<String, String> namePlaces = routeManager.getPlaces();

        String opt = "";

        System.out.println("Buenas......\nCalcularemos la ruta con menor congestion, para ello se lecciona de que lugar vas y hasta donde vas." +
                "\nLugares:");

        // Mostrar la lista de lugares disponibles para el usuario
        int i = 0;
        for ( String name: namePlaces.keySet()) {
            System.out.println((i+1) + ". "+ name);
            i++;
        }
        i = 0;

        // Solicitar al usuario que ingrese el lugar de inicio y el destino
        String id1 = "", id2 = "";
        do {
            System.out.print("Digita:");
            opt = sc.nextLine();

            try {
                int optInt = Integer.parseInt(opt)-1;

                if (optInt < 9 && optInt >= 0) {
                    int j = 0;
                    for ( String name: namePlaces.keySet()) {
                        if ( i == 0 ) {
                            if ( j == optInt ) {
                                id1 = namePlaces.get(name);
                                System.out.println(id1);
                            }
                        }else {
                            if ( j == optInt ) {
                                id2 = namePlaces.get(name);
                                System.out.println(id2);
                            }
                        }
                        j++;
                    }

                    i++;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Formato no valido");
            }
        }while (i <= 1);

        // Calcular y mostrar la ruta más corta entre los lugares seleccionados
        System.out.println("La ruta más rápida es:");
        List<String> route = routeManager.shortestRoute(id1, id2);
        if (route != null) {
            i = 1;
            for (String name : route) {
                System.out.println(i + " " + name);
                i++;
            }
        }else{
            System.out.println("No hay rutas disponibles");
        }



    }
}
