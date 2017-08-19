package cl.jsf.fsa.exception;

import java.io.Serializable;

/**
 *
 * @author fseguel
 */
public class BusinessExceptionDetail implements Serializable {

    private String campo;
    private String descripcionError;

    public BusinessExceptionDetail(String campo, String descripcionError) {
        this.campo = campo;
        this.descripcionError = descripcionError;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

}
