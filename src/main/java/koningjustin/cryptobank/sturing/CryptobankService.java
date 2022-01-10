package koningjustin.cryptobank.sturing;

import koningjustin.cryptobank.domain.CryptoCurrency;
import koningjustin.cryptobank.domain.User;
import koningjustin.cryptobank.exceptions.DepositIsTooHighException;
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

    public Set<User> getUsers() {
        return repository.getUsers();
    }

    public User createUser(User user) {
        return repository.createUser(user);
    }

    public User depositCryptoCurrency(User user) {
        if (user.getCryptoCurrency().getWorth() > 200){
            throw new DepositIsTooHighException();
        }
        return repository.depositorCryptoCurrency(user);
    }

    public void deleteUsers() {
        repository.deleteUsers();

    }
}
