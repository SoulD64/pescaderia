/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DELL
 */
public class ProductosProveedores {
    private int idproveedor;
    private String claveproducto;
    
    //constructor sin ningún parámetro
    public ProductosProveedores(){

    }
    
    //constructor con dos parámetros definiendo primero idProveedor
    public ProductosProveedores(int idproveedor, String claveproducto){
        this.setClaveProducto(claveproducto);
        this.setIdProveedor(idproveedor);
    }
    
    //constructor definido con dos parámetros comenzando con claveProducto
    public ProductosProveedores(String claveproducto, int idproveedor){
        this.setClaveProducto(claveproducto);
        this.setIdProveedor(idproveedor);
    }

    public int getIdProveedor() {
        return idproveedor;
    }

    public void setIdProveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getClaveProducto() {
        return claveproducto;
    }

    public void setClaveProducto(String claveproducto) {
        this.claveproducto = claveproducto;
    }
}
