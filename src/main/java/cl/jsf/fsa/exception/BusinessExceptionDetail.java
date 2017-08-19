package cl.jsf.fsa.exception;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fseguel
 */
public class BusinessExceptionDetail implements Serializable {

    @Getter
    @Setter
    private String campo;
    @Getter
    @Setter
    private String descripcionError;

    public BusinessExceptionDetail(String campo, String descripcionError) {
        this.campo = campo;
        this.descripcionError = descripcionError;
    }

}
