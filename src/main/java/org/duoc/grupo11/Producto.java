package org.duoc.grupo11;

import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadStock;
    
    // Constructor
    public Producto(String codigo, String nombre, String descripcion, double precio, int cantidadStock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }
    
    // Getters y setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }
    
    // Método para actualizar el precio
    public void actualizarPrecio(double nuevoPrecio) {
        if (nuevoPrecio >= 0) {
            this.precio = nuevoPrecio;
            System.out.println("Precio actualizado correctamente.");
        } else {
            System.out.println("Error: El precio no puede ser negativo.");
        }
    }
    
    // Método para descripción detallada
    public String obtenerDescripcionDetallada() {
        return "Código: " + codigo + 
               "\nNombre: " + nombre + 
               "\nDescripción: " + descripcion + 
               "\nPrecio: $" + precio + 
               "\nStock: " + cantidadStock + " unidades";
    }
    
    @Override
    public String toString() {
        return nombre + " (Código: " + codigo + ") - $" + precio + " - Stock: " + cantidadStock;
    }
}