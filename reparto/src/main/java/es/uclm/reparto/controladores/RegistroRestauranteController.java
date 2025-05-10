package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.persistencia.RestauranteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroRestauranteController {

    @Autowired
    private RestauranteDAO restauranteDAO;

    @GetMapping("/registroRestaurante")
    public String mostrarFormularioRegistroRestaurante(Model model) {
        model.addAttribute("restaurante", new Restaurante()); // Objeto vacío para el formulario
        return "registroRestaurante"; // Renderiza el HTML
    }

    @PostMapping("/registroRestaurante")
    public String procesarRegistroRestaurante(@ModelAttribute Restaurante restaurante) {
        restauranteDAO.save(restaurante); // Guarda el restaurante en la base de datos
        return "registroExitoso"; // Redirige a la página de éxito
    }
}
