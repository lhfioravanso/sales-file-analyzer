package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Sale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaleParserTest {

    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        String[] lineData = new String[] { "003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"};
        Sale sale = SaleParser.parse(lineData);

        assertEquals(10, sale.getId());
        assertEquals(3, sale.getItens().size());
        assertEquals("Pedro", sale.getSellerName());
        assertEquals(3, sale.getItens().size());
        assertEquals(1199.0, sale.getTotal());
    }

    @Test
    void ShouldParseThrowsException() {
        String[] lineData = new String[] { "", "", "", "", ""};

        try {
            SaleParser.parse(lineData);
        } catch (InvalidLineDataSizeException e) {
            assertEquals("Quantidade de registros da linha inválido!", e.getMessage());
        }
    }
}