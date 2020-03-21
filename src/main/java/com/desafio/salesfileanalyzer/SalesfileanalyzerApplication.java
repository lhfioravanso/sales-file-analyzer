package com.desafio.salesfileanalyzer;

import com.desafio.salesfileanalyzer.model.InputFile;
import com.desafio.salesfileanalyzer.model.OutputFile;
import com.desafio.salesfileanalyzer.parser.SalesFileParser;
import com.desafio.salesfileanalyzer.reader.InputFileReader;
import com.desafio.salesfileanalyzer.util.Constants;
import com.desafio.salesfileanalyzer.util.DirectoryUtil;
import com.desafio.salesfileanalyzer.watcher.DirectoryWatcher;
import com.desafio.salesfileanalyzer.writer.OutputFileWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
public class SalesfileanalyzerApplication {

	private static Logger logger = Logger.getLogger(SalesfileanalyzerApplication.class.getName());
	private static String inputPath;
	private static String outputPath;

	public static void main(String[] args) {
		SpringApplication.run(SalesfileanalyzerApplication.class, args);

		configurePaths();
		createDirectoriesIfNotExists();

		DirectoryWatcher directoryWatcher = new DirectoryWatcher(inputPath, outputPath);
		directoryWatcher.start();
	}

	private static void configurePaths(){
		String homePath = System.getProperty("user.home");

		inputPath = String.format(Constants.INPUT_PATH, homePath);
		outputPath = String.format(Constants.OUTPUT_PATH, homePath);
	}

	private static void createDirectoriesIfNotExists(){
		try {
			DirectoryUtil.createIfNotExists(inputPath);
			DirectoryUtil.createIfNotExists(outputPath);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Não foi possível criar os diretórios de entrada/saida.", e);
		}
	}

	public void processFile(Path inputPath, Path outputPath, String fileName) {
		logger.log(Level.INFO, () -> "Processando o arquivo " + fileName + " ...");

		SalesFileParser salesFileParser = new SalesFileParser();
		InputFileReader inputFileReader = new InputFileReader(salesFileParser);

		InputFile inputFile = inputFileReader.read(inputPath, fileName);
		String outputReport = inputFile.generateReport();

		OutputFileWriter outputFileWriter = new OutputFileWriter(outputPath);
		OutputFile outputFile = new OutputFile(fileName, outputReport);
		outputFileWriter.write(outputFile);

		logger.log(Level.INFO, () -> "O arquivo '"+ fileName +"' foi processado com sucesso!");
	}
}
