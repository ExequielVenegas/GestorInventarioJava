package org.duoc.grupo11;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private Inventario inventario;
    private Scanner scanner;

    public MenuPrincipal() {
        inventario = new Inventario();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            try {
                System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIO ===");
                System.out.println("1. Agregar producto");
                System.out.println("2. Buscar producto por código");
                System.out.println("3. Buscar producto por nombre");
                System.out.println("4. Buscar producto por descripción");
                System.out.println("5. Listar todos los productos");
                System.out.println("6. Actualizar precio de producto");
                System.out.println("7. Actualizar stock de producto");
                System.out.println("8. Eliminar producto");
                System.out.println("9. Generar informe de inventario");
                System.out.println("10. Guardar inventario");
                System.out.println("11. Cargar inventario");
                System.out.println("12. Salir");
                System.out.print("Seleccione una opción: ");
                
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        buscarPorCodigo();
                        break;
                    case 3:
                        buscarPorNombre();
                        break;
                    case 4:
                        buscarPorDescripcion();
                        break;
                    case 5:
                        listarProductos();
                        break;
                    case 6:
                        actualizarPrecio();
                        break;
                    case 7:
                        actualizarStock();
                        break;
                    case 8:
                        eliminarProducto();
                        break;
                    case 9:
                        generarInforme();
                        break;
                    case 10:
                        guardarInventario();
                        break;
                    case 11:
                        cargarInventario();
                        break;
                    case 12:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer
                opcion = 0; // Reiniciar opción
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                opcion = 0; // Reiniciar opción
            }
        } while (opcion != 12);
    }
    
    private void agregarProducto() {
        try {
            System.out.println("\n--- AGREGAR PRODUCTO ---");
            System.out.print("Código: ");
            String codigo = scanner.nextLine();
            
            // NOTA: La validación de código duplicado ahora se hace solo en Inventario.agregarProducto()
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();
            
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            
            System.out.print("Cantidad en stock: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precio, cantidad);
            inventario.agregarProducto(nuevoProducto);
        } catch (InputMismatchException e) {
            System.out.println("Error: Tipo de dato incorrecto. Por favor ingrese valores válidos.");
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            System.out.println("Error inesperado al agregar producto: " + e.getMessage());
        }
    }
    
    private void buscarPorCodigo() {
        System.out.println("\n--- BUSCAR POR CÓDIGO ---");
        System.out.print("Ingrese el código: ");
        String codigo = scanner.nextLine();
        
        Producto producto = inventario.buscarPorCodigo(codigo);
        if (producto != null) {
            System.out.println("\nProducto encontrado:");
            System.out.println(producto.obtenerDescripcionDetallada());
        } else {
            System.out.println("No se encontró ningún producto con ese código.");
        }
    }
    
    private void buscarPorNombre() {
        System.out.println("\n--- BUSCAR POR NOMBRE ---");
        System.out.print("Ingrese el nombre (o parte de él): ");
        String nombre = scanner.nextLine();
        
        List<Producto> resultados = inventario.buscarPorNombre(nombre);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos con ese nombre.");
        } else {
            System.out.println("\nProductos encontrados (" + resultados.size() + "):");
            for (Producto producto : resultados) {
                System.out.println("- " + producto);
            }
        }
    }
    
    private void buscarPorDescripcion() {
        System.out.println("\n--- BUSCAR POR DESCRIPCIÓN ---");
        System.out.print("Ingrese la descripción (or parte de ella): ");
        String descripcion = scanner.nextLine();
        
        List<Producto> resultados = inventario.buscarPorDescripcion(descripcion);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos con esa descripción.");
        } else {
            System.out.println("\nProductos encontrados (" + resultados.size() + "):");
            for (Producto producto : resultados) {
                System.out.println("- " + producto);
            }
        }
    }
    
    private void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        List<Producto> productos = inventario.listarTodosLosProductos();
        
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Producto producto : productos) {
                System.out.println("- " + producto);
            }
        }
    }
    
    private void actualizarPrecio() {
        try {
            System.out.println("\n--- ACTUALIZAR PRECIO ---");
            System.out.print("Ingrese el código del producto: ");
            String codigo = scanner.nextLine();
            
            Producto producto = inventario.buscarPorCodigo(codigo);
            if (producto != null) {
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                
                producto.actualizarPrecio(nuevoPrecio);
            } else {
                System.out.println("No se encontró ningún producto con ese código.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor ingrese un número válido para el precio.");
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            System.out.println("Error inesperado al actualizar precio: " + e.getMessage());
        }
    }
    
    private void actualizarStock() {
        try {
            System.out.println("\n--- ACTUALIZAR STOCK ---");
            System.out.print("Ingrese el código del producto: ");
            String codigo = scanner.nextLine();
            
            Producto producto = inventario.buscarPorCodigo(codigo);
            if (producto != null) {
                System.out.print("Nueva cantidad en stock: ");
                int nuevaCantidad = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                inventario.actualizarStock(codigo, nuevaCantidad);
            } else {
                System.out.println("No se encontró ningún producto con ese código.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor ingrese un número válido para la cantidad.");
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            System.out.println("Error inesperado al actualizar stock: " + e.getMessage());
        }
    }
    
    private void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        
        inventario.eliminarProducto(codigo);
    }
    
    private void generarInforme() {
        inventario.generarInforme();
    }
    
    private void guardarInventario() {
        System.out.print("Nombre del archivo para guardar (ej: inventario.dat): ");
        String nombreArchivo = scanner.nextLine();
        inventario.guardarInventario(nombreArchivo);
    }

    private void cargarInventario() {
        System.out.print("Nombre del archivo para cargar (ej: inventario.dat): ");
        String nombreArchivo = scanner.nextLine();
        inventario.cargarInventario(nombreArchivo);
    }
}