/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Modulo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Modulo {

    private Modulo m_modulo;

    public N_Modulo() {
        this.m_modulo = new Modulo();
    }

    public int registrarModulo(String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i) {
        // No olvidar primero settear los datos
        this.m_modulo.setModulo(nombre, sigla, descripcion, costo, horas_a, horas_i);
        return this.m_modulo.registrarModulo();
    }

    public DefaultTableModel obtenerModulo(int id) {
        return this.m_modulo.obtenerModulo(id);
    }

    public DefaultTableModel obtenerModulos() {
        return this.m_modulo.obtenerModulos();
    }

    public void modificarModulo(int id, String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i) {
        // No olvidar primero settear los datos
        this.m_modulo.setModulo(nombre, sigla, descripcion, costo, horas_a, horas_i);
        this.m_modulo.setId(id);
        this.m_modulo.modificarModulo();
    }

    public void eliminarModulo(int id) {
        this.m_modulo.setId(id);
        this.m_modulo.eliminarModulo();
    }
}
