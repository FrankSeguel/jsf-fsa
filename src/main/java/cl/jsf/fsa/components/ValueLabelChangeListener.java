package cl.jsf.fsa.components;

import cl.jsf.fsa.exception.BusinessException;
import java.io.Serializable;

/**
 *
 * @author Francisco Seguel A. (frankseguel@gmail.com)
 * @version 1.0
 */
public interface ValueLabelChangeListener<T> extends Serializable {

    public void execute(GenericValueLabelBean<T> value) throws BusinessException;
}
