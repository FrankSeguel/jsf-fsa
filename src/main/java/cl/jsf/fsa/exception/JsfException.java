package cl.jsf.fsa.exception;

/**
 *
 * @author fseguel
 */
public class JsfException extends RuntimeException {

    public JsfException() {
        super();
    }

    public JsfException(String reason) {
        super(reason);
    }

    public JsfException(Throwable cause) {
        super(cause);
    }

    public JsfException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
