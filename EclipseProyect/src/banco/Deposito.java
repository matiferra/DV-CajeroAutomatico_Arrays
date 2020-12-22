package banco;

public class Deposito extends Transaccion{

    private int monto;
    
    public Deposito(){
        Transaccion.setContadorTransacciones(Transaccion.getcontadorTransacciones() + 1);
        super.setIdTransaccion(Transaccion.getcontadorTransacciones());
    }
    
    //METODOS DE LA CLASE
    public void depositar(int dineroIngresado, CuentaBancaria datosCuenta){
        this.monto = dineroIngresado;
        datosCuenta.setSaldo(datosCuenta.getSaldo() + dineroIngresado);
        super.setDatosCuenta(datosCuenta);
        Pantalla.imprimirMensaje("\nSe han ingresado $" + dineroIngresado);
    }

    @Override
    public String toString() {
        return "\n\n---DEPOSITO---\n"
                + super.toString() + "\n"
                + "Nro Cuenta = " + super.getDatosCuenta().getNroCuenta() + "\n"
                + "Importe = "+monto+ "\n";
    }
   
    
}
