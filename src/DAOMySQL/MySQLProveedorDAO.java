/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOException;
import Modelo.Proveedor;
import MySQLConexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.IProveedorDAO;

/**
 *
 * @author Saúl
 */
public class MySQLProveedorDAO implements IProveedorDAO {

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    private final String INSERT = "INSERT INTO proveedores (nombrepila, apellidopaterno, telefono, email) VALUES (?, ?, ?, ?)";
    private final String UPDATE = "UPDATE Proveedores SET nombrepila = ?, apellidopaterno = ?, telefono = ?, email = ? WHERE idproveedor = ?";
    private final String DELETE = "DELETE FROM proveedores WHERE idproveedor = ?";
    private final String GETALL = "SELECT idproveedor, nombrepila, apellidopaterno, telefono, email FROM proveedores";
    private final String GETONE = "SELECT idproveedor, nombrepila, apellidopaterno, telefono, email FROM proveedores WHERE idproveedor = ?";

    private void cerrarConexiones(PreparedStatement ps, ResultSet rs, Connection conn) throws DAOException {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL: " + ex);
        }
    }

    @Override
    public void insertar(Proveedor proveedor) throws DAOException {
        try {
        conn = Conectar.realizarConexion();
        ps = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, proveedor.getNombrePila());
        ps.setString(2, proveedor.getApellidoPaterno());
        ps.setString(3, proveedor.getTelefono());
        ps.setString(4, proveedor.getEmail());

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new DAOException("No se pudo guardar el nuevo proveedor");
        }

        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            proveedor.setIdProveedor(rs.getInt(1));
        } else {
            throw new DAOException("No se pudo obtener el ID generado para el proveedor");
        }

    } catch (SQLException ex) {
        throw new DAOException("Error de SQL: " + ex);
    } finally {
        cerrarConexiones(ps, rs, conn);
        }
    }

    @Override
    public void modificar(Proveedor proveedor) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(UPDATE);
            
            ps.setString(1, proveedor.getNombrePila());
            ps.setString(2, proveedor.getApellidoPaterno());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setInt(5, proveedor.getIdProveedor());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("No se encontró el proveedor con ID = " + proveedor.getIdProveedor());
            }

        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: " + ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    @Override
    public void eliminar(Integer idProveedor) throws DAOException {
        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, idProveedor);

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Hubo un problema y no se pudo eliminar el registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL: " + ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }
    }

    @Override
    public List<Proveedor> obtenerTodos() throws DAOException {
        List<Proveedor> proveedores = new ArrayList<>();

        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GETALL);
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
            throw new DAOException("Error en SQL: " + ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }

        return proveedores;
    }

    @Override
    public Proveedor obtener(Integer idProveedor) throws DAOException {
        Proveedor proveedor = null;

        try {
            conn = Conectar.realizarConexion();
            ps = conn.prepareStatement(GETONE);
            ps.setInt(1, idProveedor);
            rs = ps.executeQuery();

            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idproveedor"));
                proveedor.setNombrePila(rs.getString("nombrepila"));
                proveedor.setApellidoPaterno(rs.getString("apellidopaterno"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEmail(rs.getString("email"));
            } else {
                throw new DAOException("No se encontró el proveedor con clave: " + idProveedor);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: " + ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }

        return proveedor;
    }

}
