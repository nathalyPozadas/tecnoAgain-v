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
public class N_Docente {

    private Persona m_persona;

    public N_Docente() {
        this.m_persona = new Persona();
    }

    public DefaultTableModel obtenerDocente(int id) {
        return this.m_persona.obtenerPersona(id);
    }

    public DefaultTableModel obtenerDocentes() {
        return this.m_persona.obtenerPersonas("Docente");
    }

    public int registrarDocente(String nombre, String apellido, String ci, String telefono, String correo) {
        // No olvidar primero settear los datos
        this.m_persona.setPersona(nombre, apellido, ci, telefono, correo,"Docente");
        return this.m_persona.registrarPersona();
    }

    public void modificarDocente(int id, String nombre, String apellido, String ci, String telefono, String correo) {
        // No olvidar primero settear los datos
        this.m_persona.setPersona(nombre, apellido, ci, telefono, correo,"Docente");
        this.m_persona.setId(id);
        this.m_persona.modificarPersona();
    }

    public void eliminarDocente(int id) {
        this.m_persona.setId(id);
        this.m_persona.eliminarPersona();
    }
}
