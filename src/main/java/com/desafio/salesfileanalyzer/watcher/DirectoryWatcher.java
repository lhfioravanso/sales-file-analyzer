package com.desafio.salesfileanalyzer.watcher;

import com.desafio.salesfileanalyzer.SalesfileanalyzerApplication;
import com.desafio.salesfileanalyzer.util.Constants;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectoryWatcher {

    private static Logger logger = Logger.getLogger(DirectoryWatcher.class.getName());

    private Path pathToWatch;
    private Path outputPath;

    public DirectoryWatcher(String inputPath, String outputPath) {
        this.pathToWatch = Paths.get(inputPath);
        this.outputPath = Paths.get(outputPath);
    }

    public void start() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            pathToWatch.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE);

            logger.log(Level.INFO, () -> "Observando o diretório: " + pathToWatch);

            boolean valid;
            do {
                WatchKey watchKey = watchService.take();
                SalesfileanalyzerApplication salesfileanalyzerApplication = new SalesfileanalyzerApplication();

                for (WatchEvent event : watchKey.pollEvents()) {
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                        String fileName = event.context().toString();

                        if (validExtension(fileName)){
                            new Thread(() -> {
                                try {
                                    preventSameFileUsage();
                                    salesfileanalyzerApplication.processFile(pathToWatch, outputPath, fileName);
                                } catch (Exception e) {
                                    logger.log(Level.SEVERE, e.getMessage());
                                }
                            }).start();
                        }
                    }
                }
                valid = watchKey.reset();

            } while (valid);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void preventSameFileUsage () throws InterruptedException {
        Thread.sleep(100);
    }

    private static boolean validExtension(String fileName)
    {
        return fileName.endsWith(Constants.FILE_EXTENSION_INPUT);
    }


}
