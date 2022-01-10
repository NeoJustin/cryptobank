package koningjustin.cryptobank;

import com.fasterxml.jackson.databind.ObjectMapper;
import koningjustin.cryptobank.domain.CryptoCurrency;
import koningjustin.cryptobank.domain.ImmutableCryptoCurrency;
import koningjustin.cryptobank.domain.ImmutableUser;
import koningjustin.cryptobank.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CryptobankCurrencyTest {

    private static User USER1 = ImmutableUser.builder()
            .user(UUID.randomUUID())
            .cryptoCurrency(ImmutableCryptoCurrency.builder().worth(2).build())
            .build();
    private static User USER2 = ImmutableUser.builder()
            .user(UUID.randomUUID())
            .cryptoCurrency(ImmutableCryptoCurrency.builder().worth(42).build())
            .build();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void deleteUsers() throws Exception {
        mockMvc.perform(delete("/cryptobank/deleteAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getUsers_Empty() throws Exception {
        mockMvc.perform(get("/cryptobank/users"))
                .andExpect(status().isOk())
                .andExpect(a -> assertThat(a.getResponse().getContentAsString())
                        .isEqualTo("[]"));
    }

    @Test
    void createOneUser() throws Exception {
        createUser(USER1)
                .andExpect(status().isOk())
                .andExpect(result -> assertUser(result, USER1));
    }

    @Test
    void depositCryptoCurrency() throws Exception {
        createUser(USER1);
        putCryptoCurrency(ImmutableUser.copyOf(USER1)
                .withCryptoCurrency(ImmutableCryptoCurrency.builder()
                        .worth(5).build()))
                .andExpect(status().isOk())
                .andExpect(result -> assertWorth(result, 7));
    }

    @Test
    void depositMoreThan200_ForbiddenException() throws Exception {
        createUser(USER1);
        putCryptoCurrency(ImmutableUser.copyOf(USER1)
                .withCryptoCurrency(ImmutableCryptoCurrency.builder()
                        .worth(201).build()))
                .andExpect(status().isForbidden());
    }

    @Test
    void depositOfDifferentUsersDontAddUp() throws Exception {
        createUser(USER1);
        createUser(USER2);
        putCryptoCurrency(ImmutableUser.copyOf(USER1)
                .withCryptoCurrency(ImmutableCryptoCurrency.builder()
                        .worth(200).build()));
        putCryptoCurrency(ImmutableUser.copyOf(USER2)
                .withCryptoCurrency(ImmutableCryptoCurrency.builder()
                        .worth(200).build()))
                .andExpect(result -> assertWorth(result, 242));
    }

    private ResultActions createUser(User user) throws Exception {
        return mockMvc.perform(post("/cryptobank/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
    }

    private ResultActions putCryptoCurrency(User user) throws Exception {
        return mockMvc.perform(put("/cryptobank/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
    }

    private void assertWorth(MvcResult result, int worth) throws Exception {
        User user = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
        assertThat(user.getCryptoCurrency().getWorth()).isEqualTo(worth);
    }

    private void assertUser(MvcResult result, User user) throws Exception {
        User userResponse = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
        assertThat(userResponse).isEqualTo(user);
    }
}
