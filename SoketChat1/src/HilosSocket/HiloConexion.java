package HilosSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{
    BufferedReader bfr;
    PrintWriter pw;
    Socket socket;

    public HiloConexion(Socket socket){
        this.socket=socket;
    }

    public void procesarLineas() throws IOException {
        String lineaNumero=bfr.readLine();

    }
}
