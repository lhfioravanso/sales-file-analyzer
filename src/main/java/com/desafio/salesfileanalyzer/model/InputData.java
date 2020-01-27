package com.desafio.salesfileanalyzer.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class InputData {

    private List<Customer> Customers;
    private List<Seller> Sellers;
    private List<Sale> Sales;
    private String fileName;

    public InputData(String fileName) {
        this.fileName = fileName;
    }

    public void addCustomer(String cnpj, String name, String businessArea)
    {
        if (this.Customers == null)
            this.Customers = new ArrayList<>();

        this.Customers.add(new Customer(cnpj, name, businessArea));
    }

    public void addSeller(String cpf, String name, double salary)
    {
        if (this.Sellers == null)
            this.Sellers = new ArrayList<>();

        this.Sellers.add(new Seller(cpf, name, salary));
    }

    public void addSale(int saleId, List<Item> itens, String salesmanName)
    {
        if (this.Sales == null)
            this.Sales = new ArrayList<>();

        this.Sales.add(new Sale(saleId, itens, salesmanName ));
    }


    public int getCountCustomers()
    {
        return this.Customers != null ? this.Customers.size() : 0;
    }

    public int getCountSellers()
    {
        return this.Sellers != null ? this.Sellers.size() : 0;
    }

    public int getMostExpensiveSaleId() throws Exception {
        if (this.Sales.isEmpty()){
            throw new Exception("Nenhuma venda encontrada!");
        }

        return this.Sales.
                stream().
                max(Comparator.comparing(Sale::getTotal)).
                get().
                getId();
    }

    public String getWorstSeller() throws Exception {
        /*
         * Retorna o pior vendedor baseado no total de vendas
         */
        if (this.Sales.isEmpty())
            throw new Exception("Nenhuma venda encontrada!");

        Map<String, Double> sumSalesBySeller =
                Sales.stream().collect(
                        Collectors.groupingBy(Sale::getSellerName,
                                Collectors.summingDouble(Sale::getTotal)));

        return sumSalesBySeller.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).get().getKey();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
