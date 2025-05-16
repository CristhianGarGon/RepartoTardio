package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    private static final Logger log = LoggerFactory.getLogger(RegistroController.class);

    private final UsuarioDAO usuarioDAO;

    public RegistroController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // Muestra el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        log.info("Formulario de registro mostrado.");
        return "registro"; // Renderiza la plantilla registro.html
    }

    // Procesa los datos enviados desde el formulario
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        // Guarda el usuario en la base de datos
        Usuario usuarioGuardado = usuarioDAO.save(usuario);
        log.info("Usuario guardado: {}", usuarioGuardado);
        return "redirect:/login"; // Renderiza la plantilla de confirmación
    }
}