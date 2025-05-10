package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.Cliente;
import es.uclm.reparto.persistencia.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroClienteController {

    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/registroCliente")
    public String mostrarFormularioRegistroCliente(Model model) {
        model.addAttribute("cliente", new Cliente()); // Objeto vacío para el formulario
        return "registroCliente"; // Renderiza el HTML
    }

    @PostMapping("/registroCliente")
    public String procesarRegistroCliente(@ModelAttribute Cliente cliente) {
        clienteDAO.save(cliente); // Guarda el cliente en la base de datos
        return "registroExitoso"; // Redirige a la página de éxito
    }
}