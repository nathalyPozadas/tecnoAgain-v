/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Brian
 */
public class Helper {

    public static final String HELP_GLOBAL = "CATASTRO MONTERO - GUIA DE USUARIO\n\n"
            + "A continuacion se listaran los comandos disponibles para la interacción con el sistema.\n"
            //1
            + "GESTIONAR EMPLEADOS\n"
            + "REGISTRAR_EMPLEADOS\n"
            + ""
            
            
            
            
            + "REGISTRARDOCENTE\n"
            + "Registrar Docente \n"
            + "El comando que permite registrar un Docente es: REGISTRARDOCENTE \n"
            + "parametros: REGISTRARDOCENTE [nombre, apellido, ci, telefono, correo]\n"
            + "\n"
            + "OBTENERDOCENTE\n"
            + "Obtener Docente \n"
            + "El comando que permite obtener los datos de un Docente es: OBTENERDOCENTE \n"
            + "parametros: OBTENERDOCENTE [id]\n"
            + "\n"
            + "OBTENERDOCENTES\n"
            + "Obtener todos los Docentes \n"
            + "El comando que permite obtener todos los Docentes es: OBTENERDOCENTES \n"
            + "parametros: OBTENERDOCENTES []\n"
            + "\n"
            + "ELIMINARDOCENTE\n"
            + "Eliminar Docente \n"
            + "El comando que permite Eliminar un Docente es: ELIMINARDOCENTE \n"
            + "parametros: ELIMINARDOCENTE [id]\n"
            + "\n"
            //2
            + "REGISTRARADMINISTRADOR\n"
            + "Registrar Administrador \n"
            + "El comando que permite registrar un Administrador es: REGISTRARADMINISTRADOR \n"
            + "parametros: REGISTRARADMINISTRADOR [nombre, apellido, ci, telefono, correo]\n"
            + "\n"
            + "OBTENERADMINISTRADOR\n"
            + "Obtener Administrador \n"
            + "El comando que permite obtener los datos de un Administrador es: HELP_OBTENERADMINISTRADOR \n"
            + "parametros: HELP_OBTENERADMINISTRADOR [id]\n"
            + "\n"
            + "OBTENERADMINISTRADORES\n"
            + "Obtener todos los Administradores \n"
            + "El comando que permite obtener todos los Administradores es: OBTENERADMINISTRADORES \n"
            + "parametros: OBTENERADMINISTRADORES []\n"
            + "\n"
            + "ELIMINARADMINISTRADOR\n"
            + "Eliminar Administrador \n"
            + "El comando que permite Eliminar un Administrador es: ELIMINARADMINISTRADOR \n"
            + "parametros: ELIMINARADMINISTRADOR [id]\n"
            + "\n"
            //3
            + "REGISTRARCLIENTE\n"
            + "Registrar Cliente \n"
            + "El comando que permite registrar un Cliente es: REGISTRARCLIENTE \n"
            + "parametros: REGISTRARCLIENTE [nombre, apellido, ci, telefono, correo]\n"
            + "\n"
            + "OBTENERCLIENTE\n"
            + "Obtener Cliente \n"
            + "El comando que permite obtener los datos de un Cliente es: OBTENERCLIENTE \n"
            + "parametros: OBTENERCLIENTE [id]\n"
            + "\n"
            + "OBTENERCLIENTES\n"
            + "Obtener todos los Clientes \n"
            + "El comando que permite obtener los datos de un Cliente es: OBTENERCLIENTES \n"
            + "parametros: OBTENERCLIENTES []\n"
            + "\n"
            + "ELIMINARCLIENTE\n"
            + "Eliminar Cliente \n"
            + "El comando que permite Eliminar un Cliente es: ELIMINARCLIENTE \n"
            + "parametros: ELIMINARCLIENTE [id]\n"
            + "\n"
            //4
            + "REGISTRARCARGO\n"
            + "Registrar Administrador \n"
            + "El comando que permite registrar un Cargo es: REGISTRARCARGO \n"
            + "parametros: REGISTRARCARGO [nombre]\n"
            + "\n"
            + "OBTENERCARGOS\n"
            + "Obtener todos los Cargos \n"
            + "El comando que permite obtener todos los Cargos es: OBTENERCARGOS \n"
            + "parametros: OBTENERCARGOS []\n"
            + "\n"
            + "ELIMINARCARGO\n"
            + "Eliminar Cargo \n"
            + "El comando que permite Eliminar un Cargo es: ELIMINARCARGO \n"
            + "parametros: ELIMINARCARGO [id]\n"
            + "\n"
            //5
            + "REGISTRARPROFESION\n"
            + "Registrar Profesion \n"
            + "El comando que permite registrar una Profesion es: REGISTRARPROFESION \n"
            + "parametros: REGISTRARPROFESION [nombre]\n"
            + "\n"
            + "OBTENERPROFESIONES\n"
            + "Obtener todas las Profesiones \n"
            + "El comando que permite obtener todas las Profesiones es: OBTENERPROFESIONES \n"
            + "parametros: OBTENERPROFESIONES []\n"
            + "\n"
            + "ELIMINARPROFESION\n"
            + "Eliminar Profesion \n"
            + "El comando que permite Eliminar una Profesion es: ELIMINARPROFESION \n"
            + "parametros: ELIMINARPROFESION [id]\n"
            + "\n"
            //6
            + "REGISTRARPROGRAMA\n"
            + "Registrar Programa \n"
            + "El comando que permite registrar un Programa es: REGISTRARPROGRAMA \n"
            + "parametros: REGISTRARPROGRAMA [nombre,presupuesto, version, edicion, tipo, fecha_ela, fecha_apro]\n"
            + "\n"
            + "OBTENERPROGRAMA\n"
            + "Obtener Programa \n"
            + "El comando que permite obtener los datos de un Programa es: OBTENERPROGRAMA \n"
            + "parametros: OBTENERPROGRAMA [id]\n"
            + "\n"
            + "OBTENERPROGRAMAS\n"
            + "Obtener Programas \n"
            + "El comando que permite obtener un listado de todos los Programa es: OBTENERPROGRAMAS \n"
            + "parametros: OBTENERPROGRAMAS []\n"
            + "\n"
            + "ELIMINARPROGRAMA\n"
            + "Eliminar Programa \n"
            + "El comando que permite Eliminar un Programa es: ELIMINARPROGRAMA \n"
            + "parametros: ELIMINARPROGRAMA [id]\n"
            + "\n"
            //7
            + "REGISTRARMODULO\n"
            + "Registrar Modulo \n"
            + "El comando que permite registrar un Modulo es: REGISTRARMODULO \n"
            + "parametros: REGISTRARMODULO [nombre, sigla, descripcion, costo, horas_a, horas_i]\n"
            + "\n"
            + "OBTENERMODULO\n"//solo este
            + "Obtener Modulo \n"
            + "El comando que permite obtener los datos de un modulo es: OBTENERMODULO \n"
            + "parametros: OBTENERMODULO [id]\n"
            + "\n"
            + "ELIMINARMODULO\n"
            + "Eliminar Modulo \n"
            + "El comando que permite Eliminar un Modulo es: ELIMINARMODULO \n"
            + "parametros: ELIMINARMODULO [id]\n"
            + "\n"
            //8
            + "REGISTRARPROFESIONDOCENTE\n"
            + "Registrar Profesiones a un Docente \n"
            + "El comando que permite registrar las Profesiones de un Docente es: REGISTRARPROFESIONDOCENTE \n"
            + "parametros: REGISTRARPROFESIONDOCENTE [id_persona, id_profesion]\n"
            + "\n"
            //9
            + "REGISTRARCARGOADMIN\n"
            + "Registrar Cargos a un Administrador \n"
            + "El comando que permite registrar los Cargos de un Administrador es: REGISTRARCARGOADMIN \n"
            + "parametros: REGISTRARCARGOADMIN [id_persona, id_cargo]\n"
            + "\n"
            //10
            + "REGISTRARMODULOSPROGRAMA\n"
            + "Registrar Modulos a un Programa \n"
            + "El comando que permite registrar los Modulos de un Programa es: REGISTRARMODULOSPROGRAMA \n"
            + "parametros: REGISTRARMODULOSPROGRAMA [id_programa, id_modulo]\n"
            + "\n"
            //11
            + "REGISTRAROFERTAPROGRAMA\n"
            + "Registrar Oferta de un Programa \n"
            + "El comando que permite registrar la Oferta de un Programa es: REGISTRAROFERTAPROGRAMA \n"
            + "parametros: REGISTRAROFERTAPROGRAMA [fecha_inicio, fecha_fin, descripcion, id_programa]\n"
            + "\n"
            //12
            + "REGISTRARCONVALIDACION\n"
            + "Registrar Convalidacion \n"
            + "El comando que permite registrar una Convalidacion es: REGISTRARCONVALIDACION \n"
            + "parametros: REGISTRARCONVALIDACION [id_programa_convalidar, id_programa_cursado, id_estudiante]\n"
            + "\n"
            //13
            + "REGISTRARBOLETAINSCRIPCION\n"
            + "Registrar Boleta de Inscripcion \n"
            + "El comando que permite registrar una Boleta de Inscripcion es: REGISTRARBOLETAINSCRIPCION \n"
            + "parametros: REGISTRARBOLETAINSCRIPCION [id_estudiante, boolPago, fecha_fin]\n"
            + "Si BOOLPAGO = 1 => PLAN DE PAGO CONTADO\n"
            + "Si BOOLPAGO = 2 => PLAN DE PAGO POR 8 CUOTAS\n"
            + "\n"
            //14
            + "REGISTRARCUOTA\n"
            + "Registrar Cuota\n"
            + "El comando que permite registrar una Cuota es: REGISTRARCUOTA \n"
            + "parametros: REGISTRARCUOTA [fecha, monto, id_plan_pago]\n";

    //1
    public static final String HELP_REGISTRARDOCENTE = "Registrar Docente \n"
            + "\n"
            + "El comando que permite registrar un Docente es: REGISTRARDOCENTE \n"
            + "parametros: REGISTRARDOCENTE[nombre, apellido, ci, telefono, correo]\n";
    public static final String HELP_OBTENERDOCENTE = "Obtener Docente \n"
            + "\n"
            + "El comando que permite obtener los datos de un Docente es: OBTENERDOCENTE \n"
            + "parametros: OBTENERDOCENTE[id]\n";
    public static final String HELP_OBTENERDOCENTES = "Obtener todos los Docentes \n"
            + "\n"
            + "El comando que permite obtener todos los Docentes es: OBTENERDOCENTES \n"
            + "parametros: OBTENERDOCENTES[]\n";
    public static final String HELP_ELIMINARDOCENTE = "Eliminar Docente \n"
            + "\n"
            + "El comando que permite Eliminar un Docente es: ELIMINARDOCENTE \n"
            + "parametros: ELIMINARDOCENTE[id]\n";
    //2
    public static final String HELP_REGISTRARADMINISTRADOR = "Registrar Administrador \n"
            + "\n"
            + "El comando que permite registrar un Administrador es: REGISTRARADMINISTRADOR \n"
            + "parametros: REGISTRARADMINISTRADOR[nombre, apellido, ci, telefono, correo]\n";
    public static final String HELP_OBTENERADMINISTRADOR = "Obtener Administrador \n"
            + "\n"
            + "El comando que permite obtener los datos de un Administrador es: HELP_OBTENERADMINISTRADOR \n"
            + "parametros: HELP_OBTENERADMINISTRADOR[id]\n";
    public static final String HELP_OBTENERADMINISTRADORES = "Obtener todos los Administradores \n"
            + "\n"
            + "El comando que permite obtener todos los Administradores es: OBTENERADMINISTRADORES \n"
            + "parametros: OBTENERADMINISTRADORES[]\n";
    public static final String HELP_ELIMINARADMINISTRADOR = "Eliminar Administrador \n"
            + "\n"
            + "El comando que permite Eliminar un Administrador es: ELIMINARADMINISTRADOR \n"
            + "parametros: ELIMINARADMINISTRADOR[id]\n";
    //3
    public static final String HELP_REGISTRARCLIENTE = "Registrar Cliente \n"
            + "\n"
            + "El comando que permite registrar un Cliente es: REGISTRARCLIENTE \n"
            + "parametros: REGISTRARCLIENTE[nombre, apellido, ci, telefono, correo]\n";
    public static final String HELP_OBTENERCLIENTE = "Obtener Cliente \n"
            + "\n"
            + "El comando que permite obtener los datos de un Cliente es: OBTENERCLIENTE \n"
            + "parametros: OBTENERCLIENTE[id]\n";
    public static final String HELP_OBTENERCLIENTES = "Obtener todos los Clientes \n"
            + "\n"
            + "El comando que permite obtener los datos de un Cliente es: OBTENERCLIENTES \n"
            + "parametros: OBTENERCLIENTES[]\n";
    public static final String HELP_ELIMINARCLIENTE = "Eliminar Cliente \n"
            + "\n"
            + "El comando que permite Eliminar un Cliente es: ELIMINARCLIENTE \n"
            + "parametros: ELIMINARCLIENTE[id]\n";
    //4
    public static final String HELP_REGISTRARCARGO = "Registrar Administrador \n"
            + "\n"
            + "El comando que permite registrar un Cargo es: REGISTRARCARGO \n"
            + "parametros: REGISTRARCARGO[nombre]\n";
    public static final String HELP_OBTENERCARGOS = "Obtener todos los Cargos \n"
            + "\n"
            + "El comando que permite obtener todos los Cargos es: OBTENERCARGOS \n"
            + "parametros: OBTENERCARGOS[]\n";
    public static final String HELP_ELIMINARCARGO = "Eliminar Cargo \n"
            + "\n"
            + "El comando que permite Eliminar un Cargo es: ELIMINARCARGO \n"
            + "parametros: ELIMINARCARGO[id]\n";
    //5
    public static final String HELP_REGISTRARPROFESION = "Registrar Profesion \n"
            + "\n"
            + "El comando que permite registrar una Profesion es: REGISTRARPROFESION \n"
            + "parametros: REGISTRARPROFESION[nombre]\n";
    public static final String HELP_OBTENERPROFESIONES = "Obtener todas las Profesiones \n"
            + "\n"
            + "El comando que permite obtener todas las Profesiones es: OBTENERPROFESIONES \n"
            + "parametros: OBTENERPROFESIONES[]\n";
    public static final String HELP_ELIMINARPROFESION = "Eliminar Profesion \n"
            + "\n"
            + "El comando que permite Eliminar una Profesion es: ELIMINARPROFESION \n"
            + "parametros: ELIMINARPROFESION[id]\n";
    //6
    public static final String HELP_REGISTRARPROGRAMA = "Registrar Programa \n"
            + "\n"
            + "El comando que permite registrar un Programa es: REGISTRARPROGRAMA \n"
            + "parametros: REGISTRARPROGRAMA[nombre,presupuesto, version, edicion, tipo, fecha_ela, fecha_apro]\n";
    public static final String HELP_OBTENERPROGRAMA = "Obtener Programa \n"
            + "\n"
            + "El comando que permite obtener los datos de un Programa es: OBTENERPROGRAMA \n"
            + "parametros: OBTENERPROGRAMA[id]\n";
    public static final String HELP_OBTENERPROGRAMAS = "Obtener Programas \n"
            + "\n"
            + "El comando que permite obtener un listado de todos los Programa es: OBTENERPROGRAMAS \n"
            + "parametros: OBTENERPROGRAMAS[]\n";
    public static final String HELP_ELIMINARPROGRAMA = "Eliminar Programa \n"
            + "\n"
            + "El comando que permite Eliminar un Programa es: ELIMINARPROGRAMA \n"
            + "parametros: ELIMINARPROGRAMA[id]\n";
    //7
    public static final String HELP_REGISTRARMODULO = "Registrar Modulo \n"
            + "\n"
            + "El comando que permite registrar un Modulo es: REGISTRARMODULO \n"
            + "parametros: REGISTRARMODULO[nombre, sigla, descripcion, costo, horas_a, horas_i]\n";
    public static final String HELP_OBTENERMODULO = "Obtener Modulo \n"
            + "\n"
            + "El comando que permite obtener los datos de un modulo es: OBTENERMODULO \n"
            + "parametros: OBTENERMODULO[id]\n";
    public static final String HELP_ELIMINARMODULO = "Eliminar Modulo \n"
            + "\n"
            + "El comando que permite Eliminar un Modulo es: ELIMINARMODULO \n"
            + "parametros: ELIMINARMODULO[id]\n";
    //8
    public static final String HELP_REGISTRARPROFESIONDOCENTE = "Registrar Profesiones a un Docente \n"
            + "\n"
            + "El comando que permite registrar las Profesiones de un Docente es: REGISTRARPROFESIONDOCENTE \n"
            + "parametros: REGISTRARPROFESIONDOCENTE[id_docente, id_profesion]\n";
    //9
    public static final String HELP_REGISTRARCARGOADMIN = "Registrar Cargos a un Administrador \n"
            + "\n"
            + "El comando que permite registrar los Cargos de un Administrador es: REGISTRARCARGOADMIN \n"
            + "parametros: REGISTRARCARGOADMIN[id_admin, id_cargo]\n";
    //10
    public static final String HELP_REGISTRARMODULOSPROGRAMA = "Registrar Modulos a un Programa \n"
            + "\n"
            + "El comando que permite registrar los Modulos de un Programa es: REGISTRARMODULOSPROGRAMA \n"
            + "parametros: REGISTRARMODULOSPROGRAMA[id_programa, id_modulo]\n";
    public static final String HELP_OBTENERMODULOSPROGRAMA = "Obtiene los Modulos de un Programa \n"
            + "\n"
            + "El comando que permite obtener los Modulos de un Programa es: OBTENERMODULOSPROGRAMA \n"
            + "parametros: OBTENERMODULOSPROGRAMA[id_programa]\n";
    //11
    public static final String HELP_REGISTRAROFERTAPROGRAMA = "Registrar Oferta de un Programa \n"
            + "\n"
            + "El comando que permite registrar la Oferta de un Programa es: REGISTRAROFERTAPROGRAMA \n"
            + "parametros: REGISTRAROFERTAPROGRAMA[fecha_inicio, fecha_fin, descripcion, id_programa]\n";
    //12
    public static final String HELP_REGISTRARCONVALIDACION = "Registrar Convalidacion \n"
            + "\n"
            + "El comando que permite registrar una Convalidacion es: REGISTRARCONVALIDACION \n"
            + "parametros: REGISTRARCONVALIDACION[id_programa_convalidar, id_programa_cursado, id_estudiante]\n";
    //13
    public static final String HELP_REGISTRARBOLETAINSCRIPCION = "Registrar Boleta de Inscripcion \n"
            + "\n"
            + "El comando que permite registrar una Boleta de Inscripcion es: REGISTRARBOLETAINSCRIPCION \n"
            + "parametros: REGISTRARBOLETAINSCRIPCION[id_estudiante, boolPago, fecha_fin]\n"
            + "Si BOOLPAGO = 1 => PLAN DE PAGO CONTADO\n"
            + "Si BOOLPAGO = 2 => PLAN DE PAGO POR 8 CUOTAS";
    //14
    public static final String HELP_REGISTRARCUOTA = "Registrar Cuota \n"
            + "\n"
            + "El comando que permite registrar una Cuota es: REGISTRARCUOTA \n"
            + "parametros: REGISTRARCUOTA[fecha, monto, id_plan_pago]\n";

}
