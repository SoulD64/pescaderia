/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOManager;
import DAO.IProductoDAO;
import DAO.IProveedorDAO;
import DAO.IProductoProveedorDAO;

/**
 *
 * @author Saúl
 */
public class MySQLDAOManager implements DAOManager {

    private IProductoDAO productos = null;
    private IProveedorDAO proveedores = null;
    private IProductoProveedorDAO productoproveedor = null;

    //con esta clase haremos el uso del patrón singleton para reusar objetos si estos ya han sido creados
    @Override
    public IProductoDAO getProductoDAO() {
        if (productos == null) {
            productos = new MySQLProductoDAO();
        }
        return productos;
    } //fin del metodo getProductoDAO

    @Override
    public IProveedorDAO getProveedorDAO() {
        if (proveedores == null) {
            proveedores = new MySQLProveedorDAO();
        }
        return proveedores;
    } //fin del metodo getProveedoresDAO

    @Override
    public IProductoProveedorDAO getProductoProveedorDAO() {
        if (productoproveedor == null) {
            productoproveedor = new MySQLProductoProveedorDAO();
        }
        return productoproveedor;
    }

}
