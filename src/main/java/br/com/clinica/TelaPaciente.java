package br.com.clinica;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaPaciente extends JFrame {
    private JTextField txtNome, txtCpf, txtTelefone;
    private DefaultListModel<String> modeloLista;
    private PacienteDAO dao = new PacienteDAO();

    public TelaPaciente() {
        setTitle("Cadastro de Pacientes");
        setSize(720, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Pacientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(230, 20, 300, 30);
        add(titulo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(45, 85, 100, 25);
        add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(145, 85, 280, 30);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(45, 130, 100, 25);
        add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(145, 130, 280, 30);
        add(txtCpf);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(45, 175, 100, 25);
        add(lblTelefone);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(145, 175, 280, 30);
        add(txtTelefone);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(145, 235, 120, 35);
        add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(285, 235, 120, 35);
        add(btnVoltar);

        modeloLista = new DefaultListModel<>();
        JList<String> lista = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBounds(45, 300, 620, 130);
        add(scroll);

        atualizarLista();
        btnSalvar.addActionListener(e -> salvarPaciente());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void salvarPaciente() {
        if (txtNome.getText().trim().isEmpty() || txtCpf.getText().trim().isEmpty() || txtTelefone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }
        try {
            dao.inserir(new Paciente(txtNome.getText(), txtCpf.getText(), txtTelefone.getText()));
            JOptionPane.showMessageDialog(this, "Paciente cadastrado no banco de dados!");
            txtNome.setText("");
            txtCpf.setText("");
            txtTelefone.setText("");
            atualizarLista();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar paciente: " + ex.getMessage());
        }
    }

    private void atualizarLista() {
        try {
            modeloLista.clear();
            for (Paciente p : dao.listar()) {
                modeloLista.addElement(p.getId() + " - " + p.getNome() + " - CPF: " + p.getCpf() + " - Tel: " + p.getTelefone());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar pacientes: " + ex.getMessage());
        }
    }

    private void voltar() {
        new TelaMenu().setVisible(true);
        dispose();
    }
}
