import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.zip.CheckedOutputStream;

public class Cliente {
    //Creamos las mismas variables que en el servidor
    Scanner sc = new Scanner(System.in);
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream bufferdeentrada=null;
    private DataOutputStream bufferdesalida=null;
    final String COMANDO_TERMINACION = "salir()";

    public void levantarConexion(String ip,int puerto){
        try{
            socket=new Socket(ip,puerto);
            System.out.println("Conectado a : "+ socket.getInetAddress().getHostAddress());

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            do{
                cadena=(String)bufferdeentrada.readUTF();
                System.out.println("\n[Cliente] => "+cadena);
                System.out.println("\n[Usted] =>");
            }while(!cadena.equals(COMANDO_TERMINACION));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    }

    public void ejecutarConexion(String ip,int puerto){
        Thread hilo =new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    levantarConexion(ip,puerto);
                    flujos();
                    recibirDatos();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrarConexion();
                }
            }
        });
        hilo.start();
    }

    public void escribirDatos(){
        while(true){
            System.out.println("[Usted] =>");
            enviar(sc.nextLine());
        }
    }

    public void enviar(String cadena){
        try{
            bufferdesalida.writeUTF(cadena);
            bufferdesalida.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void main() {
        Cliente cliente=new Cliente();
        System.out.println("Ingrese la dirección IP: [localhost por defecto] ");
        String ip = cliente.sc.nextLine();
        if (ip.length()<=0) ip="localhost";
        System.out.println("Ingrese el puerto: [5500 por defecto] ");
        String puerto = cliente.sc.nextLine();
        if (puerto.length() <= 0) puerto = "5500";
        cliente.ejecutarConexion(ip,Integer.valueOf(puerto));
        cliente.escribirDatos();
    }

}
