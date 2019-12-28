package hu.idomsoft.okmanyszerviz.verification;

import hu.idomsoft.okmanyszerviz.config.HelperConfig;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
@Log
@Data
public class KepEllenorzes {

    private final HelperConfig helper;

    private boolean formatumOk;
    private boolean meretOk;


    public KepEllenorzes(HelperConfig helper) {
        this.helper = helper;
    }

    /**
     * Feldolgozza a vizsgált képet és feltölti az osztály változóit.
     * @param vizsgaltKep A kép, amit ellenőrizni kell.
     *
     * @throws InvalidParameterException Hibás képformátum esetén
     */
    public void feldolgozas(byte[] vizsgaltKep) throws InvalidParameterException {

        formatumOk = false;
        meretOk = false;

        ImageInputStream imageInputStream = null;
        try {
            imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(vizsgaltKep));
        } catch (IOException e) {
            throw new InvalidParameterException("Nem sikerült a kép ellenőrzése: nem sikerült a byte array dekódolása");
        }

        try {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(imageInputStream);

            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                reader.setInput(imageInputStream, true);
                String formatName = reader.getFormatName();

                if (Arrays.asList(helper.getOkmanyTipusok()).contains(formatName.toLowerCase())) {
                    formatumOk = true;
                    meretOk = (reader.getWidth(0) == helper.getOkmanyW()) && (reader.getHeight(0) == helper.getOkmanyH());
                }
            }
            else {
                log.info("Helytelen formátum.");
            }

        } catch (IOException e) {
            log.info("Helytelen formátum.");
        }
    }

}
