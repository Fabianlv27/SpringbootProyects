public class ClienteHiloBanco {
    public void verificarCadenas(BufferReader bfr,PrintWriter pw){
        pw.println(4)
        pw.println("1500;3;2");
        pw.println("1400;3;2");
        pw.println("1600;3;2");
        pw.println("1760;3;2");
        pw.flush();
        double dinero1=bfr.readLine();
        double dinero2=bfr.readLine();
        double dinero3=bfr.readLine();
        double dinero4=bfr.readLine();
    }

    static void main() {
        ClienteHiloBanco cliente=new ClienteHiloBanco();
        InetSocketAddress direccion=new InetSocketAddress("localhost", 9876);
        Socket conexion=new Socket();
        try {
            conexion.connect(direccion);
            BufferedReader bfr=Utilidades.getFlujoLectura(conexion);
            PrintWriter pw=Utilidades.getFlujoEscritura(conexion);
            cliente.verificarCadenas(bfr, pw);
            pw.close();
            bfr.close();
            conexion.close();
        } catch (IOException e) {
            //Quiza el servidor no está encendido
            //Quizá lo esté pero su cortafuegos
            //impide conexiones
            //...
        }
    }

}