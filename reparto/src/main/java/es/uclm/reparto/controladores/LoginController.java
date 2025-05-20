package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.UsuarioDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioDAO usuarioDAO;

    public LoginController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarInicioSesion(@RequestParam String usuario,
                                       @RequestParam String password,
                                       HttpSession session,
                                       Model model) {
        Usuario u = usuarioDAO.findByNickname(usuario);

        if (u != null && u.getPassword().equals(password)) {
            session.setAttribute("usuario", u);
            switch (u.getRol()) {
                case "CLIENTE":
                    return "redirect:/cliente/menu";
                case "RESTAURANTE":
                    return "redirect:/restaurante/menu";
                case "REPARTIDOR":
                    return "redirect:/repartidor/menu";
                default:
                    return "menu";
            }
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}