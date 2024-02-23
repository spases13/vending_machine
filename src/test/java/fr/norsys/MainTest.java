package fr.norsys;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void selectProductTest() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5),
                new Product("Coca", 7),
                new Product("Twix", 10),
                new Product("Bueno", 12),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[]{5, 3, 8, 10});

        vendingMachine.selectProduct(1);
        assertNotNull(vendingMachine.getSelectedProduct());

        vendingMachine.selectProduct(2);
        assertNotNull(vendingMachine.getSelectedProduct());

        vendingMachine.selectProduct(5);
        assertNull(vendingMachine.getSelectedProduct());
    }

    @Test
    void introduceMoneyTest() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5),
                new Product("Coca", 7),
                new Product("Twix", 10),
                new Product("Bueno", 12),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[]{5, 3, 8, 10});

        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        assertEquals(1, vendingMachine.getSumIntroducedMoney());

        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        assertEquals(6, vendingMachine.getSumIntroducedMoney());
    }

    @Test
    void makeSuccessfulPaymentTest() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5),
                new Product("Coca", 7),
                new Product("Twix", 10),
                new Product("Bueno", 12),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[]{5, 3, 8, 10});

        vendingMachine.selectProduct(3); // Select Twix (price: 10)
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        assertTrue(vendingMachine.makePayment());
        assertNull(vendingMachine.getSelectedProduct());
    }

    @Test
    void makeUnsuccessfulPaymentTest() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5),
                new Product("Coca", 7),
                new Product("Twix", 10),
                new Product("Bueno", 12),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[]{5, 3, 8, 10});

        vendingMachine.selectProduct(3); // Select Twix (price: 10)
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_1);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        assertFalse(vendingMachine.makePayment());
        assertNotNull(vendingMachine.getSelectedProduct());
    }

    @Test
    void refundTest() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5),
                new Product("Coca", 7),
                new Product("Twix", 10),
                new Product("Bueno", 12),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[]{5, 3, 8, 10});

        vendingMachine.selectProduct(3); // Select Twix (price: 10)
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        int previousIntroducedMoney = vendingMachine.getSumIntroducedMoney();
        Product previousSelectedProduct = vendingMachine.getSelectedProduct();

        vendingMachine.takeRefund();
        
        assertEquals(previousIntroducedMoney, vendingMachine.getSumIntroducedMoney());
        assertNull(vendingMachine.getSelectedProduct());
        assertNotNull(previousSelectedProduct);
    }

    @Test
    void resetVendingMachineTest() {
        Product[] arrProducts = new Product[] {
                new Product("Water", 5),
                new Product("Coca", 7),
                new Product("Twix", 10),
                new Product("Bueno", 12),
        };

        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[]{5, 3, 8, 10});

        vendingMachine.selectProduct(3); // Select Twix (price: 10)
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_5);
        vendingMachine.introduceMoney(VendingMachine.Coin.DIRHAM_2);

        int previousIntroducedMoney = vendingMachine.getSumIntroducedMoney();
        Product previousSelectedProduct = vendingMachine.getSelectedProduct();

        vendingMachine.resetVendingMachine();

        assertEquals(0, vendingMachine.getSumIntroducedMoney());
        assertNull(vendingMachine.getSelectedProduct());
        assertEquals(previousIntroducedMoney, vendingMachine.getSumIntroducedMoney());
        assertNotNull(previousSelectedProduct);
    }
}