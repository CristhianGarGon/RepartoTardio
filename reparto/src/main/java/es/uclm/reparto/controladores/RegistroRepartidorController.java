package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Repartidor;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.RepartidorDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroRepartidorController {

    @Autowired
    private RepartidorDAO repartidorDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping("/registroRepartidor")
    public String mostrarFormularioRegistroRepartidor(Model model) {
        model.addAttribute("repartidor", new Repartidor());
        return "registroRepartidor";
    }

    @PostMapping("/registroRepartidor")
    public String procesarRegistroRepartidor(@ModelAttribute Repartidor repartidor,
                                             @RequestParam String nickname,
                                             @RequestParam String password,
                                             Model model) {
        // Guardar repartidor
        repartidorDAO.save(repartidor);

        // Crear y guardar usuario asociado
        Usuario usuario = new Usuario();
        usuario.setNickname(nickname);
        usuario.setPassword(password);
        usuario.setRol("REPARTIDOR");
        usuarioDAO.save(usuario);

        model.addAttribute("usuario", usuario);
        return "registroExitoso";
    }
}