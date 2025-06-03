/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOMySQL;

import DAO.DAOException;
import DAO.IProductoDAO;
import Modelo.Producto;
import MySQLConexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class MySQLProductoDAO implements IProductoDAO {
    
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    
    private final String INSERT = "INSERT INTO productos (claveproducto, descripcion, precio, existencias) VALUES (?, ?, ?, ?)";
    private final String UPDATE = "UPDATE productos SET descripcion = ?, precio = ?, existencias = ? WHERE claveproducto = ?";
    private final String DELETE = "DELETE FROM productos WHERE claveproducto = ?";
    private final String GETALL = "SELECT claveproducto, descripcion, precio, existencias FROM productos";
    private final String GETONE = "SELECT claveproducto, descripcion, precio, existencias FROM productos WHERE claveproducto = ?";
    
    
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
            throw new DAOException("Error en SQL", ex);
        }
    }//fin del método cerrarConexiones
    
    

    @Override
    public void insertar(Producto producto) throws DAOException {
        try {
        conn = Conectar.realizarConexion();
        ps = conn.prepareStatement(INSERT);

        ps.setString(1, producto.getClaveProducto());
        ps.setString(2, producto.getDescripcion());
        ps.setDouble(3, producto.getPrecio());
        ps.setInt(4, producto.getExistencias());

        if (ps.executeUpdate() == 0) {
            throw new DAOException("No se pudo guardar el nuevo producto");
        }

    } catch (SQLException ex) {
        throw new DAOException("Error de SQL: ", ex);
    } finally {
        cerrarConexiones(ps, rs, conn);
        }
    }

    @Override
    public void modificar(Producto producto) throws DAOException {
        try {
            //creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            //preparamos la consulta y verificamos el resultado
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, producto.getDescripcion());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getExistencias());
            ps.setString(4, producto.getClaveProducto());
            //ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0) { //Si iguala a 0 hay un problema
                throw new DAOException("Hubo un problema " + " y no se guardaron los cambios ");

            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }//fin del finally
    }

    @Override
    public void eliminar(String claveproducto) throws DAOException {
        try {
            //creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();

            //preparamos la consulta y especificamos los parámetros de entrada
            ps = conn.prepareStatement(DELETE);
            ps.setString(1, claveproducto);
            
            //ejecutamos la consulta y verificamos el resultado
            if (ps.executeUpdate() == 0 ){
                throw new DAOException("Hubo un problema y no " + "se pudo eliminar el registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }//fin del finally
    }

    @Override
    public List<Producto> obtenerTodos() throws DAOException {
        //Lista de productos a retornar
        List <Producto> misProductos = new ArrayList<Producto>();
        
        try{
            //creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();
            
            //preparamos la consulta
            ps = conn.prepareStatement(GETALL);
            
            //ejecutamos la consulta y almacenamos el resultado
            //en un objeto ResultSet
            rs = ps.executeQuery();
            
            //recorremos el ResultSet y agregamos cada item al ArrayList
            while (rs.next() ){
                Producto miProducto = new Producto();
                miProducto.setClaveProducto(rs.getString("claveproducto"));
                miProducto.setDescripcion(rs.getString("descripcion"));
                miProducto.setPrecio(rs.getDouble("precio"));
                miProducto.setExistencias(rs.getInt("existencias"));
                misProductos.add(miProducto);
            }//terminación while
        }catch (SQLException ex) {
            throw new DAOException("Error de SQL: ", ex);
        } finally {
            cerrarConexiones(ps, rs, conn);
        }//fin del finally
        
        return misProductos;
    }

    @Override
    public Producto obtener(String claveProducto) throws DAOException {
        //Lista de producto(s) a retornar
       Producto miProducto = null;
       
       try{
          //creamos la conexión a la base de datos
            conn = Conectar.realizarConexion();
            
            //preparamos la consulta y definimos sus parámetros que recibe la consulta
            ps = conn.prepareStatement(GETONE); 
            ps.setString(1, claveProducto);
            
            //ejecutamos la consulta y almacenamos el resultado en un objeto ResultSet
            rs = ps.executeQuery();
            
            /*
            Verificamos si el resultset obtuvo un resultado y lo asignamos al objeto correspondiente
            */
            
            if (rs.next()){
                miProducto = new Producto();
                miProducto.setClaveProducto(rs.getString("claveproducto"));
                miProducto.setDescripcion(rs.getString("descripcion"));
                miProducto.setPrecio(rs.getDouble("precio"));
                miProducto.setExistencias(rs.getInt("existencias"));
            }else{
                throw new DAOException ("No se encontró el elemento");
            }
            
       }catch(SQLException ex){
           throw new DAOException("Error de SQL: ", ex);
       }finally {
          cerrarConexiones(ps, rs, conn); 
       }//fin finally
       return miProducto;
    }
  }
    

