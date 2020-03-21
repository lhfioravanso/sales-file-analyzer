package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerParserTest {

    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        String[] lineData = new String[] { "002", "12345", "Teste", "Teste2"};
        Customer customer = CustomerParser.parse(lineData);

        assertEquals("12345", customer.getCnpj());
        assertEquals("Teste", customer.getName());
        assertEquals("Teste2", customer.getBusinessArea());
    }

    @Test
    void ShouldParseThrowsException() {
        String[] lineData = new String[] { "", "", "", "", ""};

        try {
            CustomerParser.parse(lineData);
        } catch (InvalidLineDataSizeException e) {
            assertEquals("Quantidade de registros da linha inválido!", e.getMessage());
        }
    }
}