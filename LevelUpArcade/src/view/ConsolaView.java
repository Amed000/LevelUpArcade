package view;

import java.util.Scanner;

public class ConsolaView {

    private final Scanner sc = new Scanner(System.in);

    public int mostrarMenuPrincipal() {

        System.out.println("\n======================");
        System.out.println("   LEVELUP ARCADE");
        System.out.println("======================");
        System.out.println("1. Gestión de Clientes");
        System.out.println("2. Gestión de Productos");
        System.out.println("3. Gestión de Pedidos");
        System.out.println("4. IA Assistant");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");

        return leerEntero();
    }

    public int mostrarSubMenu(String titulo) {

        System.out.println("\n---- " + titulo.toUpperCase() + " ----");
        System.out.println("1. Listar");
        System.out.println("2. Añadir");
        System.out.println("3. Eliminar");
        System.out.println("0. Volver");
        System.out.print("Opción: ");

        return leerEntero();
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return sc.nextLine();
    }

    public int leerEntero() {
        try {
            int valor = Integer.parseInt(sc.nextLine());
            return valor;
        } catch (Exception e) {
            System.out.println("Entrada inválida. Intenta otra vez.");
            return -1;
        }
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}