import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DescargadorHTML {

    public static void main(String[] args) {
        int puerto=80;
        String direccion="www.google.es";

        //Mediante el dns busca la direccion ip de la direccion
        InetSocketAddress direccionRed=new InetSocketAddress(direccion,puerto);
        Socket socket=new Socket();

        try{

            socket.connect(direccionRed);

            OutputStream os=socket.getOutputStream();
            OutputStreamWriter osw=new OutputStreamWriter(os);

            InputStream is=socket.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);

            BufferedReader bfr=new BufferedReader(isr);
            PrintWriter pw=new PrintWriter(osw);

            pw.println("Get /index.html");
            pw.flush();

            String linea;
            while((linea=bfr.readLine())!=null){
                System.out.println(linea);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
