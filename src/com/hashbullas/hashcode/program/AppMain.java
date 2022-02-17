package com.hashbullas.hashcode.program;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.hashbullas.hashcode.files.FileUtils;
import com.hashbullas.hashcode.parser.Parser;

public class AppMain {

    public static String[] problemsToParse = new String[]{
            "a_example.in",
            "b_small.in",
            "c_medium.in",
            "d_quite_big.in",
            "e_also_big.in",
    };

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

        // TODO parse logic
        // ...

        // TODO program logic
        // ...

        // TODO generate solution
        // ...
        String solution = "";

        writeSolutionToFile(fileName, solution);
    }

    private static void writeSolutionToFile(String inputFileName, String solution) throws IOException {

        Path outPath = Paths.get(System.getenv("userprofile")).resolve("hashcode-results")
                .resolve(inputFileName.substring(0, inputFileName.length() - 3) + ".out");

        FileUtils.writeFile(outPath, solution);

        System.out.println("Generated file: " + outPath.toFile().getAbsolutePath());
    }
}