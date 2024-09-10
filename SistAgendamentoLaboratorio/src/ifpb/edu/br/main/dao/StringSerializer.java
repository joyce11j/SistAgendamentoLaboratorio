package ifpb.edu.br.main.dao;

import java.io.*;

public class StringSerializer implements Serializable{
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("text.txt", true)) {
            writer.write("def" + System.getProperty("line.separator"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileReader reader = new FileReader("text.txt");
             BufferedReader bufReader = new BufferedReader(reader)) {

            bufReader.lines().forEach(System.out::println);

            String line;
            while ((line = bufReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
