package ifpb.edu.br.main.dao;

import com.google.gson.Gson;
import ifpb.edu.br.main.model.Disciplina;
import ifpb.edu.br.main.model.Professor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonSerializer {
    public static void main(String[] args) {
        Professor p = new Professor("121314", "Antônio", "12345", new ArrayList<>());
        Disciplina d = new Disciplina("EDA", new Professor(p.getMatricula(), p.getNome(), p.getSenha(), p.getDisciplinas()));
        // Escrita em JSON
        Gson gson = new Gson();
        String jsonString = gson.toJson(p);
        System.out.println(jsonString);

        try (FileWriter writer = new FileWriter("dados.json")) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
