package br.com.clinica;

import java.sql.*;
import java.util.ArrayList;

public class MedicoDAO {
    public void inserir(Medico medico) throws SQLException {
        String sql = "INSERT INTO medicos (nome, especialidade, crm) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.executeUpdate();
        }
    }

    public ArrayList<Medico> listar() throws SQLException {
        ArrayList<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicos ORDER BY id";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Medico(rs.getInt("id"), rs.getString("nome"), rs.getString("especialidade"), rs.getString("crm")));
            }
        }
        return lista;
    }
}
