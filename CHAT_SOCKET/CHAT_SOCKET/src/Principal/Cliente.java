package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	Scanner sc = new Scanner(System.in);
	private ServerSocket serversocket;
	private Socket socket;
	private DataInputStream bufferedeentrada = null;
	private DataOutputStream bufferedesalida = null;
	final String COMANDO_TERMINACION = "salir()";
	
	public void levantarConexion(String ip, int puerto) {
		try {
			socket = new Socket(ip, puerto);
			System.out.println("Conectado a: " + socket.getInetAddress().getHostAddress());
		
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
	
	
	public void ejecutarConexion(String ip, int puerto) {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					levantarConexion(ip, puerto);
				flujos();
				recibirDatos();
				}finally {
					cerrarConexion();
				}
			}
			
		});
		hilo.start();
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
	
	
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		System.out.println("Ingrese la dirección IP: [localhost por defecto] ");
		String ip = cliente.sc.nextLine();
		if (ip.length() <= 0) ip = "localhost";
		System.out.println("Ingrese el puerto: [5500 por defecto] ");
		String puerto = cliente.sc.nextLine();
		if (puerto.length() <= 0) puerto = "5500";
		cliente.ejecutarConexion(ip, Integer.parseInt(puerto));
		cliente.escribirDatos();
	}
}
