package es.uclm.reparto.entidades;

import jakarta.persistence.*;

@Entity
public class ItemMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;
    
    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    public enum TipoItem {
        COMIDA, BEBIDA, POSTRE
    }

    public ItemMenu() {}

    public ItemMenu(String nombre, double precio, TipoItem tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    // Getters y setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }

    public void setPrecio(double precio) { this.precio = precio; }

    public TipoItem getTipo() { return tipo; }

    public void setTipo(TipoItem tipo) { this.tipo = tipo; }
    
    public Restaurante getRestaurante() { return restaurante; }
    
    public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }
}