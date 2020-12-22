package banco;

public class Consulta extends Transaccion {

    public Consulta() {
        Transaccion.setContadorTransacciones(Transaccion.getcontadorTransacciones() + 1);
        super.setIdTransaccion(Transaccion.getcontadorTransacciones());
    }
    
    public void consultar(CuentaBancaria cuenta){
        Pantalla.imprimirMensaje("\nSu saldo es $" + cuenta.getSaldo());
    }

    @Override
    public String toString() {
        return "\n\n---CONSULTA----\n"
                + super.toString() + "\n";
    }
    
    
    
}
