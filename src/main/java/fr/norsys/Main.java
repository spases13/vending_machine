package fr.norsys;

public class Main {
    public static void main(String[] args) {

        Product[] arrProducts = new Product[] {
            new Product("Water", 5, 5),
            new Product("Coca", 7, 3),
            new Product("Twix", 10, 8),
            new Product("Bueno", 12, 10),
        };

        int[] initialStock = new int[] { 5, 3, 8, 10 };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, initialStock);

        vendingMachine.displayProducts();

        int indexSelected = 3;
        vendingMachine.selectProduct(indexSelected);

        boolean isIntroducedEnough = false;

        while (!isIntroducedEnough) {
            vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_1);
            vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
            vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

            if (vendingMachine.getSumIntroducedMoney() >= vendingMachine.getSelectedProduct().getPrice()) {
                isIntroducedEnough = true;
                System.out.println("Payment successful. Enjoy your " + vendingMachine.getSelectedProduct().getName());
            } else {
                System.out.println("Not enough money. Please insert more coins.");
            }
        }
    }
}