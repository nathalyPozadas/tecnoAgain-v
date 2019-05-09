
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Detalle_Oferta_Modulo {
   private int id;
    private String fecha_inicio;
    private String fecha_fin;
    private String acta_nota;
    private int id_oferta_programa;
    private int id_detalle_programa_modulo;
    private int id_docente;
    private byte estado;
    private Conexion conection;

    public Detalle_Oferta_Modulo() {
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

    public int getId_oferta_programa() {
        return id_oferta_programa;
    }

    public void setId_oferta_programa(int id_oferta_programa) {
        this.id_oferta_programa = id_oferta_programa;
    }

    public int getId_detalle_programa_modulo() {
        return id_detalle_programa_modulo;
    }

    public void setId_detalle_programa_modulo(int id_detalle_programa_modulo) {
        this.id_detalle_programa_modulo = id_detalle_programa_modulo;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public String getActa_nota() {
        return acta_nota;
    }

    public void setActa_nota(String acta_nota) {
        this.acta_nota = acta_nota;
    }
    
    public void setDetalleOfertaModulo(String fecha_inicio, String fecha_fin, int id_oferta_programa, int id_detalle_programa_modulo, int id_docente, String acta_nota) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.id_oferta_programa = id_oferta_programa;
        this.id_detalle_programa_modulo = id_detalle_programa_modulo;
        this.id_docente = id_docente;
        this.acta_nota = acta_nota;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER DETALLE OFERTA MODULO*/
    public DefaultTableModel obtenerDetalleOfertaModulos() {
        DefaultTableModel detalleOfertaModulos = new DefaultTableModel();
        detalleOfertaModulos.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_fin", "id_oferta_programa", "id_detalle_programa_modulo", "id_docente", "estado","acta_nota"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM detalle_oferta_programa_detalle_programa_modulo";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleOfertaModulos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_fin"),
                    rs.getInt("id_oferta_programa"),
                    rs.getInt("id_detalle_programa_modulo"),
                    rs.getInt("id_docente"),
                    rs.getInt("estado"),
                    rs.getString("acta_nota")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleOfertaModulos;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER DETALLE OFERTA MODULO POR ID OFERTA PROGRAMA */
    public DefaultTableModel obtenerDetalleOfertaModulos(int id) {
        DefaultTableModel detalleOfertaModulos = new DefaultTableModel();
        detalleOfertaModulos.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_fin", "id_oferta_programa", "id_detalle_programa_modulo", "id_docente", "estado","acta_nota"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM detalle_oferta_programa_detalle_programa_modulo where id_oferta_programa=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleOfertaModulos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_fin"),
                    rs.getInt("id_oferta_programa"),
                    rs.getInt("id_detalle_programa_modulo"),
                    rs.getInt("id_docente"),
                    rs.getInt("estado"),
                    rs.getString("acta_nota")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleOfertaModulos;
    }
    
    //==============================================================================
    //==============================================================================
    /*REGISTRAR DETALLE OFERTA MODULO*/
    public int registrarDetalleOfertaModuloSD() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO detalle_oferta_programa_detalle_programa_modulo (fecha_inicio, fecha_fin, id_oferta_programa, id_detalle_programa_modulo, estado,acta_nota) "
                +"VALUES(?,?,?,?,'1',?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_fin);
            ps.setInt(3, this.id_oferta_programa);
            ps.setInt(4, this.id_detalle_programa_modulo);
            ps.setString(5, this.acta_nota);
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
    /*REGISTRAR DETALLE OFERTA MODULO*/
    public int registrarDetalleOfertaModulo() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO detalle_oferta_programa_detalle_programa_modulo (fecha_inicio, fecha_fin, id_oferta_programa, id_detalle_programa_modulo, id_docente, estado,acta_nota) "
                +"VALUES(?,?,?,?,?,'1',?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_fin);
            ps.setInt(3, this.id_oferta_programa);
            ps.setInt(4, this.id_detalle_programa_modulo);
            ps.setInt(5, this.id_docente);
            ps.setString(6, this.acta_nota);
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
    /*MODIFICAR DETALLE OFERTA MODULO*/
    public void modificarDetalleOfertaModulo() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE detalle_oferta_programa_detalle_programa_modulo SET fecha_inicio = ?,fecha_fin = ?,id_docente = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_fin);
            ps.setInt(3, this.id_docente);
            ps.setInt(4, this.id);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================
    /*ELIMINAR DETALLE OFERTA MODULO*/

    public void eliminarDetalleOfertaModulo() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE detalle_oferta_programa_detalle_programa_modulo SET estado = '0' WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================
    /*CERRAR ACTA NOTA DETALLE OFERTA MODULO*/

    public void cerrarActaNotaDetalleOfertaModulo() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE detalle_oferta_programa_detalle_programa_modulo SET acta_nota = 'Cerrada.' WHERE id = ?";
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
