package ifpb.edu.br.main.view;

import ifpb.edu.br.main.controller.Controlador;
import ifpb.edu.br.main.dao.Serializador;
import ifpb.edu.br.main.model.Disciplina;
import ifpb.edu.br.main.model.Professor;

import java.awt.*;
import javax.swing.*;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaCadastro extends JPanel implements Serializable {
    private Serializador serializador;
    private Controlador controlador;
    private List<Disciplina> disciplinas;
    private GerenciadorDeTelas gerenciadorDeTelas;
    private JLabel labelMatricula;
    private JLabel labelNome;
    private JLabel labelSenha;
    private JLabel labelDisciplina;
    private JTextField textDisciplina;
    private JTextField textMatricula;
    private JTextField textNome;
    private JTextArea areaDescricao;
    private JPasswordField textSenha;
    private JButton btnCadastrar;
    private JButton btnAdicionar;
    private JButton btnVoltar;

    public TelaCadastro(GerenciadorDeTelas gerenciadorDeTelas) {
        controlador = Controlador.getInstance();
        this.gerenciadorDeTelas = gerenciadorDeTelas;
        serializador = new Serializador();
        disciplinas = new ArrayList<>();
        configurar();
    }

    public void configurar() {
        this.setBackground(new Color(0xFFF8B8D9, true));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel labelTitulo = new JLabel("Cadastro");
        labelTitulo.setFont(new Font("Arial", 1, 17));
        labelTitulo.setForeground(new Color(0x9D1888));

        labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Arial", 1, 15));
        labelNome.setForeground(new Color(0x9D1888));
        textNome = new JTextField(20);

        labelMatricula = new JLabel("Matrícula:");
        labelMatricula.setFont(new Font("Arial", 1, 15));
        labelMatricula.setForeground(new Color(0x9D1888));
        textMatricula = new JTextField(20);

        labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", 1, 15));
        labelSenha.setForeground(new Color(0x9D1888));
        textSenha = new JPasswordField(20);

        labelDisciplina = new JLabel("Disciplina:");
        labelDisciplina.setFont(new Font("Arial", 1, 15));
        labelDisciplina.setForeground(new Color(0x9D1888));
        textDisciplina = new JTextField(20);

        btnAdicionar = new JButton("+");
        btnAdicionar.setBackground(new Color(0x9D1888));
        btnAdicionar.setForeground(new Color(0xFFFFFF));

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", 1, 13));
        btnCadastrar.setBackground(new Color(0x9D1888));
        btnCadastrar.setForeground(new Color(0xFFFFFF));

        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", 1, 13));
        btnVoltar.setBackground(new Color(0x9D1888));
        btnVoltar.setForeground(new Color(0xFFFFFF));

        areaDescricao = new JTextArea(10, 20);
        areaDescricao.setEditable(false);
        areaDescricao.setLineWrap(true);
        areaDescricao.setWrapStyleWord(true);

        c.insets = new Insets(10, 0, 10, 10);

        //Configuração do título Cadastro
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = 10;
        this.add(labelTitulo, c);

        //Configuração do nome
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = 22;
        this.add(labelNome, c);

        c.gridx = 1;
        c.anchor = 21;
        this.add(textNome, c);

        //Configuração da matrícula
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = 22;
        this.add(labelMatricula, c);

        c.gridx = 1;
        c.anchor = 21;
        this.add(textMatricula, c);

        //Configuração da senha
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = 22;
        this.add(labelSenha, c);

        c.gridx = 1;
        c.anchor = 21;
        this.add(textSenha, c);

        //Configuração da disciplina
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = 22;
        this.add(labelDisciplina, c);

        c.gridx = 1;
        c.anchor = 21;
        this.add(textDisciplina, c);

        //Configuração do botão adicionar
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = 21;
        this.add(btnAdicionar, c);

        //Configuração da área de descrição
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;
        c.anchor = 21;
        c.fill = 1;
        this.add(new JScrollPane(areaDescricao), c);

        //Configuração do botão cadastrar
        c.gridx = 2;
        c.gridy = 7;
        c.gridwidth = 1;
        c.anchor = 21;
        this.add(btnCadastrar, c);

        c.gridx = 0;
        c.gridy = 7;
        this.add(this.btnVoltar, c);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = getTextNome().trim();
                String matricula = getTextMatricula().trim();
                String disciplina = getTextDisciplina().trim();
                String senha = getTextSenha().trim();

                //Validação dos campos
                if (nome.isEmpty() || matricula.isEmpty() || disciplina.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "ERRO NO CADASTRO", JOptionPane.WARNING_MESSAGE);
                } else if (controlador.matriculaExistente(matricula)) { //Verifica se a matrícula já existe
                    JOptionPane.showMessageDialog(null, "Já existe cadastro com essa matrícula.", "ERRO NO CADASTRO", JOptionPane.WARNING_MESSAGE);
                } else {
                    Professor professor = new Professor(matricula, nome, senha, disciplinas);
                    disciplinas.forEach(d -> d.setProfessor(professor));
                    controlador.addProfessor(professor);

                    //Condição para efetuar login após o cadastro
                    if (controlador.login(matricula, senha)) {
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Cadastro concluído", JOptionPane.INFORMATION_MESSAGE);
                        gerenciadorDeTelas.mostrarTela("TelaPrincipal");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao logar após cadastro.", "ERRO NO LOGIN", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        btnAdicionar.addActionListener(e -> {
            String nome = getTextNome().trim();
            String disciplina = getTextDisciplina().trim();
            areaDescricao.append("Disciplina(s): \n" + disciplina);
            disciplinas.add(new Disciplina(disciplina, null));
        });

        this.btnVoltar.addActionListener((e) -> this.gerenciadorDeTelas.mostrarTela("TelaLogin"));
    }

    public String getTextNome() {
        return textNome.getText();
    }

    public String getTextMatricula() {
        return textMatricula.getText();
    }

    public String getTextDisciplina() {
        return textDisciplina.getText();
    }

    public String getTextSenha() {
        return new String(textSenha.getPassword());
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void addCadastrarListener(ActionListener listener) {
        btnCadastrar.addActionListener(listener);
    }
}