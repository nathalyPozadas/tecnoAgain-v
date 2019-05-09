package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Brian
 */
public class Detalle_Persona_Profesion {

    private int id;
    private int id_persona;
    private int id_profesion;
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

    public int getId_profesion() {
        return id_profesion;
    }

    public void setId_profesion(int id_profesion) {
        this.id_profesion = id_profesion;
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

    public Detalle_Persona_Profesion() {
        this.conection = Conexion.getInstancia();
    }

    public void setDetalle_Persona_Profesion(int id_persona, int id_profesion) {

        this.id_persona = id_persona;
        this.id_profesion = id_profesion;
        this.estado = 1;
    }

//==============================================================================
//==============================================================================
    /* OBTENER DETALLE MEDIANTE EL ID DE UN PROGRAMA */
    public DefaultTableModel obtenerDetalle_Persona_Profesion(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalle_persona_profesion = new DefaultTableModel();
        detalle_persona_profesion.setColumnIdentifiers(new Object[]{
            "id", "id_persona", "nombre", "apellido", "id_profesion", "profesion"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle_persona_profesion.id as idd"
                + "persona.id as idp,\n"
                + "persona.nombre as nombrep,\n"
                + "persona.apellido,\n"
                + "profesion.id as idc,\n"
                + "profesion.nombre as nombrec\n"
                + "FROM detalle_persona_profesion, persona, profesion\n"
                + "WHERE detalle_persona_profesion.id_persona=persona.id"
                + " and detalle_persona_profesion.id_profesion=profesion.id"
                + " and persona.id=?"
                + " and detalle_persona_profesion.estado='1'";

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
                detalle_persona_profesion.addRow(new Object[]{
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
        return detalle_persona_profesion;
    }

//==============================================================================
//==============================================================================    
    /* REGISTRAR DETALLE */
    public int registrarDetalle_Persona_Profesion() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO detalle_persona_profesion(\n"
                + "id_persona,id_profesion,estado)\n"
                + "VALUES(?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.id_persona);
            ps.setInt(2, this.id_profesion);
            
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
    public void eliminarDetalle_Persona_Profesion() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE detalle_persona_profesion SET \n"
                + "estado = '0',\n"
                + "WHERE detalle_persona_profesion.id = ?";
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
