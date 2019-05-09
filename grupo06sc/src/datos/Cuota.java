
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Cuota {
    private int id;
    private String fecha;
    private int monto;
    private int id_plan_pago;
    private byte estado;
    private Conexion conection;

    public Cuota() {
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

    public int getId_plan_pago() {
        return id_plan_pago;
    }

    public void setId_plan_pago(int id_plan_pago) {
        this.id_plan_pago = id_plan_pago;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
    
    public void setCuota(String fecha, int monto, int id_plan_pago) {
        this.fecha = fecha;
        this.monto = monto;
        this.id_plan_pago = id_plan_pago;
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER CUOTAS*/
    public DefaultTableModel obtenerCuotas() {
        DefaultTableModel cuotas = new DefaultTableModel();
        cuotas.setColumnIdentifiers(new Object[]{
            "id", "fecha", "monto", "id_plan_pago", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM cuota";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                cuotas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("monto"),
                    rs.getInt("id_plan_pago"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cuotas;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER CUOTA POR ID DE PLAN DE PAGO*/
    public DefaultTableModel obtenerCuotas(int id) {
        DefaultTableModel cuota = new DefaultTableModel();
        cuota.setColumnIdentifiers(new Object[]{
            "id", "fecha", "monto", "id_plan_pago", "estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM cuota where id_plan_pago=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                cuota.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("monto"),
                    rs.getInt("id_plan_pago"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cuota;
    }
    //==============================================================================
    //==============================================================================
    /*REGISTRAR CUOTA*/
    public int registrarCuota() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO cuota (fecha, monto, id_plan_pago, estado) "
                +"VALUES(?,?,?,'1')";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha);
            ps.setInt(2, this.monto);
            ps.setInt(3, this.id_plan_pago);
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
    /*MODIFICAR CUOTA POR ID PLAN DE PAGO*/
    public void modificarCuota() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE cuota SET monto = ?,fecha = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.monto);
            ps.setString(2, this.fecha);
            ps.setInt(3, this.id);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================
    /*ELIMINAR CUOTA*/

    public void eliminarCuota() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE cuota SET estado = '0' WHERE id = ?";
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
