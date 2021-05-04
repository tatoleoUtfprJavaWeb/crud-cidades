package br.edu.utfpr.cp.espjava.crudCidades.cidade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CidadeController {

    private Set<Cidade> cidades;
    private final CidadeRepository repository;

    public CidadeController(CidadeRepository repository) {
        this.repository = repository;
        this.cidades = new HashSet<>();
    }

    @GetMapping("/")
    public String listar(Model model){
        model.addAttribute("listaCidades",
                repository.findAll().stream()
                    .map(cidade -> new Cidade(cidade.getNome(), cidade.getEstado()))
                    .collect(Collectors.toList())
        );
        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(@Valid Cidade cidade, BindingResult validacao, Model model){

        if (validacao.hasErrors()){
            validacao.getFieldErrors()
                    .forEach(fieldError ->
                                model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage())
                            );
            model.addAttribute("nomeInformado", cidade.getNome());
            model.addAttribute("estadoInformado", cidade.getEstado());
            model.addAttribute("listaCidades", cidades);
            return "/crud";

        } else {
            repository.save(cidade.clonar());
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(@RequestParam String nome, @RequestParam String estado){

        var cidadeEstadoEncontrada = repository.findByNomeAndEstado(nome, estado);

        cidadeEstadoEncontrada.ifPresent(repository::delete);

        return "redirect:/";
    }

    @GetMapping("/preparaAlterar")
    public String preparaAlterar(@RequestParam String nome, @RequestParam String estado, Model model){

        var cidadeAtual = repository.findByNomeAndEstado(nome, estado);

        cidadeAtual.ifPresent( cidadeEncontrada -> {
            model.addAttribute("cidadeAtual", cidadeEncontrada);
            model.addAttribute("listaCidades", repository.findAll());
        });

        return "/crud";
    }


    @PostMapping("/alterar")
    public String alterar(@RequestParam String nomeAtual, @RequestParam String estadoAtual, @Valid Cidade cidade,
                          BindingResult validacao, Model model){

        var cidadeAtual = repository.findByNomeAndEstado(nomeAtual, estadoAtual);

        if (cidadeAtual.isPresent()) {
            var cidadeEncontrada = cidadeAtual.get();
            cidadeEncontrada.setNome(cidade.getNome());
            cidadeEncontrada.setEstado(cidade.getEstado());

            repository.saveAndFlush(cidadeEncontrada);
        }
        return "redirect:/";
    }
}
;
