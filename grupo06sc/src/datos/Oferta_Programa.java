
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Oferta_Programa {
    private int id;
    private String fecha_inicio;
    private String fecha_fin;
    private String descripcion;
    private int id_programa;
    private byte estado;
    private Conexion conection;

    public Oferta_Programa() {
        this.conection = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setOfertaPrograma(String fecha_inicio, String fecha_fin, String descripcion, int id_programa) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.descripcion = descripcion;
        this.id_programa = id_programa;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER OFERTA PROGRAMAS*/
    public DefaultTableModel obtenerOfertaProgramas() {
        DefaultTableModel oferta_programas = new DefaultTableModel();
        oferta_programas.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_fin", "descripcion", "id_programa", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM oferta_programa";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                oferta_programas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_fin"),
                    rs.getString("descripcion"),
                    rs.getInt("id_programa"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return oferta_programas;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER OFERTA DE PROGRAMA DE UN ID */
    public DefaultTableModel obtenerOfertaPrograma(int id) {
        DefaultTableModel OfertaPrograma = new DefaultTableModel();
        OfertaPrograma.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_fin", "descripcion", "id_programa", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM oferta_programa where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                OfertaPrograma.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_fin"),
                    rs.getString("descripcion"),
                    rs.getInt("id_programa"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return OfertaPrograma;
    }
    //==============================================================================
    //==============================================================================
    /*REGISTRAR UN OFERTA DE PROGRAMA*/
    public int registrarOfertaPrograma() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO oferta_programa (fecha_inicio,fecha_fin,descripcion,id_programa,estado) "
                +"VALUES(?,?,?,?,'1')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_fin);
            ps.setString(3, this.descripcion);
            ps.setInt(4, this.id_programa);
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
    /*MODIFICAR UN OFERTA DE PROGRAMA*/
    public void modificarOfertaPrograma() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE oferta_programa SET descripcion = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.descripcion);
            ps.setInt(2, this.id);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================
    /*ELIMINAR UN OFERTA DE PROGRAMA*/

    public void eliminarOfertaPrograma() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE oferta_programa SET estado = '0' WHERE id = ?";
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
