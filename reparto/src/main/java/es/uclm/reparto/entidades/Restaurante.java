package es.uclm.reparto.entidades;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "restaurante_id")
    private List<ItemMenu> menu; // Menú del restaurante

    // Constructor vacío
    public Restaurante() {}

    // Constructor con parámetros
    public Restaurante(String nombre, String direccion, List<ItemMenu> menu) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.menu = menu;
    }

    // Getters y Setters
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<ItemMenu> getMenu() {
        return menu;
    }

    public void setMenu(List<ItemMenu> menu) {
        this.menu = menu;
    }
}
