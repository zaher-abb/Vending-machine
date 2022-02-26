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
        Output productCoin = vendingMachine.returnProduct(Product.PASTA, COINS);
        Assertions.assertNotNull(productCoin.getProduct());
    }

    @Test
    public void shouldReturnRequestedProduct() throws Exception {
        Output productCoin = vendingMachine.returnProduct(Product.PASTA, COINS);
        assertEquals(Product.PASTA, productCoin.getProduct());
    }

    @Test
    public void shouldThrowExceptionIFPProductNotFound() {
        /**
         * assertThrows should be a not found Product to throw the Exception
         *
         * */
        assertThrows(VendingMachineException.class, () -> vendingMachine.returnProduct(Product.SODA, COINS));
    }

    @Test
    public void shouldDeductProductFromInventory() throws Exception {
        Output productCoin = vendingMachine.returnProduct(Product.PASTA, COINS);
        assertEquals(4, vendingMachine.inventory.get(productCoin.getProduct()));
    }

    @Test
    public void shouldAddInsertedCoinsToCoinInventory() throws Exception {
        vendingMachine.returnProduct(Product.PASTA, COINS);
        assertEquals(6, vendingMachine.COINS_INVENTORY.get(Coin.FIVE));
        assertEquals(8, vendingMachine.COINS_INVENTORY.get(Coin.TEN));
    }

    @Test
    public void shouldThrowExceptionWhenInsufficientFunds() {
        assertThrows(VendingMachineException.class, () -> {
            vendingMachine.returnProduct(Product.SPARKLING_WATER, COINS);
        });
    }

    @Test
    public void shouldReturn25() throws Exception {
        Output productCoin = vendingMachine.returnProduct(Product.PASTA,
                List.of(Coin.FIVE, Coin.TWENTY_FIVE, Coin.TEN));
        assertEquals(List.of(Coin.TWENTY_FIVE), productCoin.getCoin());
    }

    @Test
    public void shouldReturn() throws Exception {
        Output productCoin = vendingMachine.returnProduct(Product.PASTA,
                List.of(Coin.FIVE, Coin.TEN, Coin.TEN, Coin.TEN));
        assertEquals(List.of(Coin.TEN, Coin.FIVE, Coin.FIVE), productCoin.getCoin());
    }
}
