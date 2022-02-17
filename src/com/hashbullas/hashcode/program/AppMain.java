package com.hashbullas.hashcode.program;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.hashbullas.hashcode.files.FileUtils;
import com.hashbullas.hashcode.parser.Parser;

class ClientPreferences {

    public Set<String> likedIngredients;
    public Set<String> dislikedIngredients;

    public ClientPreferences() {
        likedIngredients = new HashSet<>();
        dislikedIngredients = new HashSet<>();
    }
}

public class AppMain {

    public static String[] problemsToParse = new String[]{
            "a_an_example.in.txt",
            "b_basic.in.txt",
            "c_coarse.in.txt",
            "d_difficult.in.txt",
            "e_elaborate.in.txt",
    };

    public static List<ClientPreferences> clientPreferences;

    public static void main(String[] args) throws Exception {

        for (String problem : problemsToParse) {
            processFile(problem);
        }

        Path outDir = Paths.get(System.getenv("userprofile")).resolve("hashcode-results");
        Desktop.getDesktop().open(outDir.toFile());
    }

    private static void processFile(String fileName) throws IOException {

        String problemInput = FileUtils.readFile(fileName);

        Parser p = new Parser(problemInput);

        var nClients = p.valueAt(0, 0, Integer.class);

        clientPreferences = new ArrayList<>(nClients);

        for (int i = 0; i < nClients; i++) {

            var firstRow = 2 * i + 1;
            var secondRow = 2 * i + 2;

            var client = new ClientPreferences();

            var nLikedIngredients = p.valueAt(firstRow, 0, Integer.class);

            List<String> likedIngredients = new ArrayList<>(nLikedIngredients);

            for (int j = 0; j < nLikedIngredients; j++) {
                likedIngredients.add(p.valueAt(firstRow, j + 1, String.class));
            }

            client.likedIngredients = new HashSet<>(likedIngredients);

            var nDislikedIngredients = p.valueAt(secondRow, 0, Integer.class);

            List<String> dislikedIngredients = new ArrayList<>(nDislikedIngredients);

            for (int j = 0; j < nDislikedIngredients; j++) {
                dislikedIngredients.add(p.valueAt(secondRow, j + 1, String.class));
            }

            client.dislikedIngredients = new HashSet<>(dislikedIngredients);

            clientPreferences.add(client);
        }

        var pizzaIngredients = new HashSet<>();

        // TODO program logic
        // ...

        String solution = "";
        solution += pizzaIngredients.size();
        solution += " ";
        solution += String.join(" ", pizzaIngredients.toArray(new String[]{}));

        writeSolutionToFile(fileName, solution);
    }

    private static void writeSolutionToFile(String inputFileName, String solution) throws IOException {

        Path outPath = Paths.get(System.getenv("userprofile")).resolve("hashcode-results")
                .resolve(inputFileName.substring(0, inputFileName.length() - 7) + ".out");

        FileUtils.writeFile(outPath, solution);

        System.out.println("Generated file: " + outPath.toFile().getAbsolutePath());
    }
}