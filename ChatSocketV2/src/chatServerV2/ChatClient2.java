package chatServerV2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient2 {
    Scanner sc = new Scanner(System.in);
    private Socket socket;
    private DataInputStream bufferdeentrada = null;
    private DataOutputStream bufferdesalida = null;
    final String COMANDO_TERMINACION = "SALIR";


    public void levantarConexion(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            System.out.println("Conectado a : " + socket.getInetAddress().getHostAddress());
        } catch (IOException e) {

            System.out.println("Error: No se pudo conectar. Verifica que el servidor este encendido.");
            System.exit(0);
        }
    }


    public void flujos() {
        try {
            bufferdeentrada = new DataInputStream(socket.getInputStream());
            bufferdesalida = new DataOutputStream(socket.getOutputStream());
            bufferdesalida.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void chatear(){
        try{
            System.out.print("Introduce tu nombre de usuario: ");
            String nombre = sc.nextLine();
            enviar(nombre);

            String bienvenida=bufferdeentrada.readUTF();
            System.out.println("[Servidor] => "+ bienvenida);
            String cadena="";
            do{
                System.out.println("[Usted] => ");
                cadena=sc.nextLine();
                enviar(cadena);

                if (!cadena.equalsIgnoreCase(COMANDO_TERMINACION)){
                    String respuesta=bufferdeentrada.readUTF();
                    System.out.println("[Servidor] => "+ respuesta);
                }
            } while (!cadena.equalsIgnoreCase(COMANDO_TERMINACION));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            cerrarConexion();
        }
    }

    public void enviar(String cadena){
        try {
            bufferdesalida.writeUTF(cadena);
            bufferdesalida.flush();
        } catch (IOException e) {
            System.out.println("Error al enviar los datos " + e.getMessage());
        }
    }

    public void cerrarConexion(){
        try {
            bufferdeentrada.close();
            bufferdesalida.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void main() {
        ChatClient2 cliente =new ChatClient2();
        cliente.levantarConexion("localhost",5000);
        if (cliente.socket != null){
            cliente.flujos();
            cliente.chatear();
        }
    }
}