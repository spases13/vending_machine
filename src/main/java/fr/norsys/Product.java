package fr.norsys;

public class Product {

    public enum ProductType {
        WATER,
        COCA,
        TWIX,
        BUENO
    }

    private ProductType name;
    private int price;
    private int initialStock;

    Product(ProductType name, int price, int initialStock) {
        this.name = name;
        this.price = price;
        this.initialStock = initialStock;
    }

    public ProductType getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getInitialStock() {
        return initialStock;
    }
}