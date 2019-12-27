package hu.idomsoft.okmanyszerviz.kodszotar;

import lombok.Data;
import lombok.extern.java.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NotLinkException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log
@Data
public class OkmanyKonyvtar {

    private List<Okmanytipus> okmanytipusList;

    /**
     * Konstruktor
     */
    public OkmanyKonyvtar() {
    }

    /**
     * Betölti a kódszótár JSON-ból az okmányokat.
     */
    public void betolt() {

        okmanytipusList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject dictionary = (JSONObject)jsonParser.parse(new FileReader("kodszotar46_okmanytipus.json"));

            JSONArray rows = (JSONArray) dictionary.get("rows");
            for (Object row : rows) {
                JSONObject okmanyTipus = (JSONObject) row;

                okmanytipusList.add(new Okmanytipus(
                        (int) okmanyTipus.get("kod"),
                        (String) okmanyTipus.get("ertek"),
                        (String) okmanyTipus.get("validacio")
                ));
            }

            log.info(String.format("Az okmány kódszótár betöltése sikerült (%d db elem)", okmanytipusList.size()));

        } catch (ParseException | IOException e) {
            log.info(String.format("Sikertelen a kódszótár json betöltése: %s", e));
        }
        catch (Exception e) {
            log.info(String.format("Sikertelen a kódszótár json feldolgozása: %s", e));
        }
    }
}
