package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexionBanco implements Runnable {

    BufferedReader bfr;
    PrintWriter pw;
    Socket socket;

    public HiloConexionBanco(Socket socket) {
        this.socket = socket;
    }

    public void procesarLineas() throws IOException {

        //Obtenemos primero cuantas lineas le vamos a
        // pasar para crear el array de respuestas
        String lineaNumeros = bfr.readLine();
        Integer nLineas=Integer.valueOf(lineaNumeros);
        String[] lineas=new  String[nLineas];
        double[] resultados new double[nLineas];

        //Ahora que sabemos cuantas veces vamos a leer procedemos a hacerlo con readLine()
        for (int i = 0; i < nLineas; i++) {
            String linea = bfr.readLine();
            lineas[i] = linea;
            resultados[i] = CalculadorIntereses.calcular(linea);
        }
        for (int i = 0; i < nLineas; i++) {
            pw.println(resultados[i]);
            //Con esto le decimos que deje de esperar mas resultados y los mande al cliente
            pw.flush();
        }

    }

    public void run() {
        try {
            //nos evitamos declarar el PrintWriter y el BufferedReader cada vez que lo necesitemos
            bfr = Utilidades.getFlujoLectura(this.socket);
            pw = Utilidades.getFlujoEscritura(this.socket);
            procesarLineas();

        } catch (IOException e) {
            System.out.println("Hubo una interrupción");
        }

    }

}
