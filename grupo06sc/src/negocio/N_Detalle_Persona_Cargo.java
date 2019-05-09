/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Cargo;
import datos.Detalle_Persona_Cargo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Detalle_Persona_Cargo {

    private Detalle_Persona_Cargo m_detalle;

    public N_Detalle_Persona_Cargo() {
        this.m_detalle = new Detalle_Persona_Cargo();
    }

    /* OBTENER DETALLE MEDIANTE EL ID DE UNA PERSONA */
    public DefaultTableModel obtenerDetalle_Persona_Cargo(int idPersona) {
        return this.m_detalle.obtenerDetalle_Persona_Cargo(idPersona);
    }

    public int registrarDetalle_Persona_Cargo(int id_persona, int id_cargo) {
        // No olvidar primero settear los datos
        this.m_detalle.setDetalle_Persona_Cargo(id_persona, id_cargo);
        return this.m_detalle.registrarDetalle_Persona_Cargo();
    }

    public void eliminarCargo(int id) {
        this.m_detalle.setId(id);
        this.m_detalle.eliminarDetalle_Persona_Cargo();
    }
}
