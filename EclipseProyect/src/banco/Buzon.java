package banco;

import java.util.Scanner;

public class Buzon extends Hardware {

    private Scanner sc = new Scanner(System.in);
    private int dineroIngresado;
    
    
    public Buzon(int nroSerie, String marca, String modelo) {
        super(nroSerie, marca, modelo);
    }

    //GETTER
    public int getDineroIngresado() {
        return dineroIngresado;
    }

    //METODOS DE LA CLASE
    public int recibirDinero(){
        Pantalla.imprimirMensaje("Ingrese Monto: $");
        /// validar nroGrandes + Strings?
        String input = sc.next();
        try{
            this.dineroIngresado = Integer.parseInt(input);
            Pantalla.imprimirMensaje("Dinero Ingresado con Exito!");
        } catch(Exception e){
            System.out.println("NO HA INGRESADO UN NUMERO");
        }
        return dineroIngresado;
    }
    
    public String expulsarDinero(){
        return "Por favor retire su dinero\n";
    }

    
    
    
}
