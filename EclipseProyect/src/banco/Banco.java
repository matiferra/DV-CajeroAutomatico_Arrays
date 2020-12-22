
package banco;

import java.util.ArrayList;

public class Banco {

    private String nombre;
    private CajeroAutomatico cajero;
    private ArrayList<Cliente> clientes;

    public Banco(String nombre, CajeroAutomatico cajero) {
        this.nombre = nombre;
        this.cajero = cajero;
        clientes  = new ArrayList<>();
    }

    //GETTERS    
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public ArrayList<CuentaBancaria> getBaseDatos() {
        ArrayList<CuentaBancaria> cuentasAcumulador = new ArrayList<>();
        
        for (Cliente cliente : clientes) {
            cuentasAcumulador.add(cliente.getCuentaBancaria());
        }
     
        return cuentasAcumulador;
    }

    //SETTERS
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
 
    public void setCuentasClientes(ArrayList<CuentaBancaria> cuentasCliente) {
        for (int i=0; i<clientes.size(); i++) {
            if(clientes.get(i).getCuentaBancaria().getNroCuenta().equalsIgnoreCase(cuentasCliente.get(i).getNroCuenta())){
                clientes.get(i).setCuentaBancaria(cuentasCliente.get(i));
            }
        }
    }
    
    //METODOS DE LA CLASE
    public void mostrarBaseDatos() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getCuentaBancaria());
        }
    }

    public void abrirCajero(){
        this.cajero.start(this);
    }

    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    
    
}
