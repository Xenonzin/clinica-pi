package br.com.clinica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/clinica_pi";
    private static final String USUARIO = "root";
    private static final String SENHA = "Xenonzin240711#";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
