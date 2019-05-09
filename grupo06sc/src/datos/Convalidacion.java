
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Convalidacion {
    private int id;
    private String fecha;
    private int id_programa_convalidar;
    private int id_programa_cursado;
    private int id_estudiante;
    private byte estado;
    private Conexion conection;

    public Convalidacion() {
        this.conection = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_programa_convalidar() {
        return id_programa_convalidar;
    }

    public void setId_programa_convalidar(int id_programa_convalidar) {
        this.id_programa_convalidar = id_programa_convalidar;
    }

    public int getId_programa_cursado() {
        return id_programa_cursado;
    }

    public void setId_programa_cursado(int id_programa_cursado) {
        this.id_programa_cursado = id_programa_cursado;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }
    
    public void setConvalidacion(String fecha, int id_programa_convalidar, int id_programa_cursado, int id_estudiante) {
        this.fecha = fecha;
        this.id_programa_convalidar = id_programa_convalidar;
        this.id_programa_cursado = id_programa_cursado;
        this.id_estudiante = id_estudiante;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER CONVALIDACIONES*/
    public DefaultTableModel obtenerConvalidaciones() {
        DefaultTableModel convalidaciones = new DefaultTableModel();
        convalidaciones.setColumnIdentifiers(new Object[]{
            "id", "fecha", "id_programa_convalidar", "id_programa_cursado", "id_estudiante", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM convalidacion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                convalidaciones.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("id_programa_convalidar"),
                    rs.getInt("id_programa_cursado"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return convalidaciones;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER CONVALIDACION POR ID */
    public DefaultTableModel obtenerConvalidacion(int id) {
        DefaultTableModel Convalidacion = new DefaultTableModel();
        Convalidacion.setColumnIdentifiers(new Object[]{
            "id", "fecha", "id_programa_convalidar", "id_programa_cursado", "id_estudiante", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM convalidacion where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                Convalidacion.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("id_programa_convalidar"),
                    rs.getInt("id_programa_cursado"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Convalidacion;
    }
    //==============================================================================
    //==============================================================================
    /*REGISTRAR CONVALIDACION*/
    public int registrarConvalidacion() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO convalidacion (fecha,id_programa_convalidar,id_programa_cursado,id_estudiante,estado) "
                +"VALUES(?,?,?,?,'1')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha);
            ps.setInt(2, this.id_programa_convalidar);
            ps.setInt(3, this.id_programa_cursado);
            ps.setInt(4, this.id_estudiante);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
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
    /*ELIMINAR CONVALIDACION*/

    public void eliminarConvalidacion() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE convalidacion SET estado = '0' WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
