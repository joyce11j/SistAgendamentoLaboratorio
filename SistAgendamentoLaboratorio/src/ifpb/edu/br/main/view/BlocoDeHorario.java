package ifpb.edu.br.main.view;

import java.io.Serializable;

public class BlocoDeHorario implements Serializable {
    private boolean[][] bloco = new boolean[11][5];
    private InfoBloco infoBloco = new InfoBloco();

    public BlocoDeHorario() {
    }

    public InfoBloco getInfoBloco() {
        return this.infoBloco;
    }

    public void setInfoBloco(InfoBloco infoBloco) {
        this.infoBloco = infoBloco;
    }

    public boolean getHorario(int linha, int coluna) {
        return this.bloco[linha][coluna];
    }

    public void setHorario(int linha, int coluna, boolean ocupado) {
        this.bloco[linha][coluna] = ocupado;
    }
}
