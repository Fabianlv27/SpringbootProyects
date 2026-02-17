package chatServerV2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {

    Scanner sc = new Scanner(System.in);
    private ServerSocket serverSocket;

    final String COMANDO_TERMINACION = "SALIR";



    public void ejecutarConexion(int puerto){
        try{
            serverSocket =new ServerSocket(puerto);
            System.out.println("Esperando conexion entrante en el puerto " + puerto);
            //Ahora colocamos el bucle while para el socket siga escuchando
            //a varios clientes

            while (true){
                Socket socketCliente=serverSocket.accept();
                System.out.println("Nuevo cliente conectado desde: " + socketCliente.getInetAddress());

                new HiloCliente(socketCliente).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void main() {
      ChatServer cs=new ChatServer();
      cs.ejecutarConexion(5000);
    }

    class HiloCliente extends Thread{
        //definimos las variables que implican la conexion de un
        //solo cliente
        private Socket socket;
        private DataInputStream bufferdeentrada=null;
        private DataOutputStream bufferdesalida=null;

        public HiloCliente(Socket socket){
            this.socket=socket;
        }

        public void flujos(){
            try{
                bufferdeentrada=new DataInputStream(socket.getInputStream());
                bufferdesalida=new DataOutputStream(socket.getOutputStream());
                bufferdesalida.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void recibirDatos(){
            String cadena="";
            try{
                String nombreUsuario=(String)bufferdeentrada.readUTF();
                bufferdesalida.writeUTF("Bienvenido "+ nombreUsuario+ ", el servidor esta listo");
                bufferdesalida.flush();
                do{
                    cadena=(String)bufferdeentrada.readUTF();
                    System.out.println("\n[Cliente " + nombreUsuario + "]=> " + cadena);
                    if (!cadena.equalsIgnoreCase(COMANDO_TERMINACION)){
                        String respuesta=cadena.toUpperCase() +" (Longitud: "+ cadena.length() + ")";
                        bufferdesalida.writeUTF(respuesta);
                        bufferdesalida.flush();
                    }

                }while(!cadena.equals(COMANDO_TERMINACION));
            } catch (IOException e) {
                System.out.println("Conexion perdida con un cliente.");
            } finally {
                cerrarConexion();
            }
        }

        public void cerrarConexion(){
            try{
                bufferdeentrada.close();
                bufferdesalida.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Conversacion finalizada con un cliente.");
        }

        @Override
        public void run() {
            flujos();
            recibirDatos();
        }
    }

}
