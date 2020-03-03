package com.desafio.salesfileanalyzer.reader;

import com.desafio.salesfileanalyzer.model.InputFile;
import com.desafio.salesfileanalyzer.parser.IFileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {

    private IFileParser fileParser;

    public InputFileReader(IFileParser parser){
        this.fileParser = parser;
    }

    public InputFile read(Path filePath, String fileName) {
        InputFile inputFile = null;

        List<String> lines = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath.resolve(fileName).toString(), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            if(lines.size() > 0){
                inputFile = fileParser.parseFile(fileName, lines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputFile;
    }

}
