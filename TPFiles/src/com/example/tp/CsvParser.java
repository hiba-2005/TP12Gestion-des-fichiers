package com.example.tp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static List<Record> readCsv(String path) throws IOException {

        List<Record> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            // Lire la première ligne (en-tête) et la sauter
            line = br.readLine();
            // Si tu veux vérifier : System.out.println("Header = " + line);

            while ((line = br.readLine()) != null) {
                // id,name,score
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    continue; // ligne mal formée
                }

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double score = Double.parseDouble(parts[2]);

                result.add(new Record(id, name, score));
            }
        }

        return result;
    }

    public static void writeCsv(List<Record> records, String path) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            // Écrire l’en-tête
            bw.write("id,name,score");
            bw.newLine();

            // Écrire les enregistrements
            for (Record r : records) {
                bw.write(r.getId() + "," + r.getName() + "," + r.getScore());
                bw.newLine();
            }
        }
    }
}
