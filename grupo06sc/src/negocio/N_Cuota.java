
package negocio;

import datos.Cuota;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

public class N_Cuota {
    private Cuota cuota;

    public N_Cuota() {
        this.cuota = new Cuota();
    }
    
    public DefaultTableModel obtenerCuotas(int id) {// ID PLAN DE PAGO
        return this.cuota.obtenerCuotas(id);
    }
    
    public DefaultTableModel obtenerCuotas() {
        return this.cuota.obtenerCuotas();
    }

    public int registrarCuota(String fecha, int monto, int id_plan_pago) {
        this.cuota.setCuota(fecha, monto, id_plan_pago);
        return this.cuota.registrarCuota();
    }

    public void modificarCuota(int id_plan_pago, int monto) {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fecha_actual = dia + "/" + (mes+1) + "/" + año + "-" + hora + ":" + minuto + ":" +  segundo;
        
        this.cuota.setMonto(monto);
        this.cuota.setFecha(fecha_actual);
        this.cuota.setId(id_plan_pago);
        this.cuota.modificarCuota();
    }

    public void eliminarCuota(int id) {
        this.cuota.setId(id);
        this.cuota.eliminarCuota();
    }
}
