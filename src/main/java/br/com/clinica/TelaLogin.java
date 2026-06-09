package br.com.clinica;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;

    public TelaLogin() {
        setTitle("Sistema de Clínica - Login");
        setSize(430, 310);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Sistema de Clínica");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(110, 25, 240, 30);
        add(titulo);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(55, 90, 100, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(145, 90, 210, 30);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(55, 135, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(145, 135, 210, 30);
        add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(145, 195, 130, 35);
        add(btnEntrar);

        btnEntrar.addActionListener(e -> entrar());
    }

    private void entrar() {
        if (txtUsuario.getText().trim().isEmpty() || new String(txtSenha.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha usuário e senha.");
            return;
        }
        new TelaMenu().setVisible(true);
        dispose();
    }
}
