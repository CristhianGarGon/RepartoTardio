package es.uclm.reparto.controladores;

import es.uclm.reparto.entidades.ItemMenu;
import es.uclm.reparto.persistencia.ItemMenuDAO;
import es.uclm.reparto.entidades.Restaurante;
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
    public String procesarFormularioCrearMenu(@ModelAttribute ItemMenu itemMenu) {
        itemMenuDAO.save(itemMenu);
        return "redirect:/restaurante/editarMenu";
    }

    @GetMapping("/editarMenu")
    public String mostrarFormularioEditarMenu(Model model) {
        List<ItemMenu> items = itemMenuDAO.findAll();
        model.addAttribute("items", items);
        return "editarMenu";
    }
}