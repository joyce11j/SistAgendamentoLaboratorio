package ifpb.edu.br.main.dao;

import ifpb.edu.br.main.model.Disciplina;
import ifpb.edu.br.main.model.Professor;

import java.io.IOException;
import java.util.List;

public interface DisciplinaDAO {
    void adicionarDisciplina(Disciplina disciplina) throws IOException;
    void removerDisciplina(String nomeDisciplina) throws IOException;
    Professor buscarDisciplina(String nomeDisciplina);
    List<Professor> listarDisciplinas();
}
