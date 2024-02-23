package fr.norsys;

public class Product {

    public enum ProductName {
        WATER,
        COCA,
        TWIX,
        BUENO
    }

    private ProductName name;
    private int price;
    private int initialStock;

    Product(ProductName name, int price, int initialStock) {
        this.name = name;
        this.price = price;
        this.initialStock = initialStock;
    }

    public ProductName getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getInitialStock() {
        return initialStock;
    }
}