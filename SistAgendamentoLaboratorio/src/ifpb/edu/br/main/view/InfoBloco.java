package ifpb.edu.br.main.view;

import ifpb.edu.br.main.controller.Controlador;

import java.io.*;

public class InfoBloco implements Serializable {
    private Controlador controlador;
    private String[][] disciplinas;
    private String[][] professores;
    private boolean[][] ocupado;

    public InfoBloco() {
        ocupado = new boolean[11][5];
        disciplinas = new String[11][5];
        professores = new String[11][5];
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public String getDisciplina(int horario, int dia) {
        validarIndices(horario, dia);
        return disciplinas[horario][dia];
    }

    public void setDisciplina(int horario, int dia, String disciplina) {
        validarIndices(horario, dia);
        disciplinas[horario][dia] = disciplina;
    }

    public String getProfessor(int horario, int dia) {
        validarIndices(horario, dia);
        return professores[horario][dia];
    }

    public void setProfessor(int horario, int dia, String professor) {
        validarIndices(horario, dia);
        professores[horario][dia] = professor;
    }

    //Métodos para lidar com o status de ocupação
    public boolean isOcupado(int horario, int dia) {
        validarIndices(horario, dia);
        return ocupado[horario][dia];
    }

    public void setOcupado(int horario, int dia, boolean ocupado) {
        validarIndices(horario, dia);
        this.ocupado[horario][dia] = ocupado;
    }

    //Método para validar os índices
    private void validarIndices(int horario, int dia) {
        if (horario < 0 || horario >= 11 || dia < 0 || dia >= 5) {
            throw new IndexOutOfBoundsException("Índices fora dos limites: horário deve ser de 0 a 10 e dia de 0 a 4.");
        }
    }

    //Salva InfoBloco em um arquivo
    public void salvarInfoBloco(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Carrega InfoBloco de um arquivo
    public static InfoBloco carregarInfoBloco(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (InfoBloco) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
