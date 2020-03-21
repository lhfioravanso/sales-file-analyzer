package com.desafio.salesfileanalyzer.model;

import java.util.*;
import java.util.stream.Collectors;


public class SalesInputFile extends InputFile {

    private List<Customer> customers;
    private List<Seller> sellers;
    private List<Sale> sales;

    public SalesInputFile(String fileName) {
        super.setFileName(fileName);
    }

    public void addCustomer(Customer customer)
    {
        if (this.customers == null)
            this.customers = new ArrayList<>();

        this.customers.add(customer);
    }

    public void addSeller(Seller seller){
        if (this.sellers == null)
            this.sellers = new ArrayList<>();

        this.sellers.add(seller);
    }

    public void addSale(Sale sale)
    {
        if (this.sales == null)
            this.sales = new ArrayList<>();

        this.sales.add(sale);
    }

    public int getCountCustomers()
    {
        return this.customers != null ? this.customers.size() : 0;
    }

    public int getCountSellers()
    {
        return this.sellers != null ? this.sellers.size() : 0;
    }

    public Sale getMostExpensiveSale() {
        Sale sale = null;

        if (!this.sales.isEmpty())
            sale = this.sales.stream().max(Comparator.comparing(Sale::getTotal)).orElse(null);

        return sale;
    }

    public String getWorstSeller() {
        /*
         * Retorna o pior vendedor baseado no total de vendas
         */
        String worstSeller = null;

        if (!this.sales.isEmpty()) {
            Map<String, Double> sumSalesBySeller =
                    sales.stream().collect(
                            Collectors.groupingBy(Sale::getSellerName,
                                    Collectors.summingDouble(Sale::getTotal)));

            worstSeller = Objects.requireNonNull(sumSalesBySeller.entrySet().
                    stream().min(Map.Entry.comparingByValue()).orElse(null)).getKey();
        }

        return worstSeller;
    }

    public List<String> createOutputData() {
        List<String> outputData = new ArrayList<>();

        outputData.add("Quantidade de clientes no arquivo de entrada: " + this.getCountCustomers());
        outputData.add("Quantidade de vendedor no arquivo de entrada: " + this.getCountSellers());

        Sale mostExpensiveSale = this.getMostExpensiveSale();
        outputData.add(String.format("ID da venda mais cara: %s",
                mostExpensiveSale != null ? mostExpensiveSale.getId() : "Arquivo não possui vendas para calcular esta informação."));

        String worstSeller = this.getWorstSeller();
        outputData.add(String.format("O pior vendedor: %s",
                worstSeller != null ? worstSeller : "Arquivo não possui vendas para calcular esta informação."));

        return outputData;
    }

    @Override
    public String generateReport() {
        List<String> outputData = createOutputData();

        StringBuilder sb = new StringBuilder();
        for (String data: outputData) {
            sb.append(data);
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }
}
