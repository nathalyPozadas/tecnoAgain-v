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
public class N_Administrador {

    private Persona m_persona;

    public N_Administrador() {
        this.m_persona = new Persona();
    }

    public DefaultTableModel obtenerAdministrador(int id) {
        return this.m_persona.obtenerPersona(id);
    }

    public DefaultTableModel obtenerAdministradores() {
        return this.m_persona.obtenerPersonas("Administrador");
    }

    public int registrarAdministrador(String nombre, String apellido, String ci, String telefono, String correo) {
        // No olvidar primero settear los datos
        this.m_persona.setPersona(nombre, apellido, ci, telefono, correo, "Administrador");
        return this.m_persona.registrarPersona();
    }

    public void modificarAdministrador(int id, String nombre, String apellido, String ci, String telefono, String correo) {
        // No olvidar primero settear los datos
        this.m_persona.setPersona(nombre, apellido, ci, telefono, correo, "Administrador");
        this.m_persona.setId(id);
        this.m_persona.modificarPersona();
    }

    public void eliminarAdministrador(int id) {
        this.m_persona.setId(id);
        this.m_persona.eliminarPersona();
    }

    public String verificar(String correo) {
        return this.m_persona.verificar(correo);
    }
}
