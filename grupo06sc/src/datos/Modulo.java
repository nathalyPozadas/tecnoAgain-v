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
public class Modulo {
    
    private int id;
    private String nombre;
    private String sigla;
    private String descripcion;
    private int costo;
    private int horas_aula;
    private int horas_investigacion;
    private byte estado;

    Conexion conection;

    public Modulo() {
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getHoras_aula() {
        return horas_aula;
    }

    public void setHoras_aula(int horas_aula) {
        this.horas_aula = horas_aula;
    }

    public int getHoras_investigacion() {
        return horas_investigacion;
    }

    public void setHoras_investigacion(int horas_investigacion) {
        this.horas_investigacion = horas_investigacion;
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

    public void setModulo(String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i) {

        this.nombre = nombre;
        this.sigla = sigla;
        this.descripcion = descripcion;
        this.costo = costo;
        this.horas_aula = horas_a;
        this.horas_investigacion = horas_i;
        this.estado = 1;
    }

//==============================================================================
//==============================================================================

    /* OBTENER MODULO DE UN ID */
    public DefaultTableModel obtenerModulo(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel modulo = new DefaultTableModel();
        modulo.setColumnIdentifiers(new Object[]{
            "id", "nombre", "sigla", "descripcion", "costo", "horas_aula", "horas_investigacion", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "modulo.id,\n"
                + "modulo.nombre,\n"
                + "modulo.sigla,\n"
                + "modulo.descripcion,\n"
                + "modulo.costo,\n"
                + "modulo.horas_aula,\n"
                + "modulo.horas_investigacion,\n"
                + "modulo.estado,\n"
                + "FROM modulo\n"
                + "WHERE modulo.id=?"
                + " and modulo.estado=1";
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
                modulo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("sigla"),
                    rs.getString("descripcion"),
                    rs.getInt("costo"),
                    rs.getInt("horas_aula"),
                    rs.getInt("horas_investigacion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modulo;
    }

//==============================================================================
//==============================================================================

    /*OBTENER MODULOS*/
    public DefaultTableModel obtenerModulos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel modulos = new DefaultTableModel();
        modulos.setColumnIdentifiers(new Object[]{
            "id", "nombre", "sigla", "descripcion", "costo", "horas_aula", "horas_investigacion", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "modulo.id,\n"
                + "modulo.nombre,\n"
                + "modulo.sigla,\n"
                + "modulo.descripcion,\n"
                + "modulo.costo,\n"
                + "modulo.horas_aula,\n"
                + "modulo.horas_investigacion,\n"
                + "modulo.estado\n"
                + "FROM modulo\n"
                + "WHERE modulo.estado='1'";
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
                    rs.getString("sigla"),
                    rs.getString("descripcion"),
                    rs.getInt("costo"),
                    rs.getInt("horas_aula"),
                    rs.getInt("horas_investigacion"),
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
/*REGISTRAR UN MODULO*/
    public int registrarModulo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO modulo(\n"
                + "nombre,sigla,descripcion,costo,horas_aula,horas_investigacion,estado)\n"
                + "VALUES(?,?,?,?,?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro se usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables

            ps.setString(1, this.nombre);
            ps.setString(2, this.sigla);
            ps.setString(3, this.descripcion);
            ps.setInt(4, this.costo);
            ps.setInt(5, this.horas_aula);
            ps.setInt(6, this.horas_investigacion);

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
/*MODIFICAR UN MODULO*/
    public void modificarModulo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE modulo SET \n"
                + "nombre = ?,\n"
                + "sigla = ?\n"
                + "descripcion = ?\n"
                + "costo = ?\n"
                + "horas_aula = ?\n"
                + "horas_investigacion = ?\n"
                + "WHERE modulo.id = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.sigla);
            ps.setString(3, this.descripcion);
            ps.setInt(4, this.costo);
            ps.setInt(5, this.horas_aula);
            ps.setInt(6, this.horas_investigacion);
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
/*ELIMINAR UN MODULO*/
    public void eliminarModulo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE modulo SET \n"
                + "estado = ?,\n"
                + "WHERE modulo.id = ?";
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
