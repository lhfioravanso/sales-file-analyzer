package com.desafio.salesfileanalyzer.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SalesInputFile extends InputFile {

    private List<Customer> Customers;
    private List<Seller> Sellers;
    private List<Sale> Sales;

    public SalesInputFile(String fileName) {
        super.setFileName(fileName);
    }

    public void addCustomer(Customer customer)
    {
        if (this.Customers == null)
            this.Customers = new ArrayList<>();

        this.Customers.add(customer);
    }

    public void addSeller(Seller seller){
        if (this.Sellers == null)
            this.Sellers = new ArrayList<>();

        this.Sellers.add(seller);
    }

    public void addSale(Sale sale)
    {
        if (this.Sales == null)
            this.Sales = new ArrayList<>();

        this.Sales.add(sale);
    }

    public int getCountCustomers()
    {
        return this.Customers != null ? this.Customers.size() : 0;
    }

    public int getCountSellers()
    {
        return this.Sellers != null ? this.Sellers.size() : 0;
    }

    public Sale getMostExpensiveSale() {
        Sale sale = null;

        if (this.Sales != null)
            sale = this.Sales.stream().
                    max(Comparator.comparing(Sale::getTotal)).
                    get();

        return sale;
    }

    public String getWorstSeller() {
        /*
         * Retorna o pior vendedor baseado no total de vendas
         */
        String worstSeller = null;

        if (this.Sales != null) {
            Map<String, Double> sumSalesBySeller =
                    Sales.stream().collect(
                            Collectors.groupingBy(Sale::getSellerName,
                                    Collectors.summingDouble(Sale::getTotal)));

            worstSeller = sumSalesBySeller.entrySet().
                    stream().min(Comparator.comparing(Map.Entry::getValue)).get().getKey();
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
