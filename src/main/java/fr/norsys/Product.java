package fr.norsys;

public class Product {
    private String name;
    private int price;
    private int initialStock;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.initialStock = 0;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getInitialStock() {
        return initialStock;
    }
}