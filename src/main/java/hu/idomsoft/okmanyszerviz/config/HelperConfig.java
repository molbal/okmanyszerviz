package hu.idomsoft.okmanyszerviz.config;

import hu.idomsoft.okmanyszerviz.kodszotar.OkmanyKonyvtar;
import hu.idomsoft.okmanyszerviz.kodszotar.Okmanytipus;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Log
@Configuration
public class HelperConfig {

    /**
     * Létrehoz egy Okmánytípus beant
     * @return
     */
    @Bean
    @Scope("singleton")
    public OkmanyKonyvtar getOkmanyKonyvtar() {
        log.info("Okmánykönyvtár létrehozása");
        OkmanyKonyvtar ok = new OkmanyKonyvtar();
        ok.betolt();
        return ok;
    }
}
