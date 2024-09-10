package ifpb.edu.br.main.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

import ifpb.edu.br.main.controller.Controlador;
import ifpb.edu.br.main.dao.Serializador;

public class TelaLogin extends JPanel implements Serializable {
    private Controlador controlador;
    private Serializador serializador;
    private GerenciadorDeTelas gerenciadorDeTelas;
    private JLabel labelMatricula;
    private JLabel labelSenha;
    private JTextField textMatricula;
    private JPasswordField textSenha;
    private JButton btnEntrar;
    private JButton btnCadastrar;

    public TelaLogin(GerenciadorDeTelas gerenciadorDeTelas) {
        this.gerenciadorDeTelas = gerenciadorDeTelas;
        controlador = Controlador.getInstance();
        serializador = new Serializador();
        System.out.println(controlador);
        configurar();
    }

    public void configurar() {
        this.setBackground(new Color(0xFFF8B8D9, true));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel labelNomeSistema = new JLabel("<html><center> Reservas de Horário<br>para Uso de Laboratório </center></html>");
        labelNomeSistema.setFont(new Font("Arial", 1, 18));
        labelNomeSistema.setForeground(new Color(0x9D1888));
//        JLabel labelTitulo = new JLabel("Login");
//        labelTitulo.setFont(new Font("Arial", 1, 16));

        labelMatricula = new JLabel("Matrícula:");
        labelMatricula.setFont(new Font("Arial", 1, 15));
        labelMatricula.setForeground(new Color(0x9D1888));
        textMatricula = new JTextField(20);

        labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", 1, 15));
        labelSenha.setForeground(new Color(0x9D1888));
        textSenha = new JPasswordField(20);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", 1, 13));
        btnEntrar.setBackground(new Color(0x9D1888));
        btnEntrar.setForeground(new Color(0xFFFFFF));

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", 1, 13));
        btnCadastrar.setBackground(new Color(0x9D1888));
        btnCadastrar.setForeground(new Color(0xFFFFFF));

        //Espaçamento dos componentes
        c.insets = new Insets(10, 0, 10, 10);

        //Configuração do nome do sistema
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        this.add(labelNomeSistema, c);

//        //Configuração do título Login
//        c.gridy = 1;
//        this.add(labelTitulo, c);

        //Configuração da matrícula
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(labelMatricula, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(textMatricula, c);

        //Configuração da senha
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(labelSenha, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(textSenha, c);

        //Configuração dos botões
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(this.btnEntrar, c);

        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(btnCadastrar, c);

        btnEntrar.addActionListener(e -> {
            String matricula = textMatricula.getText().trim();
            String senha = new String(textSenha.getPassword()).trim();

            if (matricula.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "ERRO DE LOGIN", JOptionPane.WARNING_MESSAGE);
            } else if (controlador.validarLogin(matricula, senha)) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                gerenciadorDeTelas.mostrarTela("TelaPrincipal");
            } else {
                JOptionPane.showMessageDialog(null, "Matrícula ou senha incorretos.", "ERRO DE LOGIN", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public String getTextMatricula() {
        return textMatricula.getText();
    }

    public String getTextSenha() {
        return new String(textSenha.getPassword());
    }

    public void addEntrarListener(ActionListener listener) {
        btnEntrar.addActionListener(listener);
    }

    public void addCadastrarListener(ActionListener listener) {
        btnCadastrar.addActionListener(listener);
    }
}