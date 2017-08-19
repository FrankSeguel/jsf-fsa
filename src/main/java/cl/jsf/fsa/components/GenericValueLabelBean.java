package cl.jsf.fsa.components;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * 
 *
 * @author Francisco Seguel A. (frankseguel@gmail.com)
 * @version 1.0
 */
public class GenericValueLabelBean<T> {
    
    private T value = null;
    private T oldValue = null;
    @Getter
    @Setter
    private String label = null;
    private List<ValueLabelChangeListener<T>> changeListeners = null;
    @Getter
    @Setter
    private String labelProperty = null;

    public GenericValueLabelBean() {
        this.changeListeners = new ArrayList<ValueLabelChangeListener<T>>();
    }

    public GenericValueLabelBean(T value, String label) {
        this();
        this.value = value;
        this.label = label;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        if (!ObjectUtils.equals(this.oldValue, this.value)
                && this.changeListeners.size() > 0) {
            // Ejecutamos los listeners registrados
            for (ValueLabelChangeListener<T> listener : this.changeListeners) {
                listener.execute(this);
            }
        }
        // Seteo el label correspondiente
        if (this.labelProperty != null && this.value != null) {
            try {
                this.label = String.valueOf(PropertyUtils.getProperty(
                        this.value, this.labelProperty));
            } catch (Exception ex) {
            }
        } else {
            this.label = null;
        }
        this.oldValue = value;
    }

    public T getOldValue() {
        return oldValue;
    }

    public void setOldValue(T oldValue) {
        this.oldValue = oldValue;
    }

    public void addChangeListener(ValueLabelChangeListener changeListener) {
        this.changeListeners.add(changeListener);
    }

}
