package org.duoc.grupo11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {
    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto("P001", "Laptop", "Laptop gaming", 1500.00, 10);
    }

    @Test
    public void testCreacionProducto() {
        assertNotNull(producto);
        assertEquals("P001", producto.getCodigo());
        assertEquals("Laptop", producto.getNombre());
        assertEquals("Laptop gaming", producto.getDescripcion());
        assertEquals(1500.00, producto.getPrecio(), 0.01);
        assertEquals(10, producto.getCantidadStock());
    }

    @Test
    public void testActualizarPrecio() {
        producto.actualizarPrecio(1400.00);
        assertEquals(1400.00, producto.getPrecio(), 0.01);
    }

    @Test
    public void testActualizarPrecioNegativo() {
        producto.actualizarPrecio(-100.00);
        // El precio no debería cambiar
        assertEquals(1500.00, producto.getPrecio(), 0.01);
    }

    @Test
    public void testActualizarStock() {
        producto.setCantidadStock(15);
        assertEquals(15, producto.getCantidadStock());
    }

    @Test
    public void testObtenerDescripcionDetallada() {
        String descripcion = producto.obtenerDescripcionDetallada();
        assertTrue(descripcion.contains("Laptop"));
        assertTrue(descripcion.contains("1500.0"));
        assertTrue(descripcion.contains("10"));
    }
}