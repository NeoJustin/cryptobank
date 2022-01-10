package koningjustin.cryptobank.opslag;

import koningjustin.cryptobank.domain.CryptoCurrency;
import koningjustin.cryptobank.domain.ImmutableCryptoCurrency;
import koningjustin.cryptobank.domain.ImmutableUser;
import koningjustin.cryptobank.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CryptobankRepository {

    private Set<User> users = new HashSet<>();

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public User depositorCryptoCurrency(User user) {
        User retrievedUser = users.stream()
                .filter(u -> u.getUser().equals(user.getUser()))
                .findAny().get();
        return ImmutableUser.copyOf(retrievedUser)
                .withCryptoCurrency(ImmutableCryptoCurrency.builder()
                        .worth(retrievedUser.getCryptoCurrency().getWorth()
                                + user.getCryptoCurrency().getWorth())
                        .build());
    }

    public Set<User> getUsers() {
        return users;
    }

    public void deleteUsers() {
        users = new HashSet<>();
    }
}
