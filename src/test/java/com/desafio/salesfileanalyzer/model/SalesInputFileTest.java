package com.desafio.salesfileanalyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesInputFileTest {

    private SalesInputFile salesInputFile;

    @BeforeEach
    void setUp() {
        /*
            001ç1234567891234çPedroç50000
            001ç3245678865434çPauloç40000.99
            002ç2345675434544345çJose da SilvaçRural
            002ç2345675433444345çEduardo PereiraçRural
            003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
            003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
         */
        this.salesInputFile = new SalesInputFile("filename.dat");

        this.salesInputFile.addSeller("1234567891234", "Pedro", 50000);
        this.salesInputFile.addSeller("3245678865434", "Paulo", 40000.99);
        this.salesInputFile.addCustomer("2345675434544345", "Jose da Silva", "Rural");
        this.salesInputFile.addCustomer("2345675433444345", "Eduardo Pereira", "Rural");

        List<Item> itens = new ArrayList<>();
        itens.add(new Item(1,10,100));
        itens.add(new Item(2,30,2.50));
        itens.add(new Item(3,40,3.10));
        this.salesInputFile.addSale(10, itens, "Pedro");

        itens.clear();
        itens.add(new Item(1,34,10));
        itens.add(new Item(2,33,1.50));
        itens.add(new Item(3,40,0.10));
        this.salesInputFile.addSale(8, itens, "Paulo");
    }

    @Test
    void getFileName(){
        assertEquals(this.salesInputFile.getFileName(), "filename.dat");
    }
    @Test
    void getCountCustomers() {
        assertEquals(this.salesInputFile.getCountCustomers(), 2);
    }

    @Test
    void getCountSellers() {
        assertEquals(this.salesInputFile.getCountSellers(), 2);
    }

    @Test
    void getMostExpensiveSaleId() throws Exception {
        assertEquals(this.salesInputFile.getMostExpensiveSaleId(), 10);
    }

    @Test
    void getWorstSeller() throws Exception {
        assertEquals(this.salesInputFile.getWorstSeller(), "Paulo");
    }
}