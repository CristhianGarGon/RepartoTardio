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
    
    @GetMapping("/menu")
    public String mostrarMenuCliente(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario); // para mostrar el nick
        return "clienteMenu"; // muestra clienteMenu.html
    }

    // Mostrar todos los restaurantes
    @GetMapping("/buscarRestaurantes")
    public String buscarRestaurantes(Model model) {
        model.addAttribute("restaurantes", restauranteDAO.findAll());
        return "buscarRestaurantes";
    }

    // Ver menú de un restaurante específico
    @GetMapping("/verMenu/{id}")
    public String verMenuRestaurante(@PathVariable Long id, Model model) {
        Restaurante restaurante = restauranteDAO.findById(id).orElse(null);
        if (restaurante == null) return "redirect:/cliente/buscarRestaurantes";

        List<ItemMenu> menu = itemMenuDAO.findByRestaurante(restaurante);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("menu", menu);
        return "verMenuRestaurante";
    }
    
    @GetMapping("/verFavoritos")
    public String verFavoritos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = clienteDAO.findById(usuario.getId()).orElse(null);

        if (cliente != null) {
            model.addAttribute("favoritos", cliente.getFavoritos());
        }

        return "verFavoritos"; // Debe existir en /templates
    }

    // Marcar como favorito
    @PostMapping("/")
    public String marcarFavorito(@PathVariable Long id, HttpSession session) {
        Restaurante restaurante = restauranteDAO.findById(id).orElse(null);
        Cliente cliente = (Cliente) session.getAttribute("usuario");

        if (cliente != null && restaurante != null) {
            // Añadimos el restaurante al campo favoritos (tipo CSV o similar)
            String favoritos = cliente.getFavoritos() == null ? "" : cliente.getFavoritos();
            if (!favoritos.contains(restaurante.getNombre())) {
                favoritos += restaurante.getNombre() + ",";
                cliente.setFavoritos(favoritos);
                clienteDAO.save(cliente);
            }
        }

        return "redirect:/cliente/buscarRestaurantes";
    }
   
}