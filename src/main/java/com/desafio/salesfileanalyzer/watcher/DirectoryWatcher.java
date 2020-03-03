package com.desafio.salesfileanalyzer.watcher;

import com.desafio.salesfileanalyzer.SalesfileanalyzerApplication;
import com.desafio.salesfileanalyzer.util.Constants;
import java.nio.file.*;

public class DirectoryWatcher {
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

            boolean valid = true;
            do {
                WatchKey watchKey = watchService.take();
                SalesfileanalyzerApplication salesfileanalyzerApplication = new SalesfileanalyzerApplication();

                for (WatchEvent event : watchKey.pollEvents()) {
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                        String fileName = event.context().toString();

                        if (validExtension(fileName)){
                            new Thread(() -> {
                                try {
                                    salesfileanalyzerApplication.processFile(pathToWatch, outputPath, fileName);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                    }
                }
                valid = watchKey.reset();

            } while (valid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean validExtension(String fileName)
    {
        return fileName.endsWith(Constants.FILE_EXTENSION_INPUT);
    }


}
