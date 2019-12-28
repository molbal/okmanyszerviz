package hu.idomsoft.okmanyszerviz.rest;

import hu.idomsoft.okmanyszerviz.config.HelperConfig;
import hu.idomsoft.okmanyszerviz.kodszotar.OkmanyKonyvtar;
import hu.idomsoft.okmanyszerviz.kodszotar.Okmanytipus;
import hu.idomsoft.okmanyszerviz.models.OkmanyDTO;
import hu.idomsoft.okmanyszerviz.verification.KepEllenorzes;
import hu.idomsoft.okmanyszerviz.verification.ValidacioException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Log
public class OkmanyREST {

    @Autowired
    OkmanyKonyvtar okmanyKonyvtar;

    @Autowired
    HelperConfig helper;

    @Autowired
    KepEllenorzes kepEllenorzes;


    /**
     * Ellenőrzi a megadott Okmányt, hibamentesség esetén kitölti az érvényesség mezőt, vagy hibák esetén visszaadja a hibalistát.
     * @param okmany Ellenőrzenő okmány
     * @return
     */
    @SneakyThrows
    @ApiOperation("Ellenőrzi a megadott Okmányt, hibamentesség esetén kitölti az érvényesség mezőt, vagy hibák esetén visszaadja a hibalistát.")
    @PostMapping("/okmany/ellenoriz")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A megadott okmány helyes"),
            @ApiResponse(code = 400, message = "A megadott okmány helytelen adatokat tartalmaz. Hibalistával tér vissza a webszerviz.", response = ValidacioHibaErrorResponse.class)})
    public OkmanyDTO ellenoriz(@RequestBody OkmanyDTO okmany) {
        log.fine(String.format("Okmány ellenőrzése: %s", okmany.toString()));

        List<String> hibaLista = new ArrayList<>();
        try {
            kepEllenorzes.feldolgozas(okmany.getOkmanyKep());
        }
        catch (Exception e) {
            log.severe(String.format("Sikertelen képfeldolgozás: %s", e));
            hibaLista.add("Helytelen képformátum: A megadott kép típusa nem felismerhető.");
        }

        // Képformátum ellenőrzés
        if (!kepEllenorzes.isFormatumOk())
            hibaLista.add(String.format("Helytelen képformátum. Az elfogadott formátumok listája %s a megadott képformátum pedig %s",
                String.join(",", helper.getOkmanyTipusok()),
                kepEllenorzes.getActualFormat()));

        // Képméret ellenőrzés
        if (!kepEllenorzes.isMeretOk())
            hibaLista.add(String.format("Helytelen képméret. Az elfogadott képméret %d x %d a megaott kép mérete pedig %d x %d",
                    helper.getOkmanyW(),
                    helper.getOkmanyH(),
                    kepEllenorzes.getActualW(),
                    kepEllenorzes.getActualH()));

        // Okmánytípus ellenőrzés
        int okmanyTipus = Integer.parseInt(okmany.getOkmTipus());
        if (!okmanyKonyvtar.getOkmanytipusMap().containsKey(okmanyTipus))
            hibaLista.add(String.format("Nem létezik ilyen számú okmánytípus: %d", okmanyTipus));
        else {
            // Okmányszám ellenőrzés
            Okmanytipus okmanytipus = okmanyKonyvtar.getOkmanytipusMap().get(okmanyTipus);
            if (!okmanytipus.validal(okmany.getOkmanySzam())) {
                hibaLista.add(String.format("A megadott okmányszám (%s) felel meg a formai követelményeknek (A követelmények ellenőrzése a %s típushoz a %s regex kifejezéssel történik.)",
                        okmany.getOkmanySzam(),
                        okmanytipus.getErtek(),
                        okmanytipus.getValidacio()));
            }
        }

        // Érvényesség vizsgálat
        okmany.setErvenyes(!(new Date()).after(okmany.getLejarDat()));

        if (hibaLista.size() > 0) {
            log.fine(String.format("Okmány ellenőrizve, hibákat tartalmazott: %s", String.join(", ", hibaLista)));
            throw new ValidacioException(hibaLista);
        }
        else {
            log.fine("Okmány ellenőrizve, nincs hiba.");
            return okmany;
        }

    }

    /**
     * Visszaadja a betöltött okmánytípusok listáját.
     * @return
     */
    @ApiOperation("Visszaadja a betöltött okmánytípusok listáját.")
    @GetMapping("/debug/okmanytipus")
    public Map<Integer, Okmanytipus> getOkmanyTipus() {
        return okmanyKonyvtar.getOkmanytipusMap();
    }
}
