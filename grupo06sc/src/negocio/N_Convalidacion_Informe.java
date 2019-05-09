
package negocio;

import datos.Convalidacion_Informe;
import javax.swing.table.DefaultTableModel;

public class N_Convalidacion_Informe {
    private Convalidacion_Informe convalidacion_Informe;

    public N_Convalidacion_Informe() {
        this. convalidacion_Informe = new Convalidacion_Informe();
    }
    
    public DefaultTableModel obtenerConvalidacionInformes(int id) {//ID CONVALIDACION
        return this.convalidacion_Informe.obtenerConvalidacionInformes(id);
    }
    
    public DefaultTableModel obtenerConvalidacionInformes() {
        return this.convalidacion_Informe.obtenerConvalidacioInformes();
    }

    public void eliminarConvalidacionInform(int id) {
        this.convalidacion_Informe.setId(id);
        this.convalidacion_Informe.eliminarConvalidacionInforme();
    }
}
