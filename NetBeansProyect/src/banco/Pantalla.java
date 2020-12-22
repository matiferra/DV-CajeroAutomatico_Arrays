
package banco;

public class Pantalla extends Hardware {

    public Pantalla(int nroSerie, String marca, String modelo) {
        super(nroSerie, marca, modelo);
    }
    
    //METODOS DE LA CLASE
    public static void imprimirMensaje(String mensaje){
        System.out.print(mensaje);
    }
    
}
