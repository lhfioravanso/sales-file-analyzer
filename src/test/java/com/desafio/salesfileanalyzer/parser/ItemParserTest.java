package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemParserTest {
    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        String[] lineData = new String[] { "1", "10", "100" };
        Item item = ItemParser.parse(lineData);

        assertEquals(1, item.getId());
        assertEquals(10, item.getQuantity());
        assertEquals(100, item.getPrice());
        assertEquals(1000, item.getTotal());
    }

    @Test
    void ShouldParseThrowsException() {
        String[] lineData = new String[] { "", "", "", "", ""};

        try {
            ItemParser.parse(lineData);
        } catch (InvalidLineDataSizeException e) {
            assertEquals("Quantidade de registros da linha inválido!", e.getMessage());
        }
    }
}