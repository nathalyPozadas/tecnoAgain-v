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
public class Detalle_Persona_Cargo {

    private int id;
    private int id_persona;
    private int id_cargo;
    private byte estado;

    Conexion conection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public Conexion getConection() {
        return conection;
    }

    public void setConection(Conexion conection) {
        this.conection = conection;
    }

    public Detalle_Persona_Cargo() {
        this.conection = Conexion.getInstancia();
    }

    public void setDetalle_Persona_Cargo(int id_persona, int id_cargo) {

        this.id_persona = id_persona;
        this.id_cargo = id_cargo;
        this.estado = 1;
    }

//==============================================================================
//==============================================================================
    /* OBTENER DETALLE MEDIANTE EL ID DE UNA PERSONA */
    public DefaultTableModel obtenerDetalle_Persona_Cargo(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalle_persona_cargo = new DefaultTableModel();
        detalle_persona_cargo.setColumnIdentifiers(new Object[]{
            "id", "id_persona", "nombre", "apellido", "id_cargo", "cargo"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle_persona_cargo.id as idd"
                + "persona.id as idp,\n"
                + "persona.nombre as nombrep,\n"
                + "persona.apellido,\n"
                + "cargo.id as idc,\n"
                + "cargo.nombre as nombrec\n"
                + "FROM detalle_persona_cargo, persona, cargo\n"
                + "WHERE detalle_persona_cargo.id_persona=persona.id"
                + " and detalle_persona_cargo.id_cargo=cargo.id"
                + " and persona.id=?"
                + " and detalle_persona_cargo.estado='1'";;

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
                detalle_persona_cargo.addRow(new Object[]{
                    rs.getInt("idd"),
                    rs.getInt("idp"),
                    rs.getString("nombrep"),
                    rs.getString("apellido"),
                    rs.getInt("idc"),
                    rs.getString("nombrec"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalle_persona_cargo;
    }

//==============================================================================
//==============================================================================    
    /* REGISTRAR DETALLE */
    public int registrarDetalle_Persona_Cargo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO detalle_persona_cargo(\n"
                + "id_persona,id_cargo,estado)\n"
                + "VALUES(?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.id_persona);
            ps.setInt(2, this.id_cargo);

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
    public void eliminarDetalle_Persona_Cargo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE detalle_persona_cargo SET \n"
                + "estado = '0',\n"
                + "WHERE detalle_persona_cargo.id = ?";
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
