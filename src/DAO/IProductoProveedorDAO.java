/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Modelo.Producto;
import Modelo.ProductosProveedores;
import Modelo.Proveedor;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface IProductoProveedorDAO extends IDAO <ProductosProveedores, Integer> {
    List<Producto> obtenerProductoPorProveedor (int idproveedor) throws DAOException;
    List<Proveedor> obtenerProveedorPorProducto (String claveproducto) throws DAOException;
    
    void eliminarRelacion(int idproveedor, String claveproducto ) throws DAOException;
}
