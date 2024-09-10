package ifpb.edu.br.main.dao;

import ifpb.edu.br.main.model.Professor;

import java.io.IOException;
import java.util.List;

public interface ProfessorDAO {
    void adicionarProfessor(Professor professor) throws IOException;
    void removerProfessor(String matricula) throws IOException;
    Professor buscarProfessorPorMatricula(String matricula);
    List<Professor> listarProfessores();
}

