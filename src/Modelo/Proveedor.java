/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DELL
 */
public class Proveedor {

    private int idproveedor;
    private String nombrePila;
    private String apellidoPaterno;
    private String telefono;
    private String email;

    //Constructor vacío
    public Proveedor() {
        super();
    }

    //Contrusctor con 4 parámetros
    public Proveedor(String nombrepila, String apellidopaterno, String telefono, String email) {
        this.nombrePila = nombrepila;
        this.apellidoPaterno = apellidopaterno;
        this.telefono = telefono;
        this.email = email;
    }

    //Contrusctor con 5 parámetros
    public Proveedor(int idproveedor, String nombrepila, String apellidopaterno, String telefono, String email) {
        this.idproveedor = idproveedor;
        this.nombrePila = nombrepila;
        this.apellidoPaterno = apellidopaterno;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdProveedor() {
        return idproveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idproveedor = idProveedor;
    }

    public String getNombrePila() {
        return nombrePila;
    }

    public void setNombrePila(String nombrePila) {
        this.nombrePila = nombrePila;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
