package ifpb.edu.br.main.view;

import ifpb.edu.br.main.controller.Controlador;

import java.awt.*;
import javax.swing.*;
import java.io.Serializable;
import java.util.Calendar;

public class TelaPrincipal extends JPanel implements Serializable {
    private Controlador controlador;
    private CalendarioSemanal calendarioSemanal;
    private JanelaCalendario janelaCalendario;
    private JPanel jPanel;

    public TelaPrincipal() {
        controlador = Controlador.getInstance();
        System.out.println(controlador);
        this.setLayout(new BorderLayout());
        calendarioSemanal = new CalendarioSemanal();
        janelaCalendario = new JanelaCalendario(Controlador.getInstance());
        configurar();
    }

    public void configurar() {
        jPanel = new JPanel(new BorderLayout());

        String[] stringMeses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
                "Outubro", "Novembro", "Dezembro"};

        //Inicializa o com o mês atual
        Calendar mesAtual = Calendar.getInstance();
        int mes = mesAtual.get(Calendar.MONTH);

        //Cria um painel no topo e adiciona o jPanel
        JPanel topoPanel = new JPanel(new BorderLayout());
        topoPanel.add(jPanel, BorderLayout.NORTH);

        this.add(topoPanel, BorderLayout.NORTH);
        this.add(janelaCalendario, BorderLayout.CENTER);

        this.setSize(400, 300);
        this.setVisible(true);
    }

}
