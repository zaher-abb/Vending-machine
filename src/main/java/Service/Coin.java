package Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Coin {
    TWENTY_FIVE(25),
    TEN(10),
    FIVE(5),
    ONE(1);

    private final double value;
}
