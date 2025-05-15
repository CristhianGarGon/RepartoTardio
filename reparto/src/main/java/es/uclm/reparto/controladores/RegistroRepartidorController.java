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
	public String mostrarFormularioRegistro(Model model) {
	    model.addAttribute("repartidor", new Repartidor());
	    return "registroRepartidor";
	}

	@PostMapping("/registroRepartidor")
	public String procesarRegistro(@ModelAttribute Repartidor repartidor,
	                               @RequestParam String nickname,
	                               @RequestParam String password,
	                               Model model) {
	    Usuario usuario = new Usuario();
	    usuario.setNickname(nickname);
	    usuario.setPassword(password);
	    usuario.setRol("REPARTIDOR");
	    usuarioDAO.save(usuario);

	    repartidor.setUsuario(usuario);
	    repartidorDAO.save(repartidor);

	    model.addAttribute("usuario", usuario); // Necesario para registroExitoso.html
	    return "registroExitoso";
	}



}