package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    final Map<Coin, Integer> COINS_INVENTORY = new HashMap<>() {{
        put(Coin.FIVE, 5);
        put(Coin.TEN, 7);

    }};
    final Map<Product, Integer> inventory = new HashMap<>() {{
        put(Product.PASTA, 5);
        put(Product.SPARKLING_WATER, 7);

    }};

    public Product returnProduct(Product product, List<Coin> coins) throws Exception {
        if (inventory.getOrDefault(product, 0) > 0) {
            checkSufficientFunds(product.getPrice(), coins);
            addInsertedCoinsToCoinInventory(coins);
            inventory.put(product, inventory.get(product) - 1);
            return product;
        } else {
            throw new Exception("Product not found");
        }
    }

    private void addInsertedCoinsToCoinInventory(List<Coin> coins) {
        coins.forEach(coin -> COINS_INVENTORY.put(coin, COINS_INVENTORY.get(coin) + 1));
    }

    private void checkSufficientFunds(double productPrice, List<Coin> coins) throws Exception {
        if (coins.stream().mapToDouble(Coin::getValue).sum() < productPrice) {
            throw new Exception("Insufficient Funds");
        }

    }

    public double returnTheChange(double productPrice, List<Coin> coins) {

        return coins.stream().mapToDouble(Coin::getValue).sum() - productPrice;

    }
}
