package chatServerV2;
import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        int puerto = 5000; // El puerto especificado en el examen

        // 1. Instanciamos el ServerSocket una sola vez
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto + ". Esperando clientes...");

            // Bucle infinito para aceptar peticiones
            while (true) {
                // Se detiene aquí hasta que un cliente se conecta
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado desde: " + clienteSocket.getInetAddress());

                // 2. Concurrencia: Lanzamos un hilo independiente para este cliente
                ManejadorCliente manejador = new ManejadorCliente(clienteSocket);
                Thread hiloCliente = new Thread(manejador);
                hiloCliente.start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

// Esta clase contiene la "Lógica de intercambio" para cada cliente individual
class ManejadorCliente implements Runnable {
    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream())
        ) {
            // 3. Lógica inicial: Recibir nombre de usuario
            String nombreUsuario = entrada.readUTF();

            // 4. Responder con el mensaje de bienvenida
            salida.writeUTF("Bienvenido " + nombreUsuario + ", el servidor está listo");
            salida.flush();

            String mensaje;
            // Bucle de comunicación con este cliente específico
            do {
                mensaje = entrada.readUTF(); // Espera el mensaje del cliente

                if (!mensaje.equalsIgnoreCase("SALIR")) {
                    // 5. Convertir a mayúsculas y añadir la longitud
                    String respuesta = mensaje.toUpperCase() + " (Longitud: " + mensaje.length() + ")";
                    salida.writeUTF(respuesta);
                    salida.flush();
                }
            } while (!mensaje.equalsIgnoreCase("SALIR")); // Cierre si envía SALIR

            System.out.println("El cliente " + nombreUsuario + " ha finalizado la conexión.");

        } catch (IOException e) {
            System.out.println("Conexión perdida con un cliente.");
        } finally {
            // 6. Cierre: Cerrar el socket específico
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}