package fr.norsys;
import fr.norsys.Product.ProductName;

public class Main {
    public static void main(String[] args) {
        Product[] arrProducts = new Product[] {
                new Product(ProductName.WATER, 5, 5),
                new Product(ProductName.COCA, 7, 3),
                new Product(ProductName.TWIX, 10, 8),
                new Product(ProductName.BUENO, 12, 10),
        };
        int[] initialStock = new int[] { 5, 3, 8, 10 };
        VendingMachine vm = new VendingMachine(arrProducts, initialStock);
        vm.displayProducts();
        vm.selectProduct(ProductName.TWIX);

        vm.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_2);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_5);

        vm.displayBudget();  
        vm.performBuyingAction();

    }
}