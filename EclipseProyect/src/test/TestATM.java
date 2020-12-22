package test;

import banco.Pantalla;
import banco.CajeroAutomatico;
import banco.Banco;
import banco.CuentaCorriente;
import banco.Cliente;
import banco.Buzon;
import banco.Teclado;
import banco.CajaAhorros;
import banco.Impresora;

public class TestATM {

    public static void main(String[] args) {

        //CREAR CLIENTE
        Cliente c1 = new Cliente("Ferrario Matias", "34122541", "011-15242424");
        Cliente c2 = new Cliente("Roberto Rossa", "36545777", "011-15966581");
        Cliente c3 = new Cliente("Carlos F. Vazquez", "32487154", "011-15558249");

        c1.agregarCuentaBancaria(new CajaAhorros("51651", "1351351354", 12346, 70000.0));
        c2.agregarCuentaBancaria(new CuentaCorriente(10000, "165165884664", "541125469", 12345, 200500.25));
        c3.agregarCuentaBancaria(new CajaAhorros("51235", "165165884664", 99999, 54500.25));

        //CREAR CAJERO
        // HARDWARE
        Teclado teclado = new Teclado(6952235, "Toshiba", "2020");
        Buzon buzon = new Buzon(1531231, "DAEWO", "2020");
        Impresora impresora = new Impresora(9654223, "HP", "2020");
        Pantalla pantalla = new Pantalla(1657562, "LG", "2020");

        //INSTANCIAR OBJETO
        CajeroAutomatico ATM = new CajeroAutomatico(teclado, buzon, impresora, pantalla);

        //CREAR BANCO
        Banco banco = new Banco("Galicia", ATM);
        banco.agregarCliente(c1);
        banco.agregarCliente(c2);
        banco.agregarCliente(c3);

        banco.abrirCajero();

        

    }

}
