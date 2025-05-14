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

    @Column
    private String direcciones;
    
    @Column
    private String favoritos; // Ejemplo de almacenamiento tipo "Rest1,Rest2,..."
    

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Cliente() {}

    public Cliente(String nombre, String apellidos, String dni, String pedidos, String direcciones, Usuario usuario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.pedidos = pedidos;
        this.direcciones = direcciones;
        this.usuario = usuario;
    }
    @ManyToMany
    private List<Restaurante> Listarfavoritos = new ArrayList<>();

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

    public String getDirecciones() { return direcciones; }
    public void setDirecciones(String direcciones) { this.direcciones = direcciones; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public String getFavoritos() { return favoritos; }
    public void setFavoritos(String favoritos) { this.favoritos = favoritos; }


    @Override
    public String toString() {
        return String.format("Cliente [id=%s, nombre=%s, apellidos=%s, dni=%s]", id, nombre, apellidos, dni);
    }
}
