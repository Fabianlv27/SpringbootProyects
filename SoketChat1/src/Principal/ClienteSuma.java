package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSuma {
    public static void main(String[] args) throws IOException {
        try(Socket socket=new Socket("localhost",12345);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ){
            Scanner teclado=new Scanner(System.in);
            System.out.println("Dame dos numeros");
            double n1=teclado.nextDouble();
            double n2=teclado.nextDouble();
            dos.writeDouble(n1);
            dos.writeDouble(n2);
            double suma=dis.readDouble();
            System.out.println("La suma es:"+suma);
        }catch (IOException e){
            System.out.println("Error");
        }
    }
}
