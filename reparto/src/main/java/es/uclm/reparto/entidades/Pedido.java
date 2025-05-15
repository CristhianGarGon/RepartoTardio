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

	@ManyToMany
	@JoinTable(
	    name = "pedido_item_menu",
	    joinColumns = @JoinColumn(name = "pedido_id"),
	    inverseJoinColumns = @JoinColumn(name = "item_menu_id")
	)
	private List<ItemMenu> items;

	@Column
	private String direccionEntrega;

	@Column
	private double total;

	public Pedido() {}

	public Long getId() { return id; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public Restaurante getRestaurante() { return restaurante; }
	public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }

	public List<ItemMenu> getItems() { return items; }
	public void setItems(List<ItemMenu> items) { this.items = items; }

	public String getDireccionEntrega() { return direccionEntrega; }
	public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }

	public double getTotal() { return total; }
	public void setTotal(double total) { this.total = total; }

}
