
package negocio;

import datos.Boleta_Inscripcion;
import datos.Cuota;
import datos.Plan_Pago;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

public class N_Boleta_Inscripcion {
    private Boleta_Inscripcion boleta_Inscripcion;
    private Plan_Pago plan_Pago;
    private Cuota cuota;

    public N_Boleta_Inscripcion() {
        this.boleta_Inscripcion = new Boleta_Inscripcion();
        this.plan_Pago = new Plan_Pago();
        this.cuota = new Cuota();
    }
    
    public DefaultTableModel obtenerBoletaInscripcion(int id) {
        return this.boleta_Inscripcion.obtenerBoletaInscripcion(id);
    }
    
    public DefaultTableModel obtenerBoletaInscripciones() {
        return this.boleta_Inscripcion.obtenerBoletaInscripciones();
    }
    
//    public void operacion1(String prop1, String prop2, String prop3) {
//  propiedad1 = (prop1!=null)?prop1:"val1";
//  propiedad2 = (prop2!=null)?prop2:"val2";
//  propiedad3 = (prop3!=null)?prop3:"val3";
//}

    public int registrarBoletaInscripcion(int id_estudiante, int boolPago, String fecha_fin) {
        // SI BOOLPAGO = 1 => PLAN DE PAGO CONTADO
        // SI BOOLPAGO = 2 => PLAN DE PAGO POR 8 CUOTA
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fecha_actual = dia + "/" + (mes+1) + "/" + año + "-" + hora + ":" + minuto + ":" +  segundo;
        this.boleta_Inscripcion.setBoletaInscripcion(fecha_actual, id_estudiante);
        int id_boleta = this.boleta_Inscripcion.registrarBoletaInscripcion();//ID BOLETA INSCRIPCION
        if (boolPago == 1) {//INSERTO PLAN DE PAGO AL CONTADO
            this.plan_Pago.setPlanPago(fecha_actual, fecha_actual, 0, id_boleta);
            this.plan_Pago.registrarPlanPago();
        }else if (boolPago == 2) {//INSERTO PLAN DE PAGO POR 8 CUOTAS
            this.plan_Pago.setPlanPago(fecha_actual, fecha_fin, 0, id_boleta);
            int id_plan_pago = this.plan_Pago.registrarPlanPago();
            for (int i = 0; i < 8; i++) {
                this.cuota.setCuota("", 0, id_plan_pago);
                this.cuota.registrarCuota();
            }
        }
        return id_boleta;
    }

    public void eliminarBoletaInscripcion(int id) {
        this.boleta_Inscripcion.setId(id);
        this.boleta_Inscripcion.eliminarBoletaInscripcion();
    }
}
