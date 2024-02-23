package fr.norsys;

import fr.norsys.Product.ProductType;

public class Main {
    public static void main(String[] args) {
        Product[] arrProducts = new Product[] {
                new Product(ProductType.WATER, 5, 5),
                new Product(ProductType.COCA, 7, 3),
                new Product(ProductType.TWIX, 10, 8),
                new Product(ProductType.BUENO, 12, 10),
        };
        int[] initialStock = new int[] { 5, 3, 8, 10 };
        VendingMachine vm = new VendingMachine(arrProducts, initialStock);
        vm.displayProducts();
        vm.selectProduct(ProductType.TWIX);

        vm.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_2);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_5);

        vm.performBuyingAction();
    }
}