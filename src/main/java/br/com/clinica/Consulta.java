package br.com.clinica;

public class Consulta {
    private int id;
    private String paciente;
    private String medico;
    private String data;
    private String horario;

    public Consulta(int id, String paciente, String medico, String data, String horario) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.horario = horario;
    }

    @Override
    public String toString() {
        return id + " - Paciente: " + paciente + " | Médico: " + medico + " | Data: " + data + " | Horário: " + horario;
    }
}
