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
public class Programa {

    private int id;
    private String nombre;
    private int costo;
    private int presupuesto;
    private String version;
    private String edicion;
    private String tipo_programa;
    private String fecha_elaboracion;
    private String fecha_aprobacion;
    private byte estado;

    Conexion conection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Programa() {
        this.conection = Conexion.getInstancia();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getTipo_programa() {
        return tipo_programa;
    }

    public void setTipo_programa(String tipo_programa) {
        this.tipo_programa = tipo_programa;
    }

    public String getFecha_elaboracion() {
        return fecha_elaboracion;
    }

    public void setFecha_elaboracion(String fecha_elaboracion) {
        this.fecha_elaboracion = fecha_elaboracion;
    }

    public String getFecha_aprobacion() {
        return fecha_aprobacion;
    }

    public void setFecha_aprobacion(String fecha_aprobacion) {
        this.fecha_aprobacion = fecha_aprobacion;
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

    public void setPrograma(String nombre, int presupuesto, String version, String edicion, String tipo, String fecha_ela, String fecha_apro) {

        this.nombre = nombre;
        this.costo = 0;
        this.presupuesto = presupuesto;
        this.version = version;
        this.edicion = edicion;
        this.tipo_programa = tipo;
        this.fecha_aprobacion = fecha_apro;
        this.fecha_elaboracion = fecha_ela;
        this.estado = 1;
    }

//==============================================================================
//==============================================================================

    /* OBTENER PROGRAMA DE UN ID */
    public DefaultTableModel obtenerPrograma(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel programa = new DefaultTableModel();
        programa.setColumnIdentifiers(new Object[]{
            "id", "nombre", "costo", "presupuesto", "version", "edicion", "tipo_programa", "fecha_aprobacion", "fecha_elaboracion", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "programa.id,\n"
                + "programa.nombre,\n"
                + "programa.costo,\n"
                + "programa.presupuesto,\n"
                + "programa.version,\n"
                + "programa.edicion,\n"
                + "programa.tipo_programa,\n"
                + "programa.fecha_aprobacion,\n"
                + "programa.fecha_elaboracion,\n"
                + "programa.estado\n"
                + "FROM programa\n"
                + "WHERE programa.id=?"
                + " and programa.estado=1";
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
                programa.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("costo"),
                    rs.getInt("presupuesto"),
                    rs.getString("version"),
                    rs.getString("edicion"),
                    rs.getString("tipo_programa"),
                    rs.getString("fecha_aprobacion"),
                    rs.getString("fecha_elaboracion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return programa;
    }

//==============================================================================
//==============================================================================

    /*OBTENER PROGRAMAS*/
    public DefaultTableModel obtenerProgramas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel programas = new DefaultTableModel();
        programas.setColumnIdentifiers(new Object[]{
            "id", "nombre", "costo", "presupuesto", "version", "edicion", "tipo_programa", "fecha_aprobacion", "fecha_elaboracion", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "programa.id,\n"
                + "programa.nombre,\n"
                + "programa.costo,\n"
                + "programa.presupuesto,\n"
                + "programa.version,\n"
                + "programa.edicion,\n"
                + "programa.tipo_programa,\n"
                + "programa.fecha_aprobacion,\n"
                + "programa.fecha_elaboracion,\n"
                + "programa.estado\n"
                + "FROM programa\n"
                + "WHERE programa.estado='1'";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                programas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("costo"),
                    rs.getInt("presupuesto"),
                    rs.getString("version"),
                    rs.getString("edicion"),
                    rs.getString("tipo_programa"),
                    rs.getString("fecha_aprobacion"),
                    rs.getString("fecha_elaboracion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return programas;
    }

//==============================================================================
//==============================================================================
/*REGISTRAR UN PROGRAMA*/
    public int registrarPrograma() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO programa(\n"
                + "nombre, costo, presupuesto, version, edicion, tipo_programa, fecha_aprobacion,fecha_elaboracion,estado)\n"
                + "VALUES(?,?,?,?,?,?,?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro se usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables

            ps.setString(1, this.nombre);
            ps.setInt(2, this.costo);
            ps.setInt(3, this.presupuesto);
            ps.setString(4, this.version);
            ps.setString(5, this.edicion);
            ps.setString(6, this.tipo_programa);
            ps.setString(7, this.fecha_aprobacion);
            ps.setString(8, this.fecha_elaboracion);

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
/*MODIFICAR UN PROGRAMA*/
    public void modificarPrograma() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE programa SET \n"
                + "nombre = ?,\n"
                + "presupuesto = ?\n"
                + "version = ?\n"
                + "edicion = ?\n"
                + "tipo_programa = ?\n"
                + "fecha_aprobacion = ?\n"
                + "fecha_elaboracion = ?\n"
                + "WHERE programa.id = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setInt(2, this.presupuesto);
            ps.setString(3, this.version);
            ps.setString(4, this.edicion);
            ps.setString(5, this.tipo_programa);
            ps.setString(6, this.fecha_aprobacion);
            ps.setString(7, this.fecha_elaboracion);
            ps.setInt(8, this.id);
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
/*ELIMINAR UN PROGRAMA*/
    public void eliminarPrograma() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE programa SET \n"
                + "estado = ?,\n"
                + "WHERE programa.id = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, this.id);
            int rows = ps.executeUpdate();
            System.out.println(rows);
            // Cierro la conexion
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
