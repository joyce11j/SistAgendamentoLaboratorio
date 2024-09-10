package ifpb.edu.br.main.view;

import ifpb.edu.br.main.controller.Controlador;
import ifpb.edu.br.main.model.Disciplina;
import ifpb.edu.br.main.model.Professor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

public class JanelaCalendario extends JPanel {
    private Controlador controlador;
    private JList<String> disciplinaList;
    private JList<String> professorList;
    private CalendarioSemanal calendario;
    private InfoBloco[][] infoBlocos;
    private JLabel[] labelsDias;
    private JComboBox<String> comboMes;

    public JanelaCalendario(Controlador controlador) {
        this.calendario = controlador.getCalendario();
        this.controlador = Controlador.getInstance();

        InfoBloco infoBlocoCarregado = InfoBloco.carregarInfoBloco("infoBloco.dat");
        if (infoBlocoCarregado != null) {
            controlador.getBlocoAtual().setInfoBloco(infoBlocoCarregado);
        }

        this.infoBlocos = new InfoBloco[11][5];

        //Inicialização de InfoBloco
        for (int i = 0; i < infoBlocos.length; i++) {
            for (int j = 0; j < infoBlocos[i].length; j++) {
                infoBlocos[i][j] = new InfoBloco();
            }
        }

        this.labelsDias = new JLabel[5];

        this.disciplinaList = new JList<>();
        this.professorList = new JList<>();

        this.setLayout(new BorderLayout());

        JPanel navegacaoPanel = criarPainelDeNavegacao();
        this.add(navegacaoPanel, BorderLayout.NORTH);

        JPanel panel = criarPainelDeHorarios();
        this.add(panel, BorderLayout.CENTER);

        atualizarListDisciplinas();
        atualizarListProfessores();

        atualizarDiasDaSemana();
        atualizarBlocos();
    }

    private JPanel criarPainelDeNavegacao() {
        JPanel navegacaoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navegacaoPanel.setBackground(new Color(0xFFFFFF));

        JButton btnSemAnt = criarBotao("Semana Anterior");
        btnSemAnt.setFont(new Font("Arial", 1, 13));

        JButton btnProxSem = criarBotao("Próxima Semana");
        btnProxSem.setFont(new Font("Arial", 1, 13));

        JButton btnFinalizar = criarBotao("Concluir Agendamento");
        btnFinalizar.setFont(new Font("Arial", 1, 13));

        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        comboMes = new JComboBox<>(meses);
        comboMes.setFont(new Font("Arial", 1, 13));
        comboMes.setBackground(new Color(0x9D1888));
        comboMes.setForeground(new Color(0xFFFFFF));
        comboMes.setSelectedIndex(Calendar.getInstance().get(2));

        comboMes.addActionListener((e) -> {
            int mesSelecionado = this.comboMes.getSelectedIndex();
            controlador.getCalendario().atualizarMes(mesSelecionado);
            controlador.getCalendario().setPrimeiraSemanaDoMes(mesSelecionado);
            this.atualizarDiasDaSemana();
            this.atualizarBlocos();
        });

        JLabel selecaoCombo = new JLabel("Selecione o Mês:");
        selecaoCombo.setFont(new Font("Arial", 1, 13));
        navegacaoPanel.add(selecaoCombo);
        navegacaoPanel.add(comboMes);
        navegacaoPanel.add(btnSemAnt);
        navegacaoPanel.add(btnProxSem);
        navegacaoPanel.add(btnFinalizar);

        btnSemAnt.addActionListener(e -> {
            Controlador.getInstance().getCalendario().semanaAnterior();
            atualizarDiasDaSemana();
            atualizarBlocos();
        });

        btnProxSem.addActionListener(e -> {
            Controlador.getInstance().getCalendario().proximaSemana();
            atualizarDiasDaSemana();
            atualizarBlocos();
        });

        btnFinalizar.addActionListener((e) -> {
            InfoBloco infoBlocoAtual = controlador.getBlocoAtual().getInfoBloco();
            infoBlocoAtual.salvarInfoBloco("infoBloco.dat");

            Object[] options = new Object[]{"Ok", "Encerrar"};

            int escolha = JOptionPane.showOptionDialog(this, "Agendamento Realizado com Sucesso!", "Confirmação", -1, 1, (Icon)null, options, options[0]);
            if (escolha == 1) {
                System.exit(0);
            }
        });

        return navegacaoPanel;
    }

    private JPanel criarPainelDeHorarios() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xFFFFFFFF, true));
        panel.setLayout(new GridLayout(11, 6));

        panel.add(new JLabel("HORÁRIO/DIA", SwingConstants.CENTER));

        //Adição dos dias da semana
        String[] dias = {"SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA"};
        for (int i = 0; i < dias.length; i++) {
            labelsDias[i] = new JLabel("", SwingConstants.CENTER);
            panel.add(labelsDias[i]);
        }

        //Adição dos horários e botões de alocação
        String[] horarios = new String[]{"07:00h - 7:50h", "7:50h - 08:40h", "08:40h - 09:30h", "09:50h - 10:40h",
                "10:40h - 11:30h", "11:30h - 12:20h", "13:50h - 14:40h", "14:40h - 15:30h", "15:50h - 16:40h", "16:40h - 17:30h"};

        for (int i = 0; i < horarios.length; i++) {
            JLabel labelHorario = new JLabel(horarios[i], SwingConstants.CENTER);
            panel.add(labelHorario);

            for (int j = 0; j < dias.length; j++) {
                BotaoAlocacao botao = new BotaoAlocacao("", "");
                configurarBotaoAlocacao(botao, i, j);
                panel.add(botao);
            }
        }

        return panel;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(0x9D1888));
        botao.setForeground(new Color(0xFFFFFF));
        return botao;
    }

    private void configurarBotaoAlocacao(BotaoAlocacao botao, int linha, int coluna) {
        if (controlador.getCalendario() == null || controlador.getBlocoAtual() == null) {
            System.out.println("Calendário não inicializado.");
            botao.setBackground(new Color(0xE3C8D6));
            return;
        }

        InfoBloco infoBloco = controlador.getCalendario().getBlocoAtual().getInfoBloco();

        if (infoBloco == null) {
            System.out.println("InfoBloco não encontrado.");
            botao.setBackground(new Color(0xE3C8D6));
            return;
        }

        configurarPopupMenu(botao, linha, coluna);
    }

    private void configurarPopupMenu(BotaoAlocacao botao, int linha, int coluna) {
        Professor professorLogado = Controlador.getInstance().getUsuarioLogado();

        if (professorLogado == null) {
            System.out.println("Erro: professor não está logado.");
            return;
        }

        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(new Color(0xF8B8D9));
        menu.setForeground(new Color(0xFFFFFF));
        menu.add(new JLabel("Selecionar Disciplina:"));

        List<Disciplina> disciplinasProfessor = professorLogado.getDisciplinas();
        for (Disciplina disciplina : disciplinasProfessor) {
            JMenuItem itemDisciplina = new JMenuItem(disciplina.getNomeDisciplina());

            itemDisciplina.addActionListener(e -> {
                InfoBloco info = Controlador.getInstance().getCalendario().getBlocoAtual().getInfoBloco();
                boolean estaOcupado = info.isOcupado(linha, coluna);

                if (!estaOcupado) {
                    botao.setDisciplina(disciplina.getNomeDisciplina());
                    botao.setProfessor(professorLogado.getNome());
                    botao.setBackground(new Color(0xF8B8D9));
                    info.setDisciplina(linha, coluna, disciplina.getNomeDisciplina());
                    info.setProfessor(linha, coluna, professorLogado.getNome());
                    info.setOcupado(linha, coluna, true);
                    atualizarBotaoTexto(botao, linha, coluna);
                } else {
                    JOptionPane.showMessageDialog(this, "Este horário já está ocupado!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });

            menu.add(itemDisciplina);
        }

        botao.setComponentPopupMenu(menu);

        //Listener para desagendar com clique normal (esquerdo)
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {  //Verifica se foi um clique com o botão esquerdo
                    InfoBloco info = Controlador.getInstance().getCalendario().getBlocoAtual().getInfoBloco();
                    boolean estaOcupado = info.isOcupado(linha, coluna);

                    if (estaOcupado && info.getProfessor(linha, coluna).equals(professorLogado.getNome())) {
                        info.setOcupado(linha, coluna, false);
                        info.setDisciplina(linha, coluna, null);
                        info.setProfessor(linha, coluna, null);
                        botao.setText(""); //Limpa o texto do botão
                        botao.setBackground(new Color(0x9D1888));
                    }
                }
            }
        });
    }

    private void atualizarListDisciplinas() {
        if (disciplinaList == null) {
            disciplinaList = new JList<>();
        }
        List<Disciplina> disciplinas = Controlador.getInstance().getDisciplinas();
        String[] disciplinasArray = disciplinas.stream().map(Disciplina::getNomeDisciplina).toArray(String[]::new);
        disciplinaList.setListData(disciplinasArray);
    }

    private void atualizarListProfessores() {
        if (professorList == null) {
            professorList = new JList<>();
        }
        List<Professor> professores = Controlador.getInstance().getProfessores();
        String[] professoresArray = professores.stream().map(Professor::getNome).toArray(String[]::new);
        professorList.setListData(professoresArray);
    }

    private void atualizarBotaoTexto(BotaoAlocacao botao, int linha, int coluna) {
        InfoBloco infoBloco = Controlador.getInstance().getCalendario().getBlocoAtual().getInfoBloco();
        String disciplina = infoBloco.getDisciplina(linha, coluna);
        String professor = infoBloco.getProfessor(linha, coluna);
        String info = (disciplina != null ? disciplina : "") + " - " + (professor != null ? professor : "");
        botao.setText(info);
    }

    private void atualizarDiasDaSemana() {
        CalendarioSemanal calendario = controlador.getCalendario();

        if (calendario == null) {
            System.out.println("Calendário não inicializado.");
            return;
        }

        String[] diasAtualizados = calendario.getDiasDaSemana();
        for (int i = 0; i < labelsDias.length; i++) {
            labelsDias[i].setText(diasAtualizados[i]);
        }
    }

    private void atualizarBlocos() {
        if (controlador == null || controlador.getBlocoAtual() == null) {
            System.out.println("Calendário não inicializado.");
            return;
        }

        Component[] componentes = ((JPanel) this.getComponent(1)).getComponents();
        int indice = 0;

        for (Component componente : componentes) {
            if (componente instanceof BotaoAlocacao) {
                BotaoAlocacao botao = (BotaoAlocacao) componente;
                int linha = (indice/5);
                int coluna = indice%5;

                InfoBloco infoBloco = controlador.getBlocoAtual().getInfoBloco();
                boolean ocupado = infoBloco.isOcupado(linha, coluna);
                botao.setBackground(ocupado ? new Color(0xF8B8D9) : new Color(0x9D1888));

                String disciplina = infoBloco.getDisciplina(linha, coluna);
                String professor = infoBloco.getProfessor(linha, coluna);
                String info = (disciplina != null ? disciplina : "") + " - " + (professor != null ? professor : "");
                botao.setText(info);

                indice++;
            }
        }
    }
}