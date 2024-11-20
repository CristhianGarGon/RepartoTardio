package es.uclm.reparto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String mostrarMenu(Model model) {
        return "menu"; // Renderiza el archivo `menu.html`
    }

    @GetMapping("/anonimo")
    public String continuarComoAnonimo() {
        return "anonimo"; // Renderiza el archivo `anonimo.html`
    }

    @GetMapping("/registro-usuario")
    public String registrarUsuario() {
        return "registro"; // Renderiza el archivo `registro.html`
    }

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login"; // Renderiza el archivo `login.html`
    }
}
