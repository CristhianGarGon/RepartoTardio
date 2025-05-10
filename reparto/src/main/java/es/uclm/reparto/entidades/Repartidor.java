package es.uclm.reparto.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Repartidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String nif;

    @Column
    private int eficiencia;

    @ElementCollection
    @CollectionTable(name = "zonas_repartidor", joinColumns = @JoinColumn(name = "repartidor_id"))
    @Column(name = "zona")
    private List<String> zonas;

    public Repartidor() {}

    public Repartidor(String nombre, String apellidos, String nif, int eficiencia, List<String> zonas) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.eficiencia = eficiencia;
        this.zonas = zonas;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(int eficiencia) {
        this.eficiencia = eficiencia;
    }

    public List<String> getZonas() {
        return zonas;
    }

    public void setZonas(List<String> zonas) {
        this.zonas = zonas;
    }

    @Override
    public String toString() {
        return String.format("Repartidor [id=%s, nombre=%s, apellidos=%s, nif=%s, eficiencia=%d, zonas=%s]", id, nombre, apellidos, nif, eficiencia, zonas);
    }
}
