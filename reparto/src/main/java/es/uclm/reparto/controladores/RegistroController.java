package es.uclm.reparto.business.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.uclm.RepartoDom.business.entity.Usuario;

@Controller
public class RegistroController {

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        // Pasa un objeto vacío de tipo Usuario a la plantilla
        model.addAttribute("usuario", new Usuario());  // Aquí se agrega el objeto vacío 'usuario'
        return "registro"; // Renderiza la plantilla `registro.html`
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model) {
        // Por ahora, simulamos el guardado del usuario sin interactuar con una base de datos
        model.addAttribute("usuario", usuario);
        return "registroExitoso"; // Redirige a la plantilla de confirmación
    }
}
