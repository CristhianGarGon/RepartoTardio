package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.entidades.ItemMenu;
import es.uclm.reparto.persistencia.RestauranteDAO;
import es.uclm.reparto.persistencia.ItemMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/anonimo")
public class AnonimoController {

    private final RestauranteDAO restauranteDAO;
    private final ItemMenuDAO itemMenuDAO;

    @Autowired
    public AnonimoController(RestauranteDAO restauranteDAO, ItemMenuDAO itemMenuDAO) {
        this.restauranteDAO = restauranteDAO;
        this.itemMenuDAO = itemMenuDAO;
    }

    @GetMapping("/buscar")
    public String buscarRestaurantes(Model model) {
        model.addAttribute("restaurantes", restauranteDAO.findAll());
        return "buscarAnonimo"; // nueva vista sin botones de acción
    }

    @GetMapping("/verMenu/{id}")
    public String verMenu(@PathVariable Long id, Model model) {
        Restaurante restaurante = restauranteDAO.findById(id).orElse(null);
        if (restaurante == null) return "redirect:/anonimo/buscar";

        List<ItemMenu> menu = itemMenuDAO.findByRestaurante(restaurante);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("menu", menu);
        return "verMenuAnonimo"; 
    }
}