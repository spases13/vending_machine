package fr.norsys;

import org.junit.jupiter.api.Test;
import fr.norsys.Product.ProductName;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testSelectProduct() {
        Product[] arrProducts = new Product[]{
                new Product(ProductName.WATER, 5, 5),
                new Product(ProductName.COCA, 7, 3),
                new Product(ProductName.TWIX, 10, 8),
                new Product(ProductName.BUENO, 12, 10),
        };
        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[]{5, 3, 8, 10});
        vendingMachine.selectProduct(ProductName.TWIX);
        assertNotNull(vendingMachine.getSelectedProduct());
    }

    @Test
    void testUserWantsHisMoneyBack() {
        Product[] arrProducts = new Product[]{
                new Product(ProductName.WATER, 5, 5),
                new Product(ProductName.COCA, 7, 3),
                new Product(ProductName.TWIX, 10, 8),
                new Product(ProductName.BUENO, 12, 10),
        };
        int[] initialStock = new int[]{5, 3, 8, 10};
        VendingMachine vm = new VendingMachine(arrProducts, initialStock);
        vm.displayProducts();
        vm.selectProduct(ProductName.TWIX);

        vm.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_2);
        vm.displayBudget();
        vm.resetMoney();
        vm.displayBudget();
        assertFalse(vm.getBalance() > 0, "Balance should be reset to 0");
    }

    @Test
    void testUserPerformBuyingAndReceiveExchange() {
        Product[] arrProducts = new Product[]{
                new Product(ProductName.WATER, 5, 5),
                new Product(ProductName.COCA, 7, 3),
                new Product(ProductName.TWIX, 10, 8),
                new Product(ProductName.BUENO, 12, 10),
        };
        int[] initialStock = new int[]{5, 3, 8, 10};
        VendingMachine vm = new VendingMachine(arrProducts, initialStock);
        vm.displayProducts();
        vm.selectProduct(ProductName.TWIX);

        vm.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_2);
        vm.introduceMoney(VendingMachine.Coin.DIRHAM_5);

        vm.displayBudget();
        vm.performBuyingAction();
        assertTrue(vm.getExchange() >= 0, "Exchange should be non-negative");
    }
}