package br.com.clinica;

import javax.swing.*;
import java.awt.*;

public class TelaMenu extends JFrame {
    public TelaMenu() {
        setTitle("Sistema de Clínica - Menu Principal");
        setSize(520, 390);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Menu Principal");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(170, 30, 220, 35);
        add(titulo);

        JButton btnPacientes = new JButton("Cadastro de Pacientes");
        btnPacientes.setBounds(140, 100, 240, 40);
        add(btnPacientes);

        JButton btnMedicos = new JButton("Cadastro de Médicos");
        btnMedicos.setBounds(140, 155, 240, 40);
        add(btnMedicos);

        JButton btnConsultas = new JButton("Agendar Consulta");
        btnConsultas.setBounds(140, 210, 240, 40);
        add(btnConsultas);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(140, 265, 240, 40);
        add(btnSair);

        btnPacientes.addActionListener(e -> { new TelaPaciente().setVisible(true); dispose(); });
        btnMedicos.addActionListener(e -> { new TelaMedico().setVisible(true); dispose(); });
        btnConsultas.addActionListener(e -> { new TelaConsulta().setVisible(true); dispose(); });
        btnSair.addActionListener(e -> System.exit(0));
    }
}
