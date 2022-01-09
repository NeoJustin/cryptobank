package koningjustin.cryptobank.controller;

import koningjustin.cryptobank.domain.CryptoCurrency;
import koningjustin.cryptobank.sturing.CryptobankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @GetMapping(value = "currency")
    public @ResponseBody List<CryptoCurrency> getCryptoCurrency() {
        return service.getCryptoCurrency();
    }

}
