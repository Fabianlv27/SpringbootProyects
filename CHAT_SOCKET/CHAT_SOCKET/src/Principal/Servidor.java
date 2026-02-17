package Principal;

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
	private DataInputStream bufferedeentrada = null;
	private DataOutputStream bufferedesalida = null;
	final String COMANDO_TERMINACION = "salir()";
	
	
	public void levantarConexion(int puerto) {
		try {
			serversocket = new ServerSocket(puerto);
			System.out.println("Esperando conexión entrante en el puerto "+String.valueOf(puerto));
			socket = serversocket.accept();
		}catch(Exception e) {
			System.out.println("Error en la conexión "+e.getMessage());
			System.exit(0);
		}
	}
	
	public void flujos() {
		try {
			bufferedeentrada = new DataInputStream(socket.getInputStream());
			bufferedesalida = new DataOutputStream(socket.getOutputStream());
			bufferedesalida.flush();
		}catch(Exception e) {
			System.out.println("Error en la apertura de flujos");
			
		}
	}
	

	public void ejecutarConexion(int puerto) {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						levantarConexion(puerto);
						flujos();
						recibirDatos();
					}finally {
						cerrarConexion();
					}
					
				}
				
			}
			
		});
		hilo.start();
	}
	
	public void cerrarConexion() {
		try {
			bufferedeentrada.close();
			bufferedesalida.close();
			socket.close();
		}catch(IOException e) {
			System.out.println("Error al cerrar coenxiones ");
		}finally {
			System.out.println("Conversación finalizada");
			System.exit(0);
		}
	}
	
	
	public void recibirDatos() {
		String cadena = "";
		try {
			do {
				cadena = (String)bufferedeentrada.readUTF();
				System.out.println("\n[Cliente] => "+cadena);
				System.out.println("\n[Usted] =>");
			}while(!cadena.equals(COMANDO_TERMINACION));
		}catch(IOException e) {
			cerrarConexion();
		}
	}
	
	public void escribirDatos() {
		while(true) {
			System.out.println("[Usted] =>");
			enviar(sc.nextLine());
		}
	}
	
	public void enviar(String cadena) {
		try {
			bufferedesalida.writeUTF(cadena);
			bufferedesalida.flush();
		}catch(IOException e) {
			System.out.println("Error al enviar los datos "+e.getMessage());
		}
	}
	
	public static void main(String[] arg) {
		Servidor s = new Servidor();
		System.out.println("Indique el puerto a conectar [5500] ");
		String puerto = s.sc.nextLine();
		if(puerto.length()<=0) puerto = "5500";
		s.ejecutarConexion(Integer.valueOf(puerto));
		s.escribirDatos();
	}
	
	
}
