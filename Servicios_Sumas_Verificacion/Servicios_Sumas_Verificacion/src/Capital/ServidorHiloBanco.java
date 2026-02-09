public class ServidorHiloBanco {

    public void servir(){
        ServerSocket serverSocket = null;
        final int PUERTO=9876;
        try {
            serverSocket=new ServerSocket(PUERTO);
            while(true){
                Socket conexion;
                //la conexion al cliente
                conexion=serverSocket.accept();

                HiloConexionBanco hiloConexion=new HiloConexionBanco(conexion);
                Thread hilo=new Thread(hiloConexion);
                hilo.start();
            }
        }catch (IOException e){

        }
    }

    static void main() {
        ServidorHiloBanco servidor = new ServidorHiloBanco();
        servidor.servir();
    }
}
