/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.jsf.fsa.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Bean base para Todos los 
 *
 * @author Francisco Seguel A. (frankseguel@gmail.com)
 * @version 1.0
 */
public class DropDownBean<T> extends GenericValueLabelBean<T> {
    
    @Getter
    @Setter
    private List<T> items;

    public DropDownBean() {
        super();
        this.items = new ArrayList<T>();
    }
    
    /**
     * Metodos para que funcione lo antiguo *
     */
    public boolean isSelectedValue() {
        return !(this.getValue() == null);
    }

    public T getValue() {
        T result = super.getValue();
        if (this.items != null && this.items.size() == 1
                && result == null) {
            // Significa que el dropdown solo tiene un elemento
            // asi que seteo el valor en el primero
            this.setValueIndex(0);
            result = super.getValue();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public void setByPropertyValue(String property, Object value) {
        Map m = new HashMap();
        m.put(property, value);
        this.setByProperties(m);
    }

    /**
     * *********************
     */
    /**
     * Metodos auxiliares *
     */
    /**
     * *********************
     */
    private void setValueIndex(int index) {
        T dato = this.getItems().get(index);
        super.setValue(dato);
    }

    @SuppressWarnings("unchecked")
    private void setByProperties(Map values) {
        T result = null;
        // Buscamos dentro de los items
        for (T item : this.getItems()) {
            Map vals = new HashMap();
            for (Object key : values.keySet()) {
                try {
                    vals.put(key, PropertyUtils.getProperty(item, (String) key));
                } catch (Exception ex) {
                    // No hacemos nada
                }
            }
            if (vals.equals(values)) {
                result = item;
                break;
            }
        }
        if (result != null) {
            this.setValue(result);
        }

    }
}
