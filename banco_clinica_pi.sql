DROP DATABASE IF EXISTS clinica_pi;
CREATE DATABASE clinica_pi;
USE clinica_pi;

CREATE TABLE pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    crm VARCHAR(30) NOT NULL
);

CREATE TABLE consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT NOT NULL,
    data_consulta DATE NOT NULL,
    horario TIME NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (medico_id) REFERENCES medicos(id)
);

INSERT INTO pacientes (nome, cpf, telefone) VALUES
('João Silva', '123.456.789-00', '(31) 99999-9999'),
('Maria Oliveira', '987.654.321-00', '(31) 98888-8888');

INSERT INTO medicos (nome, especialidade, crm) VALUES
('Dra. Ana Souza', 'Cardiologia', 'CRM-12345'),
('Dr. Carlos Lima', 'Clínico Geral', 'CRM-54321');

INSERT INTO consultas (paciente_id, medico_id, data_consulta, horario) VALUES
(1, 1, '2026-06-10', '14:30:00'),
(2, 2, '2026-06-11', '09:00:00');
