package com.desafio.salesfileanalyzer;

import com.desafio.salesfileanalyzer.model.InputData;
import com.desafio.salesfileanalyzer.reader.InputFileReader;
import com.desafio.salesfileanalyzer.util.Constants;
import com.desafio.salesfileanalyzer.util.DirectoryUtil;
import com.desafio.salesfileanalyzer.watcher.DirectoryWatcher;
import com.desafio.salesfileanalyzer.writer.OutputFileWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Path;


@SpringBootApplication
public class SalesfileanalyzerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalesfileanalyzerApplication.class, args);

		String homePath = System.getProperty("user.home");
		String inputPath = String.format(Constants.INPUT_PATH, homePath);
		String outputPath = String.format(Constants.OUTPUT_PATH, homePath);

		try {
			DirectoryUtil.createIfNotExists(inputPath);
			DirectoryUtil.createIfNotExists(outputPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DirectoryWatcher directoryWatcher = new DirectoryWatcher(inputPath, outputPath);

		directoryWatcher.start();

	}

	public void processFile(Path inputPath, Path outputPath, String fileName) throws Exception {
		System.out.println("Processando o arquivo '" + fileName + "'...");

		InputFileReader inputFileReader = new InputFileReader();
		OutputFileWriter outputFileWriter = new OutputFileWriter();

		InputData inputData = inputFileReader.read(inputPath, fileName);
		outputFileWriter.write(outputPath, inputData);

		System.out.println("O arquivo '" + fileName + "' foi processado com sucesso!");
	}
}
