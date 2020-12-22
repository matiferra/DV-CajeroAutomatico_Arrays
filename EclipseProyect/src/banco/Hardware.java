package banco;

public abstract class Hardware {

    private int nroSerie;
    private String marca;
    private String modelo;

    public Hardware(int nroSerie, String marca, String modelo) {
        this.nroSerie = nroSerie;
        this.marca = marca;
        this.modelo = modelo;
    }
    
    
    
}
