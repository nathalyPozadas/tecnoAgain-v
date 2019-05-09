
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Plan_Pago {
    private int id;
    private String fecha_inicio;
    private String fecha_limite;
    private int monto_total;
    private int id_boleta_inscripcion;
    private byte estado;
    private Conexion conection;

    public Plan_Pago() {
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

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public int getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }

    public int getId_boleta_inscripcion() {
        return id_boleta_inscripcion;
    }

    public void setId_boleta_inscripcion(int id_boleta_inscripcion) {
        this.id_boleta_inscripcion = id_boleta_inscripcion;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }
    
    public void setPlanPago(String fecha_inicio, String fecha_limite, int monto_total, int id_boleta_inscripcion) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_limite = fecha_limite;
        this.monto_total = monto_total;
        this.id_boleta_inscripcion = id_boleta_inscripcion;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER PLAN PAGO*/
    public DefaultTableModel obtenerPlanPagos() {
        DefaultTableModel planPagos = new DefaultTableModel();
        planPagos.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_limite", "monto_total", "id_boleta_inscripcion", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM plan_pago";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                planPagos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_limite"),
                    rs.getInt("monto_total"),
                    rs.getInt("id_boleta_inscripcion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return planPagos;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER PLAN DE PAGO POR ID */
    public DefaultTableModel obtenerPlanPago(int id) {
        DefaultTableModel planPago = new DefaultTableModel();
        planPago.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_limite", "monto_total", "id_boleta_inscripcion", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM plan_pago where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                planPago.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_limite"),
                    rs.getInt("monto_total"),
                    rs.getInt("id_boleta_inscripcion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return planPago;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER PLAN DE PAGO POR ID BOLETA */
    public DefaultTableModel obtenerPlanPagoBoleta(int id) {
        DefaultTableModel planPago = new DefaultTableModel();
        planPago.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_limite", "monto_total", "id_boleta_inscripcion", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM plan_pago where id_boleta_inscripcion=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                planPago.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_limite"),
                    rs.getInt("monto_total"),
                    rs.getInt("id_boleta_inscripcion"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return planPago;
    }
    //==============================================================================
    //==============================================================================
    /*REGISTRAR PLAN DE PAGO*/
    public int registrarPlanPago() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO plan_pago (fecha_inicio, fecha_limite, monto_total, id_boleta_inscripcion, estado) "
                +"VALUES(?,?,?,?,'1')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_limite);
            ps.setInt(3, this.monto_total);
            ps.setInt(4, this.id_boleta_inscripcion);
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
    public void modificarPlanPago() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE plan_pago SET monto_total = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.monto_total);
            ps.setInt(2, this.id);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================
    /*ELIMINAR CONVALIDACION*/

    public void eliminarPlanPago() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE plan_pago SET estado = '0' WHERE id = ?";
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
