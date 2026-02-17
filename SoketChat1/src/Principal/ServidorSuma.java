package Principal;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//el servidor (banco) va a aceptar los datos del cliente dinero,tiempo (años),interes

public class ServidorSuma extends  ServerSocket{
public ServidorSuma() throws IOException{
    super(12345);
}


    public void aceptarPeticiones(){
        while (true){
            try (Socket cliente =accept()){
                System.out.println("cliente accepted");
                DataInputStream dis=new DataInputStream(cliente.getInputStream());
                DataOutputStream dos= new DataOutputStream(cliente.getOutputStream());
                double n1=dis.readDouble();
                double n2=dis.readDouble();
                System.out.println("Servidor obteniendo numero 1: "+n1);
                System.out.println("Servidor obteniendo numero 2: "+n2);
                System.out.println("Servidor retornando suma: "+(n1+n2));

                dos.writeDouble(n1+n2);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try{
            ServidorSuma servidor=new ServidorSuma();
            servidor.aceptarPeticiones();
            servidor.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
