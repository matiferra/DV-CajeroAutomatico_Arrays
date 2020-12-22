package banco;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.TestATM;

public class CajeroAutomatico extends Hardware {

    private final int BILLETES = 500;
    private final int VALOR_BILLETE = 20;
    private static double saldoInicial;
    private double saldoActual = saldoInicial;

    private Hilo reset = new Hilo();
    private Thread reiniciarSaldo = new Thread(reset);

    private ArrayList<CuentaBancaria> cuentasClientes = new ArrayList<>();
    private ArrayList<Transaccion> transacciones = new ArrayList<>();

    private Teclado teclado;
    private Buzon buzon;
    private Impresora impresora;
    private Pantalla pantalla;

    private String menuOperaciones = "\n\n---------------MENU---------------\n"
            + "1 - Consultar saldo \n"
            + "2 - Depositar dinero\n"
            + "3 - Retirar dinero\n\n"
            + "4 - SALIR\n";

    public CajeroAutomatico(int nroSerie, String marca, String modelo) {
        super(nroSerie, marca, modelo);
        CajeroAutomatico.saldoInicial = (BILLETES * VALOR_BILLETE);
        this.saldoActual = CajeroAutomatico.saldoInicial;
    }

    public CajeroAutomatico(Teclado teclado, Buzon buzon, Impresora impresora, Pantalla pantalla) {
        this(0, "", "");
        this.teclado = teclado;
        this.buzon = buzon;
        this.impresora = impresora;
        this.pantalla = pantalla;
        CajeroAutomatico.saldoInicial = (BILLETES * VALOR_BILLETE);
        this.saldoActual = CajeroAutomatico.saldoInicial;
    }

    //GETTERS
    public ArrayList<CuentaBancaria> getCuentasDeClientes() {
        return cuentasClientes;
    }

    //SETTERS
    public void setBaseDatos(ArrayList<CuentaBancaria> clientes) {
        this.cuentasClientes = clientes;
    }

    public static void setSaldoInicial(double saldoInicial) {
        CajeroAutomatico.saldoInicial = saldoInicial;
    }

    
    public void setSaldo(CuentaBancaria datosCuenta) {
        int indice = buscarCliente(datosCuenta);
        cuentasClientes.set(indice, datosCuenta);
    }

    //METODOS DE LA CLASE
    public void mostrarBaseDatos() {
        for (CuentaBancaria cliente : cuentasClientes) {
            System.out.println(cliente);
        }
    }

    private int buscarCliente(CuentaBancaria datosCuenta) {
        int indice = 0;
        for (CuentaBancaria cliente : cuentasClientes) {
            if (cliente.getNroCuenta().equalsIgnoreCase(datosCuenta.getNroCuenta())) {
                indice = cuentasClientes.indexOf(datosCuenta);
            }
        }
        return indice;
    }

    private CuentaBancaria validacionDatos() {
        CuentaBancaria aux = null;
        String nroCuenta = "";
        int PIN = 0;
        while(aux == null){
            do{
                nroCuenta = teclado.ingresoDeNroCuenta();
                if(nroCuenta.length() != 5){
                    Pantalla.imprimirMensaje("El Nº Cuenta debe contener 5 digitos\n\n");
                }
            } while (nroCuenta.length() != 5);

            do{
                PIN = teclado.ingresoDePIN();
                if(PIN == -1){
                    Pantalla.imprimirMensaje("Vuleva a intentar\n\n");
                }else if(PIN <= 9999 || PIN >= 100000) {
                    Pantalla.imprimirMensaje("El PIN debe contener 5 digitos\n\n");
                }
            }while (PIN <= 9999 || PIN >= 100000);


            for (CuentaBancaria cuenta : cuentasClientes) {
                if (PIN == cuenta.getPIN() && nroCuenta.equalsIgnoreCase(cuenta.getNroCuenta())) {
                    aux = cuenta;
                } 
            }
            if(aux==null){
                Pantalla.imprimirMensaje("LA CUENTA NO EXISTE\n\n");
            }
        }
        return aux;
    }

    //METODOS FUNCIONAMIENTO PRINCIPAL
    public void start(Banco banco) {
        reiniciarSaldo.start();
        while (true) {
            setBaseDatos(banco.getBaseDatos());

            //PARA COMPROBAR EL FUNCIONAMIENTO DE ACTUALIZACION DE LA BD
            //ATM.mostrarBaseDatos();
            Pantalla.imprimirMensaje("\n\n"
                    + "****************************************\n"
                    + "***************Bienvenido***************\n"
                    + "****************************************\n"
                    + "\n-----------------ACCESO-----------------\n");

            //GUARDAR CUENTA INGRESADA
            CuentaBancaria datosCuenta;
            datosCuenta = validacionDatos();
            
            Pantalla.imprimirMensaje("\n\n"
                    + "****************************************\n"
                    + "***Bienvenido al Menu de Operaciones****\n"
                    + "****************************************\n");

            int opcion = -1;
            while (opcion != 4) {
                Pantalla.imprimirMensaje(menuOperaciones);
                opcion = teclado.ingresoOpcion();

                switch (opcion) {
                    case 1:
                        Consulta c = new Consulta();
                        c.consultar(datosCuenta);
                        transacciones.add(c);
                        break;
                    case 2:
                        Deposito d = new Deposito();
                        int dineroIngresado = buzon.recibirDinero();
                        if(dineroIngresado == -1){
                            Pantalla.imprimirMensaje("\n\n Vuelva a intentar");
                        }else{
                            d.depositar(dineroIngresado, datosCuenta);
                            transacciones.add(d);
                            this.saldoActual += dineroIngresado;
                        }
                        break;
                    case 3:
                        Retiro r = new Retiro();
                        int dineroRetirado = teclado.ingresoMonto();
                        if(dineroRetirado == -1){
                        Pantalla.imprimirMensaje("\n\n Vuelva a intentar");
                        } else if (dineroRetirado <= this.saldoActual) {
                            this.setSaldo(r.retirar(dineroRetirado, datosCuenta, buzon.expulsarDinero()));
                            this.saldoActual -= dineroRetirado;
                            transacciones.add(r);
                        } else if (this.saldoActual != 0) {
                            Pantalla.imprimirMensaje("Por favor, ingrese un monto menor");
                        } else {
                            Pantalla.imprimirMensaje("El cajero no dispone de efectivo");
                        }
                        break;
                    case 4:
                        Pantalla.imprimirMensaje(impresora.expulsarTicket() + "\n");
                        Pantalla.imprimirMensaje("GRACIAS POR USAR EL CAJERO CHE!\n");
                        break;
                    default:
                        Pantalla.imprimirMensaje("Ingrese una opcion valida");
                        break;
                }
            }

            banco.setCuentasClientes(getCuentasDeClientes());

            //PARA COMPROBAR EL FUNCIONAMIENTO DE ACTUALIZACION DE LA BD
            //banco.mostrarBaseDatos();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestATM.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Transaccion transaccion : transacciones) {
                Pantalla.imprimirMensaje("" + transaccion);
            }

            /* Al ir llamando a las metodos static de las clases Consulta, Retiro y Deposito
               se van creando instancias y a traves de estos objetos vamos incrementando
               el atributo static de la clase Transaccion y guardando ese dato para posible futura consulta
            
            EJEMPLO
            Consulta c = new Consulta(0);
            System.out.println(c.getIdTransaccion());
            
             */
        }
    }

}

class Hilo implements Runnable
{
    @Override
    public void run() 
    {
        while(true){
            try {
                Thread.sleep(1000*60*60*24);
                CajeroAutomatico.setSaldoInicial(500*20);
            } catch (Exception e) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}