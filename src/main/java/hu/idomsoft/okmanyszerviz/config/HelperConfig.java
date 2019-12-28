package hu.idomsoft.okmanyszerviz.config;

import hu.idomsoft.okmanyszerviz.kodszotar.OkmanyKonyvtar;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Log
@Configuration
@ComponentScan(basePackageClasses = OkmanyKonyvtar.class)
@Data
public class HelperConfig {

    @Value("${kodszotar.filenev:kodszotar46_okmanytipus.json}")
    private String kodszotarFilenev;

    @Value("${okmanykep.w:1063}")
    private int okmanyW;

    @Value("${okmanykep.h:827}")
    private int okmanyH;

    @Value("${okmanykep.tipusok:jpg,jpeg}")
    private String[] okmanyTipusok;
}
