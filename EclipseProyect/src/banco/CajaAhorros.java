
package banco;

public class CajaAhorros extends CuentaBancaria {


    public CajaAhorros(String nroCuenta, String CBU, int NIP, double saldo) {
        super(nroCuenta, CBU, NIP, saldo);
    }

    @Override
    public String toString() {
        return "\n\n -- CajaAhorro -- \n" + super.toString();
    }

    
    
}
