// Product - El objeto complejo que queremos construir
class Computadora {
    private String procesador;
    private String memoria;
    private String almacenamiento;
    private String tarjetaGrafica;
    private String sistemaOperativo;
    
    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }
    
    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }
    
    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }
    
    public void setTarjetaGrafica(String tarjetaGrafica) {
        this.tarjetaGrafica = tarjetaGrafica;
    }
    
    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }
    
    @Override
    public String toString() {
        return "Computadora:\n" +
               "- Procesador: " + procesador + "\n" +
               "- Memoria: " + memoria + "\n" +
               "- Almacenamiento: " + almacenamiento + "\n" +
               "- Tarjeta Gráfica: " + tarjetaGrafica + "\n" +
               "- Sistema Operativo: " + sistemaOperativo;
    }
}

// Builder - Interfaz abstracta
abstract class ComputadoraBuilder {
    protected Computadora computadora;
    
    public void crearNuevaComputadora() {
        computadora = new Computadora();
    }
    
    public Computadora getComputadora() {
        return computadora;
    }
    
    // Métodos abstractos para construir cada parte
    public abstract void buildProcesador();
    public abstract void buildMemoria();
    public abstract void buildAlmacenamiento();
    public abstract void buildTarjetaGrafica();
    public abstract void buildSistemaOperativo();
}

// ConcreteBuilder para computadora Gaming
class ComputadoraGamingBuilder extends ComputadoraBuilder {
    
    @Override
    public void buildProcesador() {
        computadora.setProcesador("Intel Core i9-13900K");
    }
    
    @Override
    public void buildMemoria() {
        computadora.setMemoria("32GB DDR5 6000MHz");
    }
    
    @Override
    public void buildAlmacenamiento() {
        computadora.setAlmacenamiento("1TB NVMe SSD + 2TB HDD");
    }
    
    @Override
    public void buildTarjetaGrafica() {
        computadora.setTarjetaGrafica("NVIDIA RTX 4080");
    }
    
    @Override
    public void buildSistemaOperativo() {
        computadora.setSistemaOperativo("Windows 11 Pro");
    }
}

// ConcreteBuilder para computadora de Oficina
class ComputadoraOficinaBuilder extends ComputadoraBuilder {
    
    @Override
    public void buildProcesador() {
        computadora.setProcesador("Intel Core i5-13400");
    }
    
    @Override
    public void buildMemoria() {
        computadora.setMemoria("16GB DDR4 3200MHz");
    }
    
    @Override
    public void buildAlmacenamiento() {
        computadora.setAlmacenamiento("512GB SSD");
    }
    
    @Override
    public void buildTarjetaGrafica() {
        computadora.setTarjetaGrafica("Intel UHD Graphics");
    }
    
    @Override
    public void buildSistemaOperativo() {
        computadora.setSistemaOperativo("Windows 11 Home");
    }
}

// Director - Controla el proceso de construcción
class DirectorComputadora {
    private ComputadoraBuilder builder;
    
    public void setBuilder(ComputadoraBuilder builder) {
        this.builder = builder;
    }
    
    public Computadora construirComputadora() {
        builder.crearNuevaComputadora();
        builder.buildProcesador();
        builder.buildMemoria();
        builder.buildAlmacenamiento();
        builder.buildTarjetaGrafica();
        builder.buildSistemaOperativo();
        return builder.getComputadora();
    }
    
    // Método alternativo para construcción parcial
    public Computadora construirComputadoraBasica() {
        builder.crearNuevaComputadora();
        builder.buildProcesador();
        builder.buildMemoria();
        builder.buildAlmacenamiento();
        return builder.getComputadora();
    }
}

// Clase principal para demostrar el patrón
public class PatronBuilderDemo {
    public static void main(String[] args) {
        // Crear el director
        DirectorComputadora director = new DirectorComputadora();
        
        // Construir computadora Gaming
        System.out.println("=== CONSTRUYENDO COMPUTADORA GAMING ===");
        ComputadoraGamingBuilder gamingBuilder = new ComputadoraGamingBuilder();
        director.setBuilder(gamingBuilder);
        Computadora computadoraGaming = director.construirComputadora();
        System.out.println(computadoraGaming);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Construir computadora de Oficina
        System.out.println("=== CONSTRUYENDO COMPUTADORA DE OFICINA ===");
        ComputadoraOficinaBuilder oficinaBuilder = new ComputadoraOficinaBuilder();
        director.setBuilder(oficinaBuilder);
        Computadora computadoraOficina = director.construirComputadora();
        System.out.println(computadoraOficina);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Ejemplo de construcción parcial
        System.out.println("=== CONSTRUCCIÓN PARCIAL (BÁSICA) ===");
        Computadora computadoraBasica = director.construirComputadoraBasica();
        System.out.println(computadoraBasica);
    }
}