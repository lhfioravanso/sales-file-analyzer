package com.desafio.salesfileanalyzer.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryUtil {

    public static void createIfNotExists(String directoryPath) throws IOException {

        Path path = Paths.get(directoryPath);

        if (!Files.exists(path))
            Files.createDirectory(path);
    }

}
