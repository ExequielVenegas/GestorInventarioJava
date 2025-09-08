package org.duoc.grupo11;

import java.util.Scanner;

public class GestionInventarioJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuPrincipal menu = new MenuPrincipal();
        
        try {
            menu.mostrarMenu();
        } finally {
            scanner.close();
            System.out.println("Scanner cerrado. Programa finalizado correctamente.");
        }
    }
}