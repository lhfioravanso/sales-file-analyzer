package com.desafio.salesfileanalyzer.exception;

public class InvalidLineDataSizeException extends Exception {
    public InvalidLineDataSizeException(){
        super("Quantidade de registros da linha inválido!");
    }
}
