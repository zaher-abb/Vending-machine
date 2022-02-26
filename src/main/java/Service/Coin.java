package Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Coin {
    ONE(1),
    FIVE(5),
    TEN(10),
    TWENTY_FIVE(25);

    private final double value;
}
