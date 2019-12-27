package hu.idomsoft.okmanyszerviz.kodszotar;

import hu.idomsoft.okmanyszerviz.config.HelperConfig;
import lombok.Data;
import lombok.extern.java.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

@Log
@Component
@Data
public class OkmanyKonyvtar {

    HelperConfig helper;

    private List<Okmanytipus> okmanytipusList;

    /**
     * Konstruktor
     */
    @Autowired
    public OkmanyKonyvtar(HelperConfig helper) {
        if (helper.getKodszotarFilenev() == null) {
            throw new NullPointerException("A kódszótár fájlnév null, nincs megadva");
        }
        log.info(String.format("Kódszótár betöltése a következő fájlból: %s", helper.getKodszotarFilenev()));
        if (!new File(helper.getKodszotarFilenev()).exists()) {
            throw new NullPointerException(String.format("Nem létezik a könyvtár fájl a [%s] úton.", helper.getKodszotarFilenev()));
        }
        this.helper = helper;
        betolt();
    }

    /**
     * Betölti a kódszótár JSON-ból az okmányokat.
     */
    private void betolt() {

        okmanytipusList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject dictionary = (JSONObject)jsonParser.parse(new FileReader(helper.getKodszotarFilenev()));

            JSONArray rows = (JSONArray) dictionary.get("rows");
            for (Object row : rows) {
                JSONObject okmanyTipus = (JSONObject) row;

                okmanytipusList.add(new Okmanytipus(
                        Integer.parseInt((String)okmanyTipus.get("kod")),
                        (String) okmanyTipus.get("ertek"),
                        (String) okmanyTipus.get("validacio")
                ));
            }

            log.info(String.format("Az okmány kódszótár betöltése sikerült (%d db elem)", okmanytipusList.size()));

        } catch (ParseException | IOException e) {
            log.severe(String.format("Sikertelen a kódszótár json betöltése: %s", e));
            log.info("Aktuális könyvtár:" + FileSystems.getDefault().getPath(".").toAbsolutePath().toString());
        }
        catch (Exception e) {
            log.severe(String.format("Sikertelen a kódszótár json feldolgozása: %s", e));
        }
    }

}
