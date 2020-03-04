package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerParserTest {

    @Test
    void ShouldParseSuccessful() throws InvalidLineDataSizeException {
        CustomerParser parser = new CustomerParser();
        String[] lineData = new String[] { "002", "12345", "Teste", "Teste2"};
        Customer customer = parser.parse(lineData);

        assertEquals(customer.getCnpj(), "12345");
        assertEquals(customer.getName(), "Teste");
        assertEquals(customer.getBusinessArea(), "Teste2");
    }

    @Test
    void ShouldParseThrowsException() {
        CustomerParser parser = new CustomerParser();
        String[] lineData = new String[] { "", "", "", "", ""};

        try {
            parser.parse(lineData);
        } catch (InvalidLineDataSizeException e) {
            assertEquals(e.getMessage(), "Quantidade de registros da linha inválido!");
        }
    }
}