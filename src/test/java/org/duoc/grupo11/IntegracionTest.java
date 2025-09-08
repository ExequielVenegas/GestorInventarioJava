package org.duoc.grupo11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {
    private Inventario inventario;
    private Producto producto;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario();
        producto = new Producto("P001", "Laptop", "Laptop gaming", 1500.00, 10);
    }

    @Test
    public void testIntegracionAgregarYBuscar() {
        // Agregar producto al inventario
        inventario.agregarProducto(producto);
        
        // Buscar el producto agregado
        Producto productoEncontrado = inventario.buscarPorCodigo("P001");
        
        // Verificar que es el mismo producto
        assertNotNull(productoEncontrado);
        assertEquals("Laptop", productoEncontrado.getNombre());
        assertEquals(1500.00, productoEncontrado.getPrecio(), 0.01);
    }

    @Test
    public void testIntegracionAgregarActualizarYVerificar() {
        // Agregar producto
        inventario.agregarProducto(producto);
        
        // Actualizar precio
        inventario.buscarPorCodigo("P001").actualizarPrecio(1400.00);
        
        // Verificar que el cambio se mantiene
        Producto productoActualizado = inventario.buscarPorCodigo("P001");
        assertEquals(1400.00, productoActualizado.getPrecio(), 0.01);
    }

    @Test
    public void testIntegracionAgregarEliminarYVerificar() {
        // Agregar producto
        inventario.agregarProducto(producto);
        
        // Verificar que existe
        assertNotNull(inventario.buscarPorCodigo("P001"));
        
        // Eliminar producto
        inventario.eliminarProducto("P001");
        
        // Verificar que ya no existe
        assertNull(inventario.buscarPorCodigo("P001"));
    }

    @Test
    public void testIntegracionListadoCompleto() {
        // Agregar múltiples productos
        Producto producto2 = new Producto("P002", "Mouse", "Mouse inalámbrico", 25.50, 50);
        Producto producto3 = new Producto("P003", "Teclado", "Teclado mecánico", 75.00, 30);
        
        inventario.agregarProducto(producto);
        inventario.agregarProducto(producto2);
        inventario.agregarProducto(producto3);
        
        // Verificar que todos están en el listado
        List<Producto> productos = inventario.listarTodosLosProductos();
        assertEquals(3, productos.size());
    }
}