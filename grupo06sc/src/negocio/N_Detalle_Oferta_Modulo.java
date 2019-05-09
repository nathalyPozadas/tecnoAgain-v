package negocio;

import datos.Detalle_Oferta_Modulo;
import javax.swing.table.DefaultTableModel;

public class N_Detalle_Oferta_Modulo {

    private Detalle_Oferta_Modulo detalle_Oferta_Modulo;

    public N_Detalle_Oferta_Modulo() {
        this.detalle_Oferta_Modulo = new Detalle_Oferta_Modulo();
    }

    public DefaultTableModel obtenerDetalleOfertaModulos(int id) {//ID OFERTA PROGRAMA
        return this.detalle_Oferta_Modulo.obtenerDetalleOfertaModulos(id);
    }

    public DefaultTableModel obtenerDetalleOfertaModulos() {
        return this.detalle_Oferta_Modulo.obtenerDetalleOfertaModulos();
    }

    public int registrarDetalleOfertaModulo(String fecha_inicio, String fecha_fin, int id_oferta_programa, int id_detalle_programa_modulo, int id_docente) {
        this.detalle_Oferta_Modulo.setDetalleOfertaModulo(fecha_inicio, fecha_fin, id_oferta_programa, id_detalle_programa_modulo, id_docente, "Abierta.");
        return this.detalle_Oferta_Modulo.registrarDetalleOfertaModulo();
    }

    public void cerrarActaNota(int id) {
        this.detalle_Oferta_Modulo.setId(id);
        this.detalle_Oferta_Modulo.cerrarActaNotaDetalleOfertaModulo();
    }

    public void modificarDetalleOfertaModulo(String fecha_inicio, String fecha_fin, int id_docente, int id) {
        this.detalle_Oferta_Modulo.setId(id);
        this.detalle_Oferta_Modulo.setDetalleOfertaModulo(fecha_inicio, fecha_fin, -1, -1, id_docente, "");
        this.detalle_Oferta_Modulo.modificarDetalleOfertaModulo();
    }

    public void eliminarDetalleOfertaModulo(int id) {
        this.detalle_Oferta_Modulo.setId(id);
        this.detalle_Oferta_Modulo.eliminarDetalleOfertaModulo();
    }
}
