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
public class Persona {

    private int id;
    private String nombre;
    private String apellido;
    private String ci;
    private String telefono;
    private String correo;
    private String tipo;
    private byte estado;

    Conexion conection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona() {
        this.conection = Conexion.getInstancia();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public void setPersona(String nombre, String apellido, String ci, String telefono, String correo, String tipo) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
    }

//==============================================================================
//==============================================================================

    /* OBTENER PERSONA DE UN ID */
    public DefaultTableModel obtenerPersona(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel persona = new DefaultTableModel();
        persona.setColumnIdentifiers(new Object[]{
            "id", "nombre", "apellido", "ci", "telefono", "correo", "tipo", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "persona.id,\n"
                + "persona.nombre,\n"
                + "persona.apellido,\n"
                + "persona.ci,\n"
                + "persona.telefono,\n"
                + "persona.correo,\n"
                + "persona.tipo,\n"
                + "persona.estado\n"
                + "FROM persona\n"
                + "WHERE persona.id=?"
                + " and persona.estado='1'";
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
                persona.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("ci"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("tipo"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return persona;
    }

//==============================================================================
//==============================================================================

    /*OBTENER PERSONAS*/
    public DefaultTableModel obtenerPersonas(String tipo) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel modulos = new DefaultTableModel();
        modulos.setColumnIdentifiers(new Object[]{
            "id", "nombre", "apellido", "ci", "telefono", "correo", "tipo", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "persona.id,\n"
                + "persona.nombre,\n"
                + "persona.apellido,\n"
                + "persona.ci,\n"
                + "persona.telefono,\n"
                + "persona.correo,\n"
                + "persona.tipo,\n"
                + "persona.estado\n"
                + "FROM persona\n"
                + "WHERE persona.estado='1' and persona.tipo='" + tipo + "'";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                modulos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("ci"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("tipo"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modulos;
    }

//==============================================================================
//==============================================================================
/*REGISTRAR UNA PERSONA*/
    public int registrarPersona() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO persona(\n"
                + "nombre,apellido,ci,telefono,correo,tipo,estado)\n"
                + "VALUES(?,?,?,?,?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro se usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables

            ps.setString(1, this.nombre);
            ps.setString(2, this.apellido);
            ps.setString(3, this.ci);
            ps.setString(4, this.telefono);
            ps.setString(5, this.correo);
            ps.setString(6, this.tipo);

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
/*MODIFICAR UNA PERSONA*/
    public void modificarPersona() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE persona SET \n"
                + "nombre = ?,\n"
                + "apellido = ?\n"
                + "ci = ?\n"
                + "telefono = ?\n"
                + "correo = ?\n"
                + "tipo = ?\n"
                + "WHERE persona.id = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.apellido);
            ps.setString(3, this.ci);
            ps.setString(4, this.telefono);
            ps.setString(5, this.correo);
            ps.setString(6, this.tipo);
            ps.setInt(7, this.id);
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
/*ELIMINAR UNA PERSONA*/
    public void eliminarPersona() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE persona SET \n"
                + "estado = '0'\n"
                + "WHERE persona.id = ?";
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

    public String verificar(String correo) {
        String resultado = "";

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT correo\n"
                + "FROM persona\n"
                + "WHERE persona.correo=?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                resultado=rs.getString("correo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
}
