
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Convalidacion_Informe {
    private int id;
    private String modulo_convalidado;
    private String modulo_no_convalidado;
    private int id_modulo_convalidado;
    private int id_modulo_no_convalidado;
    private int id_convalidacion;
    private byte estado;
    private Conexion conection;

    public Convalidacion_Informe() {
        this.conection = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModulo_convalidado() {
        return modulo_convalidado;
    }

    public void setModulo_convalidado(String modulo_convalidado) {
        this.modulo_convalidado = modulo_convalidado;
    }

    public String getModulo_no_convalidado() {
        return modulo_no_convalidado;
    }

    public void setModulo_no_convalidado(String modulo_no_convalidado) {
        this.modulo_no_convalidado = modulo_no_convalidado;
    }

    public int getId_modulo_convalidado() {
        return id_modulo_convalidado;
    }

    public void setId_modulo_convalidado(int id_modulo_convalidado) {
        this.id_modulo_convalidado = id_modulo_convalidado;
    }

    public int getId_modulo_no_convalidado() {
        return id_modulo_no_convalidado;
    }

    public void setId_modulo_no_convalidado(int id_modulo_no_convalidado) {
        this.id_modulo_no_convalidado = id_modulo_no_convalidado;
    }

    public int getId_convalidacion() {
        return id_convalidacion;
    }

    public void setId_convalidacion(int id_convalidacion) {
        this.id_convalidacion = id_convalidacion;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }
    
    public void setConvalidacionInforme(String modulo_convalidado, String modulo_no_convalidado, int id_modulo_convalidado, int id_modulo_no_convalidado, int id_convalidacion) {
        this.modulo_convalidado = modulo_convalidado;
        this.modulo_no_convalidado = modulo_no_convalidado;
        this.id_modulo_convalidado = id_modulo_convalidado;
        this.id_modulo_no_convalidado = id_modulo_no_convalidado;
        this.id_convalidacion = id_convalidacion;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER CONVALIDACION INFORMES*/
    public DefaultTableModel obtenerConvalidacioInformes() {
        DefaultTableModel convalidacionInformes = new DefaultTableModel();
        convalidacionInformes.setColumnIdentifiers(new Object[]{
            "id", "modulo_convalidado", "modulo_no_convalidado", "id_modulo_convalidado", "id_modulo_no_convalidado", "id_convalidacion", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM convalidacion_informe";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                convalidacionInformes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("modulo_convalidado"),
                    rs.getString("modulo_no_convalidado"),
                    rs.getInt("id_modulo_convalidado"),
                    rs.getInt("id_modulo_no_convalidado"),
                    rs.getInt("id_convalidacion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return convalidacionInformes;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER CONVALIDACION INFORMES POR ID CONVALIDACION*/
    public DefaultTableModel obtenerConvalidacionInformes(int id) {
        DefaultTableModel ConvalidacionInformes = new DefaultTableModel();
        ConvalidacionInformes.setColumnIdentifiers(new Object[]{
            "id", "modulo_convalidado", "modulo_no_convalidado", "id_modulo_convalidado", "id_modulo_no_convalidado", "id_convalidacion", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM convalidacion_informe where id_convalidacion=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                ConvalidacionInformes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("modulo_convalidado"),
                    rs.getString("modulo_no_convalidado"),
                    rs.getInt("id_modulo_convalidado"),
                    rs.getInt("id_modulo_no_convalidado"),
                    rs.getInt("id_convalidacion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ConvalidacionInformes;
    }
    //==============================================================================
    //==============================================================================
    /*REGISTRAR CONVALIDACION INFORME*/
    public int registrarConvalidacionInforme() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO convalidacion_informe (modulo_convalidado, modulo_no_convalidado, id_modulo_convalidado, id_modulo_no_convalidado, id_convalidacion, estado) "
                +"VALUES(?,?,?,?,?,'1')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.modulo_convalidado);
            ps.setString(2, this.modulo_no_convalidado);
            ps.setInt(3, this.id_modulo_convalidado);
            ps.setInt(4, this.id_modulo_no_convalidado);
            ps.setInt(5, this.id_convalidacion);
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
    /*ELIMINAR CONVALIDACION INFORME*/

    public void eliminarConvalidacionInforme() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE convalidacion_informe SET estado = '0' WHERE id = ?";
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
