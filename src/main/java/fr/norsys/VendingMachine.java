package fr.norsys;

import java.util.ArrayList;
import java.util.List;

import fr.norsys.Product.ProductType;

public class VendingMachine {
    private Product[] products;
    private int[] stock;
    private int balance = 20;
    private int exchange = 0;
    private Product selectedProduct = null;
    private List<Integer> introducedMoney = new ArrayList<>();

    public enum Coin {
        DIRHAM_1(1),
        DIRHAM_2(2),
        DIRHAM_5(5),
        DIRHAM_10(10);

        private final int value;

        Coin(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    VendingMachine(Product[] products, int[] initialStock) {
        this.products = products;
        this.stock = new int[initialStock.length];
        System.arraycopy(initialStock, 0, this.stock, 0, initialStock.length);
    }

    public void displayProducts() {
        System.out.println("Available Products:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].getName() + " - Price: " + products[i].getPrice());
        }
    }

    public void selectProduct(ProductType productType) {
        selectedProduct = null;

        for (int i = 0; i < products.length; i++) {
            if (products[i].getName() == productType) {
                if (stock[i] > 0) {
                    selectedProduct = products[i];
                    System.out.println("Selected Product: " + selectedProduct.getName());
                    System.out.println("Price: " + selectedProduct.getPrice());
                    return;
                } else {
                    System.out.println("Selected product is out of stock.");
                    return;
                }
            }
        }

        System.out.println("Invalid product selection.");
    }

    public void introduceMoney(Coin money) {
        System.out.println("money introduced : " + money.value);
        introducedMoney.add(money.getValue());
        balance += money.getValue();

        // System.out.println("-------------------------");
        // System.out.println("BALANCE " + balance);
        System.out.println("INTRODUCED TOTAL : " + getSumIntroducedMoney());
        System.out.println("-------------------------");
    }

    public boolean makePayment() {
        if (selectedProduct != null && balance >= selectedProduct.getPrice()) {
            int change = balance - selectedProduct.getPrice();
            if (change > 0) {
                System.out.println("Payment successful. Enjoy your " + selectedProduct.getName() +
                        ". Don't forget your change: " + change + " Dirhams");
            } else {
                System.out.println("Payment successful. Enjoy your " + selectedProduct.getName());
            }

            stock[getIndex(selectedProduct)]--;
            resetAfterPayment();
            return true;
        } else {
            System.out.println("Payment failed. Not enough money or no product selected.");
            return false;
        }
    }

    public boolean takeRefund() {
        if (balance > 0) {
            System.out.println("Refunding: " + balance);
            resetAfterPayment();
            return true;
        } else {
            System.out.println("No refund available.");
            return false;
        }
    }

    public void resetVendingMachine() {
        for (int i = 0; i < stock.length; i++) {
            stock[i] = products[i].getInitialStock();
        }
        System.out.println("Vending machine has been reset.");
    }

    public int getSumIntroducedMoney() {
        return introducedMoney.stream().mapToInt(Integer::intValue).sum();
    }

    private void resetAfterPayment() {
        selectedProduct = null;
        introducedMoney.clear();
        balance = 0;
    }

    private int getIndex(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].equals(product)) {
                return i;
            }
        }
        return -1;
    }

    public Product getSelectedProduct() {
        if (selectedProduct != null) {
            return selectedProduct;
        } else {
            return null;
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getExchange() {
        return this.exchange;
    }

    public void setExchange(int exchange) {
        this.exchange = exchange;
    }

    public void performBuyingAction() {
        if (this.getSumIntroducedMoney() >= this.getSelectedProduct().getPrice()) {
            System.out.println("Payment successful. Enjoy your " + this.getSelectedProduct().getName());
            System.out.println("Introduced : " + this.getSumIntroducedMoney());
            System.out.println("Price : " + this.getSelectedProduct().getPrice());
            this.setExchange(
                    this.getSumIntroducedMoney() - this.getSelectedProduct().getPrice());
            System.out.println("Your exchange is " + this.getExchange());
        }
        else { 
            System.out.println("Not enough , insert more");
        }
    }
}