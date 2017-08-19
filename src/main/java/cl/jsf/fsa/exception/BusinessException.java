package cl.jsf.fsa.exception;

import java.util.List;

/**
 *
 * @author fseguel
 */
public class BusinessException extends JsfException {

    private int p_cod_err;
    private String p_des_err;

    private List<BusinessExceptionDetail> errores;

    public BusinessException() {
        super();
    }

    public BusinessException(int p_cod_err, String reason) {
        super(reason);
        this.p_cod_err = p_cod_err;
        this.p_des_err = reason;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public BusinessException(List<BusinessExceptionDetail> errores) {
        this.errores = errores;
    }

    public String getP_des_err() {
        return p_des_err;
    }

    public List<BusinessExceptionDetail> getErrores() {
        return errores;
    }

    public int getP_cod_err() {
        return p_cod_err;
    }

    public void setP_cod_err(int p_cod_err) {
        this.p_cod_err = p_cod_err;
    }

}
