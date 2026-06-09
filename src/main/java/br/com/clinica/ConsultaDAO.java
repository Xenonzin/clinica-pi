package br.com.clinica;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {
    public void inserir(int pacienteId, int medicoId, String data, String horario) throws SQLException {
        String sql = "INSERT INTO consultas (paciente_id, medico_id, data_consulta, horario) VALUES (?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pacienteId);
            stmt.setInt(2, medicoId);
            stmt.setString(3, data);
            stmt.setString(4, horario);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Consulta> listar() throws SQLException {
        ArrayList<Consulta> lista = new ArrayList<>();
        String sql = "SELECT c.id, p.nome AS paciente, m.nome AS medico, " +
                "DATE_FORMAT(c.data_consulta, '%d/%m/%Y') AS data_formatada, " +
                "TIME_FORMAT(c.horario, '%H:%i') AS horario_formatado " +
                "FROM consultas c INNER JOIN pacientes p ON c.paciente_id = p.id " +
                "INNER JOIN medicos m ON c.medico_id = m.id ORDER BY c.id";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Consulta(rs.getInt("id"), rs.getString("paciente"), rs.getString("medico"), rs.getString("data_formatada"), rs.getString("horario_formatado")));
            }
        }
        return lista;
    }
}
