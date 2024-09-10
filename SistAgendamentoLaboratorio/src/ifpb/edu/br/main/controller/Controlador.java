package ifpb.edu.br.main.controller;

import ifpb.edu.br.main.dao.Serializador;
import ifpb.edu.br.main.model.Disciplina;
import ifpb.edu.br.main.model.Professor;
import ifpb.edu.br.main.view.BlocoDeHorario;
import ifpb.edu.br.main.view.CalendarioSemanal;
import ifpb.edu.br.main.view.InfoBloco;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Controlador implements Serializable {
    private Dados dados;
    private Serializador serializador;
    private static Controlador instancia;
    private Professor usuarioLogado;
    private BlocoDeHorario blocoDeHorario;
    private CalendarioSemanal calendario;
    private InfoBloco infoBloco;

    private Controlador() {
        calendario = new CalendarioSemanal();
        serializador = new Serializador();
        dados = serializador.getDados();
    }

    public static Controlador getInstance() {
        if (instancia == null) {
            System.out.println("Criando nova instancia..." + new Date());
            instancia = new Controlador();
        }
        return instancia;
    }

    public void addProfessor(Professor p) {
        this.dados.getProfessoresList().add(p);
        serializador.salvar();
    }

    public void addDisciplina(Disciplina d) {
        this.dados.getDisciplinasList().add(d);
        serializador.salvar();
    }

    public boolean validarLogin(String matricula, String senha) {
        List<Professor> professoresAtualizados = dados.getProfessoresList();
        Optional<Professor> resultado = professoresAtualizados.stream()
                .filter(p -> p.getMatricula().equals(matricula) && p.getSenha().equals(senha))
                .findFirst();

        if (resultado.isPresent()) {
            usuarioLogado = resultado.get();
            return true;
        }
        return false;
    }

    public boolean matriculaExistente(String matricula) {
        return dados.getProfessoresList().stream()
                .anyMatch(professor -> professor.getMatricula().equals(matricula));
    }

    public boolean login(String matricula, String senha) {
        if (validarLogin(matricula, senha)) {
            return true;
        }
        return false;
    }

    public Professor getUsuarioLogado() {
        return usuarioLogado;
    }

    public List<Professor> getProfessores() {
        return dados.getProfessoresList();
    }

    public void setProfessores(List<Professor> professores) {
        this.dados.setProfessoresList(professores);
        serializador.salvar();
    }

    public List<Disciplina> getDisciplinas() {
        return dados.getDisciplinasList();
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.dados.setDisciplinasList(disciplinas);
        serializador.salvar();
    }

    public CalendarioSemanal getCalendario() {
        return calendario;
    }

    public void setCalendario(CalendarioSemanal calendario) {
        this.calendario = calendario;
        serializador.salvar();
    }

    public BlocoDeHorario getBlocoAtual() {
        if (calendario == null) {
            System.out.println("Calendário não inicializado.");
            return null;
        }
        return calendario.getBlocoAtual();
    }

    public void inicializarInfoBloco() {
        if (infoBloco == null) {
            infoBloco = new InfoBloco();
        }
    }

    public InfoBloco getInfoBloco(){
        return infoBloco;
    }

    public void setInfoBloco(InfoBloco infoBloco){
        this.infoBloco = infoBloco;
        serializador.salvar();
    }
}
