package koningjustin.cryptobank.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.UUID;

@JsonSerialize
@JsonDeserialize(
        builder = ImmutableCryptoCurrency.Builder.class,
        as = CryptoCurrency.class)
@Value.Immutable
public interface CryptoCurrency {

    UUID getId();

    Integer getWorth(); // in Dollars

}
