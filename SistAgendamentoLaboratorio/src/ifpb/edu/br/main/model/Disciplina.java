package ifpb.edu.br.main.model;

import java.io.Serializable;

public class Disciplina implements Serializable{
    private String nomeDisciplina;
    private Professor professor;

    public Disciplina(String nome, Professor professor) {
        this.nomeDisciplina = nome;
        this.professor = professor;
    }

    public String getNomeDisciplina() { return nomeDisciplina; }

    public Professor getProfessor() { return professor; }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return nomeDisciplina;
    }
}