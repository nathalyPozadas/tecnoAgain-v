/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Detalle_Persona_Profesion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Detalle_Persona_Profesion {

    private Detalle_Persona_Profesion m_detalle;

    public N_Detalle_Persona_Profesion() {
        this.m_detalle = new Detalle_Persona_Profesion();
    }

    /* OBTENER DETALLE MEDIANTE EL ID DE UNA PERSONA (Docente) */
    public DefaultTableModel obtenerDetalle_Persona_Profesion(int idPersona) {
        return this.m_detalle.obtenerDetalle_Persona_Profesion(idPersona);
    }

    public int registrarDetalle_Persona_Profesion(int id_persona, int id_profesion) {
        // No olvidar primero settear los datos
        this.m_detalle.setDetalle_Persona_Profesion(id_persona, id_profesion);
        return this.m_detalle.registrarDetalle_Persona_Profesion();
    }

    public void eliminarProfesion(int id) {
        this.m_detalle.setId(id);
        this.m_detalle.eliminarDetalle_Persona_Profesion();
    }
}
