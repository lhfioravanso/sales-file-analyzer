package com.desafio.salesfileanalyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private Sale sale;
    @BeforeEach
    void setUp() {
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(1, 10, 100));
        this.sale = new Sale(1, itens, "Pedro");
    }

    @Test
    void getId() {
        assertEquals(this.sale.getId(), 1);
    }

    @Test
    void setId() {
        this.sale.setId(2);
        assertEquals(this.sale.getId(), 2);
    }

    @Test
    void getItensCount() {
        assertEquals(this.sale.getItens().size(), 1);
    }

    @Test
    void setItens() {
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(1, 1, 10));
        itens.add(new Item(1, 3, 15));
        this.sale.setItens(itens);

        assertEquals(this.sale.getItens().size(), 2);
        assertEquals(this.sale.getItens().get(1).getPrice(), 15);
    }

    @Test
    void getSellerName() {
        assertEquals(this.sale.getSellerName(), "Pedro");
    }

    @Test
    void setSellerName() {
        this.sale.setSellerName("Test");
        assertEquals(this.sale.getSellerName(), "Test");
    }

    @Test
    void getTotal() {
        assertEquals(this.sale.getTotal(), 1000);
    }
}