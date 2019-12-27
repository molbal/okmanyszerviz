package hu.idomsoft.okmanyszerviz.kodszotar;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Okmanytipus {

    @ApiModelProperty("Okmánytípus kódja")
    private int kod;

    @ApiModelProperty("Okmánytípus neve")
    private String ertek;

    @ApiModelProperty("Okmánytípus azonosítójának ellenőrzésére szolgáló reguláris kifejezés")
    private String validacio;

    public Okmanytipus(int kod, String ertek, String validacio) {
        this.kod = kod;
        this.ertek = ertek;
        this.validacio = validacio;
    }

    /**
     * Ellenőrzi, hogy a megadott okmányszám megfelel-e a validációs regex-nek.
     * @param okmanySzam Okmányszám
     * @return Megfelelés esetén igaz, hiba esetén hamis.
     */
    public boolean validal(String okmanySzam) {
        Matcher matcher = Pattern.compile(this.validacio).matcher(okmanySzam);
        return matcher.matches();
    }
}

