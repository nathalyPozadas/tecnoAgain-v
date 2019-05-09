
package negocio;

import datos.Detalle_Boleta;
import datos.Plan_Pago;
import javax.swing.table.DefaultTableModel;

public class N_Detalle_Boleta {
   private Detalle_Boleta detalle_Boleta;
    private Plan_Pago plan_Pago;

    public N_Detalle_Boleta() {
        this.detalle_Boleta = new Detalle_Boleta();
        this.plan_Pago = new Plan_Pago();
    }
    
    public DefaultTableModel obtenerDetalleBoletas(int id) {//ID BOLETA
        return this.detalle_Boleta.obtenerDetalleBoletasInscripcion(id);
    }
    
    public DefaultTableModel obtenerDetalleBoletas() {
        return this.detalle_Boleta.obtenerDetalleBoletasInscripcion();
    }

    public int registrarDetalleBoleta(int id_boleta_inscripcion, int id_detalle_oferta_programa_detalle_programa_modulo) {
        this.detalle_Boleta.setDetallaBoleta(0, id_boleta_inscripcion, id_detalle_oferta_programa_detalle_programa_modulo);
        int detalle_boleta = this.detalle_Boleta.registrarDetalleBoletaInscripcion();
        
        int costo_modulo = (int) this.detalle_Boleta.obtenerCosto(id_detalle_oferta_programa_detalle_programa_modulo).getValueAt(0, 0);
                                //METODO 1 - OBTENER EL COSTO DEL MODULO POR ID id_detalle_oferta_programa_detalle_programa_modulo
                                //PARA ACTUALIZAR EL MONTO TOTAL DEL PLAN DE PAGO
        DefaultTableModel plan_pago = this.plan_Pago.obtenerPlanPagoBoleta(id_boleta_inscripcion);
        int monto_total = (int) plan_pago.getValueAt(0, 3);
        int id_plan_pago = (int) plan_pago.getValueAt(0, 0);
        monto_total = monto_total + costo_modulo;
        this.plan_Pago.setMonto_total(monto_total);
        this.plan_Pago.setId(id_plan_pago);
        this.plan_Pago.modificarPlanPago();
        return detalle_boleta;
    }
    
    public void modificarDetalleBoleta(int nota_final, int id) {
        if (detalle_Boleta.verificarActaNota(id).getRowCount() == 0) {//ACTA DE NOTAS CERRADA  
            System.out.println("Acta Nota Cerrada.");
            return;
        }
        this.detalle_Boleta.setNota_final(nota_final);
        this.detalle_Boleta.setId(id);
        this.detalle_Boleta.modificarDetalleBoletaInscripcion();
    }

    public void eliminarDetalleBoleta(int id) {
        this.detalle_Boleta.setId(id);
        this.detalle_Boleta.eliminarDetalleBoletaInscripcion();
    }
}
