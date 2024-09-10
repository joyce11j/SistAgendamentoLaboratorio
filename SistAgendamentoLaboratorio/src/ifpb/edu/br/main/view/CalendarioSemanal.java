package ifpb.edu.br.main.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarioSemanal implements Serializable {
    private List<BlocoDeHorario> blocosDeHorario;
    private int semanaAtual;

    public CalendarioSemanal() {
        blocosDeHorario = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            blocosDeHorario.add(new BlocoDeHorario());
        }
        Calendar calendar = Calendar.getInstance();
        semanaAtual = calendar.get(3) - 1;
    }

    public BlocoDeHorario getBlocoAtual() {
        if (blocosDeHorario.isEmpty()) {
            System.out.println("Nenhum bloco de horário disponível.");
            return null;
        }
        return blocosDeHorario.get(semanaAtual);
    }

    public void semanaAnterior() {
        if (semanaAtual > 0) {
            --semanaAtual;
        }
    }

    public void proximaSemana() {
        if (semanaAtual < 51) {
            ++semanaAtual;
        }
    }

    public String[] getDiasDaSemana() {
        String[] dias = {"SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA"};
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.set(3, semanaAtual + 1);
        tempCalendar.set(7, 2);

        for (int i = 0; i < dias.length; i++) {
            String diaComNumero = dias[i] + " - " + tempCalendar.get(Calendar.DAY_OF_MONTH);
            dias[i] = diaComNumero;
            tempCalendar.add(5, 1);
        }

        return dias;
    }

    public void atualizarMes(int mes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, mes);
        calendar.set(5, 1);
        calendar.set(1, Calendar.getInstance().get(1));

        semanaAtual = calendar.get(3) - 1;
    }

    public void setSemanaAtual(int semana) {
        this.semanaAtual = semana;
    }

    public int getSemanaAtual() {
        return semanaAtual;
    }

    public void setPrimeiraSemanaDoMes(int mes) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.MONTH, mes);
        calendario.set(Calendar.DAY_OF_MONTH, 1);

        //Atualiza a semana atual para a primeira semana do mês
        this.setSemanaAtual(calendario.get(Calendar.WEEK_OF_YEAR));
    }
}
