package koningjustin.cryptobank.opslag;

import koningjustin.cryptobank.domain.CryptoCurrency;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CryptobankRepository {

    private Set<CryptoCurrency> currencies = new HashSet<>();

    public Set<CryptoCurrency> getCryptoCurrency() {
        return currencies;
    }

    public CryptoCurrency putCryptoCurrency(CryptoCurrency currency) {
        currencies.add(currency);
        return currency;
    }
}
