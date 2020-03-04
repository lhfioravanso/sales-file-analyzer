package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Sale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaleParserTest {

    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        SaleParser parser = new SaleParser();
        String[] lineData = new String[] { "003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"};
        Sale sale = parser.parse(lineData);

        assertEquals(sale.getId(), 10);
        assertEquals(sale.getItens().size(), 3);
        assertEquals(sale.getSellerName(), "Pedro");
        assertEquals(sale.getItens().size(), 3);
        assertEquals(sale.getTotal(), 1199.0);
    }

    @Test
    void ShouldParseThrowsException() {
        SaleParser parser = new SaleParser();
        String[] lineData = new String[] { "", "", "", "", ""};

        try {
            parser.parse(lineData);
        } catch (InvalidLineDataSizeException e) {
            assertEquals(e.getMessage(), "Quantidade de registros da linha inválido!");
        }
    }
}