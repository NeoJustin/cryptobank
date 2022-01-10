package koningjustin.cryptobank;

import com.fasterxml.jackson.databind.ObjectMapper;
import koningjustin.cryptobank.domain.CryptoCurrency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

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
    void putAndGetCrypto_HasCrypto() throws Exception {
        mockMvc.perform(post("/cryptobank/currency")
                .content("1"))
                .andExpect(status().isOk())
                .andExpect(result -> assertWorth(result, 1));
        mockMvc.perform(get("/cryptobank/currency"))
                .andExpect(status().isOk())
                .andExpect(a -> assertCryptoCurrencyHasBeenStored(a));
    }

    private void assertCryptoCurrencyHasBeenStored(MvcResult a) throws Exception {
        Set<CryptoCurrency> myObjects = objectMapper.readValue(a.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(Set.class, CryptoCurrency.class));
        assertThat(myObjects).hasSize(1);
    }

    private void assertWorth(MvcResult a, int worth) throws Exception {
        CryptoCurrency cryptoCurrency = objectMapper.readValue(a.getResponse().getContentAsString(), CryptoCurrency.class);
        assertThat(cryptoCurrency.getWorth()).isEqualTo(worth);
    }

}
