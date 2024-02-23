package fr.norsys;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testSelectProduct() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5, 5),
                new Product("Coca", 7, 3),
                new Product("Twix", 10, 8),
                new Product("Bueno", 12, 10),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[] { 5, 3, 8, 10 });

        vendingMachine.selectProduct(1);
        assertNotNull(vendingMachine.getSelectedProduct());

        vendingMachine.selectProduct(2);
        assertNotNull(vendingMachine.getSelectedProduct());

        vendingMachine.selectProduct(4);
    }

    @Test
    void testIntroduceMoney() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5, 5),
                new Product("Coca", 7, 3),
                new Product("Twix", 10, 8),
                new Product("Bueno", 12, 10),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[] { 5, 3, 8, 10 });

        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        assertEquals(1, vendingMachine.getSumIntroducedMoney());

        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        assertEquals(6, vendingMachine.getSumIntroducedMoney());
    }

    @Test
    void testMakeSuccessfulPayment() {

        Product[] arrProducts = new Product[] {
                new Product("Water", 5, 5),
                new Product("Coca", 7, 3),
                new Product("Twix", 10, 8),
                new Product("Bueno", 12, 10),
        };
        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[] { 5, 3, 8, 10 });

        vendingMachine.selectProduct(3); 
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        assertTrue(vendingMachine.makePayment());
        assertNull(vendingMachine.getSelectedProduct());
    }

    @Test
    void testMakeUnsuccessfulPayment() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5, 5),
                new Product("Coca", 7, 3),
                new Product("Twix", 10, 8),
                new Product("Bueno", 12, 10),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[] { 5, 3, 8, 10 });

        vendingMachine.selectProduct(3); 
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        assertFalse(vendingMachine.makePayment());
        assertNotNull(vendingMachine.getSelectedProduct());
    }

    @Test
    void testRefund() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5, 5),
                new Product("Coca", 7, 3),
                new Product("Twix", 10, 8),
                new Product("Bueno", 12, 10),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[] { 5, 3, 8, 10 });

        vendingMachine.selectProduct(3);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        int refundAmount = vendingMachine.getSumIntroducedMoney();

        assertTrue(vendingMachine.takeRefund());

        assertEquals(0, vendingMachine.getSumIntroducedMoney());
        assertNotEquals(refundAmount, vendingMachine.getSumIntroducedMoney());
    }

    @Test
    void testResetVendingMachine() {

        Product[] arrProducts = new Product[] {
                new Product("Water", 5, 5),
                new Product("Coca", 7, 3),
                new Product("Twix", 10, 8),
                new Product("Bueno", 12, 10),
        };
        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[] { 5, 3, 8, 10 });

        vendingMachine.selectProduct(3);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        Product previousSelectedProduct = vendingMachine.getSelectedProduct();

        vendingMachine.resetVendingMachine();

        assertEquals(7, vendingMachine.getSumIntroducedMoney());
        assertNotNull(previousSelectedProduct);
    }
}