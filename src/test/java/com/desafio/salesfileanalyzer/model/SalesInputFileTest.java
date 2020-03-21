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

        this.salesInputFile.addSeller(new Seller("1234567891234", "Pedro", 50000));
        this.salesInputFile.addSeller(new Seller("3245678865434", "Paulo", 40000.99));
        this.salesInputFile.addCustomer(new Customer("2345675434544345", "Jose da Silva", "Rural"));
        this.salesInputFile.addCustomer(new Customer("2345675433444345", "Eduardo Pereira", "Rural"));

        List<Item> itens = new ArrayList<>();
        itens.add(new Item(1,10,100));
        itens.add(new Item(2,30,2.50));
        itens.add(new Item(3,40,3.10));

        this.salesInputFile.addSale(new Sale(10, itens, "Pedro"));

        itens.clear();
        itens.add(new Item(1,34,10));
        itens.add(new Item(2,33,1.50));
        itens.add(new Item(3,40,0.10));
        this.salesInputFile.addSale(new Sale(8, itens, "Paulo"));
    }

    @Test
    void getFileName(){
        assertEquals("filename.dat", this.salesInputFile.getFileName());
    }
    @Test
    void getCountCustomers() {
        assertEquals(2, this.salesInputFile.getCountCustomers());
    }

    @Test
    void getCountSellers() {
        assertEquals(2, this.salesInputFile.getCountSellers());
    }

    @Test
    void getMostExpensiveSaleId() {
        assertEquals(10, this.salesInputFile.getMostExpensiveSale().getId());
    }

    @Test
    void getWorstSeller() {
        assertEquals("Paulo", this.salesInputFile.getWorstSeller());
    }
}