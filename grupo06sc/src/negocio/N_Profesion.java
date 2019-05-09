/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Profesion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class N_Profesion {

    private Profesion m_profesion;

    public N_Profesion() {
        this.m_profesion = new Profesion();
    }

    public DefaultTableModel obtenerProfesion(int id) {
        return this.m_profesion.obtenerProfesion(id);
    }

    public DefaultTableModel obtenerProfesiones() {
        return this.m_profesion.obtenerProfesiones();
    }

    public int registrarProfesion(String nombre) {
        // No olvidar primero settear los datos
        this.m_profesion.setProfesion(nombre);
        return this.m_profesion.registrarProfesion();
    }

    public void modificarProfesion(int id, String nombre) {
        // No olvidar primero settear los datos
        this.m_profesion.setProfesion(nombre);
        this.m_profesion.setId(id);
        this.m_profesion.modificarProfesion();
    }

    public void eliminarProfesion(int id) {
        this.m_profesion.setId(id);
        this.m_profesion.eliminarProfesion();
    }
}
