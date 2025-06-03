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
        return "Computadora Ensamblada:\n" +
               "-----------------------\n" +
               "- Procesador: " + (procesador != null ? procesador : "N/A") + "\n" +
               "- Memoria: " + (memoria != null ? memoria : "N/A") + "\n" +
               "- Almacenamiento: " + (almacenamiento != null ? almacenamiento : "N/A") + "\n" +
               "- Tarjeta Gráfica: " + (tarjetaGrafica != null ? tarjetaGrafica : "N/A") + "\n" +
               "- Sistema Operativo: " + (sistemaOperativo != null ? sistemaOperativo : "N/A");
    }
}

// Builder - Interfaz abstracta para la construcción
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
    public void buildProcesador() { computadora.setProcesador("Intel Core i9-13900K"); }
    @Override
    public void buildMemoria() { computadora.setMemoria("32GB DDR5 6000MHz"); }
    @Override
    public void buildAlmacenamiento() { computadora.setAlmacenamiento("2TB NVMe SSD + 4TB HDD"); }
    @Override
    public void buildTarjetaGrafica() { computadora.setTarjetaGrafica("NVIDIA GeForce RTX 4080"); }
    @Override
    public void buildSistemaOperativo() { computadora.setSistemaOperativo("Windows 11 Pro"); }
}

// ConcreteBuilder para computadora de Oficina
class ComputadoraOficinaBuilder extends ComputadoraBuilder {
    @Override
    public void buildProcesador() { computadora.setProcesador("Intel Core i5-13400"); }
    @Override
    public void buildMemoria() { computadora.setMemoria("16GB DDR4 3200MHz"); }
    @Override
    public void buildAlmacenamiento() { computadora.setAlmacenamiento("1TB SSD"); }
    @Override
    public void buildTarjetaGrafica() { computadora.setTarjetaGrafica("Intel UHD Graphics 730"); }
    @Override
    public void buildSistemaOperativo() { computadora.setSistemaOperativo("Windows 11 Home"); }
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
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        // Crear el director que orquesta la construcción
        DirectorComputadora director = new DirectorComputadora();
        String separador = "==================================================";

        // Construir una Computadora Gaming
        System.out.println("### CONSTRUYENDO PC GAMING ###");
        ComputadoraGamingBuilder gamingBuilder = new ComputadoraGamingBuilder();
        director.setBuilder(gamingBuilder);
        Computadora computadoraGaming = director.construirComputadora();
        System.out.println(computadoraGaming);

        System.out.println("\n" + separador + "\n");

        // Construir una Computadora de Oficina
        System.out.println("### CONSTRUYENDO PC DE OFICINA ###");
        ComputadoraOficinaBuilder oficinaBuilder = new ComputadoraOficinaBuilder();
        director.setBuilder(oficinaBuilder);
        Computadora computadoraOficina = director.construirComputadora();
        System.out.println(computadoraOficina);
    }
}
