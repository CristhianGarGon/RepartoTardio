package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.*;
import es.uclm.reparto.persistencia.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired private RestauranteDAO restauranteDAO;
	@Autowired private ItemMenuDAO itemMenuDAO;
	@Autowired private ClienteDAO clienteDAO;
	@Autowired private PedidoDAO pedidoDAO;
	@Autowired private RepartidorDAO repartidorDAO;

	@GetMapping("/menu")
	public String mostrarMenuCliente(HttpSession session, Model model) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario");
	    model.addAttribute("usuario", usuario);
	    return "clienteMenu";
	}

	@GetMapping("/buscarRestaurantes")
	public String buscarRestaurantes(Model model) {
	    model.addAttribute("restaurantes", restauranteDAO.findAll());
	    return "buscarRestaurantes";
	}

	@GetMapping("/realizarPedido/{restauranteId}")
	public String mostrarFormularioPedido(@PathVariable Long restauranteId, Model model) {
	    Restaurante restaurante = restauranteDAO.findById(restauranteId).orElse(null);
	    if (restaurante == null) return "redirect:/cliente/buscarRestaurantes";

	    List<ItemMenu> menu = itemMenuDAO.findByRestaurante(restaurante);
	    model.addAttribute("restaurante", restaurante);
	    model.addAttribute("menu", menu);
	    return "realizarPedido";
	}

	@GetMapping("/verMenu/{id}")
	public String verMenuRestaurante(@PathVariable Long id, Model model) {
	    Restaurante restaurante = restauranteDAO.findById(id).orElse(null);
	    if (restaurante == null) return "redirect:/cliente/buscarRestaurantes";

	    List<ItemMenu> menu = itemMenuDAO.findAll();
	    model.addAttribute("restaurante", restaurante);
	    model.addAttribute("menu", menu);
	    return "verMenuRestaurante";
	}

	@GetMapping("/verFavoritos")
	public String verFavoritos(HttpSession session, Model model) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario");
	    Cliente cliente = clienteDAO.findByUsuario(usuario);

	    if (cliente != null) {
	        model.addAttribute("favoritos", cliente.getFavoritosList());
	    }

	    return "verFavoritos";
	}

	@PostMapping("/realizarPedido")
	public String procesarPedido(@RequestParam("itemIds") List<Long> itemIds,
	                             @RequestParam("direccionEntrega") String direccionEntrega,
	                             @RequestParam("restauranteId") Long restauranteId,
	                             HttpSession session) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario");
	    Cliente cliente = clienteDAO.findByUsuario(usuario);
	    Restaurante restaurante = restauranteDAO.findById(restauranteId).orElse(null);

	    System.out.println("🔎 Usuario en sesión: " + usuario);
	    System.out.println("🔎 Cliente encontrado: " + cliente);
	    System.out.println("📦 Restaurante ID: " + restauranteId);
	    System.out.println("📦 Restaurante encontrado: " + restaurante);
	    System.out.println("📝 Items seleccionados: " + itemIds);

	    if (cliente != null && restaurante != null && itemIds != null && !itemIds.isEmpty()) {
	        List<ItemMenu> itemsSeleccionados = itemMenuDAO.findAllById(itemIds);
	        double total = itemsSeleccionados.stream().mapToDouble(ItemMenu::getPrecio).sum();

	        Pedido pedido = new Pedido();
	        pedido.setCliente(cliente);
	        pedido.setRestaurante(restaurante);
	        pedido.setItems(itemsSeleccionados);
	        pedido.setDireccionEntrega(direccionEntrega);
	        pedido.setTotal(total);

	        // Asignar repartidor automáticamente
	        Repartidor repartidorAsignado = repartidorDAO.findAll().stream()
	                .sorted((r1, r2) -> Integer.compare(r2.getEficiencia(), r1.getEficiencia()))
	                .findFirst().orElse(null);

	        if (repartidorAsignado != null) {
	            pedido.setRepartidor(repartidorAsignado);
	            System.out.println("🚚 Repartidor asignado automáticamente: " + repartidorAsignado.getNombre());
	        }

	        pedidoDAO.save(pedido);
	        System.out.println("✅ Pedido guardado con ID: " + pedido.getId());
	    } else {
	        System.out.println("❌ No se pudo guardar el pedido. Datos nulos.");
	    }

	    return "redirect:/cliente/menu";
	}

}