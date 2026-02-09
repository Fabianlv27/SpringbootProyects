package Banco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBanco {
    static void main() {
        try(
                Socket socket=new Socket("localhost",12345);
        DataInputStream dis=new DataInputStream(socket.getInputStream());
        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
        ){
            Scanner sc=new Scanner(System.in);
            System.out.println("Ingrese el capital");
            double capital=sc.nextDouble();
            System.out.println("Ingrese el interes anual");
            double interes=sc.nextDouble();
            System.out.println("Ingrese el tiempo en años");
            double años=sc.nextDouble();
            dos.writeDouble(capital);
            dos.writeDouble(interes);
            dos.writeDouble(años);
            double pagos=dis.readDouble();
            System.out.println("Dinero a pagar: "+pagos);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
