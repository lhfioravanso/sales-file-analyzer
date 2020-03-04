package com.desafio.salesfileanalyzer.exception;

public class InvalidLayoutException extends Exception {
    public InvalidLayoutException(){
        super("Layout do arquivo inválido!");
    }
}
