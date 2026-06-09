package br.com.clinica;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaConsulta extends JFrame {
    private JComboBox<Paciente> cbPaciente;
    private JComboBox<Medico> cbMedico;
    private JTextField txtData, txtHorario;
    private DefaultListModel<String> modeloLista;
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private MedicoDAO medicoDAO = new MedicoDAO();
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    public TelaConsulta() {
        setTitle("Agendamento de Consultas");
        setSize(780, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Agendamento de Consultas");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(240, 20, 350, 30);
        add(titulo);

        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setBounds(45, 85, 120, 25);
        add(lblPaciente);
        cbPaciente = new JComboBox<>();
        cbPaciente.setBounds(165, 85, 350, 30);
        add(cbPaciente);

        JLabel lblMedico = new JLabel("Médico:");
        lblMedico.setBounds(45, 130, 120, 25);
        add(lblMedico);
        cbMedico = new JComboBox<>();
        cbMedico.setBounds(165, 130, 350, 30);
        add(cbMedico);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(45, 175, 120, 25);
        add(lblData);
        txtData = new JTextField("10/06/2026");
        txtData.setBounds(165, 175, 350, 30);
        add(txtData);

        JLabel lblHorario = new JLabel("Horário:");
        lblHorario.setBounds(45, 220, 120, 25);
        add(lblHorario);
        txtHorario = new JTextField("14:30");
        txtHorario.setBounds(165, 220, 350, 30);
        add(txtHorario);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(165, 280, 130, 35);
        add(btnAgendar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(315, 280, 130, 35);
        add(btnVoltar);

        modeloLista = new DefaultListModel<>();
        JList<String> lista = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBounds(45, 345, 680, 130);
        add(scroll);

        carregarCombos();
        atualizarLista();
        btnAgendar.addActionListener(e -> agendarConsulta());
        btnVoltar.addActionListener(e -> voltar());
    }

    private void carregarCombos() {
        try {
            cbPaciente.removeAllItems();
            cbMedico.removeAllItems();
            for (Paciente p : pacienteDAO.listar()) cbPaciente.addItem(p);
            for (Medico m : medicoDAO.listar()) cbMedico.addItem(m);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage());
        }
    }

    private void agendarConsulta() {
        if (cbPaciente.getSelectedItem() == null || cbMedico.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Cadastre pelo menos um paciente e um médico.");
            return;
        }
        if (txtData.getText().trim().isEmpty() || txtHorario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha data e horário.");
            return;
        }
        try {
            Paciente p = (Paciente) cbPaciente.getSelectedItem();
            Medico m = (Medico) cbMedico.getSelectedItem();
            consultaDAO.inserir(p.getId(), m.getId(), txtData.getText(), txtHorario.getText());
            JOptionPane.showMessageDialog(this, "Consulta agendada no banco de dados!");
            atualizarLista();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao agendar consulta: " + ex.getMessage());
        }
    }

    private void atualizarLista() {
        try {
            modeloLista.clear();
            for (Consulta c : consultaDAO.listar()) modeloLista.addElement(c.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar consultas: " + ex.getMessage());
        }
    }

    private void voltar() {
        new TelaMenu().setVisible(true);
        dispose();
    }
}
