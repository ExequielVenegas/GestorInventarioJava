package org.duoc.grupo11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {
    private Inventario inventario;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario();
        producto1 = new Producto("P001", "Laptop", "Laptop gaming", 1500.00, 10);
        producto2 = new Producto("P002", "Mouse", "Mouse inalámbrico", 25.50, 50);
    }

    @Test
    public void testAgregarProducto() {
        inventario.agregarProducto(producto1);
        assertNotNull(inventario.buscarPorCodigo("P001"));
    }

    @Test
    public void testEliminarProducto() {
        inventario.agregarProducto(producto1);
        inventario.eliminarProducto("P001");
        assertNull(inventario.buscarPorCodigo("P001"));
    }

    @Test
    public void testEliminarProductoInexistente() {
        inventario.eliminarProducto("P999");
        // Debería manejar el caso sin lanzar excepciones
    }

    @Test
    public void testBuscarPorNombre() {
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
        
        List<Producto> resultados = inventario.buscarPorNombre("lap");
        assertEquals(1, resultados.size());
        assertEquals("Laptop", resultados.get(0).getNombre());
    }

    @Test
    public void testBuscarPorDescripcion() {
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
        
        List<Producto> resultados = inventario.buscarPorDescripcion("gaming");
        assertEquals(1, resultados.size());
        assertEquals("Laptop", resultados.get(0).getNombre());
    }

    @Test
    public void testBuscarPorNombreInexistente() {
        inventario.agregarProducto(producto1);
        
        List<Producto> resultados = inventario.buscarPorNombre("tablet");
        assertTrue(resultados.isEmpty());
    }

    @Test
    public void testBuscarPorDescripcionInexistente() {
        inventario.agregarProducto(producto1);
        
        List<Producto> resultados = inventario.buscarPorDescripcion("inexistente");
        assertTrue(resultados.isEmpty());
    }

    @Test
    public void testListarTodosLosProductos() {
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
        
        List<Producto> productos = inventario.listarTodosLosProductos();
        assertEquals(2, productos.size());
    }

    @Test
    public void testActualizarStock() {
        inventario.agregarProducto(producto1);
        inventario.actualizarStock("P001", 15);
        
        Producto productoActualizado = inventario.buscarPorCodigo("P001");
        assertEquals(15, productoActualizado.getCantidadStock());
    }

    @Test
    public void testActualizarStockNegativo() {
        inventario.agregarProducto(producto1);
        inventario.actualizarStock("P001", -5);
        
        // El stock no debería cambiar
        Producto productoActualizado = inventario.buscarPorCodigo("P001");
        assertEquals(10, productoActualizado.getCantidadStock());
    }

    @Test
    public void testExisteProductoConNombre() {
        inventario.agregarProducto(producto1);
        assertTrue(inventario.existeProductoConNombre("Laptop"));
        assertFalse(inventario.existeProductoConNombre("Inexistente"));
    }
}