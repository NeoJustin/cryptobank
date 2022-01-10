package koningjustin.cryptobank;

import com.fasterxml.jackson.databind.ObjectMapper;
import koningjustin.cryptobank.domain.CryptoCurrency;
import koningjustin.cryptobank.domain.ImmutableCryptoCurrency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CryptobankCurrencyTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCrypto_Empty() throws Exception {
        mockMvc.perform(get("/cryptobank/currency"))
                .andExpect(status().isOk())
                .andExpect(a -> assertThat(a.getResponse().getContentAsString())
                        .isEqualTo("[]"));
    }

    @Test
    void postCryptoCurrency_HasCrypto() throws Exception {
        CryptoCurrency cryptoCurrency = ImmutableCryptoCurrency.builder()
                .id(UUID.randomUUID())
                .worth(1).build();

        mockMvc.perform(post("/cryptobank/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cryptoCurrency)))
                .andExpect(status().isOk())
                .andExpect(result -> assertWorth(result, 1));

        mockMvc.perform(get("/cryptobank/currency"))
                .andExpect(status().isOk())
                .andExpect(a -> assertCryptoCurrencyHasBeenStored(a, cryptoCurrency));
    }

    private void assertCryptoCurrencyHasBeenStored(MvcResult a, CryptoCurrency cryptoCurrency) throws Exception {
        Set<CryptoCurrency> myObjects = objectMapper.readValue(a.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(Set.class, CryptoCurrency.class));
        assertThat(myObjects).containsExactly(cryptoCurrency);
    }

    private void assertWorth(MvcResult a, int worth) throws Exception {
        CryptoCurrency cryptoCurrency = objectMapper.readValue(a.getResponse().getContentAsString(), CryptoCurrency.class);
        assertThat(cryptoCurrency.getWorth()).isEqualTo(worth);
    }

}
