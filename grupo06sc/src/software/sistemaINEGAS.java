/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;

import correo.ClienteSMTP;
import negocio.N_Docente;
import procesador.AnalizadorLex;
import procesador.Cinta;
import procesador.Parser;
import procesador.Token;
import utils.Helper;
import utils.Utils;
import correo.MimeMail;
import negocio.N_Administrador;
import negocio.N_Boleta_Inscripcion;
import negocio.N_Cargo;
import negocio.N_Cliente;
import negocio.N_Convalidacion;
import negocio.N_Convalidacion_Informe;
import negocio.N_Cuota;
import negocio.N_Detalle_Boleta;
import negocio.N_Detalle_Oferta_Modulo;
import negocio.N_Detalle_Persona_Cargo;
import negocio.N_Detalle_Persona_Profesion;
import negocio.N_Detalle_Programa_Modulo;
import negocio.N_Modulo;
import negocio.N_Oferta_Programa;
import negocio.N_Plan_Pago;
import negocio.N_Profesion;
import negocio.N_Programa;

/**
 *
 * @author Brian
 */
public class sistemaINEGAS {

    public void processMessage(String Message) {
        // Setteando Variables
        // System.out.println("Class Acropolis:processMessage:Message " + Message);

        String destinatario = Utils.getDestinatario(Message);
        System.out.println("Destinatario: " + destinatario);
        String content = Utils.getSubjectOrden(Message);
        int cantidad = content.length();

        System.out.println("Mensaje 2: " + content);
        Cinta cinta = new Cinta(content);
        AnalizadorLex analex = new AnalizadorLex(cinta);
        System.out.println(cinta);
        Parser parser = new Parser(analex);
        // Verificar Orden
        parser.Expresion();
        if (parser.errorFlag) {

            // Enviar Correo de Error
            ClienteSMTP.sendMail(destinatario, "Error de Comando",
            //ClienteSMTP.sendMail("grupo06@mail.ficct.uagrm.edu.bo"," ",
                    "El comando introducido es incorrecto, trate consultando las ayudas con el comando HELP"
            );
            return;
        }
//        Verifico si es Administrador
        /*
        N_Administrador admin = new N_Administrador();
        if (admin.verificar(destinatario) == "") {
            ClienteSMTP.sendMail(destinatario, "Sitema - Postgrado INEGAS", "Usted no es Administrador del Sistema, necesita de privilegios para poder hacer uso del Sistema");
            System.out.println("Email enviado.");
            return;
        }
        */
//         Si todo va bien, procesar el Comando
        analex.Init();
        Token token = analex.Preanalisis();
        System.out.println(token.getToStr());

        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            ClienteSMTP.sendMail(destinatario, "CATASTRO MONTERO - GUIA DE USUARIO", Helper.HELP_GLOBAL);
            //ClienteSMTP.sendMail("nathalyPozadas@outlook.com", "Ayudas - Sistema INEGAS", "aaaa");
            //ClienteSMTP.sendMail("nath.1475369@gmail.com", "Ayudas - Sistema INEGAS", "aaaa");
            //ClienteSMTP.sendMail("raqb_350@hotmail.com", "Ayudas - Sistema INEGAS", "aaaa");
           // ClienteSMTP.sendMail("rene.villarroel@upcet.com.bo", "Ayudas - Sistema", "AAAAA");
            return;
        }

        // Sino es HELP, es una funcionalidad
        System.out.println(token.getAtributo());
        switch (token.getAtributo()) {
            //==1
            case Token.REGISTRARDOCENTE:
                registrarDocente(analex, destinatario);
                break;
            case Token.OBTENERDOCENTE:
                obtenerDocente(analex, destinatario);
                break;
            case Token.OBTENERDOCENTES:
                obtenerDocentes(analex, destinatario);
                break;
            case Token.ELIMINARDOCENTE:
                eliminarDocente(analex, destinatario);
                break;
            //==2    
            case Token.REGISTRARADMINISTRADOR:
                registrarAdministrador(analex, destinatario);
                break;
            case Token.OBTENERADMINISTRADOR:
                obtenerAdministrador(analex, destinatario);
                break;
            case Token.OBTENERADMINISTRADORES:
                obtenerAdministradores(analex, destinatario);
                break;
            case Token.ELIMINARADMINISTRADOR:
                eliminarAdministrador(analex, destinatario);
                break;
            //==3    
            case Token.REGISTRARCLIENTE:
                registrarCliente(analex, destinatario);
                break;
            case Token.OBTENERCLIENTE:
                obtenerCliente(analex, destinatario);
                break;
            case Token.OBTENERCLIENTES:
                obtenerClientes(analex, destinatario);
                break;
            case Token.ELIMINARCLIENTE:
                eliminarCliente(analex, destinatario);
                break;
            //==4    
            case Token.REGISTRARCARGO:
                registrarCargo(analex, destinatario);
                break;
            case Token.OBTENERCARGOS:
                obtenerCargos(analex, destinatario);
                break;
            //==5    
            case Token.REGISTRARPROFESION:
                registrarProfesion(analex, destinatario);
                break;
            case Token.OBTENERPROFESIONES:
                registrarProfesion(analex, destinatario);
                break;
            case Token.ELIMINARPROFESION:
                eliminarProfesion(analex, destinatario);
                break;
            //==6    
            case Token.REGISTRARPROGRAMA:
                registrarPrograma(analex, destinatario);
                break;
            case Token.OBTENERPROGRAMA:
                obtenerPrograma(analex, destinatario);
                break;
            case Token.OBTENERPROGRAMAS:
                obtenerProgramas(analex, destinatario);
                break;
            case Token.ELIMINARPROGRAMA:
                eliminarPrograma(analex, destinatario);
                break;
            //==7
            case Token.REGISTRARMODULO:
                registrarModulo(analex, destinatario);
                break;
            case Token.OBTENERMODULO:
                obtenerModulo(analex, destinatario);
                break;
            case Token.ELIMINARMODULO:
                eliminarModulo(analex, destinatario);
                break;
            //==8
            case Token.REGISTRARPROFESIONDOCENTE:
                registrarProfesionDocente(analex, destinatario);
                break;
            case Token.ELIMINARPROFESIONDOCENTE:
                eliminarProfesionDocente(analex, destinatario);
                break;
            //==9
            case Token.REGISTRARCARGOADMIN:
                registrarCargoAdmin(analex, destinatario);
                break;
            case Token.ELIMINARCARGOADMIN:
                eliminarCargoAdmin(analex, destinatario);
                break;
            //==10
            case Token.REGISTRARMODULOSPROGRAMA:
                registrarModulosPrograma(analex, destinatario);
                break;
            case Token.OBTENERMODULOSPROGRAMA:
                obtenerModulosPrograma(analex, destinatario);
                break;
            case Token.ELIMINARMODULOPROGRAMA:
                eliminarModuloPrograma(analex, destinatario);
                break;

            //==11
            case Token.REGISTRAROFERTAPROGRAMA:
                registrarOfertaPrograma(analex, destinatario);
                break;

            //==12    
            case Token.REGISTRARCONVALIDACION:
                registrarConvalidacion(analex, destinatario);
                break;
            //13

            case Token.REGISTRARBOLETAINSCRIPCION:
                registrarBoletaInscripcion(analex, destinatario);
                break;
            //14  
            case Token.OBTENERCUOTAS:
                obtenerCuotas(analex, destinatario);
                break;
            case Token.ELIMINARCUOTA:
                eliminarCuota(analex, destinatario);
                break;
            //15    
            case Token.MODIFICARCRONOGRAMAMODULO:
                modificarCronogramaModulo(analex, destinatario);
                break;
            case Token.ELIMINARCRONOGRAMAMODULO:
                eliminarCronogramaModulo(analex, destinatario);
                break;
            //16    
            case Token.REGISTRARMODULOBOLETA:
                registrarModuloBoleta(analex, destinatario);
                break;
            //17 
            case Token.CERRARACTADENOTAS:
                cerrarActaNotas(analex, destinatario);
                break;
            //18
            case Token.OBTENERNOTA:
                obtenerNota(analex, destinatario);
                break;
            case Token.OBTENERNOTAS:
                obtenerNotas(analex, destinatario);
                break;
            case Token.ELIMINARNOTA:
                eliminarNota(analex, destinatario);
                break;
            //19
            case Token.REGISTRARPLANPAGO:
                registrarPlanPago(analex, destinatario);
                break;
            case Token.OBTENERPLANPAGO:
                obtenerPlanPago(analex, destinatario);
                break;
            case Token.OBTENERPLANESPAGOS:
                obtenerPlanPagos(analex, destinatario);
                break;
            case Token.ELIMINARPLANPAGO:
                eliminarPlanPago(analex, destinatario);
                break;
            //20
            case Token.OBTENERCONVALIDACIONINFORME:
                obtenerConvalidacionInforme(analex, destinatario);
                break;
            case Token.ELIMINARCONVALIDACIONINFORME:
                eliminarConvalidacion(analex, destinatario);
                break;

        }
    }
//==============================================================================
//==============================================================================
//============================METODOS DE REGISTROS==============================
//==============================================================================
//==============================================================================

    //=======1
    private void registrarDocente(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARDOCENTE);
            return;
        }

        // Sino, ejecutar el comando
        N_Docente docenteN = new N_Docente();

        // Atributos
        //id, nombre, apellido, ci, telefono, correo
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String apellido = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String ci = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String correo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            docenteN.registrarDocente(nombre, apellido, ci, telefono, correo);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Docente", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(docenteN.obtenerDocentes()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Docente", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerDocente(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Docente docenteN = new N_Docente();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(docenteN.obtenerDocente(id));
        MimeMail mimemailer = new MimeMail();
        try {
            mimemailer.sendHtmlEmail(destinatario, "Docente ", "<h1>Docente</h1> \n <h4>Detalle del Docente</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Docente", "error durante el Proceso");

        }
    }

    private void obtenerDocentes(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Docente docenteN = new N_Docente();

        // Atributos
        analex.Avanzar();

        try {
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(docenteN.obtenerDocentes());
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Docentes ", "<h1>Docentes</h1> \n <h4>Listado de Docentes</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Docentes", "error durante el Proceso");

        }
    }

    private void eliminarDocente(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_ELIMINARDOCENTE);
            return;
        }
        N_Docente dN = new N_Docente();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            dN.eliminarDocente(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Docente", "Eliminacion Correcta - Lista de Docentes Activos\n" + Utils.dibujarTablawithHTML(dN.obtenerDocentes()));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======2
    private void registrarAdministrador(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARADMINISTRADOR);
            return;
        }

        // Sino, ejecutar el comando
        N_Administrador administradorN = new N_Administrador();

        // Atributos
        //id, nombre, apellido, ci, telefono, correo
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String apellido = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String ci = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String correo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            administradorN.registrarAdministrador(nombre, apellido, ci, telefono, correo);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Administrador", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(administradorN.obtenerAdministradores()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Administrador", "error durante el registro, verifique con el comando HELP");
        }
    }

    private void obtenerAdministrador(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Sino, ejecutar el comando
        N_Administrador administradorN = new N_Administrador();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(administradorN.obtenerAdministrador(id));
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Administrador ", "<h1>Administrador</h1> \n <h4>Detalle del Administrador</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Administrador", "error durante el Proceso");

        }
    }

    private void obtenerAdministradores(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        //ejecutar el comando
        N_Administrador adminN = new N_Administrador();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();

        try {

            MimeMail mimemailer = new MimeMail();
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(adminN.obtenerAdministradores());
            mimemailer.sendHtmlEmail(destinatario, "Administradores ", "<h1>Administradores</h1> \n <h4>Listado de Administradores</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Administradores", "error durante el Proceso");

        }
    }

    private void eliminarAdministrador(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_ELIMINARADMINISTRADOR);
            return;
        }
        N_Administrador aN = new N_Administrador();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            aN.eliminarAdministrador(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Administrador", "Eliminacion Correcta - Lista de Administradores Activos\n" + Utils.dibujarTablawithHTML(aN.obtenerAdministradores()));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======3
    private void registrarCliente(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARCLIENTE);
            return;
        }

        // Sino, ejecutar el comando
        N_Cliente clienteN = new N_Cliente();

        // Atributos
        //id, nombre, apellido, ci, telefono, correo
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String apellido = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String ci = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String correo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            clienteN.registrarCliente(nombre, apellido, ci, telefono, correo);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Cliente", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(clienteN.obtenerClientes()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Cliente", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerCliente(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_OBTENERCLIENTE);
            return;
        }
        // Sino, ejecutar el comando
        N_Cliente clienteN = new N_Cliente();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(clienteN.obtenerCliente(id));
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Cliente ", "<h1>Cliente</h1> \n <h4>Detalle del Cliente</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Cliente", "error durante el Proceso");

        }
    }

    private void obtenerClientes(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_OBTENERCLIENTES);
            return;
        }
        // Sino, ejecutar el comando
        N_Cliente clienteN = new N_Cliente();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(clienteN.obtenerClientes());
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Clientes ", "<h1>Clientes</h1> \n <h4>Listado de Clientes</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Clientes", "error durante el Proceso");

        }
    }

    private void eliminarCliente(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Cliente cN = new N_Cliente();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        MimeMail mailer = new MimeMail();
        try {
            cN.eliminarCliente(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Cliente", "Eliminacion Correcta - Lista de Clientes Activos\n" + Utils.dibujarTablawithHTML(cN.obtenerClientes()));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======4
    private void registrarCargo(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARCARGO);
            return;
        }

        // Sino, ejecutar el comando
        N_Cargo cargoN = new N_Cargo();

        // Atributos
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            cargoN.registrarCargo(nombre);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Cargo", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(cargoN.obtenerCargos()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Cargo", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerCargos(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Cargo cargoN = new N_Cargo();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(cargoN.obtenerCargos());
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Cargos ", "<h1>Cargos</h1> \n <h4>Listado de Cargos</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Clientes", "error durante el Proceso");

        }
    }

    private void eliminarCargo(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_ELIMINARCARGO);
            return;
        }
        N_Cargo cN = new N_Cargo();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            cN.eliminarCargo(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Cargo", "Eliminacion Correcta\n" + Utils.dibujarTablawithHTML(cN.obtenerCargo(id)));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======5
    private void registrarProfesion(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARPROFESION);
            return;
        }

        // Sino, ejecutar el comando
        N_Profesion profesionN = new N_Profesion();

        // Atributos
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            profesionN.registrarProfesion(nombre);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Profesion", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(profesionN.obtenerProfesiones()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Profesion", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerProfesiones(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_OBTENERPROFESIONES);
            return;
        }
        // Sino, ejecutar el comando
        N_Profesion pN = new N_Profesion();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(pN.obtenerProfesiones());
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Profesiones ", "<h1>Profesiones</h1> \n <h4>Listado de Profesiones</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Profesiones", "error durante el Proceso");

        }
    }

    private void eliminarProfesion(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_ELIMINARPROFESION);
            return;
        }
        N_Profesion pN = new N_Profesion();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            pN.eliminarProfesion(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Profesion", "Eliminacion Correcta\n" + Utils.dibujarTablawithHTML(pN.obtenerProfesion(id)));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======6
    private void registrarPrograma(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARPROGRAMA);
            return;
        }
        // Sino, ejecutar el comando
        N_Programa programaN = new N_Programa();

        // Atributos
        //String nombre, int presupuesto, String version, String edicion, String tipo, String fecha_ela, String fecha_apro
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int presupuesto = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String version = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String edicion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String tipo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String fecha_ela = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String fecha_apro = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        try {
            programaN.registrarPrograma(nombre, presupuesto, version, edicion, tipo, fecha_ela, fecha_apro);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Programa", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(programaN.obtenerProgramas()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Programa", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerPrograma(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_OBTENERPROGRAMA);
            return;
        }
        // Sino, ejecutar el comando
        N_Programa pN = new N_Programa();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(pN.obtenerPrograma(id));
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Programa ", "<h1>Programa</h1> \n <h4>Detalle del Programa</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Programa", "error durante el Proceso");

        }

    }

    private void obtenerProgramas(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_OBTENERPROGRAMAS);
            return;
        }
        // Sino, ejecutar el comando
        N_Programa pN = new N_Programa();

        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(pN.obtenerProgramas());
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Programas ", "<h1>Programas</h1> \n <h4>Listado de Programas</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Programas", "error durante el Proceso");

        }
    }

    private void eliminarPrograma(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_ELIMINARPROGRAMA);
            return;
        }
        N_Programa pN = new N_Programa();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            pN.eliminarPrograma(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Programa", "Eliminacion Correcta\n" + Utils.dibujarTablawithHTML(pN.obtenerPrograma(id)));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======7
    private void registrarModulo(AnalizadorLex analex, String destinatario) {

        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARMODULO);
            return;
        }
        // Sino, ejecutar el comando
        N_Modulo moduloN = new N_Modulo();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String sigla = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int costo = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int horas_a = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        try {
            moduloN.registrarModulo(nombre, sigla, descripcion, costo, horas_a, horas_a);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Modulo", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(moduloN.obtenerModulos()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Modulo", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerModulo(AnalizadorLex analex, String destinatario) {

        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_OBTENERPROGRAMA);
            return;
        }
        // Sino, ejecutar el comando
        N_Modulo mN = new N_Modulo();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(mN.obtenerModulo(id));
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Modulo ", "<h1>Modulo</h1> \n <h4>Detalle del Modulo</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Modulo", "error durante el Proceso");

        }

    }

    private void eliminarModulo(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_ELIMINARMODULO);
            return;
        }
        N_Modulo mN = new N_Modulo();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            mN.eliminarModulo(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Modulo", "Eliminacion Correcta\n" + Utils.dibujarTablawithHTML(mN.obtenerModulo(id)));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //======8
    private void registrarProfesionDocente(AnalizadorLex analex, String destinatario) {

        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARPROFESIONDOCENTE);
            return;
        }
        // Sino, ejecutar el comando
        N_Detalle_Persona_Profesion ppN = new N_Detalle_Persona_Profesion();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id_persona = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_profesion = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        try {
            ppN.registrarDetalle_Persona_Profesion(id_persona, id_profesion);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Profesion a un Docente", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Profesion a un Docente", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void eliminarProfesionDocente(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Detalle_Persona_Profesion ppN = new N_Detalle_Persona_Profesion();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            ppN.eliminarProfesion(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Profesion de un Docente", "Eliminacion Correcta\n" + Utils.dibujarTablawithHTML(ppN.obtenerDetalle_Persona_Profesion(id)));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //======9
    private void registrarCargoAdmin(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Detalle_Persona_Cargo pcN = new N_Detalle_Persona_Cargo();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id_persona = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_cargo = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        try {
            pcN.registrarDetalle_Persona_Cargo(id_persona, id_cargo);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Cargo a un Administrador", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Cargo a un Administrador", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void eliminarCargoAdmin(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Detalle_Persona_Cargo pcN = new N_Detalle_Persona_Cargo();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            pcN.eliminarCargo(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Cargo de un Administrador", "Eliminacion Correcta\n" + Utils.dibujarTablawithHTML(pcN.obtenerDetalle_Persona_Cargo(id)));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======10
    private void registrarModulosPrograma(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Detalle_Programa_Modulo pmN = new N_Detalle_Programa_Modulo();

        // Atributos
        //String nombre, String sigla, String descripcion, int costo, int horas_a, int horas_i
        analex.Avanzar();
        int id_persona = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_modulo = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        try {
            pmN.registrarDetalle_Programa_Modulo(id_persona, id_modulo);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Modulo a un Programa", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Modulo a un Programa", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerModulosPrograma(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Sino, ejecutar el comando
        N_Detalle_Programa_Modulo pmN = new N_Detalle_Programa_Modulo();

        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        String message = Utils.dibujarTablawithHTMLwithoutOpciones(pmN.obtenerDetalle_Programa_Modulo(id));
        try {

            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Modulos del Programa", "<h1>Modulos del Programa</h1> \n <h4>Listado de Modulos</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Modulos", "error durante el Proceso");

        }
    }

    private void eliminarModuloPrograma(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Detalle_Programa_Modulo pmN = new N_Detalle_Programa_Modulo();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            pmN.eliminarModulo(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Modulo de un Programa", "Eliminacion Correcta\n" + Utils.dibujarTablawithHTML(pmN.obtenerDetalle_Programa_Modulo(id)));
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======11
    private void registrarOfertaPrograma(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Oferta_Programa opN = new N_Oferta_Programa();

        // Atributos
        //fecha_inicio, fecha_fin, descripcion, id_programa
        analex.Avanzar();
        String fecha_inicio = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String fecha_fin = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_programa = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            opN.registrarOfertaPrograma(fecha_inicio, fecha_fin, descripcion, id_programa);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar la Oferta de un Programa", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar a Oferta de un Programa", "error durante el registro, verifique con el comando HELP");

        }
    }

    //=======12
    private void registrarConvalidacion(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        // Sino, ejecutar el comando
        N_Convalidacion cN = new N_Convalidacion();

        // Atributos
        // id_programa_convalidar, id_programa_cursado, id_estudiante
        analex.Avanzar();
        int id_programa_convalidar = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_programa_cursado = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_estudiante = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            cN.registrarConvalidacion(id_programa_convalidar, id_programa_cursado, id_estudiante);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Convalidacion", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Convalidacion", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void eliminarConvalidacion(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Convalidacion cN = new N_Convalidacion();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            cN.eliminarConvalidacion(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Convalidacion", "Eliminacion Correcta");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
    //=======13

    private void registrarBoletaInscripcion(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Boleta_Inscripcion bN = new N_Boleta_Inscripcion();

        // Atributos
        //int id_estudiante, int boolPago, String fecha_fin
        analex.Avanzar();
        int id_estudiante = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int boolPago = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String fecha_fin = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            bN.registrarBoletaInscripcion(id_estudiante, boolPago, fecha_fin);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Boleta de Inscripcion", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Boleta de Inscripcion", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void eliminarBoletaInscripcion(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Boleta_Inscripcion bN = new N_Boleta_Inscripcion();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            bN.eliminarBoletaInscripcion(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Boleta de Inscripcion", "Eliminacion Correcta");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======14
    private void obtenerCuotas(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Cuota cN = new N_Cuota();

        // Atributos
        analex.Avanzar();
        int id_plan_pago = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(cN.obtenerCuotas(id_plan_pago));
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Cuotas", "<h1>Cuotas</h1> \n <h4>Detalle de Cuotas del Plan de Pago</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Cuotas", "error durante el Proceso");

        }
    }

    private void eliminarCuota(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Cuota cN = new N_Cuota();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        //String message = Utils.dibujarTablaProductos(productoNegocio.obtenerProducto(idProducto));
        MimeMail mailer = new MimeMail();
        try {
            cN.eliminarCuota(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Cuota", "Eliminacion Correcta");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======15
    private void modificarCronogramaModulo(AnalizadorLex analex, String destinatario) {
// Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Detalle_Oferta_Modulo mN = new N_Detalle_Oferta_Modulo();

        // Atributos
        analex.Avanzar();
        String fecha_inicio = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String fecha_fin = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        
        analex.Avanzar();
        int id_docente = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            mN.modificarDetalleOfertaModulo(fecha_inicio, fecha_fin, id_docente, id);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Modificar Cronograma Modulo", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Modificar Cronograma Modulo", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void eliminarCronogramaModulo(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Detalle_Oferta_Modulo cN = new N_Detalle_Oferta_Modulo();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        MimeMail mailer = new MimeMail();
        try {
            cN.eliminarDetalleOfertaModulo(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Cronograma Modulo", "Eliminacion Correcta");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //=======16
    private void registrarModuloBoleta(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Detalle_Boleta bN = new N_Detalle_Boleta();

        // Atributos
        //int id_boleta_inscripcion, int id_detalle_oferta_programa_detalle_programa_modulo
        analex.Avanzar();
        int id_boleta_inscripcion = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_detalle_oferta_programa_detalle_programa_modulo = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            bN.registrarDetalleBoleta(id_boleta_inscripcion, id_detalle_oferta_programa_detalle_programa_modulo);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Boleta Modulo", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Boleta Modulo", "error durante el registro, verifique con el comando HELP");

        }
    }

    //17
    private void cerrarActaNotas(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Detalle_Oferta_Modulo dN = new N_Detalle_Oferta_Modulo();

        // Atributos
        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            dN.cerrarActaNota(id);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Cerrar Acta de Notas", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Cerrar Acta de Notas", "error durante el registro, verifique con el comando HELP");

        }
    }

    //18
    private void obtenerNota(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Detalle_Boleta bN = new N_Detalle_Boleta();

        // Atributos
        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(bN.obtenerDetalleBoletas(id));
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Obtener Nota", "<h1>Nota</h1> \n <h4>Detalle de la Nota</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Nota", "error durante el Proceso");

        }
    }

    private void obtenerNotas(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Detalle_Boleta bN = new N_Detalle_Boleta();

        try {
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(bN.obtenerDetalleBoletas());
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Obtener Notas", "<h1>Notas</h1> \n <h4>Detalle de las Notas</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Notas", "error durante el Proceso");

        }
    }

    private void eliminarNota(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Detalle_Boleta bN = new N_Detalle_Boleta();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        MimeMail mailer = new MimeMail();
        try {
            bN.eliminarDetalleBoleta(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Nota", "Eliminacion Correcta");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //19
    private void registrarPlanPago(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Plan_Pago ppN = new N_Plan_Pago();
        // Atributos
        //String fecha_inicio, String fecha_limite,int monto_total, int id_boleta_inscripcion

        analex.Avanzar();
        String fecha_inicio = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        String fecha_limite = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int monto_total = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        analex.Avanzar();
        int id_boleta_inscripcion = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            ppN.registrarPlanPago(fecha_inicio, fecha_limite, monto_total, id_boleta_inscripcion);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Plan de Pago", "Registro realizado correctamente\n");
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Plan de Pago", "error durante el registro, verifique con el comando HELP");

        }
    }

    private void obtenerPlanPago(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Plan_Pago ppN = new N_Plan_Pago();

        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(ppN.obtenerPlanPago(id));
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Obtener Plan de Pago", "<h1>Plan de Pago</h1> \n <h4>Detalle del PLan de Pago</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Plan de Pago", "error durante el Proceso");

        }
    }

    private void obtenerPlanPagos(AnalizadorLex analex, String destinatario) {
// Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Plan_Pago ppN = new N_Plan_Pago();

        try {
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(ppN.obtenerPlanPagos());
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Obtener Planes de Pago", "<h1>Planes de Pago</h1> \n <h4>Detalle de los PLanes de Pago</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Planes de Pago", "error durante el Proceso");

        }
    }

    private void eliminarPlanPago(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Plan_Pago ppN = new N_Plan_Pago();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        MimeMail mailer = new MimeMail();
        try {
            ppN.eliminarPlanPago(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Plan de Pago", "Eliminacion Correcta");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

    //20
    private void obtenerConvalidacionInforme(AnalizadorLex analex, String destinatario) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        N_Convalidacion_Informe cN = new N_Convalidacion_Informe();

        analex.Avanzar();
        int id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();

        try {
            String message = Utils.dibujarTablawithHTMLwithoutOpciones(cN.obtenerConvalidacionInformes(id));
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Obtener Convalidacion Informe", "<h1>Convalidacion Informe</h1> \n <h4>Detalle de Convalidacion Informe</4> \n" + message);

        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Obtener Convalidacion Informe", "error durante el Proceso");

        }
    }

    private void eliminarConvalidacionInforme(AnalizadorLex analex, String destinatario) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        N_Convalidacion_Informe cN = new N_Convalidacion_Informe();

        analex.Avanzar();
        // Atributos
        int id = Integer.valueOf(analex.Preanalisis().getToStr());

        MimeMail mailer = new MimeMail();
        try {
            cN.eliminarConvalidacionInform(id);
            mailer.sendHtmlEmail(destinatario, "Eliminar Convalidacion Informe", "Eliminacion Correcta");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }

}
