package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.ItemMenu;
import es.uclm.reparto.persistencia.ItemMenuDAO;
import es.uclm.reparto.persistencia.RestauranteDAO;
import jakarta.servlet.http.HttpSession;
import es.uclm.reparto.entidades.Restaurante;
import es.uclm.reparto.entidades.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private ItemMenuDAO itemMenuDAO;
    
    @Autowired
    private RestauranteDAO restauranteDAO;

    @GetMapping("/menu")
    public String mostrarMenuRestaurante() {
        return "restauranteMenu";
    }

    @GetMapping("/crearMenu")
    public String mostrarFormularioCrearMenu(Model model) {
        model.addAttribute("itemMenu", new ItemMenu());
        model.addAttribute("tipos", ItemMenu.TipoItem.values());
        return "crearMenu";
    }

    @PostMapping("/crearMenu")
    public String procesarFormularioCrearMenu(@ModelAttribute ItemMenu itemMenu, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Restaurante restaurante = restauranteDAO.findByUsuario(usuario);

        if (restaurante != null) {
            itemMenu.setRestaurante(restaurante);
            itemMenuDAO.save(itemMenu);
        }

        return "redirect:/restaurante/editarMenu";
    }

    @GetMapping("/editarMenu")
    public String mostrarFormularioEditarMenu(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Restaurante restaurante = restauranteDAO.findByUsuario(usuario);
        if (restaurante == null) {
            throw new RuntimeException("Restaurante no encontrado para el usuario");
        }

        List<ItemMenu> items = itemMenuDAO.findByRestaurante(restaurante);
        model.addAttribute("items", items);
        return "editarMenu";
    }
    
    @PostMapping("/eliminarItem/{id}")
    public String eliminarItem(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Restaurante restaurante = restauranteDAO.findByUsuario(usuario);

        if (restaurante == null) {
            throw new RuntimeException("Restaurante no encontrado para el usuario");
        }

        ItemMenu item = itemMenuDAO.findById(id).orElse(null);
        if (item != null && item.getRestaurante().getId().equals(restaurante.getId())) {
            itemMenuDAO.delete(item);
        }

        return "redirect:/restaurante/editarMenu";
    }

    @PostMapping("/editarItem")
    public String editarItem(@RequestParam Long id,
                             @RequestParam String nombre,
                             @RequestParam double precio,
                             @RequestParam ItemMenu.TipoItem tipo,
                             HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Restaurante restaurante = restauranteDAO.findByUsuario(usuario);

        if (restaurante == null) {
            throw new RuntimeException("Restaurante no encontrado para el usuario");
        }

        ItemMenu item = itemMenuDAO.findById(id).orElse(null);
        if (item != null && item.getRestaurante().getId().equals(restaurante.getId())) {
            item.setNombre(nombre);
            item.setPrecio(precio);
            item.setTipo(tipo);
            itemMenuDAO.save(item);
        }

        return "redirect:/restaurante/editarMenu";
    }

}