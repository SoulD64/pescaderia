/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author DELL
 */
public class Producto {
    //campos o propiedades
    private String claveProducto;
    private String descripcion;
    private double precio;
    private int existencias;

    private Set<String> claves = new HashSet (0);
    private Set<Proveedor> proveedores;
    
    public Producto() {
        super();
    }
    
    public Set getClaves() {
        return claves;
    }

    public void setClaves(Set claves) {
        this.claves = claves;
    }
  
    public Producto(String claveProducto, String descripcion, double precio, int existencias) {
        this.claveProducto = claveProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
    }

    // Getters y Setters
    public String getClaveProducto() { 
        return claveProducto; 
    }
    public void setClaveProducto(String claveProducto) { 
        this.claveProducto = claveProducto; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    public double getPrecio() { 
        return precio; 
    }
    
    public void setPrecio(double precio) { 
        this.precio = precio; 
    }

    public int getExistencias() { 
        return existencias; 
    }
    
    public void setExistencias(int existencias) { 
        this.existencias = existencias; 
    }

    public Set<Proveedor> getProveedores() {
        return proveedores; 
    }
    
    public void setProveedores(Set<Proveedor> proveedores) { 
        this.proveedores = proveedores;
    }

    @Override
    public String toString() {
        return descripcion + " - $" + precio;
    }
}
