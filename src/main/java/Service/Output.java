package Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Output {

    private final List<Coin> coin;
    private final Product product;



}
