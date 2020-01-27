package com.desafio.salesfileanalyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    private Seller seller;

    @BeforeEach
    void setUp() {
        this.seller = new Seller("1234567891234","Pedro", 50000);
    }

    @Test
    void testGetCpf() {
        assertEquals(this.seller.getCpf(), "1234567891234");
    }

    @Test
    void testSetCpf() {
        this.seller.setCpf("12345");
        assertEquals(this.seller.getCpf(), "12345");
    }

    @Test
    void getName() {
        assertEquals(this.seller.getName(), "Pedro");
    }

    @Test
    void setName() {
        this.seller.setName("Test");
        assertEquals(this.seller.getName(), "Test");
    }

    @Test
    void getSalary() {
        assertEquals(this.seller.getSalary(), 50000);
    }

    @Test
    void setSalary() {
        this.seller.setSalary(1000);
        assertEquals(this.seller.getSalary(), 1000);
    }
}