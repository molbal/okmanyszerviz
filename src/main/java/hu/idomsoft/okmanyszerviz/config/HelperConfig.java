package hu.idomsoft.okmanyszerviz.config;

import hu.idomsoft.okmanyszerviz.kodszotar.OkmanyKonyvtar;
import hu.idomsoft.okmanyszerviz.kodszotar.Okmanytipus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class HelperConfig {

    /**
     * Létrehoz egy Okmánytípus beant
     * @return
     */
    @Bean
    @Scope("singleton")
    public OkmanyKonyvtar getOkmanyKonyvtar() {
        OkmanyKonyvtar ok = new OkmanyKonyvtar();
        ok.betolt();
        return ok;
    }
}
