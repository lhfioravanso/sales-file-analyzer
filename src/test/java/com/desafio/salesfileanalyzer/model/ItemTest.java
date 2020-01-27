package com.desafio.salesfileanalyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        this.item = new Item(1, 5, 10);
    }

    @Test
    void getId() {
        assertEquals(this.item.getId(), 1);
    }

    @Test
    void setId() {
        this.item.setId(2);
        assertEquals(this.item.getId(), 2);
    }

    @Test
    void getQuantity() {
        assertEquals(this.item.getQuantity(), 5);
    }

    @Test
    void setQuantity() {
        this.item.setQuantity(10);
        assertEquals(this.item.getQuantity(), 10);
    }

    @Test
    void getPrice() {
        assertEquals(this.item.getPrice(), 10);
    }

    @Test
    void setPrice() {
        this.item.setPrice(15);
        assertEquals(this.item.getPrice(), 15);
    }

    @Test
    void getTotal() {
        assertEquals(this.item.getTotal(), 50);
    }
}