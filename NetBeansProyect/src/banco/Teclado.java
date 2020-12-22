
package banco;

import java.util.Scanner;

public class Teclado extends Hardware {

    private Scanner sc = new Scanner(System.in);

    public Teclado(int nroSerie, String marca, String modelo) {
        super(nroSerie, marca, modelo);
    }
    
    //METODOS DE LA CLASE
    public String ingresoDeNroCuenta(){
        Pantalla.imprimirMensaje("Ingrese Numero de Cuenta: ");
        return sc.next();
    }
    
    public int ingresoDePIN(){
        int PIN = -1;
        Pantalla.imprimirMensaje("Ingrese PIN: ");
        String input = sc.next();
        try{
            PIN = Integer.parseInt(input);
        } catch(Exception e){
            System.out.println("INGRESO UN PIN NO NUMERICO");
        }
        return PIN;
    }
    
    public int ingresoMonto(){
        int monto = -1;
        Pantalla.imprimirMensaje("Ingrese Monto: $");
        String input = sc.next();
        try{
            monto = Integer.parseInt(input);
        } catch(Exception e){
            Pantalla.imprimirMensaje("NO HA INGRESADO UN NUMERO");
        }
        return monto;
    }
    
    public int ingresoOpcion(){
        Pantalla.imprimirMensaje("\nOpcion Ingresada: ");
        return sc.nextInt();
    }
    

    
}
