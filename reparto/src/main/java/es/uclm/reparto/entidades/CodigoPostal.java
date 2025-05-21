package es.uclm.reparto.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CodigoPostal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @ManyToMany(mappedBy = "zonas")
    private List<Repartidor> repartidores;
    
    // --- Constructores ---

    public CodigoPostal() {
    }

    public CodigoPostal(String codigo) {
        this.codigo = codigo;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Repartidor> getRepartidores() {
        return repartidores;
    }

    public void setRepartidores(List<Repartidor> repartidores) {
        this.repartidores = repartidores;
    }

    // --- toString (opcional) ---

    @Override
    public String toString() {
        return "CodigoPostal{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
