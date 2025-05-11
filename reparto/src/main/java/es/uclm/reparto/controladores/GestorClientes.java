package es.uclm.reparto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class GestorClientes{

    @GetMapping("/buscarRestaurantes")
    public String buscarRestaurantes(Model model) {
        // Aquí añadirías los restaurantes al modelo
        return "buscarRestaurantes";
    }

    @GetMapping("/verFavoritos")
    public String verFavoritos(Model model) {
        // Aquí añadirías los favoritos del cliente
        return "favoritos";
    }

    @GetMapping("/realizarPedido")
    public String realizarPedido(Model model) {
        // Aquí se mostraría el formulario de pedido
        return "formularioPedido";
    }
}
