package controller;

import java.util.Scanner;

import model.Usuario;
import model.Cliente;
import model.Producto;

import view.ConsolaView;
import view.LoginView;

import controller.ClienteController;
import controller.ProductoController;
import controller.PedidoController;

/**
 * Controlador principal de la aplicación.
 * Gestiona login y acceso por roles.
 */
public class MainApp {

    public static void main(String[] args) {

        // =========================
        // LOGIN (FASE 7)
        // =========================
        LoginView loginView = new LoginView();
        loginView.setVisible(true);

        // Espera hasta que cierre login
        while (loginView.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Usuario logueado
        Usuario usuario = loginView.getUsuarioLogueado();

        if (usuario == null) {
            System.out.println("Acceso denegado");
            System.exit(0);
        }

        System.out.println("Bienvenido " + usuario.getUsername() +
                " - Rol: " + usuario.getRol());

        // =========================
        // SISTEMA PRINCIPAL
        // =========================
        Scanner sc = new Scanner(System.in);

        ConsolaView view = new ConsolaView();

        ClienteController clienteController = new ClienteController();
        ProductoController productoController = new ProductoController();
        PedidoController pedidoController = new PedidoController();

        int opcion;

        do {

            view.menu();

            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                // =========================
                // CLIENTES (ADMIN)
                // =========================
                case 1:

                    if (!usuario.getRol().equalsIgnoreCase("ADMIN")) {
                        System.out.println(" No tienes permisos");
                        break;
                    }

                    System.out.println("\n--- NUEVO CLIENTE ---");

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    Cliente cliente = new Cliente(nombre, email, telefono);

                    clienteController.insertarCliente(cliente);

                    System.out.println(" Cliente añadido correctamente");
                    break;

                // =========================
                // LISTAR CLIENTES
                // =========================
                case 2:

                    System.out.println("\n--- LISTA CLIENTES ---");
                    System.out.println(clienteController.listarClientes());
                    break;

                // =========================
                // PRODUCTOS (ADMIN)
                // =========================
                case 3:

                    if (!usuario.getRol().equalsIgnoreCase("ADMIN")) {
                        System.out.println(" No tienes permisos");
                        break;
                    }

                    System.out.println("\n--- NUEVO PRODUCTO ---");

                    System.out.print("Nombre: ");
                    String nombreProducto = sc.nextLine();

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    System.out.print("ID Categoría: ");
                    int idCategoria = sc.nextInt();

                    System.out.print("ID Proveedor: ");
                    int idProveedor = sc.nextInt();
                    sc.nextLine();

                    Producto producto = new Producto(
                            nombreProducto,
                            descripcion,
                            precio,
                            stock,
                            idCategoria,
                            idProveedor
                    );

                    productoController.insertarProducto(producto);

                    System.out.println(" Producto añadido correctamente");
                    break;

                // =========================
                // LISTAR PRODUCTOS
                // =========================
                case 4:

                    System.out.println("\n--- LISTA PRODUCTOS ---");
                    System.out.println(productoController.listarProductos());
                    break;

                // =========================
                // PEDIDOS
                // =========================
                case 5:

                    System.out.println("\n--- LISTA PEDIDOS ---");
                    System.out.println(pedidoController.listarPedidos());
                    break;

                // =========================
                // SALIR
                // =========================
                case 0:

                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);

        sc.close();
    }
}