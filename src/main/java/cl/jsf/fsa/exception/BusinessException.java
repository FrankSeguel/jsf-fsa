package cl.jsf.fsa.exception;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fseguel
 */
public class BusinessException extends JsfException {

    @Getter
    @Setter
    private int p_cod_err;
    @Getter
    @Setter
    private String p_des_err;
    @Setter
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

}
