package fr.norsys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fr.norsys.Product.ProductName;

public class VendingMachine {
    private Product[] products;
    private int[] stock;
    private int balance = 20;
    private Product selectedProduct = null;
    private Map<Coin, Integer> introducedCoins = new HashMap<>();
    private List<Integer> introducedMoney = new ArrayList<>();
    private Map<Coin, Integer> budget = new HashMap<>();
    private int exchange;

    public int getExchange() {
        return exchange;
    }

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
        initializeBudget();
    }

    private void initializeBudget() {
        budget.put(Coin.DIRHAM_1, 10);
        budget.put(Coin.DIRHAM_2, 5);
        budget.put(Coin.DIRHAM_5, 8);
        budget.put(Coin.DIRHAM_10, 3);
    }

    public void displayProducts() {
        System.out.println("-------------------------");
        System.out.println("Available Products:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].getName() + " - Price: " + products[i].getPrice());
        }
        System.out.println("-------------------------");
    }

    public void selectProduct(ProductName productName) {
        selectedProduct = null;

        for (int i = 0; i < products.length; i++) {
            if (products[i].getName() == productName) {
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
        System.out.println("Money introduced : " + money.value);
        introducedMoney.add(money.getValue());
        balance += money.getValue();
        introducedCoins.put(money, introducedCoins.getOrDefault(money, 0) + 1);
        budget.put(money, budget.get(money) + 1);
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
        initializeBudget();
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

    public Map<Coin, Integer> getBudget() {
        return budget;
    }

    public void performBuyingAction() {
        if (selectedProduct != null && this.getSumIntroducedMoney() >= this.getSelectedProduct().getPrice()) {
            System.out.println("Payment successful. Enjoy your " + this.getSelectedProduct().getName());
            System.out.println("Introduced : " + this.getSumIntroducedMoney());
            System.out.println("Price : " + this.getSelectedProduct().getPrice());
            this.exchange = this.getSumIntroducedMoney() - this.getSelectedProduct().getPrice();
            System.out.println("Your exchange is " + this.exchange);
            System.out.println("------------------------");
            System.out.println("Coins introduced:");
            for (Map.Entry<Coin, Integer> entry : introducedCoins.entrySet()) {
                System.out.println(entry.getKey().name() + " : " + entry.getValue());
            }
            introducedCoins.clear();
            this.displayBudget();
            this.returnExchange();
            this.displayBudget();
        } else {
            System.out.println("Not enough, insert more");
        }
    }

    public void displayBudget() {
        System.out.println("------------------------");
        System.out.println("Budget by Coin:");
        for (Map.Entry<Coin, Integer> entry : budget.entrySet()) {
            System.out.println(entry.getKey().name() + " : " + entry.getValue());
        }
        System.out.println("------------------------");
    }

    private Map<Coin, Integer> calculateChange(int amount) {
        Map<Coin, Integer> change = new HashMap<>();
        Coin[] coins = Coin.values();

        for (int i = coins.length - 1; i >= 0; i--) {
            Coin coin = coins[i];
            int coinValue = coin.getValue();

            if (amount >= coinValue && budget.get(coin) > 0) {
                int coinCount = amount / coinValue;
                int availableCoins = Math.min(coinCount, budget.get(coin));

                change.put(coin, availableCoins);
                amount -= availableCoins * coinValue;
            }
        }

        if (amount != 0) {
            System.out.println("Error: Unable to provide exact change.");
        }

        return change;
    }

    public void returnExchange() {
        if (this.exchange > 0) {
            System.out.println("Returning exchange: " + exchange);
            Map<Coin, Integer> exchangeCoins = calculateChange(exchange);

            System.out.println("Coins returned:");
            for (Map.Entry<Coin, Integer> entry : exchangeCoins.entrySet()) {
                System.out.println(entry.getKey().name() + " : " + entry.getValue());
            }
            System.out.println("Total exchange: " + exchange);

            for (Map.Entry<Coin, Integer> entry : exchangeCoins.entrySet()) {
                Coin coin = entry.getKey();
                int quantity = entry.getValue();
                budget.put(coin, budget.get(coin) - quantity);
            }
            if (selectedProduct != null) {
                stock[getIndex(selectedProduct)]--;
            }

            resetAfterPayment();
        } else {
            System.out.println("No exchange available.");
        }
        System.out.println("------------------------");
    }

    public void resetMoney() {
        System.out.println("Returning all introduced coins to the user:");
        for (Map.Entry<Coin, Integer> entry : introducedCoins.entrySet()) {
            Coin coin = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(coin.name() + " : " + quantity);
            budget.put(coin, budget.get(coin) - quantity);
        }

        introducedCoins.clear();
        resetAfterPayment();
        System.out.println("All introduced coins returned.");
        System.out.println("------------------------");
    }
}