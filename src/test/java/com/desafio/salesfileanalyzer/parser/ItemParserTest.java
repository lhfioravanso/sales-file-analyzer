package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemParserTest {
    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        ItemParser parser = new ItemParser();
        String[] lineData = new String[] { "1", "10", "100" };
        Item item = parser.parse(lineData);

        assertEquals(item.getId(), 1);
        assertEquals(item.getQuantity(), 10);
        assertEquals(item.getPrice(), 100);
        assertEquals(item.getTotal(), 1000);
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