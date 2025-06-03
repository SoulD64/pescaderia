/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reporte;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import MySQLConexion.Conectar;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import java.io.InputStream;
import Vista.FrmVentanaPrincipal; 
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JasperExportManager;


public class reporte {
    // Nombre del archivo del reporte .jasper
    private final String NOMBRE_JASPER = "pescaderiareporte.jasper";

    // Método para generar el reporte desde la ventana principal
    public void generarReportePlatillosEIngredientes(FrmVentanaPrincipal parentFrame) {
        Connection conexion = null;
        try {
            // Intentamos conectarnos a la base de datos
            conexion = Conectar.realizarConexion();

            if (conexion == null) {
                System.err.println("No se pudo conectar a la base de datos.");
                return;
            }

            // Cargamos el archivo .jasper desde la carpeta de recursos
            InputStream inputStream = getClass().getResourceAsStream("/reporte/" + NOMBRE_JASPER);
            if (inputStream == null) {
                System.err.println("No se encontró el archivo del reporte en: /reporte/" + NOMBRE_JASPER);
                return;
            }

            // Cargamos el reporte
            JasperReport reporte = (JasperReport) JRLoader.loadObject(inputStream);

            // Aquí puedes enviar parámetros al reporte si es necesario
            Map<String, Object> parametros = new HashMap<>();

            // Llenamos el reporte con los datos de la base
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

            // Mostramos el visor del reporte
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Reporte de Productos");
            viewer.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            viewer.setAlwaysOnTop(true);
            viewer.setVisible(true);

            // Exportamos el reporte a un archivo PDF
            String rutaSalidaPDF = "ReporteProductos.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, rutaSalidaPDF);
            System.out.println("Reporte guardado en: " + new File(rutaSalidaPDF).getAbsolutePath());

        } catch (SQLException e) {
            System.err.println("Error con la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (JRException e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerramos la conexión cuando todo termine
            if (conexion != null) {
                try {
                    Conectar.realizarDesconexion();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
