package com.desafio.salesfileanalyzer.model;

public abstract class InputFile {

    public String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public abstract String generateReport();
}
