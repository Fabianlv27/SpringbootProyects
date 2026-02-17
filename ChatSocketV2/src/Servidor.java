import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    Scanner sc = new Scanner(System.in);
    private ServerSocket serversocket;
    private Socket socket;
    private DataInputStream bufferedentrada = null;
    private DataOutputStream bufferdesalida = null;
    final String COMANDO_TERMINACION = "salir()";

    public void levantarConexion(int puerto) {
        try {
            //Su unica misión es detectar nuevas peticiones.
            serversocket = new ServerSocket(puerto);
            System.out.println("Esperando conexion entrante en el puerto "
                    + String.valueOf(puerto));
            //Cuando llega aquí la ejecución se detiene hasta que un cliente
            //intente conectarse.
            //Cuando eso sucede usamos .accept() para realizar el Handshake
            //es decir el proceso de conexión TCP.
            //Cuando esta operación es exitosa, crea y devuelve el objeto Socket
            socket = serversocket.accept();
            //Este objeto representa la conexión específica con ese cliente,
            //lo usaremos para enviar y recibir datos (InputStream/OuputStream).
            //Mientras tanto el serversocket original queda libre para seguir
            //escuchando a otros clientes, si es que está en un bucle.

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flujos() {
        try {
            //Una vez establecemos la conexión podemos obtener la información
            // que el cliente envia y la que el servidor envia, el detalle es
            //qie socket por defecto solo entiende bytes puros, es por eso que
            //los envolvemos en estas clases para poder enviar y recibir tipos
            //de datos primitivos de Java de forma directa.
            bufferedentrada = new DataInputStream(socket.getInputStream());
            bufferdesalida = new DataOutputStream(socket.getOutputStream());
            /*
            Estos buffers son espacios de memoria RAM del servidor para no estar
            enviando byte por byte, sino que enviamos bloques de contenido (la carta
             entera y no letra por letra)
            Cuando se crea el objeto DataInputStream, el buffer de entrada recibe primero la "carta" del cliente con una cabecera
            para indicar que puede enviar, luego cuando se crea el objeto DataOutputStream, el buffer de salida coloca otra carta con otra
            cabecera en el espacio de memoria para confirmar, es decir que no se envia directamente, este espera a llenar
             todo su espacio para aprovechar el viaje pero lo podemos forzar ejecutando flush().
            En resumen el flush sirve para pasar los datos de la RAM (buffer) a la red.
            Creando estos objetos ya podemos recibir y enviar datos del cliente cuando queramos.
            */
            bufferdesalida.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recibirDatos() {
        String cadena = "";
        try {
            do {
                //Aqui es donde escuchamos los datos que recibimos
                //del cliente.
                //Teniendo en cuenta que esto lo ejecuta un hilo
                //podemos tener este proceso de recibir en un bucle mientras
                //enviamos a la vez.
                cadena = (String) bufferedentrada.readUTF();
                System.out.println("\n[Cliente]=> " + cadena);
                System.out.println("\n[Usted] =>");
            } while (!cadena.equals(COMANDO_TERMINACION));
        } catch (IOException e) {
            cerrarConexion();
        }
    }

    public void ejecutarConexion(int puerto) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        levantarConexion(puerto);
                        flujos();
                        recibirDatos();
                    } finally {
                        cerrarConexion();
                    }
                }
            }
        });
        hilo.start();
    }

    public void cerrarConexion() {
        try {
            bufferedentrada.close();
            //cuando ejecutamos close también se ejecuta el flush()
            bufferdesalida.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            System.out.println("Conversacion finalizada");
            System.exit(0);
        }
    }


    public void escribirDatos() {
        while (true) {
            System.out.println("[Usted] => ");
            enviar(sc.nextLine());
        }
    }

    public void enviar(String cadena) {
        try {
            //mandamos los datos al buffer
            bufferdesalida.writeUTF(cadena);
            //enviamos los datos sin importar que no ocupen todo el espacio
            //en memmoria disponible.
            bufferdesalida.flush();
        } catch (IOException e) {
            System.out.println("Error al enviar los datos " + e.getMessage());
        }

    }

    public static void main(String[] arg){
        Servidor s =new Servidor();
        System.out.println("Indique el puerto a conectar [5500]");
        String puerto=s.sc.nextLine();
        if (puerto.length()<=0) puerto="5500";
        s.ejecutarConexion(Integer.valueOf(puerto));
        s.escribirDatos();
    }

}