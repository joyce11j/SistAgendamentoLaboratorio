package ifpb.edu.br.main.controller;

import ifpb.edu.br.main.model.Disciplina;
import ifpb.edu.br.main.model.Professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dados implements Serializable {
   private List<Professor> professoresList;
   private List<Disciplina> disciplinasList;

    public Dados(){
        professoresList = new ArrayList<>();
        disciplinasList = new ArrayList<>();
    }

    public List<Professor> getProfessoresList() {
        return professoresList;
    }

    public void setProfessoresList(List<Professor> professoresList) {
        this.professoresList = professoresList;
    }

    public List<Disciplina> getDisciplinasList() {
        return disciplinasList;
    }

    public void setDisciplinasList(List<Disciplina> disciplinasList) {
        this.disciplinasList = disciplinasList;
    }
}
