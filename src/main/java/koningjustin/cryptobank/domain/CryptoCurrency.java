package koningjustin.cryptobank.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize
@JsonDeserialize(
        builder = ImmutableCryptoCurrency.Builder.class,
        as = CryptoCurrency.class)
@Value.Immutable
public interface CryptoCurrency {

    Integer getWorth(); // in Dollars

}
