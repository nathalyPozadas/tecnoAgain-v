/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Brian
 */
public class TPC {

    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
            //1           
            "REGISTRARDOCENTE",
            "MODIFICARDOCENTE",
            "OBTENERDOCENTE",
            "OBTENERDOCENTES",
            "ELIMINARDOCENTE",
            //2
            "REGISTRARADMINISTRADOR",
            "MODIFICARADMINISTRADOR",
            "OBTENERADMINISTRADOR",
            "OBTENERADMINISTRADORES",
            "ELIMINARADMINISTRADOR",
            //3
            "REGISTRARCLIENTE",
            "MODIFICARCLIENTE",
            "OBTENERCLIENTE",
            "OBTENERCLIENTES",
            "ELIMINARCLIENTE",
            //4
            "REGISTRARCARGO",
            "MODIFICARCARGO",
            "OBTENERCARGOS",
            "ELIMINARCARGO",
            //5
            "REGISTRARPROFESION",
            "MODIFICARPROFESION",
            "OBTENERPROFESIONES",
            "ELIMINARPROFESION",
            //6
            "REGISTRARPROGRAMA",
            "MODIFICARPROGRAMA",
            "OBTENERPROGRAMA",
            "OBTENERPROGRAMAS",
            "ELIMINARPROGRAMA",
            //7
            "REGISTRARMODULO",
            "MODIFICARMODULO",
            "OBTENERMODULO",
            "OBTENERMODULOS",
            "ELIMINARMODULO",
            //8
            "REGISTRARPROFESIONDOCENTE",
            "OBTENERPROFESIONDOCENTE",
            "ELIMINARPROFESIONDOCENTE",
            //9
            "REGISTRARCARGOADMIN",
            "OBTENERCARGOSADMIN",
            "ELIMINARCARGOADMIN",
            //10
            "REGISTRARMODULOSPROGRAMA",
            "OBTENERMODULOSPROGRAMA",
            "ELIMINARMODULOPROGRAMA",
            //11
            "REGISTRAROFERTAPROGRAMA",
            "OBTENEROFERTAPROGRAMA",
            "OBTENEROFERTAPROGRAMAS",
            "MODIFICAROFERTAPROGRAMA",
            "ELIMINAROFERTAPROGRAMA",
            //12
            "REGISTRARCONVALIDACION",
            "OBTENERCONVALIDACION",
            "OBTENERCONVALIDACIONES",
            "ELIMINARCONVALIDACION",
            //13
            "REGISTRARBOLETAINSCRIPCION",
            "OBTENERBOLETAINSCRIPCION",
            "OBTENERBOLETASINSCRIPCIONES",
            "ELIMINARBOLETAINSCRIPCION",
            //14   
            "OBTENERCUOTAS",
            "ELIMINARCUOTA",
            //15
            "MODIFICARCRONOGRAMAMODULO",
            "ELIMINARCRONOGRAMAMODULO",
            //16
            "REGISTRARMODULOBOLETA",
            //17
            "CERRARACTADENOTAS",
            //18
            "OBTENERNOTA",
            "OBTENERNOTAS",
            "ELIMINARNOTA",
            //19
            "REGISTRARPLANPAGO",
            "OBTENERPLANPAGO",
            "OBTENERPLANESPAGOS",
            "ELIMINARPLANPAGO",
            //20
            "OBTENERCONVALIDACIONINFORME",
            "ELIMINARCONVALIDACIONINFORME"
    //Aqui van todos los nombres de los tokens declarados

    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            //1
            new Token(Token.FUNC, Token.REGISTRARDOCENTE, "REGISTRARDOCENTE"),
            new Token(Token.FUNC, Token.OBTENERDOCENTE, "OBTENERDOCENTE"),
            new Token(Token.FUNC, Token.OBTENERDOCENTES, "OBTENERDOCENTES"),
            new Token(Token.FUNC, Token.MODIFICARDOCENTE, "MODIFICARDOCENTE"),
            new Token(Token.FUNC, Token.ELIMINARDOCENTE, "ELIMINARDOCENTE"),
            //2
            new Token(Token.FUNC, Token.REGISTRARADMINISTRADOR, "REGISTRARADMINISTRADOR"),
            new Token(Token.FUNC, Token.OBTENERADMINISTRADOR, "OBTENERADMINISTRADOR"),
            new Token(Token.FUNC, Token.OBTENERADMINISTRADORES, "OBTENERADMINISTRADORES"),
            new Token(Token.FUNC, Token.MODIFICARADMINISTRADOR, "MODIFICARADMINISTRADOR"),
            new Token(Token.FUNC, Token.ELIMINARADMINISTRADOR, "ELIMINARADMINISTRADOR"),
            //3
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTE, "OBTENERCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTES, "OBTENERCLIENTES"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.ELIMINARCLIENTE, "ELIMINARCLIENTE"),
            //4
            new Token(Token.FUNC, Token.REGISTRARCARGO, "REGISTRARCARGO"),
            new Token(Token.FUNC, Token.OBTENERCARGOS, "OBTENERCARGOS"),
            new Token(Token.FUNC, Token.MODIFICARCARGO, "MODIFICARCARGO"),
            new Token(Token.FUNC, Token.ELIMINARCARGO, "ELIMINARCARGO"),
            //5
            new Token(Token.FUNC, Token.REGISTRARPROFESION, "REGISTRARPROFESION"),
            new Token(Token.FUNC, Token.OBTENERPROFESIONES, "OBTENERPROFESIONES"),
            new Token(Token.FUNC, Token.MODIFICARPROFESION, "MODIFICARPROFESION"),
            new Token(Token.FUNC, Token.ELIMINARPROFESION, "ELIMINARPROFESION"),
            //6
            new Token(Token.FUNC, Token.REGISTRARPROGRAMA, "REGISTRARPROGRAMA"),
            new Token(Token.FUNC, Token.OBTENERPROGRAMA, "OBTENERPROGRAMA"),
            new Token(Token.FUNC, Token.OBTENERPROGRAMAS, "OBTENERPROGRAMAS"),
            new Token(Token.FUNC, Token.MODIFICARPROGRAMA, "MODIFICARPROGRAMA"),
            new Token(Token.FUNC, Token.ELIMINARPROGRAMA, "ELIMINARPROGRAMA"),
            //7
            new Token(Token.FUNC, Token.REGISTRARMODULO, "REGISTRARMODULO"),
            new Token(Token.FUNC, Token.OBTENERMODULO, "OBTENERMODULO"),
            new Token(Token.FUNC, Token.OBTENERMODULOS, "OBTENERMODULOS"),
            new Token(Token.FUNC, Token.MODIFICARMODULO, "MODIFICARMODULO"),
            new Token(Token.FUNC, Token.ELIMINARMODULO, "ELIMINARMODULO"),
            //8
            new Token(Token.FUNC, Token.REGISTRARPROFESIONDOCENTE, "REGISTRARPROFESIONDOCENTE"),
            new Token(Token.FUNC, Token.OBTENERPROFESIONESDOCENTE, "OBTENERPROFESIONDOCENTE"),
            new Token(Token.FUNC, Token.ELIMINARPROFESIONDOCENTE, "ELIMINARPROFESIONDOCENTE"),
            //9
            new Token(Token.FUNC, Token.REGISTRARCARGOADMIN, "REGISTRARCARGOADMIN"),
            new Token(Token.FUNC, Token.OBTENERCARGOSADMIN, "OBTENERCARGOSADMIN"),
            new Token(Token.FUNC, Token.ELIMINARCARGOADMIN, "ELIMINARCARGOADMIN"),
            //10
            new Token(Token.FUNC, Token.REGISTRARMODULOSPROGRAMA, "REGISTRARMODULOSPROGRAMA"),
            new Token(Token.FUNC, Token.OBTENERMODULOSPROGRAMA, "OBTENERMODULOSPROGRAMA"),
            new Token(Token.FUNC, Token.ELIMINARMODULOPROGRAMA, "ELIMINARMODULOPROGRAMA"),
            //11
            new Token(Token.FUNC, Token.REGISTRAROFERTAPROGRAMA, "REGISTRAROFERTAPROGRAMA"),
            new Token(Token.FUNC, Token.OBTENEROFERTAPROGRAMA, "OBTENEROFERTAPROGRAMA"),
            new Token(Token.FUNC, Token.OBTENEROFERTAPROGRAMAS, "OBTENEROFERTAPROGRAMAS"),
            new Token(Token.FUNC, Token.MODIFICAROFERTAPROGRAMA, "MODIFICAROFERTAPROGRAMA"),
            new Token(Token.FUNC, Token.ELIMINAROFERTAPROGRAMA, "ELIMINAROFERTAPROGRAMA"),
            //12
            new Token(Token.FUNC, Token.REGISTRARCONVALIDACION, "REGISTRARCONVALIDACION"),
            new Token(Token.FUNC, Token.OBTENERCONVALIDACION, "OBTENERCONVALIDACION"),
            new Token(Token.FUNC, Token.OBTENERCONVALIDACIONES, "OBTENERCONVALIDACIONES"),
            new Token(Token.FUNC, Token.ELIMINARCONVALIDACION, "ELIMINARCONVALIDACION"),
            //13
            new Token(Token.FUNC, Token.REGISTRARBOLETAINSCRIPCION, "REGISTRARBOLETAINSCRIPCION"),
            new Token(Token.FUNC, Token.OBTENERBOLETAINSCRIPCION, "OBTENERBOLETAINSCRIPCION"),
            new Token(Token.FUNC, Token.OBTENERBOLETASINSCRIPCIONES, "OBTENERBOLETASINSCRIPCIONES"),
            new Token(Token.FUNC, Token.ELIMINARBOLETAINSCRIPCION, "ELIMINARBOLETAINSCRIPCION"),
            //14   
            new Token(Token.FUNC, Token.OBTENERCUOTAS, "OBTENERCUOTAS"),
            new Token(Token.FUNC, Token.ELIMINARCUOTA, "ELIMINARCUOTA"),
            //15
            new Token(Token.FUNC, Token.MODIFICARCRONOGRAMAMODULO, "MODIFICARCRONOGRAMAMODULO"),
            new Token(Token.FUNC, Token.ELIMINARCRONOGRAMAMODULO, "ELIMINARCRONOGRAMAMODULO"),
            //16
            new Token(Token.FUNC, Token.REGISTRARMODULOBOLETA, "REGISTRARMODULOBOLETA"),
            //17
            new Token(Token.FUNC, Token.CERRARACTADENOTAS, "CERRARACTADENOTAS"),
            //18
            new Token(Token.FUNC, Token.OBTENERNOTA, "OBTENERNOTA"),
            new Token(Token.FUNC, Token.OBTENERNOTAS, "OBTENERNOTAS"),
            new Token(Token.FUNC, Token.ELIMINARNOTA, "ELIMINARNOTA"),
            //19
            new Token(Token.FUNC, Token.REGISTRARPLANPAGO, "REGISTRARPLANPAGO"),
            new Token(Token.FUNC, Token.OBTENERPLANPAGO, "OBTENERPLANPAGO"),
            new Token(Token.FUNC, Token.OBTENERPLANESPAGOS, "OBTENERPLANESPAGOS"),
            new Token(Token.FUNC, Token.ELIMINARPLANPAGO, "ELIMINARPLANPAGO"),
            //20
            new Token(Token.FUNC, Token.OBTENERCONVALIDACIONINFORME, "OBTENERCONVALIDACIONINFORME"),
            new Token(Token.FUNC, Token.ELIMINARCONVALIDACIONINFORME, "ELIMINARCONVALIDACIONINFORME")
    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
        return null;
    }
}
