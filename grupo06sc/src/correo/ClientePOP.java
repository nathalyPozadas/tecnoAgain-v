/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correo;

import utils.Constants;
import java.io.*;
import java.net.*;

/**
 *
 * @author Brian
 */
public class ClientePOP {

    private static final int PORT = 110; // POP

    public static String readMail() {
        // Estableciendo variables
        String result = null;
        BufferedReader reader;
        DataOutputStream writer;
        String command;

        try {
            // Estableciendo Conexion Socket
            Socket socket = new Socket(Constants.MAIL_SERVER_HOST, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new DataOutputStream(socket.getOutputStream());

            if (socket != null && reader != null && writer != null) {
                reader.readLine();
                // Autenticando Usuario
                command = "USER " + Constants.MAIL_USER + "\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                System.out.println(reader.readLine());

                command = "PASS " + Constants.MAIL_PASSWORD + "\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                System.out.println(reader.readLine());

                // Listar los correos 5
                command = "LIST \r\n";
                writer.writeBytes(command);
                System.out.println(command);
                
                char cant = reader.readLine().charAt(4);
                System.out.println(getMultiline(reader));
//                System.out.println(reader.readLine());
                
                
                // Revisar si hay correos
                System.out.println("la cantidad de mensajes es : " + cant);
                
                if (cant != '0') { // Hay mensajes
//                     Leer mensaje
                    command = "RETR 1\r\n";
                    writer.writeBytes(command);
                    result = getMultiline(reader);
                    
                    // Eliminar mensaje despues de leer
                    command = "DELE 1\r\n";
                    writer.writeBytes(command);
                    reader.readLine();
                }

                command = "QUIT\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                System.out.println(reader.readLine());
            }

            // Cerrar Conexion
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println(result);
        return result;
    }

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                // No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                // The line starts with a "." - strip it off.
                line = line.substring(1);
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
