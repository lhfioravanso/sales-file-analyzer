package com.desafio.salesfileanalyzer.util;

public final class Constants {

    private Constants(){
        throw new IllegalStateException();
    }

    public static final String FILE_EXTENSION_INPUT = ".dat";
    public static final String FILE_EXTENSION_OUPUT = ".done.dat";
    public static final String SPLIT_CHAR_ITENS = ",";
    public static final String SPLIT_CHAR_ITENS_LINE = "-";
    public static final String INPUT_PATH = "%s/data/in";
    public static final String OUTPUT_PATH = "%s/data/out";
}
