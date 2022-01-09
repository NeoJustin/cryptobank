package koningjustin.cryptobank.opslag;

import koningjustin.cryptobank.domain.CryptoCurrency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CryptobankRepository {

    public List<CryptoCurrency> getCryptoCurrency() {
        return new ArrayList<>();
    }
}
