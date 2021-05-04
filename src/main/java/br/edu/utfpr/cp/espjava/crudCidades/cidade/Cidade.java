package br.edu.utfpr.cp.espjava.crudCidades.cidade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Cidade {

    @NotBlank (message = "{app.cidade.blank}")
    @Size(min = 5, max = 60, message = "{app.cidade.size}")
    private String nome;

    @NotBlank (message = "{app.estado.blank}")
    @Size(min = 2, max = 2, message = "{app.estado.size}")
    private String estado;

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public CidadeEntidade clonar() {
        var cidadeEntidade = new CidadeEntidade();
        cidadeEntidade.setNome(this.getNome());
        cidadeEntidade.setEstado(this.getEstado());
        return cidadeEntidade;
    }

    public Cidade clonar(CidadeEntidade cidadeEntidade) {
        return new Cidade(cidadeEntidade.getNome(), cidadeEntidade.getEstado());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
