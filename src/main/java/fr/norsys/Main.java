package fr.norsys;

public class Main {
    public static void main(String[] args) {
        // Define products and initial stock
        Product[] arrProducts = new Product[] {
            new Product("Water", 5),
            new Product("Coca", 7),
            new Product("Twix", 10),
            new Product("Bueno", 12),
        };

        int[] initialStock = new int[] { 5, 3, 8, 10 };

        // Create a vending machine
        VendingMachine vendingMachine = new VendingMachine(arrProducts, initialStock);

        // Display available products
        vendingMachine.displayProducts();

        // Select a product
        int indexSelected = 3;
        vendingMachine.selectProduct(indexSelected);

        // Perform buying:
        boolean isIntroducedEnough = false;

        while (!isIntroducedEnough) {
            vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_1);
            vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
            vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

            // Check if the introduced money is enough to cover the selected product
            if (vendingMachine.getSumIntroducedMoney() >= vendingMachine.getSelectedProduct().getPrice()) {
                isIntroducedEnough = true;
                System.out.println("Payment successful. Enjoy your " + vendingMachine.getSelectedProduct().getName());
            } else {
                System.out.println("Not enough money. Please insert more coins.");
            }
        }
    }
}


