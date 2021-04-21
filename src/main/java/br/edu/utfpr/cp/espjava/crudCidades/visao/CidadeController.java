package br.edu.utfpr.cp.espjava.crudCidades.visao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class CidadeController {

    @GetMapping("/")
    public String listar(Model model){

        var cidades = Set.of(
          new Cidade("Curitiba", "PR"),
          new Cidade("São Paulo", "SP"),
          new Cidade("Florianópolis", "SC")
        );

        model.addAttribute("listaCidades", cidades);

        return "/crud";
    }
}
;
