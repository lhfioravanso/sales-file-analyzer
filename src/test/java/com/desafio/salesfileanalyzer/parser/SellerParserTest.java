package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Seller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerParserTest {

    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        SellerParser parser = new SellerParser();
        String[] lineData = new String[] { "001", "1234567891234", "Teste", "100"};
        Seller seller = parser.parse(lineData);

        assertEquals(seller.getCpf(), "1234567891234");
        assertEquals(seller.getName(), "Teste");
        assertEquals(seller.getSalary(), 100);
    }

    @Test
    void ShouldParseThrowsException() {
        SellerParser parser = new SellerParser();
        String[] lineData = new String[] { "", "", "", "", ""};

        try {
            parser.parse(lineData);
        } catch (InvalidLineDataSizeException e) {
            assertEquals(e.getMessage(), "Quantidade de registros da linha inválido!");
        }
    }
}