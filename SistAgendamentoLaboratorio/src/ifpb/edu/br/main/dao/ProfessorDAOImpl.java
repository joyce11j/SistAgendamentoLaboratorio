package ifpb.edu.br.main.dao;

import ifpb.edu.br.main.model.Professor;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {
    private static final String FILENAME = "professor.ser";
    private List<Professor> professores;

    public ProfessorDAOImpl() throws FileNotFoundException {
        this.professores = carregarProfessores();
    }

    @Override
    public void adicionarProfessor(Professor professor) throws IOException {
        professores.add(professor);
        salvarProfessores();
    }

    public void removerProfessor(String matricula) throws IOException {
        professores.removeIf(professor -> professor.getMatricula().equals(matricula));
        salvarProfessores();
    }

    @Override
    public Professor buscarProfessorPorMatricula(String matricula) {
        return null;
    }

    @Override
    public List<Professor> listarProfessores() {
        return null;
    }

    private void salvarProfessores() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            objectOutputStream.writeObject(professores);
        } catch (IIOException e){
            e.printStackTrace();
        }
    }

    private List<Professor> carregarProfessores() throws FileNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))){
            return (List<Professor>) objectInputStream.readObject();
        } catch (FileNotFoundException e){
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
