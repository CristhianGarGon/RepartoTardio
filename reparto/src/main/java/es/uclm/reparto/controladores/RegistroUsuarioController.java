package es.uclm.reparto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroUsuarioController {

    // Página de selección de tipo de usuario
    @GetMapping("/seleccionarTipoUsuario")
    public String mostrarSeleccionTipoUsuario() {
        return "seleccionarTipoUsuario";
    }

    // Procesar la selección y redirigir al formulario correspondiente
    @PostMapping("/seleccionarTipoUsuario")
    public String procesarSeleccionTipoUsuario(@RequestParam String tipoUsuario) {
        if ("CLIENTE".equalsIgnoreCase(tipoUsuario)) {
            return "redirect:/registroCliente";
        } else if ("RESTAURANTE".equalsIgnoreCase(tipoUsuario)) {
            return "redirect:/registroRestaurante";
       } else if ("REPARTIDOR".equalsIgnoreCase(tipoUsuario)) {
           return "redirect:/registroRepartidor";
        }
        return "redirect:/seleccionarTipoUsuario"; // Redirige de nuevo en caso de error
    }
}