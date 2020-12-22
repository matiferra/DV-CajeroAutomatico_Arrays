
package banco;

public abstract class CuentaBancaria {

    private String nroCuenta;
    private String CBU;
    private int PIN;
    private double saldo;
    
    public CuentaBancaria(String nroCuenta, String CBU, int PIN, double saldo) {
        this.nroCuenta = nroCuenta;
        this.CBU = CBU;
        this.PIN = PIN;
        this.saldo = saldo;
    }

    //GETTERS
    public String getNroCuenta() {
        return nroCuenta;
    }

    public String getCBU() {
        return CBU;
    }

    public int getPIN() {
        return PIN;
    }

    public double getSaldo() {
        return saldo;
    }

    //SETTERS
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public void setCBU(String CBU) {
        this.CBU = CBU;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "nroCuenta = " + nroCuenta + "\nCBU = " + CBU + "\nSaldo = " + saldo;
    }
    
    
    
}
