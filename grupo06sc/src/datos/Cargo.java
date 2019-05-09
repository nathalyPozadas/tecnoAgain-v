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
public class Cargo {
     
    private int id;
    private String nombre;
    private byte estado;
    private Conexion conection;

    public Cargo() {
        this.conection = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getEstado() {
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

    public void setCargo( String nombre) {
        this.nombre = nombre;
    }

//==============================================================================
//==============================================================================
/* OBTENER CARGO DE UN ID */
    public DefaultTableModel obtenerCargo(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel cargo = new DefaultTableModel();
        cargo.setColumnIdentifiers(new Object[]{
            "id", "nombre", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "cargo.id,\n"
                + "cargo.nombre,\n"
                + "cargo.estado\n"
                + "FROM cargo\n"
                + "WHERE cargo.id=?"
                + " and cargo.estado='1'";
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
                cargo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cargo;
    }

//==============================================================================
//==============================================================================    
/*OBTENER CARGOS*/
    public DefaultTableModel obtenerCargos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel cargos = new DefaultTableModel();
        cargos.setColumnIdentifiers(new Object[]{
            "id", "nombre", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "cargo.id,\n"
                + "cargo.nombre,\n"
                + "cargo.estado\n"
                + "FROM cargo\n"
                + "WHERE cargo.estado='1'";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                cargos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cargos;
    }
//==============================================================================
//==============================================================================
/*REGISTRAR UN CARGO*/
    public int registrarCargo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO cargo(\n"
                + "nombre,estado)\n"
                + "VALUES(?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro se usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
 
            ps.setString(1, this.nombre);
         

            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.conection.cerrarConexion();

            // Obtengo el id generado para devolverlo
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
/*MODIFICAR UN CARGO*/
    public void modificarCargo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE cargo SET \n"
                + "nombre = ?,\n"
                + "WHERE cargo.id = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setInt(2, this.id);
            int rows = ps.executeUpdate();
            System.out.println(rows);
            // Cierro la conexion
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//==============================================================================
//==============================================================================
/*ELIMINAR UN CARGO*/

    public void eliminarCargo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE cargo SET \n"
                + "estado = '0',\n"
                + "WHERE cargo.id = ?";
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
