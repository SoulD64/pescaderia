/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import DAO.DAOException;
import Modelo.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import DAO.IProductoProveedorDAO;

/**
 *
 * @author DELL
 */
public class ProductosTableModel extends AbstractTableModel {

    // DAO para acceder a productos por proveedor
    private IProductoProveedorDAO productosProveedores;

    // Lista de productos a mostrar
    private List<Proveedor> datos = new ArrayList<>();

    // Constructor con la interfaz DAO
    public ProductosTableModel(IProductoProveedorDAO productosProveedoresDAO) {
        this.productosProveedores = productosProveedoresDAO;
    }

    /**
     * Retorna el nombre de cada columna
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "idProveedor";
            case 1:
                return "Nombre";
            case 2:
                return "Apellido Paterno";
            case 3:
                return "Teléfono";
            case 4:
                return "Email";
            default:
                return "[N/D]";
        }
    }

    /**
     * retorna el número de elementos obtenidos en la tabla titulos
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return datos.size();
    }

    /**
     * retorna el número de columnas
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return 5;
    }

    /**
     * retorna el valor que contenga la intersección de una fila y columna
     * pasadas como parámetro
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor proveedor = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return proveedor.getIdProveedor();
            case 1:
                return proveedor.getNombrePila();
            case 2:
                return proveedor.getApellidoPaterno();
            case 3:
                return proveedor.getTelefono();
            case 4:
                return proveedor.getEmail();
            default:
                return "";
        }
    }

    public void updateModel(String claveProducto) throws DAOException {
        this.datos = productosProveedores.obtenerProveedorPorProducto(claveProducto);
        fireTableDataChanged();
    }

    public void limpiar() {
        datos.clear(); // Borra todos los productos
        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }
}
