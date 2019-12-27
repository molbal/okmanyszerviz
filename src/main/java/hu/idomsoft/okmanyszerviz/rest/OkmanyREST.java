package hu.idomsoft.okmanyszerviz.rest;

import hu.idomsoft.okmanyszerviz.kodszotar.OkmanyKonyvtar;
import hu.idomsoft.okmanyszerviz.kodszotar.Okmanytipus;
import hu.idomsoft.okmanyszerviz.models.OkmanyDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log
public class OkmanyREST {

    @Autowired
    OkmanyKonyvtar okmanyKonyvtar;

    /**
     * Ellenőrzi a megadott hibákat majd
     * @param okmany Ellenőrzenő okmány
     * @return
     */
    @ApiOperation("Ellenőrzi a megadott Okmányt és visszaadja a lejárati időpontot, vagy hibák esetén a hibalistát.")
    @PostMapping("/okmany/ellenoriz")
    public OkmanyDTO ellenoriz(@RequestBody OkmanyDTO okmany) {
        log.fine(String.format("Okmány ellenőrzése: %s", okmany.toString()));

        return okmany;
    }

    @ApiOperation("Visszaadja a betöltött okmánytípusok listáját.")
    @GetMapping("/debug/okmanytipus")
    public List<Okmanytipus> getOkmanyTipus() {
        return okmanyKonyvtar.getOkmanytipusList();
    }
}
