package es.uclm.reparto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String mostrarMenu(Model model) {
        return "menuInicial"; // Renderiza el archivo `menuInicial.html`
    }

    @GetMapping("/anonimo")
    public String continuarComoAnonimo() {
        return "anonimo"; // Renderiza el archivo `anonimo.html`
    }

    // ❌ ELIMINAR ESTE MÉTODO DUPLICADO
    // @GetMapping("/seleccionarTipoUsuario")
    // public String registrarUsuario() {
    //     return "seleccionarTipoUsuario";
    // }

    /*
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login"; // Renderiza el archivo `login.html`
    } */
}
