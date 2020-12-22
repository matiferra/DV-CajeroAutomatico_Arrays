package banco;

public class Retiro extends Transaccion {

    private int monto;

    public Retiro() {
        Transaccion.setContadorTransacciones(Transaccion.getcontadorTransacciones() + 1);
        super.setIdTransaccion(Transaccion.getcontadorTransacciones());
    }

    //METODOS DE LA CLASE
    public CuentaBancaria retirar(int dineroRetirado, CuentaBancaria datosCuenta, String expulsarDinero) {
        this.monto = dineroRetirado;
        if (datosCuenta instanceof CuentaCorriente) {
            CuentaCorriente auxCC = (CuentaCorriente) datosCuenta;
            if (dineroRetirado <= auxCC.getSaldo() + auxCC.getSaldoDescubiertoPermitido()) {
                Pantalla.imprimirMensaje("Operacion exitosa!\n");
                auxCC.setSaldo(auxCC.getSaldo() - dineroRetirado);
                datosCuenta = auxCC;
                Pantalla.imprimirMensaje(expulsarDinero);

            } else {
                Pantalla.imprimirMensaje("Sr Cliente, no tiene fondos suficientes\n");
            }
        } else {
            if (datosCuenta instanceof CajaAhorros) {
                CajaAhorros auxCA = (CajaAhorros) datosCuenta;
                if (dineroRetirado <= auxCA.getSaldo()) {
                    Pantalla.imprimirMensaje("Operacion exitosa!\n");
                    auxCA.setSaldo(auxCA.getSaldo() - dineroRetirado);
                    datosCuenta = auxCA;
                    Pantalla.imprimirMensaje(expulsarDinero);
                } else {
                    Pantalla.imprimirMensaje("Sr Cliente, no tiene fondos suficientes\n");
                }
            }
        }
        super.setDatosCuenta(datosCuenta);
        return datosCuenta;
    }
    
    
    @Override
    public String toString() {
        return "\n\n---RETIRO---\n"
                + super.toString() + "\n"
                + "Nro Cuenta = " + super.getDatosCuenta().getNroCuenta() + "\n"
                + "Importe = "+monto + "\n";
    }
}


