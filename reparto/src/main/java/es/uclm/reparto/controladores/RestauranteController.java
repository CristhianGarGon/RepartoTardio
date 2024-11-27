package es.uclm.reparto.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.persistencia.RestauranteDAO;

@Controller
public class RestauranteController {

    @Autowired
    private RestauranteDAO restauranteDAO; // Instancia inyectada del repositorio

    // Mostrar el formulario para registrar un restaurante con su menú inicial
    @GetMapping("/altaRestaurante")
    public String mostrarFormularioAlta(Model model) {
        Restaurante restaurante = new Restaurante();
        restaurante.setMenu(new ArrayList<>()); // Inicializa la lista del menú
        model.addAttribute("restaurante", restaurante);
        return "altaRestaurante";
    }

    // Procesar la alta de restaurante con menú inicial
    @PostMapping("/altaRestaurante")
    public String procesarAltaRestaurante(@ModelAttribute Restaurante restaurante, Model model) {
        restauranteDAO.save(restaurante); // Guarda el restaurante y su menú en la base de datos
        return "altaExitosa";
    }

    // Mostrar el formulario para editar el menú de un restaurante
    @GetMapping("/editarMenu/{id}")
    public String mostrarFormularioEdicionMenu(@PathVariable Long id, Model model) {
        Restaurante restaurante = restauranteDAO.findById(id) // Uso correcto de la instancia inyectada
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));
        model.addAttribute("restaurante", restaurante);
        return "editarMenu";
    }

    // Procesar la actualización del menú
    @PostMapping("/editarMenu")
    public String procesarEdicionMenu(@ModelAttribute Restaurante restaurante, Model model) {
        restauranteDAO.save(restaurante); // Uso correcto de la instancia inyectada
        return "edicionExitosa";
    }
}
