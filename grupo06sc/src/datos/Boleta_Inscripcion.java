
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Boleta_Inscripcion {
    private int id;
    private String fecha;
    private int id_estudiante;
    private byte estado;
    private Conexion conection;

    public Boleta_Inscripcion() {
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
    
    public void setBoletaInscripcion(String fecha, int id_estudiante) {
        this.fecha = fecha;
        this.id_estudiante = id_estudiante;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER BOLETA INSCRIPCIONES*/
    public DefaultTableModel obtenerBoletaInscripciones() {
        DefaultTableModel boletaInscripciones = new DefaultTableModel();
        boletaInscripciones.setColumnIdentifiers(new Object[]{
            "id", "fecha", "id_estudiante", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM boleta_inscripcion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                boletaInscripciones.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return boletaInscripciones;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER BOLETA INSCRIPCION POR ID*/
    public DefaultTableModel obtenerBoletaInscripcion(int id) {
        DefaultTableModel boletaInscripcion = new DefaultTableModel();
        boletaInscripcion.setColumnIdentifiers(new Object[]{
            "id", "fecha", "id_estudiante", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM boleta_inscripcion where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                boletaInscripcion.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return boletaInscripcion;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER BOLETA INSCRIPCION POR ID*/
    public DefaultTableModel convalidacion_oferta(int id_programa) {
        DefaultTableModel boletaInscripcion = new DefaultTableModel();
        boletaInscripcion.setColumnIdentifiers(new Object[]{
            "id", "fecha", "id_estudiante", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT b.id_modulo,c.nombre FROM detalle_oferta_programa_detalle_programa_modulo a,detalle_programa_modulo b,modulo c where a.id_oferta_programa=? "
                + "and a.id_detalle_programa_modulo = b.id and b.id_modulo = c.id";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_programa);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                boletaInscripcion.addRow(new Object[]{
                    rs.getInt("id_modulo"),
                    rs.getString("nombre")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return boletaInscripcion;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER MODULOS PARA CONVALIDACION POR ID ESTUDIANTE Y ID OFERTA PROGRAMA*/
    public DefaultTableModel convalidacion_estudiante(int id_estudiante,int id_programa) {
        DefaultTableModel boletaInscripcion = new DefaultTableModel();
        boletaInscripcion.setColumnIdentifiers(new Object[]{
            "id_modulo"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT d.id_modulo FROM boleta_inscripcion a,detalle_boleta_inscripcion b,detalle_oferta_programa_detalle_programa_modulo c,detalle_programa_modulo d where a.id_estudiante=? "
                        + "and a.id = b.id_boleta_inscripcion and b.id_detalle_oferta_programa_detalle_programa_modulo = c.id and "
                        + "c.id_oferta_programa = ? and c.id_detalle_programa_modulo = d.id";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_estudiante);
            ps.setInt(2, id_programa);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                boletaInscripcion.addRow(new Object[]{
                    rs.getInt("id_modulo")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return boletaInscripcion;
    }
    
    //==============================================================================
    //==============================================================================
    /*REGISTRAR BOLETA INSCRIPCION*/
    public int registrarBoletaInscripcion() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO boleta_inscripcion (fecha, id_estudiante, estado) "
                +"VALUES(?,?,'1')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha);
            ps.setInt(2, this.id_estudiante);
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
    /*ELIMINAR BOLETA INSCRIPCION*/

    public void eliminarBoletaInscripcion() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE boleta_inscripcion SET estado = '0' WHERE id = ?";
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
