package es.uclm.reparto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String procesarInicioSesion(String usuario, String password, Model model) {
        // Lógica de autenticación (puedes implementar autenticación básica o con seguridad)
        if ("admin".equals(usuario) && "admin".equals(password)) {
            model.addAttribute("nombreUsuario", usuario);
            return "bienvenido"; // Página de bienvenida después del inicio de sesión
        }
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login"; // Redirige de vuelta al login en caso de error
    }
}
