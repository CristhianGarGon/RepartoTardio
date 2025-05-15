package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Cliente;
import es.uclm.reparto.entidades.Usuario;
import es.uclm.reparto.persistencia.ClienteDAO;
import es.uclm.reparto.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroClienteController {
	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@GetMapping("/registroCliente")
	public String mostrarFormularioRegistroCliente(Model model) {
	    model.addAttribute("cliente", new Cliente());
	    return "registroCliente";
	}

	@PostMapping("/registroCliente")
	public String procesarRegistroCliente(@ModelAttribute Cliente cliente,
	                                      @RequestParam String nickname,
	                                      @RequestParam String password) {
	    // Crear y guardar el usuario
	    Usuario u = new Usuario();
	    u.setNickname(nickname);
	    u.setPassword(password);
	    u.setRol("CLIENTE");
	    usuarioDAO.save(u);

	    // Asociar el usuario al cliente
	    cliente.setUsuario(u);

	    // Guardar el cliente
	    clienteDAO.save(cliente);

	    return "registroExitoso";
	}

}
