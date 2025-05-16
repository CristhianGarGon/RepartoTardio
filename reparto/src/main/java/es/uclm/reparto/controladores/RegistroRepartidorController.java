package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Repartidor;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.entidades.CodigoPostal;
import es.uclm.reparto.persistencia.RepartidorDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import es.uclm.reparto.persistencia.CodigoPostalDAO;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroRepartidorController {

    private final RepartidorDAO repartidorDAO;
    private final UsuarioDAO usuarioDAO;
    private final CodigoPostalDAO codigoPostalDAO;

    public RegistroRepartidorController(RepartidorDAO repartidorDAO,
                                        UsuarioDAO usuarioDAO,
                                        CodigoPostalDAO codigoPostalDAO) {
        this.repartidorDAO = repartidorDAO;
        this.usuarioDAO = usuarioDAO;
        this.codigoPostalDAO = codigoPostalDAO;
    }

    @GetMapping("/registroRepartidor")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("repartidor", new Repartidor());
        model.addAttribute("codigosDisponibles", codigoPostalDAO.findAll());
        return "registroRepartidor";
    }

    @PostMapping("/registroRepartidor")
    public String procesarRegistro(@ModelAttribute Repartidor repartidor,
                                  @RequestParam String nickname,
                                  @RequestParam String password,
                                  @RequestParam List<Long> zonas, // IDs seleccionados
                                  Model model) {
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNickname(nickname);
        usuario.setPassword(password);
        usuario.setRol("REPARTIDOR");
        usuarioDAO.save(usuario);

        // Asignar usuario
        repartidor.setUsuario(usuario);

        // Obtener objetos CodigoPostal desde los IDs
        List<CodigoPostal> codigosPostales = codigoPostalDAO.findAllById(zonas);
        repartidor.setZonas(codigosPostales);

        // Guardar repartidor
        repartidorDAO.save(repartidor);

        model.addAttribute("usuario", usuario);
        return "registroExitoso";
    }
}