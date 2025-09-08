package org.duoc.grupo11;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String, Producto> productos;

    public Inventario() {
        productos = new HashMap<>();
    }

    // Método para agregar productos al inventario
    public void agregarProducto(Producto producto) {
        try {
            if (producto != null) {
                // Validación de código más flexible
                if (producto.getCodigo() == null || producto.getCodigo().trim().isEmpty()) {
                    System.out.println("Error: El código no puede estar vacío.");
                    return;
                }
                
                if (producto.getCodigo().length() < 2 || producto.getCodigo().length() > 10) {
                    System.out.println("Error: El código debe tener entre 2 y 10 caracteres.");
                    return;
                }
                
                // Validar que el código no existe
                if (productos.containsKey(producto.getCodigo())) {
                    System.out.println("Error: Ya existe un producto con el código: " + producto.getCodigo());
                    return;
                }
                
                // Validar que el nombre no existe
                if (existeProductoConNombre(producto.getNombre())) {
                    System.out.println("Error: Ya existe un producto con el nombre: " + producto.getNombre());
                    return;
                }
                
                productos.put(producto.getCodigo(), producto);
                System.out.println("Producto agregado correctamente.");
            } else {
                System.out.println("Error: No se puede agregar un producto nulo.");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado al agregar producto: " + e.getMessage());
        }
    }
    
    // Método para verificar si ya existe un producto con el mismo nombre
    public boolean existeProductoConNombre(String nombre) {
        for (Producto producto : productos.values()) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    // Método para eliminar producto por código
    public void eliminarProducto(String codigo) {
        if (productos.containsKey(codigo)) {
            productos.remove(codigo);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Error: No se encontró un producto con ese código.");
        }
    }
    
    // Método para buscar productos por código
    public Producto buscarPorCodigo(String codigo) {
        return productos.get(codigo);
    }
    
    // Método para buscar productos por nombre (búsqueda parcial)
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultados = new ArrayList<>();
        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            Producto producto = entry.getValue();
            if (producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(producto);
            }
        }
        return resultados;
    }
    
    // Método para buscar productos por descripción (búsqueda parcial)
    public List<Producto> buscarPorDescripcion(String descripcion) {
        List<Producto> resultados = new ArrayList<>();
        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            Producto producto = entry.getValue();
            if (producto.getDescripcion().toLowerCase().contains(descripcion.toLowerCase())) {
                resultados.add(producto);
            }
        }
        return resultados;
    }
    
    // Método para listar todos los productos
    public List<Producto> listarTodosLosProductos() {
        return new ArrayList<>(productos.values());
    }
    
    // Método para generar informe del inventario
    public void generarInforme() {
        System.out.println("=== INFORME DE INVENTARIO ===");
        System.out.println("Total de productos: " + productos.size());
        
        double valorTotal = 0;
        for (Producto producto : productos.values()) {
            valorTotal += producto.getPrecio() * producto.getCantidadStock();
        }
        
        System.out.println("Valor total del inventario: $" + String.format("%.2f", valorTotal));
        System.out.println("=============================");
    }
    
    // Método para actualizar stock
    public void actualizarStock(String codigo, int nuevaCantidad) {
        Producto producto = productos.get(codigo);
        if (producto != null) {
            if (nuevaCantidad >= 0) {
                producto.setCantidadStock(nuevaCantidad);
                System.out.println("Stock actualizado correctamente.");
            } else {
                System.out.println("Error: La cantidad no puede ser negativa.");
            }
        } else {
            System.out.println("Error: No se encontró un producto con ese código.");
        }
    }
    
    // Método para guardar el inventario en un archivo
    public void guardarInventario(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(productos);
            System.out.println("Inventario guardado correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }

    // Método para cargar el inventario desde un archivo
    @SuppressWarnings("unchecked")
    public void cargarInventario(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            productos = (HashMap<String, Producto>) ois.readObject();
            System.out.println("Inventario cargado correctamente desde " + nombreArchivo);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }
}