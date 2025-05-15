package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.RestauranteDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroRestauranteController {

    @Autowired
    private RestauranteDAO restauranteDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping("/registroRestaurante")
    public String mostrarFormularioRegistroRestaurante(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "registroRestaurante";
    }

    @PostMapping("/registroRestaurante")
    public String procesarRegistroRestaurante(@ModelAttribute Restaurante restaurante,
                                              @RequestParam String nickname,
                                              @RequestParam String password,
                                              Model model) {
    	
        // Crear y guardar usuario asociado
        Usuario usuario = new Usuario();
        usuario.setNickname(nickname);
        usuario.setPassword(password);
        usuario.setRol("RESTAURANTE");
        usuarioDAO.save(usuario);
        
        // Guardar restaurante
        restaurante.setUsuario(usuario);
        restauranteDAO.save(restaurante);

        model.addAttribute("usuario", usuario);
        return "registroExitoso";
    }
}