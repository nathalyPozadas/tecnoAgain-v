/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Persona;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Cliente {

    private Persona m_pesona;

    public N_Cliente() {
        this.m_pesona = new Persona();
    }

    public DefaultTableModel obtenerCliente(int id) {
        return this.m_pesona.obtenerPersona(id);
    }

    public DefaultTableModel obtenerClientes() {
        return this.m_pesona.obtenerPersonas("Cliente");
    }

    public int registrarCliente(String nombre, String apellido, String ci, String telefono, String correo) {
        // No olvidar primero settear los datos
        this.m_pesona.setPersona(nombre, apellido, ci, telefono, correo, "Cliente");
        return this.m_pesona.registrarPersona();
    }

    public void modificarCliente(int id, String nombre, String apellido, String ci, String telefono, String correo) {
        // No olvidar primero settear los datos
        this.m_pesona.setPersona(nombre, apellido, ci, telefono, correo, "Cliente");
        this.m_pesona.setId(id);
        this.m_pesona.modificarPersona();
    }

    public void eliminarCliente(int id) {
        this.m_pesona.setId(id);
        this.m_pesona.eliminarPersona();
    }
}
