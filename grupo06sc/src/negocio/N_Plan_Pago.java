
package negocio;

import datos.Plan_Pago;
import javax.swing.table.DefaultTableModel;

public class N_Plan_Pago {
    private Plan_Pago plan_Pago;

    public N_Plan_Pago() {
        this.plan_Pago = new Plan_Pago();
    }
    
    public DefaultTableModel obtenerPlanPago(int id) {
        return this.plan_Pago.obtenerPlanPago(id);
    }
    
    public DefaultTableModel obtenerPlanPagos() {
        return this.plan_Pago.obtenerPlanPagos();
    }

    public int registrarPlanPago(String fecha_inicio, String fecha_limite, int monto_total, int id_boleta_inscripcion) {
        this.plan_Pago.setPlanPago(fecha_inicio, fecha_limite, monto_total, id_boleta_inscripcion);
        return this.plan_Pago.registrarPlanPago();
    }

    public void eliminarPlanPago(int id) {
        this.plan_Pago.setId(id);
        this.plan_Pago.eliminarPlanPago();
    }
}
