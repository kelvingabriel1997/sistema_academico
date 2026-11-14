package br.edu.faex.academico.model;

public class Professor {
    private Long id;
    private String nome;

    public void setId(Long id) {
        this.id = id;
    }

    private String email;

    public Professor() {
    }

    public Professor(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo(){

        return false;
    }


    public void setId(long id) {
    }

    public void setAtivo(boolean ativo) {
    }
}
