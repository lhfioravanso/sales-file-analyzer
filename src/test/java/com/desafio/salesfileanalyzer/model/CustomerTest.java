package com.desafio.salesfileanalyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        this.customer = new Customer("2345675434544345", "Jose da Silva", "Rural");
    }

    @Test
    void getCnpj() {
        assertEquals(this.customer.getCnpj(), "2345675434544345");
    }

    @Test
    void setCnpj() {
        this.customer.setCnpj("12345");
        assertEquals(this.customer.getCnpj(), "12345");
    }

    @Test
    void getName() {
        assertEquals(this.customer.getName(), "Jose da Silva");
    }

    @Test
    void setName() {
        this.customer.setName("Teste");
        assertEquals(this.customer.getName(), "Teste");
    }

    @Test
    void getBusinessArea() {
        assertEquals(this.customer.getBusinessArea(), "Rural");
    }

    @Test
    void setBusinessArea() {
        this.customer.setBusinessArea("TI");
        assertEquals(this.customer.getBusinessArea(), "TI");
    }

}