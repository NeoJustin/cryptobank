package koningjustin.cryptobank.controller;

import koningjustin.cryptobank.domain.User;
import koningjustin.cryptobank.sturing.CryptobankService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("cryptobank")
public class CryptobankController {

    private final CryptobankService service;

    public CryptobankController(CryptobankService service) {
        this.service = service;
    }

    @RequestMapping("/greeting")
    public @ResponseBody String greeting() {
        return service.greet();
    }

    @GetMapping(value = "users",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Set<User> getCryptoCurrencies() {
        return service.getUsers();
    }

    @PostMapping(value = "user",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody User createCryptoCurrency(
            @RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping(value = "currency")
    public @ResponseBody User depositCryptoCurrency(
            @RequestBody User user) {
        return service.depositCryptoCurrency(user);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteUsers() {
        service.deleteUsers();
    }

}
