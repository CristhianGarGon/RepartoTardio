package es.uclm.reparto.entidades;

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
    private String pedidos; // texto libre o formato CSV

    @Column
    private String direcciones; // texto libre o formato CSV

    public Cliente() {}

    public Cliente(String nombre, String apellidos, String dni, String pedidos, String direcciones) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.pedidos = pedidos;
        this.direcciones = direcciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPedidos() {
        return pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }

    public String getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(String direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        return String.format(
            "Cliente [id=%s, nombre=%s, apellidos=%s, dni=%s, pedidos=%s, direcciones=%s]",
            id, nombre, apellidos, dni, pedidos, direcciones
        );
    }
}
