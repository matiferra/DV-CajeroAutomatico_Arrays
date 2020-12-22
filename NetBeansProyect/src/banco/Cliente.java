package banco;

public class Cliente {

    private String nombre;
    private String DNI;
    private String direccion;
    private String mail;
    private String telefono;
    private CuentaBancaria cuentaBancaria;
    
    public Cliente(String nombre, String DNI, String telefono){
        this(nombre, DNI, telefono, "","");
    }
    
    public Cliente(String nombre, String DNI, String direccion, String mail, String telefono) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.direccion = direccion;
        this.mail = mail;
        this.telefono = telefono;
    }

    //GETTERS
    public CuentaBancaria getCuentaBancaria() {
        return this.cuentaBancaria;
    }

    //SETTERS
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
    
    //METODOS DE LA CLASE
    public void agregarCuentaBancaria(CuentaBancaria cuenta){
        this.cuentaBancaria = cuenta;
    }

 
    
    
    
}
