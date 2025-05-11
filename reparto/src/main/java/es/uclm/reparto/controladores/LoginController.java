package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarInicioSesion(@RequestParam String usuario,
                                       @RequestParam String password,
                                       Model model) {
        Usuario u = usuarioDAO.findByNickname(usuario);

        if (u != null && u.getPassword().equals(password)) {
            model.addAttribute("usuario", u);
            switch (u.getRol()) {
                case "CLIENTE": return "clienteMenu";
                case "RESTAURANTE": return "restauranteMenu";
                case "REPARTIDOR": return "repartidorMenu";
                default: return "menu";
            }
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }
}
