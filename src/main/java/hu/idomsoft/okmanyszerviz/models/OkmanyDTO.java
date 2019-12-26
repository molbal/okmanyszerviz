package hu.idomsoft.okmanyszerviz.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ruzsinak
 */
@Data
public class OkmanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String okmTipus;

    private String okmanySzam;

    private byte[] okmanyKep;

    private Date lejarDat;

    private boolean ervenyes;

}
