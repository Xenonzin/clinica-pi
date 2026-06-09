package br.com.clinica;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaMedico extends JFrame {
    private JTextField txtNome, txtEspecialidade, txtCrm;
    private DefaultListModel<String> modeloLista;
    private MedicoDAO dao = new MedicoDAO();

    public TelaMedico() {
        setTitle("Cadastro de Médicos");
        setSize(720, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Médicos");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(240, 20, 300, 30);
        add(titulo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(45, 85, 120, 25);
        add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(165, 85, 280, 30);
        add(txtNome);

        JLabel lblEspecialidade = new JLabel("Especialidade:");
        lblEspecialidade.setBounds(45, 130, 120, 25);
        add(lblEspecialidade);
        txtEspecialidade = new JTextField();
        txtEspecialidade.setBounds(165, 130, 280, 30);
        add(txtEspecialidade);

        JLabel lblCrm = new JLabel("CRM:");
        lblCrm.setBounds(45, 175, 120, 25);
        add(lblCrm);
        txtCrm = new JTextField();
        txtCrm.setBounds(165, 175, 280, 30);
        add(txtCrm);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(165, 235, 120, 35);
        add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(305, 235, 120, 35);
        add(btnVoltar);

        modeloLista = new DefaultListModel<>();
        JList<String> lista = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBounds(45, 300, 620, 130);
        add(scroll);

        atualizarLista();
        btnSalvar.addActionListener(e -> salvarMedico());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void salvarMedico() {
        if (txtNome.getText().trim().isEmpty() || txtEspecialidade.getText().trim().isEmpty() || txtCrm.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }
        try {
            dao.inserir(new Medico(txtNome.getText(), txtEspecialidade.getText(), txtCrm.getText()));
            JOptionPane.showMessageDialog(this, "Médico cadastrado no banco de dados!");
            txtNome.setText("");
            txtEspecialidade.setText("");
            txtCrm.setText("");
            atualizarLista();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar médico: " + ex.getMessage());
        }
    }

    private void atualizarLista() {
        try {
            modeloLista.clear();
            for (Medico m : dao.listar()) {
                modeloLista.addElement(m.getId() + " - " + m.getNome() + " - " + m.getEspecialidade() + " - CRM: " + m.getCrm());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar médicos: " + ex.getMessage());
        }
    }

    private void voltar() {
        new TelaMenu().setVisible(true);
        dispose();
    }
}
