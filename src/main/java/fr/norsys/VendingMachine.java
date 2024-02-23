package fr.norsys;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private Product[] products;
    private int[] stock;
    private int solde = 50;
    static List<Integer> arrayOfIntroducedMoney = new ArrayList<>();
    private Product selectedProduct;
    private int change;

    enum Coin {
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

    public int getSumIntroducedMoney() {
        int sum = 0;
        for (int money : arrayOfIntroducedMoney) {
            sum += money;
        }
        return sum;
    }

    public void introduceMoney(Coin money) {
        arrayOfIntroducedMoney.add(money.value);
        System.out.println("introduceMoneySum = " + getSumIntroducedMoney());
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
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

    public void selectProduct(int index) {
        selectedProduct = null; // Reset selectedProduct
        change = 0; // Reset change
    
        if (index >= 1 && index <= products.length) {
            if (stock[index - 1] > 0) {
                selectedProduct = products[index - 1];
                System.out.println("Selected Product: " + selectedProduct.getName());
                System.out.println("Price: " + selectedProduct.getPrice());
                stock[index - 1]--;
            } else {
                System.out.println("Selected product is out of stock.");
            }
        } else {
            System.out.println("Invalid product selection.");
        }
    }

    public boolean makePayment() {
        if (selectedProduct != null && getSumIntroducedMoney() >= selectedProduct.getPrice()) {
            change = getSumIntroducedMoney() - selectedProduct.getPrice();
            if (change > 0) {
                System.out.println("Payment successful. Enjoy your " + selectedProduct.getName() +
                        ". Don't forget your change: " + change + " Dirhams");
            } else {
                System.out.println("Payment successful. Enjoy your " + selectedProduct.getName());
            }

            selectedProduct = null; // Reset selectedProduct after successful payment
            arrayOfIntroducedMoney.clear(); // Clear introduced money
            return true;
        } else {
            System.out.println("Payment failed. Not enough money or no product selected.");
            return false;
        }
    }

    public boolean takeRefund() {
        if (!arrayOfIntroducedMoney.isEmpty()) {
            System.out.println("Refund successful. Take your money: " + getSumIntroducedMoney() + " Dirhams");
            arrayOfIntroducedMoney.clear(); // Clear introduced money
            return true;
        } else {
            System.out.println("No money to refund.");
            return false;
        }
    }

    public void resetVendingMachine() {
        for (int i = 0; i < stock.length; i++) {
            stock[i] = products[i].getInitialStock();
        }
        System.out.println("Vending machine has been reset.");
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }
}