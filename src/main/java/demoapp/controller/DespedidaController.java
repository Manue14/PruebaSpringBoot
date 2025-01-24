package demoapp.controller;

import demoapp.service.DespedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DespedidaController {
    private final DespedidaService despedidaService;

    @Autowired
    public DespedidaController(DespedidaService despedidaService) {
        this.despedidaService = despedidaService;
    }

    /*
    Este método devuelve el mensaje como un json que se inserta directamente a la respuesta html

    @RequestMapping("/despedida/{nombre}")
    @ResponseBody
    public String despedir(@PathVariable String nombre) {
        return despedidaService.despedir(nombre);
    }
    */

    /*Este método nos muestra una vista que tengamos creada a la que le hemos insertado el mensaje producido*/
    @RequestMapping("/despedida/{nombre}")
    public String despedir(Model model, @PathVariable(value="nombre") String nombre) {
        String mensaje = despedidaService.despedir(nombre);
        model.addAttribute("mensaje", mensaje);
        return "despedida";
    }
}
