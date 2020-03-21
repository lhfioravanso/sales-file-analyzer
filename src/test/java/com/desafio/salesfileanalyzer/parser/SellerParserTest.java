package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Seller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerParserTest {

    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        String[] lineData = new String[] { "001", "1234567891234", "Teste", "100"};
        Seller seller = SellerParser.parse(lineData);

        assertEquals("1234567891234", seller.getCpf());
        assertEquals("Teste", seller.getName());
        assertEquals(100, seller.getSalary());
    }

    @Test
    void ShouldParseThrowsException() {
        String[] lineData = new String[] { "", "", "", "", ""};

        try {
            SellerParser.parse(lineData);
        } catch (InvalidLineDataSizeException e) {
            assertEquals("Quantidade de registros da linha inválido!", e.getMessage());
        }
    }
}