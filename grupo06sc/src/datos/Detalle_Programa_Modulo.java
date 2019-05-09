/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brian
 */
public class Detalle_Programa_Modulo {

    private int id;
    private int id_programa;
    private int id_modulo;
    private byte estado;

    Conexion conection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public int getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(int id_modulo) {
        this.id_modulo = id_modulo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public Conexion getConnection() {
        return conection;
    }

    public void setConnection(Conexion connection) {
        this.conection = connection;
    }

    public Detalle_Programa_Modulo() {
        this.conection = Conexion.getInstancia();
    }

    public void setDetalle_Programa_Modulo(int id_programa, int id_modulo) {

        this.id_programa = id_programa;
        this.id_modulo = id_modulo;
        this.estado = 1;
    }

//==============================================================================
//==============================================================================
    /* OBTENER DETALLE MEDIANTE EL ID DE UN PROGRAMA */
    public DefaultTableModel obtenerDetalle_Programa_Modulo(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalle_programa_modulo = new DefaultTableModel();
        detalle_programa_modulo.setColumnIdentifiers(new Object[]{
            "id", "id_programa", "programa", "id_modulo", "modulo"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle_programa_modulo.id as idd"
                + "programa.id as idp,\n"
                + "programa.nombre as nombrep,\n"
                + "modulo.id as idm\n"
                + "modulo.nombre as nombrem\n"
                + "FROM detalle_programa_modulo, modulo, programa\n"
                + "WHERE detalle_programa_modulo.id_programa=programa.id"
                + " and detalle_programa_modulo.id_modulo=modulo.id"
                + " and programa.id=?"
                + " and detalle_programa_modulo.estado='1'";

        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                detalle_programa_modulo.addRow(new Object[]{
                    rs.getInt("idd"),
                    rs.getInt("idp"),
                    rs.getString("nombrep"),
                    rs.getInt("idm"),
                    rs.getString("nombrem")});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalle_programa_modulo;
    }

//==============================================================================
//==============================================================================

    /* REGISTRAR DETALLE */
    public int registrarDetalle_Programa_Modulo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consult
        String sql = "INSERT INTO detalle_programa_modulo(id_programa,id_modulo,estado)\n"
                + "VALUES(?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.id_programa);
            ps.setInt(2, this.id_modulo);
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.conection.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
        return 0;
    }

//==============================================================================
//==============================================================================
/*ELIMINAR UN DDETALLE*/
    public void eliminarDetalle_Programa_Modulo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE detalle_programa_modulo SET \n"
                + "estado = '0'\n"
                + "WHERE detalle_programa_modulo.id = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id);
            int rows = ps.executeUpdate();
            System.out.println(rows);
            // Cierro la conexion
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
