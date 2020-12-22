
package banco;

public class Impresora extends Hardware{

    public Impresora(int nroSerie, String marca, String modelo) {
        super(nroSerie, marca, modelo);
    }
    
    public String expulsarTicket(){
        return "\nPor favor retire el ticket";
    }    
}
