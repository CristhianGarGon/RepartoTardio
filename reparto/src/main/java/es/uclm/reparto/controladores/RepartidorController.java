package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.*;
import es.uclm.reparto.persistencia.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/repartidor")
public class RepartidorController {

    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;
    private final UsuarioDAO usuarioDAO;

    public RepartidorController(PedidoDAO pedidoDAO,
                                RepartidorDAO repartidorDAO,
                                UsuarioDAO usuarioDAO) {
        this.pedidoDAO = pedidoDAO;
        this.repartidorDAO = repartidorDAO;
        this.usuarioDAO = usuarioDAO;
    }

    @GetMapping("/notificaciones")
    public String mostrarPedidosPendientes(Model model) {
        List<Pedido> pedidos = pedidoDAO.findAll().stream()
                .filter(p -> p.getRepartidor() == null)
                .toList();
        System.out.println("📦 Pedidos sin repartidor: " + pedidos.size());
        model.addAttribute("pedidos", pedidos);
        return "notificaciones";
    }

    @PostMapping("/asignar/{pedidoId}")
    public String asignarPedido(@PathVariable Long pedidoId, HttpSession session) {
        Pedido pedido = pedidoDAO.findById(pedidoId).orElse(null);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        System.out.println("🧾 Asignando pedido ID: " + pedidoId);
        System.out.println("👤 Usuario sesión: " + usuario);
        if (pedido != null && usuario != null) {
            Repartidor repartidor = repartidorDAO.findAll().stream()
                    .filter(r -> r.getUsuario().getId().equals(usuario.getId()))
                    .findFirst().orElse(null);
            System.out.println("🚴 Repartidor encontrado: " + repartidor);
            if (repartidor != null) {
                pedido.setRepartidor(repartidor);
                pedidoDAO.save(pedido);
                System.out.println("✅ Pedido asignado al repartidor");
            }
        }
        return "redirect:/repartidor/notificaciones";
    }

    @GetMapping("/recogerPedido")
    public String mostrarPedidosAsignados(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        System.out.println("👤 Usuario sesión (recoger): " + usuario);
        Repartidor repartidor = repartidorDAO.findAll().stream()
                .filter(r -> r.getUsuario().getId().equals(usuario.getId()))
                .findFirst().orElse(null);
        System.out.println("🚴 Repartidor recogedor: " + repartidor);
        List<Pedido> pedidos = pedidoDAO.findAll().stream()
                .filter(p -> p.getRepartidor() != null && p.getRepartidor().equals(repartidor) && !p.isRecogido())
                .toList();
        System.out.println("📦 Pedidos asignados no recogidos: " + pedidos.size());
        model.addAttribute("pedidos", pedidos);
        return "recogerPedido";
    }

    @PostMapping("/recoger/{id}")
    public String registrarRecogida(@PathVariable Long id) {
        Pedido pedido = pedidoDAO.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setRecogido(true);
            pedidoDAO.save(pedido);
            System.out.println("✅ Pedido marcado como recogido: " + id);
        }
        return "redirect:/repartidor/recogerPedido";
    }

    @GetMapping("/entregarPedido")
    public String mostrarPedidosParaEntrega(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        System.out.println("👤 Usuario sesión (entregar): " + usuario);
        Repartidor repartidor = repartidorDAO.findAll().stream()
                .filter(r -> r.getUsuario().getId().equals(usuario.getId()))
                .findFirst().orElse(null);
        System.out.println("🚴 Repartidor entregador: " + repartidor);
        List<Pedido> pedidos = pedidoDAO.findAll().stream()
                .filter(p -> p.getRepartidor() != null && p.getRepartidor().equals(repartidor) && p.isRecogido() && !p.isEntregado())
                .toList();
        System.out.println("📦 Pedidos para entregar: " + pedidos.size());
        model.addAttribute("pedidos", pedidos);
        return "entregarPedido"; // Asegúrate que el archivo se llama así
    }

    @PostMapping("/entregar/{id}")
    public String registrarEntrega(@PathVariable Long id) {
        Pedido pedido = pedidoDAO.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setEntregado(true);
            pedidoDAO.save(pedido);
            System.out.println("✅ Pedido entregado: " + id);
        }
        return "redirect:/repartidor/entregarPedido";
    }
}