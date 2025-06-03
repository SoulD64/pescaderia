# 🐟 Proyecto Final de Programación Orientada a Objetos II - Sistema de Gestión para Pescadería

Este proyecto es una aplicación de escritorio desarrollada en **Java** con interfaz gráfica **Swing**, orientada a la gestión de productos y proveedores de una pescadería. Permite registrar, editar, eliminar y consultar productos y proveedores, así como generar reportes en formato PDF usando **JasperReports**.

## ✨ Características principales

- **Gestión de Productos**:
  - Registro, edición, eliminación y búsqueda de productos.
  - Atributos: clave, descripción, precio, existencias.

- **Gestión de Proveedores**:
  - Registro, edición, eliminación y búsqueda de proveedores.
  - Atributos: ID, nombre, teléfono, correo.

- **Relación Producto-Proveedor**:
  - Asociación de múltiples proveedores a un producto y viceversa.
  - Tabla intermedia `productos_proveedores`.

- **Reportes**:
  - Reporte de catálogo de productos.
  - Reporte de productos junto con sus proveedores.
  - Generación de archivos PDF usando `.jasper`.

## 🛠️ Tecnologías utilizadas

- Java (JDK 8 o superior)
- Swing (JDialog, JFrame, JTable, etc.)
- MySQL
- JasperReports / iReport
- Apache NetBeans
- JDBC

## 📁 Estructura general del proyecto

ProyectoFinalPescaderia/
├── src/
│ ├── Modelo/ # Clases Producto, Proveedor, ProductosProveedores
│ ├── DAO/ # Interfaces y clases DAO para DB
│ ├── DAOMySQL / # Implementaciones de las tablas (clases DAO) de la DB 
│ ├── Vista/ # JDialogs (JDProducto, JDProveedores)
│ ├── Reporte/ # Clase para generar reportes .jasper
│ └── Util/ # Clase Conectar (conexión a BD)
├── Reportes/
│ ├── pescaderiareporte.jrxml # Archivo fuente del reporte
│ └── pescaderiareporte.jasper # Reporte compilado listo para uso
└── README.md

## ⚙️ Configuración

1. **Base de datos MySQL**:
   - Crear una base de datos llamada `pescaderia`.
   - Crear las siguientes tablas:
     - `productos (claveproducto, descripcion, precio, existencias)`
     - `proveedores (idproveedor, nombrepila, apellidopaterno, telefono, email)`
     - `productos_proveedores (claveproducto, idproveedor)`

2. **Conexión a la base de datos**:
   - Editar la clase `Conectar.java` con tus credenciales de MySQL:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/pescaderia";
     private static final String USER = "root";
     private static final String PASSWORD = "";
     ```

3. **JasperReports**:
   - El archivo `.jasper` debe estar en la carpeta `/Reportes/`.
   - Puedes actualizarlo desde iReport o JasperSoft Studio y recompilarlo.

4. **Compilar y ejecutar**:
   - Abre el proyecto en NetBeans.
   - Ejecuta la clase `FrmVentanaPrincipal`.

## 🧾 Reportes

Los reportes se generan al presionar el botón correspondiente en la ventana principal:

- Se genera y muestra un visor (`JasperViewer`).
- Se guarda automáticamente un archivo PDF (`ReporteProductos.pdf`) en la raíz del proyecto.

## 💬 Autor

- Proyecto universitario realizado por [SoulD64] (Saúl Iván)(https://github.com/SoulD64)

## 📜 Licencia

Este proyecto es solo con fines educativos.
