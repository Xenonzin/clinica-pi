package br.com.clinica;

import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO {
    public void inserir(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO pacientes (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.executeUpdate();
        }
    }

    public ArrayList<Paciente> listar() throws SQLException {
        ArrayList<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes ORDER BY id";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone")));
            }
        }
        return lista;
    }
}
