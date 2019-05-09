package negocio;

import datos.Boleta_Inscripcion;
import datos.Convalidacion;
import datos.Convalidacion_Informe;
import datos.Oferta_Programa;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

public class N_Convalidacion {

    private Convalidacion convalidacion;
    private Boleta_Inscripcion boleta_Inscripcion;
    private Convalidacion_Informe convalidacion_Informe;
    private Oferta_Programa oferta_Programa;

    public N_Convalidacion() {
        this.convalidacion = new Convalidacion();
        this.boleta_Inscripcion = new Boleta_Inscripcion();
        this.convalidacion_Informe = new Convalidacion_Informe();
        this.oferta_Programa = new Oferta_Programa();
    }

    public DefaultTableModel obtenerConvalidacion(int id) {
        return this.convalidacion.obtenerConvalidacion(id);
    }

    public DefaultTableModel obtenerConvalidaciones() {
        return this.convalidacion.obtenerConvalidaciones();
    }

    public int registrarConvalidacion(int id_programa_convalidar, int id_programa_cursado, int id_estudiante) {
        //NO SE PUEDE CONVALIDAR DOS OFERTAS DE PROGRAAS CON EL MISMO ID PROGRAMA
        if ((int) this.oferta_Programa.obtenerOfertaPrograma(id_programa_convalidar).getValueAt(0, 4) == (int) this.oferta_Programa.obtenerOfertaPrograma(id_programa_cursado).getValueAt(0, 4)) {
            return -1;
        }

        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fecha_actual = dia + "/" + (mes + 1) + "/" + año + "-" + hora + ":" + minuto + ":" + segundo;

        this.convalidacion.setConvalidacion(fecha_actual, id_programa_convalidar, id_programa_cursado, id_estudiante);
        int id_convalidacion = this.convalidacion.registrarConvalidacion();

        boolean w = true;
        DefaultTableModel modulos_cursados = this.boleta_Inscripcion.convalidacion_estudiante(id_estudiante, id_programa_cursado);
        DefaultTableModel modulos_a_convalidar = this.boleta_Inscripcion.convalidacion_oferta(id_programa_convalidar);//CON NOMBRE MODULO
        for (int i = 0; i < modulos_a_convalidar.getRowCount(); i++) {
            for (int j = 0; j < modulos_cursados.getRowCount(); j++) {
                if ((int) modulos_a_convalidar.getValueAt(i, 0) == (int) modulos_cursados.getValueAt(j, 0)) {
                    this.convalidacion_Informe.setConvalidacionInforme((String) modulos_a_convalidar.getValueAt(i, 1), "", (int) modulos_cursados.getValueAt(j, 0), 0, id_convalidacion);
                    this.convalidacion_Informe.registrarConvalidacionInforme();
                    w = false;
                }
                if (w && j == (modulos_cursados.getRowCount() - 1)) {
                    this.convalidacion_Informe.setConvalidacionInforme("", (String) modulos_a_convalidar.getValueAt(i, 1), 0, (int) modulos_cursados.getValueAt(j, 0), id_convalidacion);
                    this.convalidacion_Informe.registrarConvalidacionInforme();
                }
            }
            w = true;
        }
        return id_convalidacion;
    }

    public void eliminarConvalidacion(int id) {
        this.convalidacion.setId(id);
        this.convalidacion.eliminarConvalidacion();
    }
}
