package chatServerV2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args, Socket socket1) {
        String ip = "localhost"; // IP del servidor para pruebas
        int puerto = 5000;       // Puerto acordado
        Scanner sc = new Scanner(System.in);

        // El bloque try-with-resources gestiona la desconexión automáticamente si el servidor no está activo
        try (Socket socket = socket1;
             DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Conectado al servidor correctamente.");

            // 1. Enviar el nombre de usuario al conectarse
            System.out.print("Introduce tu nombre de usuario: ");
            String nombre = sc.nextLine();
            salida.writeUTF(nombre);
            salida.flush();

            // 2. Recibir e imprimir el mensaje de bienvenida del servidor
            String bienvenida = entrada.readUTF();
            System.out.println("[Servidor] => " + bienvenida);

            String mensaje = "";

            // 3. Bucle de comunicación
            do {
                System.out.print("[Usted] => ");
                mensaje = sc.nextLine();

                // Enviar el mensaje al servidor
                salida.writeUTF(mensaje);
                salida.flush();

                // Si no es el comando de salida, esperar y mostrar la respuesta
                if (!mensaje.equalsIgnoreCase("SALIR")) {
                    String respuestaServidor = entrada.readUTF();
                    System.out.println("[Servidor] => " + respuestaServidor);
                }

            } while (!mensaje.equalsIgnoreCase("SALIR")); // 4. Finalización

            System.out.println("Cerrando la aplicación. ¡Hasta luego!");

        } catch (IOException e) {
            // 5. Gestión de excepciones si el servidor no está activo
            System.out.println("Error de conexión: No se pudo conectar al servidor. Verifica que esté encendido.");
        } finally {
            sc.close();
        }
    }
}