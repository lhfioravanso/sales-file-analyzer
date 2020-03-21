package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLayoutException;
import com.desafio.salesfileanalyzer.model.SalesInputFile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesFileParserTest {

    @Test
    void ShouldParseFileSuccessful() throws Exception {
        SalesFileParser parser = new SalesFileParser();

        List<String> lines =
                Arrays.asList(
                "001ç1234567891234çPedroç50000",
                "001ç3245678865434çPauloç40000.99" ,
                "002ç2345675434544345çJose da SilvaçRural" ,
                "002ç2345675433444345çEduardo PereiraçRural" ,
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro" ,
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");

        SalesInputFile file = parser.parseFile("test.dat", lines);

        assertEquals(2, file.getCountCustomers());
        assertEquals(2, file.getCountSellers());
        assertEquals(10, file.getMostExpensiveSale().getId());
        assertEquals("Paulo", file.getWorstSeller());
        assertEquals("test.dat", file.getFileName());
    }

    @Test
    void ShouldThrowsInvalidLayoutException() throws Exception{
        SalesFileParser parser = new SalesFileParser();
        List<String> lines =
                Arrays.asList(
                        "004ç1234567891234çPedroç50000");

        try {
            parser.parseFile("test.dat", lines);
        } catch (InvalidLayoutException e) {
            assertEquals("Layout do arquivo inválido!", e.getMessage());
        }
    }

    @Test
    void ShouldThrowsInvalidLayoutException2() throws Exception {
        SalesFileParser parser = new SalesFileParser();

        List<String> lines =
                Arrays.asList(
                        "001-1234567891234çPedroç50000");

        try {
            parser.parseFile("test.dat", lines);
        } catch (InvalidLayoutException e) {
            assertEquals("Layout do arquivo inválido!", e.getMessage());
        }
    }
}