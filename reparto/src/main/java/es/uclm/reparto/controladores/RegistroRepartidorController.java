package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Repartidor;
import es.uclm.reparto.persistencia.RepartidorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroRepartidorController {

    @Autowired
    private RepartidorDAO repartidorDAO;

    @GetMapping("/registroRepartidor")
    public String mostrarFormularioRegistroRepartidor(Model model) {
        model.addAttribute("repartidor", new Repartidor()); // Objeto vacío para el formulario
        return "registroRepartidor"; // Renderiza el HTML
    }

    @PostMapping("/registroRepartidor")
    public String procesarRegistroRepartidor(@ModelAttribute Repartidor repartidor) {
        repartidorDAO.save(repartidor); // Guarda el repartidor en la base de datos
        return "registroExitoso"; // Redirige a la página de éxito
    }
}
