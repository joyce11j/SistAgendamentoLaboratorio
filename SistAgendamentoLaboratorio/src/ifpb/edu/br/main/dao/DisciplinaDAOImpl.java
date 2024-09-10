package ifpb.edu.br.main.dao;

import ifpb.edu.br.main.model.Disciplina;
import ifpb.edu.br.main.model.Professor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImpl implements DisciplinaDAO {
    private static final String FILE_NAME = "disciplinas.ser";
    private List<Disciplina> disciplinas;

    public DisciplinaDAOImpl() {
        disciplinas = carregarDisciplinas();
    }

    @Override
    public void adicionarDisciplina(Disciplina disciplina) throws IOException {
        disciplinas.add(disciplina);
        salvarDisciplinas();
    }

    @Override
    public void removerDisciplina(String nomeDisciplina) throws IOException {
        disciplinas.removeIf(disciplina -> disciplina.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina));
        salvarDisciplinas();
    }

    @Override
    public Professor buscarDisciplina(String nomeDisciplina) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina))
                .map(Disciplina::getProfessor)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Professor> listarDisciplinas() {
        List<Professor> professores = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            professores.add(disciplina.getProfessor());
        }
        return professores;
    }

    private void salvarDisciplinas() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(disciplinas);
        }
    }
    private List<Disciplina> carregarDisciplinas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Disciplina>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Retorna uma lista vazia se o arquivo não existir
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
