package koningjustin.cryptobank.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@JsonSerialize
@JsonDeserialize(
        builder = CryptoCurrencyLombok.CryptoCurrencyLombokBuilder.class,
        as = CryptoCurrencyLombok.class)
@Value
@Builder
public class CryptoCurrencyLombok {

    UUID user;
    Integer worth; // In dollars

}
