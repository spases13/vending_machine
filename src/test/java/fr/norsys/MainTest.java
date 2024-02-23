package fr.norsys;
import org.junit.jupiter.api.Test;
import fr.norsys.Product.ProductType;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testSelectProduct() {
        Product[] arrProducts = new Product[] {
            new Product(ProductType.WATER, 5, 5),
            new Product(ProductType.COCA, 7, 3),
            new Product(ProductType.TWIX, 10, 8),
            new Product(ProductType.BUENO, 12, 10),
    };
        VendingMachine vendingMachine = new VendingMachine(arrProducts, new int[] { 5, 3, 8, 10 });
        vendingMachine.selectProduct(ProductType.TWIX);
        assertNotNull(vendingMachine.getSelectedProduct());
    }

}