package koningjustin.cryptobank.sturing;

import koningjustin.cryptobank.domain.CryptoCurrency;
import koningjustin.cryptobank.opslag.CryptobankRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CryptobankService {

    private final CryptobankRepository repository;

    public CryptobankService(CryptobankRepository repository) {
        this.repository = repository;
    }

    public String greet() {
        return "Hello World";
    }

    public Set<CryptoCurrency> getCryptoCurrency() {
        return repository.getCryptoCurrency();
    }

    public CryptoCurrency putCryptoCurrency(CryptoCurrency currency) {
        return repository.putCryptoCurrency(currency);
    }
}
