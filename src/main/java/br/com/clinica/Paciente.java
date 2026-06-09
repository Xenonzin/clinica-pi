package br.com.clinica;

public class Paciente {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;

    public Paciente(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Paciente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
