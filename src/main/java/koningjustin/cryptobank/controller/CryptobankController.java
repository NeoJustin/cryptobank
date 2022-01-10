package koningjustin.cryptobank.controller;

import koningjustin.cryptobank.domain.CryptoCurrency;
import koningjustin.cryptobank.domain.ImmutableCryptoCurrency;
import koningjustin.cryptobank.sturing.CryptobankService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

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

    @GetMapping(value = "currency",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Set<CryptoCurrency> getCryptoCurrency() {
        return service.getCryptoCurrency();
    }

    @PostMapping(value = "currency",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody CryptoCurrency createCryptoCurrency(
            @RequestBody CryptoCurrency cryptoCurrency) {
        return service.putCryptoCurrency(cryptoCurrency);
    }

}
