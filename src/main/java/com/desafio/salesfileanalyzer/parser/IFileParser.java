package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLayoutException;
import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.InputFile;

import java.util.List;

public interface IFileParser {
    InputFile parseFile(String fileName, List<String> lines) throws InvalidLineDataSizeException, InvalidLayoutException;
}
