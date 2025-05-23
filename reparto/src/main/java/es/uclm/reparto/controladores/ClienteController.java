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
	
	private static final String USUARIO = "usuario";
	private static final String REDIRECT_BUSCAR_RESTAURANTES = "redirect:/cliente/buscarRestaurantes";


    private final RestauranteDAO restauranteDAO;
    private final ItemMenuDAO itemMenuDAO;
    private final ClienteDAO clienteDAO;
    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;
    private final CodigoPostalDAO codigoPostalDAO;

    @Autowired
    public ClienteController(RestauranteDAO restauranteDAO,
                             ItemMenuDAO itemMenuDAO,
                             ClienteDAO clienteDAO,
                             PedidoDAO pedidoDAO,
                             RepartidorDAO repartidorDAO,
                             CodigoPostalDAO codigoPostalDAO) {
        this.restauranteDAO = restauranteDAO;
        this.itemMenuDAO = itemMenuDAO;
        this.clienteDAO = clienteDAO;
        this.pedidoDAO = pedidoDAO;
        this.repartidorDAO = repartidorDAO;
        this.codigoPostalDAO = codigoPostalDAO;
    }

    @GetMapping("/menu")
    public String mostrarMenuCliente(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        model.addAttribute(USUARIO, usuario);
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
        if (restaurante == null) return REDIRECT_BUSCAR_RESTAURANTES;
        List<ItemMenu> menu = itemMenuDAO.findByRestaurante(restaurante);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("menu", menu);
        return "realizarPedido";
    }

    @GetMapping("/verMenu/{id}")
    public String verMenuRestaurante(@PathVariable Long id, Model model) {
        Restaurante restaurante = restauranteDAO.findById(id).orElse(null);
        if (restaurante == null) return REDIRECT_BUSCAR_RESTAURANTES;
        List<ItemMenu> menu = itemMenuDAO.findAll();
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("menu", menu);
        return "verMenuRestaurante";
    }

    @GetMapping("/verFavoritos")
    public String verFavoritos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        Cliente cliente = clienteDAO.findByUsuario(usuario);
        if (cliente != null) {
            model.addAttribute("favoritos", cliente.getFavoritosList());
        }
        return "verFavoritos";
    }

    @PostMapping("/eliminarFavorito/{id}")
    public String eliminarDeFavoritos(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        Cliente cliente = clienteDAO.findByUsuario(usuario);
        Restaurante restaurante = restauranteDAO.findById(id).orElse(null);
        if (cliente != null && restaurante != null) {
            cliente.getFavoritosList().remove(restaurante);
            clienteDAO.save(cliente);
            System.out.println("🗑️ Restaurante eliminado de favoritos: " + restaurante.getNombre());
        }
        return "redirect:/cliente/verFavoritos";
    }

    @PostMapping("/favorito/{id}")
    public String añadirAFavoritos(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        Cliente cliente = clienteDAO.findByUsuario(usuario);
        Restaurante restaurante = restauranteDAO.findById(id).orElse(null);
        if (cliente != null && restaurante != null) {
            if (!cliente.getFavoritosList().contains(restaurante)) {
                cliente.getFavoritosList().add(restaurante);
                clienteDAO.save(cliente);
                System.out.println("⭐ Restaurante añadido a favoritos: " + restaurante.getNombre());
            } else {
                System.out.println("⚠️ El restaurante ya está en favoritos.");
            }
        }
        return REDIRECT_BUSCAR_RESTAURANTES;
    }

    @GetMapping("/verPedidosEntregados")
    public String verPedidosEntregados(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        Cliente cliente = clienteDAO.findByUsuario(usuario);
        if (cliente != null) {
            List<Pedido> pedidosEntregados = pedidoDAO.findAll().stream()
                    .filter(p -> p.getCliente().getId().equals(cliente.getId()) && p.isEntregado())
                    .toList();
            model.addAttribute("pedidos", pedidosEntregados);
        }
        return "pedidosEntregados";
    }

    @PostMapping("/realizarPedido")
    public String procesarPedido(@RequestParam("itemIds") List<Long> itemIds,
                                 @RequestParam("restauranteId") Long restauranteId,
                                 @RequestParam("calle") String calle,
                                 @RequestParam("numero") String numero,
                                 @RequestParam("ciudad") String ciudad,
                                 @RequestParam("codigoPostal") String cpString,
                                 HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        Cliente cliente = clienteDAO.findByUsuario(usuario);
        Restaurante restaurante = restauranteDAO.findById(restauranteId).orElse(null);
        if (cliente != null && restaurante != null && itemIds != null && !itemIds.isEmpty()) {
            List<ItemMenu> itemsSeleccionados = itemMenuDAO.findAllById(itemIds);
            double total = itemsSeleccionados.stream().mapToDouble(ItemMenu::getPrecio).sum();

            if (cliente.getDireccion() == null) {
                CodigoPostal codigoPostal = codigoPostalDAO.findByCodigo(cpString);
                if (codigoPostal == null) {
                    codigoPostal = new CodigoPostal(cpString);
                    codigoPostalDAO.save(codigoPostal);
                }
                Direccion nuevaDireccion = new Direccion();
                nuevaDireccion.setCalle(calle);
                nuevaDireccion.setNumero(numero);
                nuevaDireccion.setCiudad(ciudad);
                nuevaDireccion.setCodigoPostal(codigoPostal);
                cliente.setDireccion(nuevaDireccion);
                clienteDAO.save(cliente);
            }

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setRestaurante(restaurante);
            pedido.setItems(itemsSeleccionados);
            pedido.setDireccionEntrega(cliente.getDireccion());
            pedido.setTotal(total);

            String cpPedido = cliente.getDireccion().getCodigoPostal().getCodigo();
            Repartidor repartidorAsignado = repartidorDAO.findAll().stream()
                    .filter(r -> r.getZonas().stream().anyMatch(z -> z.getCodigo().equals(cpPedido)))
                    .sorted((r1, r2) -> Integer.compare(r2.getEficiencia(), r1.getEficiencia()))
                    .findFirst().orElse(null);

            if (repartidorAsignado != null) {
                pedido.setRepartidor(repartidorAsignado);
                System.out.println("🚚 Repartidor asignado: " + repartidorAsignado.getNombre());
            }

            pedidoDAO.save(pedido);
            System.out.println("✅ Pedido guardado con ID: " + pedido.getId());
        } else {
            System.out.println("❌ No se pudo guardar el pedido. Datos nulos.");
        }
        return "redirect:/cliente/menu";
    }
}