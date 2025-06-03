/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOException;
import Modelo.Producto;
import Modelo.ProductosProveedores;
import Modelo.Proveedor;
import MySQLConexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.IProductoProveedorDAO;



/**
 *
 * @author DELL
 */
public class MySQLProductoProveedorDAO implements IProductoProveedorDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // Consulta para insertar una relación producto-proveedor
    private final String INSERT = "INSERT INTO productos_proveedores (claveproducto, idproveedor) VALUES (?, ?)";

    // Consulta para eliminar una relación producto-proveedor
    private final String DELETE = "DELETE FROM productos_proveedores WHERE idproveedor = ? AND claveproducto = ?";

    // Consulta para obtener productos según un proveedor específico
    private final String GET_PRODUCTS_BY_PROVIDER =
        "SELECT producto.claveproducto, producto.descripcion, producto.precio, producto.existencias " +
        "FROM productos producto " +
        "INNER JOIN productos_proveedores pp ON producto.claveproducto = pp.claveproducto " +
        "WHERE pp.idproveedor = ?";

    // Consulta para obtener proveedores según un producto específico
    private final String GET_PROVIDERS_BY_PRODUCT =
        "SELECT pr.idproveedor, pr.nombrepila, pr.apellidopaterno, pr.telefono, pr.email " +
        "FROM proveedores pr " +
        "INNER JOIN productos_proveedores pp ON pr.idproveedor = pp.idproveedor " +
        "WHERE pp.claveproducto = ?";

    // Consulta para obtener todas las relaciones producto-proveedor
    private final String GETALL = "SELECT * FROM productos_proveedores";

    // Cierra los recursos de la base de datos
    private void cerrarConexiones(PreparedStatement ps, ResultSet rs, Connection conn) throws DAOException {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }
    }

    // Devuelve una lista de productos asociados a un proveedor
    @Override
    public List<Producto> obtenerProductoPorProveedor(int idProveedor) throws DAOException {
        List<Producto> productos = new ArrayList<>();
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GET_PRODUCTS_BY_PROVIDER);
            ps.setInt(1, idProveedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setClaveProducto(rs.getString("claveproducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setExistencias(rs.getInt("existencias"));
                productos.add(producto);
            }

        } catch (SQLException ex) {
            throw new DAOException("Error al obtener productos por proveedor", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return productos;
    }

    // Devuelve una lista de proveedores asociados a un producto
    @Override
    public List<Proveedor> obtenerProveedorPorProducto(String claveProducto) throws DAOException {
        List<Proveedor> proveedores = new ArrayList<>();
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GET_PROVIDERS_BY_PRODUCT);
            ps.setString(1, claveProducto);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idproveedor"));
                proveedor.setNombrePila(rs.getString("nombrepila"));
                proveedor.setApellidoPaterno(rs.getString("apellidopaterno"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEmail(rs.getString("email"));
                proveedores.add(proveedor);
            }

        } catch (SQLException ex) {
            throw new DAOException("Error al obtener proveedores por producto", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return proveedores;
    }

    // Elimina una relación producto-proveedor específica
    @Override
    public void eliminarRelacion(int idproveedor, String claveproducto) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, idproveedor);
            ps.setString(2, claveproducto);

            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo eliminar el registro");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error al eliminar", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    // Inserta una nueva relación producto-proveedor
    @Override
    public void insertar(ProductosProveedores a) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, a.getClaveProducto());
            ps.setInt(2, a.getIdProveedor());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("No se pudo insertar el registro");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error al insertar", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    // Método no implementado (se podría usar para editar relaciones si fuera necesario)
    @Override
    public void modificar(ProductosProveedores a) throws DAOException {
        // No implementado
    }

    // Método no implementado (generalmente usado si 'id' fuera clave primaria)
    @Override
    public void eliminar(Integer id) throws DAOException {
        // No implementado
    }

    // Devuelve todas las relaciones producto-proveedor
    @Override
    public List<ProductosProveedores> obtenerTodos() throws DAOException {
        List<ProductosProveedores> lista = new ArrayList<>();
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GETALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductosProveedores pp = new ProductosProveedores();
                pp.setClaveProducto(rs.getString("claveproducto"));
                pp.setIdProveedor(rs.getInt("idproveedor"));
                lista.add(pp);
            }

        } catch (SQLException ex) {
            throw new DAOException("Error al obtener todos los registros", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
        return lista;
    }

    // Método no implementado (generalmente para buscar una relación por ID)
    @Override
    public ProductosProveedores obtener(Integer id) throws DAOException {
        throw new UnsupportedOperationException("No implementado");
    }
}
