package br.com.clinica;

public class Medico {
    private int id;
    private String nome;
    private String especialidade;
    private String crm;

    public Medico(int id, String nome, String especialidade, String crm) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
    }

    public Medico(String nome, String especialidade, String crm) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public String getCrm() { return crm; }

    @Override
    public String toString() {
        return id + " - " + nome + " (" + especialidade + ")";
    }
}
