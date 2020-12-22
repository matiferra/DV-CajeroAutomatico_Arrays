package banco;

public class CuentaCorriente extends CuentaBancaria {

    private double saldoDescubiertoPermitido;

    public CuentaCorriente(double saldoDescubiertoPermitido, String nroCuenta, String CBU, int NIP, double saldo) {
        super(nroCuenta, CBU, NIP, saldo);
        this.saldoDescubiertoPermitido = saldoDescubiertoPermitido;
    }
    
    //GETTERS
    public double getSaldoDescubiertoPermitido() {
        return saldoDescubiertoPermitido;
    }
    
    @Override
    public String toString() {
        return "\n\n -- CuentaCorriente -- \n" + super.toString() + "\nSaldo Descubierto Permitido = $ "+ saldoDescubiertoPermitido;
    }

    
}
