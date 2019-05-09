
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Detalle_Boleta {
   private int id;
    private int nota_final;
    private int id_boleta_inscripcion;
    private int id_detalle_oferta_programa_detalle_programa_modulo;
    private byte estado;
    private Conexion conection;

    public Detalle_Boleta() {
        this.conection = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNota_final() {
        return nota_final;
    }

    public void setNota_final(int nota_final) {
        this.nota_final = nota_final;
    }

    public int getId_boleta_inscripcion() {
        return id_boleta_inscripcion;
    }

    public void setId_boleta_inscripcion(int id_boleta_inscripcion) {
        this.id_boleta_inscripcion = id_boleta_inscripcion;
    }

    public int getId_detalle_oferta_programa_detalle_programa_modulo() {
        return id_detalle_oferta_programa_detalle_programa_modulo;
    }

    public void setId_detalle_oferta_programa_detalle_programa_modulo(int id_detalle_oferta_programa_detalle_programa_modulo) {
        this.id_detalle_oferta_programa_detalle_programa_modulo = id_detalle_oferta_programa_detalle_programa_modulo;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }
    
    public void setDetallaBoleta(int nota_final, int id_boleta_inscripcion, int id_detalle_oferta_programa_detalle_programa_modulo) {
        this.nota_final = nota_final;
        this.id_boleta_inscripcion = id_boleta_inscripcion;
        this.id_detalle_oferta_programa_detalle_programa_modulo = id_detalle_oferta_programa_detalle_programa_modulo;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER DETALLE BOLETA INSCRIPCION*/
    public DefaultTableModel obtenerDetalleBoletasInscripcion() {
        DefaultTableModel detalleBoletas = new DefaultTableModel();
        detalleBoletas.setColumnIdentifiers(new Object[]{
            "id", "nota_final", "id_boleta_inscripcion", "id_detalle_oferta_programa_detalle_programa_modulo", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM detalle_boleta_inscripcion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleBoletas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("nota_final"),
                    rs.getInt("id_boleta_inscripcion"),
                    rs.getInt("id_detalle_oferta_programa_detalle_programa_modulo"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleBoletas;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER DETALLE BOLETA INSCRIPCION POR ID BOLETA */
    public DefaultTableModel obtenerDetalleBoletasInscripcion(int id) {
        DefaultTableModel detalleBoletas = new DefaultTableModel();
        detalleBoletas.setColumnIdentifiers(new Object[]{
            "id", "nota_final", "id_boleta_inscripcion", "id_detalle_oferta_programa_detalle_programa_modulo", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM detalle_boleta_inscripcion where id_boleta_inscripcion=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleBoletas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("nota_final"),
                    rs.getInt("id_boleta_inscripcion"),
                    rs.getInt("id_detalle_oferta_programa_detalle_programa_modulo"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleBoletas;
    }
    //==============================================================================
    //==============================================================================
    /*REGISTRAR DETALLE BOLETA INSCRIPCION*/
    public int registrarDetalleBoletaInscripcion() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO detalle_boleta_inscripcion (nota_final, id_boleta_inscripcion, id_detalle_oferta_programa_detalle_programa_modulo, estado) "
                +"VALUES(?,?,?,'1')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, this.nota_final);
            ps.setInt(2, this.id_boleta_inscripcion);
            ps.setInt(3, this.id_detalle_oferta_programa_detalle_programa_modulo);
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
    /*MODIFICAR DETALLE BOLETA INSCRIPCION*/
    public void modificarDetalleBoletaInscripcion() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE detalle_boleta_inscripcion SET nota_final = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.nota_final);
            ps.setInt(2, this.id);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================
    /*ELIMINAR DETALLE BOLETA INSCRIPCION*/

    public void eliminarDetalleBoletaInscripcion() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE detalle_boleta_inscripcion SET estado = '0' WHERE id = ?";
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
        /*VERIFICAR ACTA DE NOTA*/
    public DefaultTableModel verificarActaNota(int id_detalle_boleta) {
        DefaultTableModel detalleOfertaModulos = new DefaultTableModel();
        detalleOfertaModulos.setColumnIdentifiers(new Object[]{
            "id"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT a.id FROM detalle_boleta_inscripcion a,detalle_oferta_programa_detalle_programa_modulo b where "
                + "a.id = ? and a.id_detalle_oferta_programa_detalle_programa_modulo = b.id and b.acta_nota = 'Abierta.' ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_detalle_boleta);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleOfertaModulos.addRow(new Object[]{
                    rs.getInt("id")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleOfertaModulos;
    }
    
    //==============================================================================
    //==============================================================================    
        /*COSTO DEL MODULO POR ID id_detalle_oferta_programa_detalle_programa_modulo*/
    public DefaultTableModel obtenerCosto(int id_oferta_modulo) {
        DefaultTableModel detalleOfertaModulos = new DefaultTableModel();
        detalleOfertaModulos.setColumnIdentifiers(new Object[]{
            "costo"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT c.costo FROM detalle_oferta_programa_detalle_programa_modulo a,detalle_programa_modulo b,modulo c where "
                + "a.id = ? and a.id_detalle_programa_modulo = b.id and b.id_modulo = c.id";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_oferta_modulo);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleOfertaModulos.addRow(new Object[]{
                    rs.getInt("costo")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleOfertaModulos;
    }
}
