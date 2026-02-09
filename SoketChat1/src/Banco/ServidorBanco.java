package Banco;

import Principal.ServidorSuma;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBanco extends ServerSocket {
    public ServidorBanco() throws IOException{
        //definimos el puerto
        super(12345);
    }

    public void aceptarPeticiones()   {
        while(true){
            try (Socket cliente =accept()){

                System.out.println("cliente aceptado");
                DataInputStream dis=new DataInputStream(cliente.getInputStream());
                DataOutputStream dos=new DataOutputStream(cliente.getOutputStream());
                double capital=dis.readDouble();
                double ianual =dis.readDouble();
                double años=dis.readDouble();

                System.out.println("capital:"+capital);
                System.out.println("ianual:"+ianual);
                System.out.println("años: "+años);

                //entre 100 por el porcentaje y *12 por que lo queremos en meses
                double imensual=ianual/1200;
                double dinero_total=(capital*imensual)/ (1-Math.pow((1+imensual),-1*años*12) );
                dos.writeDouble(dinero_total);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    static void main() {
        try{
            ServidorBanco servidor=new ServidorBanco();
            servidor.aceptarPeticiones();
            servidor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
