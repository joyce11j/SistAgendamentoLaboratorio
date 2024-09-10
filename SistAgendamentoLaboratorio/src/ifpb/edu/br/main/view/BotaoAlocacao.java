package ifpb.edu.br.main.view;

import javax.swing.*;

public class BotaoAlocacao extends JButton {
    private String disciplina;
    private String professor;

    public BotaoAlocacao(String disciplina, String professor) {
        super("<html>Disciplina: " + disciplina + "<br>Professor: " + professor + "</html>");
        this.disciplina = disciplina;
        this.professor = professor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
        this.setText("<html>Disciplina: " + disciplina);
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
        this.setText("<br>Professor:" + professor + "</html>");
    }
}
