package ifpb.edu.br.main.view;

import ifpb.edu.br.main.controller.Controlador;
import ifpb.edu.br.main.model.Professor;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTelas extends JFrame implements Serializable {
    private Controlador controlador;
    private List<Professor> professores;
    private TelaPrincipal telaPrincipal;
    private TelaCadastro telaCadastro;
    private TelaLogin telaLogin;
    private JPanel painelPrincipal;
    private CardLayout cardLayout;

    public GerenciadorDeTelas() {
        controlador = Controlador.getInstance();
        professores = new ArrayList<>();

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        telaLogin = new TelaLogin(this);
        telaCadastro = new TelaCadastro(this);

        painelPrincipal.add(telaLogin, "TelaLogin");
        painelPrincipal.add(telaCadastro, "TelaCadastro");

        telaLogin.addCadastrarListener(e -> cardLayout.show(painelPrincipal, "TelaCadastro"));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.add(painelPrincipal);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        mostrarTela("TelaLogin");
    }

    public void mostrarTela(String nomeDaTela) {
        System.out.println("Mostrando tela: " + nomeDaTela);
        if (nomeDaTela.equals("TelaPrincipal") && telaPrincipal == null) {
            telaPrincipal = new TelaPrincipal();
            painelPrincipal.add(telaPrincipal, "TelaPrincipal");
        }

        cardLayout.show(painelPrincipal, nomeDaTela);
    }

    public static void main(String[] args) {

        new GerenciadorDeTelas();
    }
}
