package Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Product {

    PASTA(15),
    SODA(10),
    SPARKLING_WATER(25);

    private final double price;

}
