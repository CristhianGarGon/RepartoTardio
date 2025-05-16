package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.entidades.CodigoPostal;
import es.uclm.reparto.persistencia.RestauranteDAO;
import es.uclm.reparto.persistencia.CodigoPostalDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroRestauranteController {

    private final RestauranteDAO restauranteDAO;
    private final UsuarioDAO usuarioDAO;
    private final CodigoPostalDAO codigoPostalDAO;

    public RegistroRestauranteController(RestauranteDAO restauranteDAO,
                                         UsuarioDAO usuarioDAO,
                                         CodigoPostalDAO codigoPostalDAO) {
        this.restauranteDAO = restauranteDAO;
        this.usuarioDAO = usuarioDAO;
        this.codigoPostalDAO = codigoPostalDAO;
    }

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

        restaurante.setUsuario(usuario);

        // Gestionar código postal
        String codigoPostalStr = restaurante.getDireccion().getCodigoPostal().getCodigo();
        CodigoPostal codigoPostal = codigoPostalDAO.findByCodigo(codigoPostalStr);
        if (codigoPostal == null) {
            codigoPostal = new CodigoPostal(codigoPostalStr);
            codigoPostalDAO.save(codigoPostal);
        }
        restaurante.getDireccion().setCodigoPostal(codigoPostal);

        // Guardar restaurante (con dirección en cascada)
        restauranteDAO.save(restaurante);

        model.addAttribute("usuario", usuario);
        return "registroExitoso";
    }
}