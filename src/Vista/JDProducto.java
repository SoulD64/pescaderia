/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import DAO.DAOException;
import DAO.DAOManager;
import DAOMySQL.MySQLDAOManager;
import Modelo.Producto;
import Modelo.ProductosProveedores;
import Modelo.Proveedor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Saúl
 */
public class JDProducto extends javax.swing.JDialog {

    //crearemos un objeto del tipo interface IProductoDAO
    private DAOManager manager = null;

    //modelo para nuestra tabla tblProductos
    private ProductosTableModel model;

    //propiedad para modificar el width de nuestra tabla tblProductos
    TableColumnModel columnModel = null;

    //campos para almacenar los datos del formulario
    private String claveProducto;
    private String descripcion;
    private double precio;
    private int existencias;

    /**
     * Creates new form JDProducto
     *
     * @param parent
     * @param modal
     */
    public JDProducto(java.awt.Frame parent, boolean modal, DAOManager manager) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ventana de Productos");

        //creamos la propiedad para manejar nuestros DAO
        this.manager = new MySQLDAOManager();

        //llamada al método para inicializar la tabla tblProductos
        inicializarListaProductos();
    }
    
    private void inicializarListaProductos() {
        model = new ProductosTableModel(manager.getProductoProveedorDAO());

        //asignamos el modelo pero sin llamar al método actualizar ya que al inicializar
        //el id. Autor es -1
        tblProductos.setModel(model);

        //redimensionamos las celdas
        setJTableColumnsWidth(tblProductos, 480, 40, 150, 70, 30, 80);
    }
    
    public static void setJTableColumnsWidth(JTable table, int tablePrefferedWidth, double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }
        
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePrefferedWidth * (percentages[i] / total)));
        }
    }//fin del metodo setJTableColumnsWidth

    private void actualizarListaProductos(String claveProducto) throws DAOException {
        /* Si no hay errores, actualizamos la tabla
        * para mostrar los las claves de proveedor
         */
        model.updateModel(claveProducto);

        //hacemos que se reflejen los cambios
        model.fireTableDataChanged();

        //redimensionamos la tabla
        setJTableColumnsWidth(tblProductos, 480, 40, 150, 70, 30, 80);
    }//fin del método

    /**
     * Este método permite limpiar las cajas de texto y ubicar el focus en la
     * caja de texto del nombre
     */
    private void limpiarFormulario() {
        //limpiamos las cajas de texto
        txtClaveProducto.setText("");
        txtDescripcion.setText("");
        txtExistencias.setText("");
        txtPrecio.setText("");
        txtBuscarPorClave.setText("");
        //ubicamos el focus en la caja de texto de la clave producto
        txtClaveProducto.requestFocusInWindow();
        txtAgregarClaveProducto.setText("");
        model.limpiar();
    }//fin del método limpiar formulario

    private boolean validar() {
        boolean validacion = true;
        
        String clave = txtClaveProducto.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String existenciasTexto = txtExistencias.getText().trim();
        
        if (clave.equals("")) {
            JOptionPane.showMessageDialog(null, "Especifica la clave del producto");
            txtClaveProducto.requestFocusInWindow();
            return validacion;
        }
        
        if (descripcion.equals("")) {
            JOptionPane.showMessageDialog(null, "Especifica la descripción del producto");
            txtDescripcion.requestFocusInWindow();
            return validacion;
        }
        
        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
            if (precio < 0) {
                JOptionPane.showMessageDialog(null, "El precio no puede ser negativo");
                txtPrecio.requestFocusInWindow();
                return validacion;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduce un número válido para el precio");
            txtPrecio.requestFocusInWindow();
            return validacion;
        }
        
        int existencias;
        try {
            existencias = Integer.parseInt(existenciasTexto);
            if (existencias < 0) {
                JOptionPane.showMessageDialog(null, "Las existencias no pueden ser negativas");
                txtExistencias.requestFocusInWindow();
                return validacion;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduce un número válido para las existencias");
            txtExistencias.requestFocusInWindow();
            return validacion;
        }
        
        return true; // Si todo pasa, se valida correctamente
    }

    /**
     * Imprime un mensaje de error personalizado para aquellos errores que son
     * producidos por el acceso a la base de datos
     *
     * @param ex objeto de tipo DAOException
     */
    private void imprimirMensajeDeErrorDAO(DAOException ex) {// Si getMessage existe obtenemos su valor
        String mensajeError;
        
        try {
            mensajeError = ex.getCause().getMessage();
        } catch (NullPointerException error) {
            mensajeError = "";
        }
        
        JOptionPane.showMessageDialog(null,
                ex.getMessage() + "\n"
                + "Mensaje: " + mensajeError,
                "Error", JOptionPane.ERROR_MESSAGE);
    }// Fin del método imprimirMensajeDeError

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtClaveProducto = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtExistencias = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscarPorClave = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtAgregarClaveProducto = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnEliminarProveedorProducto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Clave Producto:");

        jLabel2.setText("Descripción:");

        jLabel3.setText("Precio:");

        jLabel4.setText("Existencias:");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("O buscar por Clave Producto:"));

        jLabel5.setText("Clave del Producto elegido:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarPorClave, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscarPorClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedores de Producto"));

        jLabel6.setText("Agregar nuevos proveedores:");

        txtAgregarClaveProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgregarClaveProductoActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel7.setText("(Escribe la id de Proveedor)");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "idProveedor", "Nombre", "Apellido", "Telefono", "Email"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        btnEliminarProveedorProducto.setText("Eliminar Proveedor de Producto");
        btnEliminarProveedorProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAgregarClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregar))
                            .addComponent(btnEliminarProveedorProducto))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAgregarClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarProveedorProducto)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 420, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addGap(88, 88, 88))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnGuardar)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnEliminar)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //llamamos al método limpiarFormulario
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()) {
            String claveProducto = txtClaveProducto.getText().trim();
            
            if (!claveProducto.isEmpty()) {
                // Recuperamos los datos del formulario
                String descripcion = txtDescripcion.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int existencias = Integer.parseInt(txtExistencias.getText().trim());
                
                Producto miProducto = new Producto(claveProducto, descripcion, precio, existencias);
                
                try {
                    // Verificamos si ya existe el producto con esa clave
                    Producto existente = manager.getProductoDAO().obtener(claveProducto);
                    
                    if (existente == null) {
                        // No existe: INSERTAR
                        manager.getProductoDAO().insertar(miProducto);
                        JOptionPane.showMessageDialog(null, "Producto insertado correctamente");
                    } else {
                        // Existe: MODIFICAR
                        manager.getProductoDAO().modificar(miProducto);
                        JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
                    }
                    
                    txtClaveProducto.setText(miProducto.getClaveProducto());
                } catch (DAOException ex) {
                    imprimirMensajeDeErrorDAO(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La clave del producto no puede estar vacía.");
                txtClaveProducto.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String claveproducto = txtClaveProducto.getText().trim();

        // Verificamos que el campo no esté vacío
        if (!claveproducto.isEmpty()) {
            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Deseas eliminar el producto con clave = " + claveproducto + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    manager.getProductoDAO().eliminar(claveproducto);
                    JOptionPane.showMessageDialog(null, "El producto ha sido eliminado correctamente");

                    // Limpiar campos de texto tras eliminación
                    txtClaveProducto.setText("");
                    txtDescripcion.setText("");
                    txtPrecio.setText("");
                    txtExistencias.setText("");

                    // Aquí podrías también actualizar la tabla si usas JTable
                } catch (DAOException ex) {
                    imprimirMensajeDeErrorDAO(ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Primero selecciona o busca un producto para poder eliminarlo");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            // Obtenemos la clave del producto a buscar
            String claveProducto = txtBuscarPorClave.getText().trim();
            
            if (claveProducto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Especifica una clave para buscar", "Error", JOptionPane.ERROR_MESSAGE);
                txtBuscarPorClave.requestFocusInWindow();
                return;
            }

            // Obtenemos los datos del producto desde la BD
            Producto miProducto = manager.getProductoDAO().obtener(claveProducto);
            
            if (miProducto == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el producto con clave " + claveProducto);
                return;
            }

            // Mostramos los datos en las cajas de texto del formulario
            txtClaveProducto.setText(miProducto.getClaveProducto());
            txtDescripcion.setText(miProducto.getDescripcion());
            txtPrecio.setText(String.valueOf(miProducto.getPrecio()));
            txtExistencias.setText(String.valueOf(miProducto.getExistencias()));
            actualizarListaProductos(miProducto.getClaveProducto());
            
        } catch (DAOException ex) {
            imprimirMensajeDeErrorDAO(ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            // Obtenemos la clave del producto seleccionada en la interfaz (claveproducto)
            String claveproducto = txtClaveProducto.getText().trim();

            // Verificamos que no esté vacía la clave del proveedor a agregar
            if (!txtAgregarClaveProducto.getText().trim().equals("")) {
                // Convertimos el texto a entero
                int idProveedor = Integer.parseInt(txtAgregarClaveProducto.getText().trim());

                // Verificamos si el proveedor existe
                Proveedor miProveedor = manager.getProveedorDAO().obtener(idProveedor);

                // Creamos el objeto para la relación
                ProductosProveedores productosproveedores = new ProductosProveedores(idProveedor, claveproducto);

                // Insertamos la relación
                manager.getProductoProveedorDAO().insertar(productosproveedores);

                // Actualizamos la lista
                actualizarListaProductos(claveproducto);
                
                JOptionPane.showMessageDialog(null, "Se han guardado los datos correctamente");
                
            } else {
                JOptionPane.showMessageDialog(null, "Especifica el ID del proveedor");
            }
        } catch (DAOException ex) {
            imprimirMensajeDeErrorDAO(ex);
            txtAgregarClaveProducto.requestFocus();
            txtAgregarClaveProducto.selectAll();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El ID del proveedor debe ser un número válido");
            txtAgregarClaveProducto.requestFocus();
            txtAgregarClaveProducto.selectAll();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarProveedorProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorProductoActionPerformed
        if (tblProductos.getRowCount() > 0 && tblProductos.getSelectedRow() != -1) {
            try {
                // Obtenemos el idProveedor seleccionado de la tabla (columna 0)
                int idproveedor = Integer.parseInt(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).toString());

                // Obtenemos la clave del producto mostrado en la caja de texto
                String claveproducto = txtClaveProducto.getText().trim();
                
                if (claveproducto.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se ha especificado una clave de producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Confirmación
                int opcion = JOptionPane.showConfirmDialog(this,
                        "¿Seguro que deseas eliminar la relación del proveedor ID " + idproveedor
                        + " con el producto " + claveproducto + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                
                if (opcion == JOptionPane.YES_OPTION) {
                    // Eliminamos la relación producto-proveedor
                    manager.getProductoProveedorDAO().eliminarRelacion(idproveedor, claveproducto);

                    // Actualizamos la tabla
                    actualizarListaProductos(claveproducto); // O usar la claveProducto para obtener el ID proveedor actual, si aplica

                    JOptionPane.showMessageDialog(this, "Relación eliminada correctamente.");
                }
            } catch (DAOException ex) {
                ex.printStackTrace();
                imprimirMensajeDeErrorDAO(ex);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID del proveedor no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un proveedor de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarProveedorProductoActionPerformed

    private void txtAgregarClaveProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgregarClaveProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgregarClaveProductoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDProducto dialog = new JDProducto(new javax.swing.JFrame(), true, new MySQLDAOManager());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarProveedorProducto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtAgregarClaveProducto;
    private javax.swing.JTextField txtBuscarPorClave;
    private javax.swing.JTextField txtClaveProducto;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtExistencias;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
