package Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class VendingMachine {

    final Map<Coin, Integer> COINS_INVENTORY = new HashMap<>() {{
        put(Coin.FIVE, 5);
        put(Coin.TEN, 7);

    }};
    final Map<Product, Integer> inventory = new HashMap<>() {{
        put(Product.PASTA, 5);
        put(Product.SPARKLING_WATER, 7);

    }};

    public Output returnProduct(Product product, List<Coin> coins) throws VendingMachineException {
        if (inventory.getOrDefault(product, 0) > 0) {
            checkSufficientFunds(product.getPrice(), coins);
            addInsertedCoinsToCoinInventory(coins);
            inventory.put(product, inventory.get(product) - 1);
            return new Output(returnTheChange(product.getPrice(), coins), product);
        } else {
            throw new VendingMachineException("Product not found");
        }
    }

    private void addInsertedCoinsToCoinInventory(List<Coin> coins) {
        coins.forEach(coin -> COINS_INVENTORY.put(coin, COINS_INVENTORY.getOrDefault(coin, 0) + 1));
    }

    private void checkSufficientFunds(double productPrice, List<Coin> coins) throws VendingMachineException {
        if (coins.stream().mapToDouble(Coin::getValue).sum() < productPrice) {
            throw new VendingMachineException("Insufficient Funds");
        }

    }


    private List<Coin> returnTheChange(double productPrice, List<Coin> coins) {
        List<Coin> returnedCoins = new ArrayList<>();
        AtomicReference<Double> change = new AtomicReference<>(coins.stream().mapToDouble(Coin::getValue).sum() - productPrice);
        while (change.get() > 0) {
            Arrays.stream(Coin.values()).forEach(coin -> {
                if (change.get() >= coin.getValue() && COINS_INVENTORY.containsKey(coin)) {
                    returnedCoins.add(coin);
                    change.updateAndGet(v -> v - coin.getValue());
                }
            });
        }
        return returnedCoins;
    }
}
