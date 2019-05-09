/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Cargo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Cargo {

    private Cargo m_cargo;

    public N_Cargo() {
        this.m_cargo = new Cargo();
    }

    public DefaultTableModel obtenerCargo(int id) {
        return this.m_cargo.obtenerCargo(id);
    }

    public DefaultTableModel obtenerCargos() {
        return this.m_cargo.obtenerCargos();
    }

    public int registrarCargo(String nombre) {
        // No olvidar primero settear los datos
        this.m_cargo.setCargo(nombre);
        return this.m_cargo.registrarCargo();
    }

    public void modificarCargo(int id, String nombre) {
        // No olvidar primero settear los datos
        this.m_cargo.setCargo(nombre);
        this.m_cargo.setId(id);
        this.m_cargo.modificarCargo();
    }

    public void eliminarCargo(int id) {
        this.m_cargo.setId(id);
        this.m_cargo.eliminarCargo();
    }
}
