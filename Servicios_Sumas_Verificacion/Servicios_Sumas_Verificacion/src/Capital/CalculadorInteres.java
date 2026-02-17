public class CalculadorInteres {
    public static double calcular(String linea){
        String[] Parametros = linea.split(";");
        //capital;interes;tiempo
        double capital=Double.parseDouble(Parametros[0]);
        double imensual= Double.parseDouble(Parametros[1])/1200;
        double tiempo=Double.parseDouble(Parametros[2])*12;

        double dinero_total=(capital*imensual)/ (1-Math.pow((1+imensual),-1*tiempo) );
        return dinero_total;
    }
}