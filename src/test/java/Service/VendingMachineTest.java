package Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VendingMachineTest {

    private final VendingMachine vendingMachine = new VendingMachine();
    private final static List<Coin> COINS = List.of(Coin.FIVE, Coin.TEN);

    @Test
    public void shouldReturnProduct() throws Exception {
        Product product = vendingMachine.returnProduct(Product.PASTA, COINS);
        Assertions.assertNotNull(product);
    }

    @Test
    public void shouldReturnRequestedProduct() throws Exception {
        Product product = vendingMachine.returnProduct(Product.PASTA, COINS);
        assertEquals(Product.PASTA, product);
    }

    @Test
    public void shouldThrowExceptionIFPProductNotFound() {
        /**
         * assertThrows should be a not found Product to throw the Exception
         *
         * */
        assertThrows(Exception.class, () -> vendingMachine.returnProduct(Product.SODA, COINS));
    }

    @Test
    public void shouldDeductProductFromInventory() throws Exception {
        Product product = vendingMachine.returnProduct(Product.PASTA, COINS);
        assertEquals(4, vendingMachine.inventory.get(product));
    }

    @Test
    public void shouldAddInsertedCoinsToCoinInventory() throws Exception {
        vendingMachine.returnProduct(Product.PASTA, COINS);
        assertEquals(6, vendingMachine.COINS_INVENTORY.get(Coin.FIVE));
        assertEquals(8, vendingMachine.COINS_INVENTORY.get(Coin.TEN));
    }

    @Test
    public void shouldThrowExceptionWhenInsufficientFunds() throws Exception {
        assertThrows(Exception.class, () -> {
            vendingMachine.returnProduct(Product.SPARKLING_WATER, COINS);
        });
    }

    @Test
    public void shouldReturnTheChange() throws Exception {
        vendingMachine.returnProduct(Product.PASTA, COINS);
        assertEquals(10,vendingMachine.returnTheChange(Product.PASTA.getPrice(), COINS));
    }
}
