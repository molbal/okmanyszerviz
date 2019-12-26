package hu.idomsoft.okmanyszerviz.rest;

import hu.idomsoft.okmanyszerviz.models.OkmanyDTO;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log
public class OkmanyREST {

    /**
     * Ellenőrzi a megadott hibákat majd
     * @param okmany
     * @return
     */
    @PostMapping("/okmany/ellenoriz")
    public List<String> ellenoriz(@RequestBody OkmanyDTO okmany) {
        log.fine(String.format("Okmány ellenőrzése: %s", okmany.toString()));

        ArrayList<String> hibaLista = new ArrayList<>();

        return hibaLista;
    }
}
