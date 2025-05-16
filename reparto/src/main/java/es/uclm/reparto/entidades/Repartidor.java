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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(
	    name = "repartidor_codigos_postales",
	    joinColumns = @JoinColumn(name = "repartidor_id"),
	    inverseJoinColumns = @JoinColumn(name = "codigo_postal_id")
	)
	private List<CodigoPostal> zonas;


	public Repartidor() {}

	public Repartidor(String nombre, String apellidos, String nif, int eficiencia, Usuario usuario) {
	    this.nombre = nombre;
	    this.apellidos = apellidos;
	    this.nif = nif;
	    this.eficiencia = eficiencia;
	    this.usuario = usuario;
	}

	// Getters y setters

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }

	public String getNif() { return nif; }
	public void setNif(String nif) { this.nif = nif; }

	public int getEficiencia() { return eficiencia; }
	public void setEficiencia(int eficiencia) { this.eficiencia = eficiencia; }

	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }
	
	// Getter y Setter para zonas (códigos postales que cubre el repartidor)

	public List<CodigoPostal> getZonas() {
	    return zonas;
	}

	public void setZonas(List<CodigoPostal> zonas) {
	    this.zonas = zonas;
	}


	@Override
	public String toString() {
	    return String.format("Repartidor [id=%s, nombre=%s, apellidos=%s, nif=%s]", id, nombre, apellidos, nif);
	}


}
