package com.desafio.salesfileanalyzer.writer;

import com.desafio.salesfileanalyzer.model.OutputFile;
import com.desafio.salesfileanalyzer.util.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputFileWriter {

    private Path outputPath;
    private static Logger logger = Logger.getLogger(OutputFileWriter.class.getName());

    public OutputFileWriter(Path outputPath){
        this.outputPath = outputPath;
    }

    public void write(OutputFile outputFile) {

        final String fileName = generateFileName(outputFile.getFileName());

        try (FileWriter writer = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter(writer)) {
                bw.write(outputFile.getContent());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }


    private String generateFileName(String fileName){
        fileName = fileName.replace(Constants.FILE_EXTENSION_INPUT, Constants.FILE_EXTENSION_OUPUT);
        return outputPath.resolve(fileName).toString();
    }


}
