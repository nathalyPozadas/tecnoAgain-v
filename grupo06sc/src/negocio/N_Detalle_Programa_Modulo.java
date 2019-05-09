/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Detalle_Programa_Modulo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Detalle_Programa_Modulo {
    private Detalle_Programa_Modulo m_detalle;
    
    /* OBTENER DETALLE MEDIANTE EL ID DE UNA PROGRAMA */
    public DefaultTableModel obtenerDetalle_Programa_Modulo(int idPrograma) {
        return this.m_detalle.obtenerDetalle_Programa_Modulo(idPrograma);
    }
    
     public int registrarDetalle_Programa_Modulo(int id_programa, int id_modulo) {
        // No olvidar primero settear los datos
        this.m_detalle.setDetalle_Programa_Modulo(id_programa, id_modulo);
        return this.m_detalle.registrarDetalle_Programa_Modulo();
    }

    public void eliminarModulo(int id) {
        this.m_detalle.setId(id);
        this.m_detalle.eliminarDetalle_Programa_Modulo();
    }
}
