package es.uclm.reparto.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Restaurante restaurante;

	@ManyToOne
	private Repartidor repartidor;

	@ManyToMany
	@JoinTable(
	    name = "pedido_item_menu",
	    joinColumns = @JoinColumn(name = "pedido_id"),
	    inverseJoinColumns = @JoinColumn(name = "item_menu_id")
	)
	private List<ItemMenu> items;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "direccion_id")
	private Direccion direccionEntrega;

	@Column
	private double total;

	@Column
	private boolean recogido;

	@Column
	private boolean entregado;

	public Pedido() {
		//Constructor innecesario
	}

	// Getters y setters

	public Long getId() { return id; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public Restaurante getRestaurante() { return restaurante; }
	public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }

	public List<ItemMenu> getItems() { return items; }
	public void setItems(List<ItemMenu> items) { this.items = items; }

	public Direccion getDireccionEntrega() {
	    return direccionEntrega;
	}

	public void setDireccionEntrega(Direccion direccionEntrega) {
	    this.direccionEntrega = direccionEntrega;
	}


	public double getTotal() { return total; }
	public void setTotal(double total) { this.total = total; }

	public Repartidor getRepartidor() { return repartidor; }
	public void setRepartidor(Repartidor repartidor) { this.repartidor = repartidor; }

	public boolean isRecogido() { return recogido; }
	public void setRecogido(boolean recogido) { this.recogido = recogido; }

	public boolean isEntregado() { return entregado; }
	public void setEntregado(boolean entregado) { this.entregado = entregado; }
}