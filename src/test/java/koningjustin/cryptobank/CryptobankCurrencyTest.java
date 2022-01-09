package koningjustin.cryptobank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CryptobankCurrencyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCrypto_Leeg() throws Exception {
        mockMvc.perform(get("/cryptobank/currency"))
                .andExpect(status().isOk())
                .andExpect(a -> assertThat(a.getResponse().getContentAsString())
                        .isEqualTo("[]"));
    }

}
