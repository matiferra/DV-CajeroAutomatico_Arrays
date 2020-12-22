
package banco;

public abstract class Transaccion {

    private static int contadorTransacciones = 0;
    private int idTransaccion;
    private CuentaBancaria datosCuenta;

    //GETTERS
    public static int getcontadorTransacciones(){
        return contadorTransacciones;
    }

    public CuentaBancaria getDatosCuenta() {
        return datosCuenta;
    }

    //SETTERS
    public static void setContadorTransacciones(int idTransaccion) {
        contadorTransacciones = idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public void setDatosCuenta(CuentaBancaria datosCuenta) {
        this.datosCuenta = datosCuenta;
    }
   
    // METODOS DE LA CLASE
    // Para aplicar Polimorfismo 
    //public abstract void ejecutarTransaccion();
    
    @Override
    public String toString() {
        return "Nro transaccion: "+idTransaccion;
    }
    
    
    
}
