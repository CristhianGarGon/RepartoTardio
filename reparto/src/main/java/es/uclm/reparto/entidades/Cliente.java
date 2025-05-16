package es.uclm.reparto.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column
    private String pedidos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    public Cliente() {}

    public Cliente(String nombre, String apellidos, String dni, String pedidos, Direccion direccion, Usuario usuario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.pedidos = pedidos;
        this.direccion = direccion;
        this.usuario = usuario;
    }
    
    @ManyToMany
    @JoinTable(
        name = "cliente_favorito",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "restaurante_id")
    )
    private List<Restaurante> favoritosList = new ArrayList<>();

    public List<Restaurante> getFavoritosList() {
        return favoritosList;
    }

    public void setFavoritosList(List<Restaurante> favoritosList) {
        this.favoritosList = favoritosList;
    }

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getPedidos() { return pedidos; }
    public void setPedidos(String pedidos) { this.pedidos = pedidos; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("Cliente [id=%s, nombre=%s, apellidos=%s, dni=%s]", id, nombre, apellidos, dni);
    }
}
