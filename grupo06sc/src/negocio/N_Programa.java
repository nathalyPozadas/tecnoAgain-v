/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Programa;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Programa {

    private Programa m_programa;

    public N_Programa() {
        this.m_programa = new Programa();
    }

    public DefaultTableModel obtenerPrograma(int id) {
        return this.m_programa.obtenerPrograma(id);
    }

    public DefaultTableModel obtenerProgramas() {
        return this.m_programa.obtenerProgramas();
    }

    public int registrarPrograma(String nombre, int presupuesto, String version, String edicion, String tipo, String fecha_ela, String fecha_apro) {
        // No olvidar primero settear los datos
        this.m_programa.setPrograma(nombre, presupuesto, version, edicion, tipo, fecha_ela, fecha_apro);
        return this.m_programa.registrarPrograma();
    }

    public void modificarPrograma(int id, String nombre, int costo, int presupuesto, String version, String edicion, String tipo, String fecha_ela, String fecha_apro) {
        // No olvidar primero settear los datos
        this.m_programa.setPrograma(nombre, presupuesto, version, edicion, tipo, fecha_ela, fecha_apro);
        this.m_programa.setId(id);
        this.m_programa.modificarPrograma();
    }

    public void eliminarPrograma(int id) {
        this.m_programa.setId(id);
        this.m_programa.eliminarPrograma();
    }

}
