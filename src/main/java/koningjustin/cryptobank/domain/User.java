package koningjustin.cryptobank.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.UUID;

@JsonSerialize
@JsonDeserialize(
        builder = ImmutableUser.Builder.class,
        as = User.class)
@Value.Immutable
public interface User {

    UUID getUser();

    CryptoCurrency getCryptoCurrency();

}
