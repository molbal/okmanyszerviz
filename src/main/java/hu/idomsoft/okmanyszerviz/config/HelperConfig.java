package hu.idomsoft.okmanyszerviz.config;

import hu.idomsoft.okmanyszerviz.kodszotar.Okmanytipus;
import org.springframework.context.annotation.Bean;

public class HelperConfig {

    /**
     * Létrehoz egy Okmánytípus beant
     * @return
     */
    @Bean
    public Okmanytipus getOkmanytipus() {

        Okmanytipus ot = new Okmanytipus();

        return ot;
    }
}
