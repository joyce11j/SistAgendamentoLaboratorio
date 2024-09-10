package ifpb.edu.br.main.dao;

import ifpb.edu.br.main.controller.Dados;

import java.io.*;

public class Serializador {
    private Dados dados;

    public Serializador() {
        File arquivo = new File("dados.bin");
        if (arquivo.exists()) {
            ler();
        } else {
            dados = new Dados();
            salvar();
        }
    }

    public Dados getDados() {
        return dados;
    }

    public void salvar() {
        try (FileOutputStream fos = new FileOutputStream("dados.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(dados);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void ler() {
        try (FileInputStream fis = new FileInputStream("dados.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            dados = (Dados) ois.readObject();
        } catch (EOFException e) {
            System.out.println("O arquivo de dados está incompleto ou corrompido.");
            dados = new Dados();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao ler os dados: " + e.getMessage());
            dados = new Dados();
        }
    }
}
