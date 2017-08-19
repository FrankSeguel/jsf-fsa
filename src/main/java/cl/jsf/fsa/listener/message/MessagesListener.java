/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.jsf.fsa.listener.message;

import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MessagesListener implements PhaseListener {

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @SuppressWarnings("unchecked")
    public void beforePhase(PhaseEvent e) {
        FacesContext fc = e.getFacesContext();
        UIViewRoot root = fc.getViewRoot();

        Iterator i = fc.getClientIdsWithMessages();
        while (i.hasNext()) {
            String clientId = (String) i.next();
            String label = "";
            String family = null;

            Iterator j = fc.getMessages(clientId);
            while (j.hasNext()) {
                FacesMessage fm = (FacesMessage) j.next();
                String summary = fm.getSummary();
                String detalle = fm.getDetail();

                if (clientId != null) {
                    UIComponent c = root.findComponent(clientId);
                    if (c != null) {
                        family = c.getFamily();
                        if ("org.primefaces.component".equals(family)) {
                            UIOutput ui = (UIOutput) root.findComponent(clientId + "label");
                            if (ui != null) {
                                label = ui.getValue().toString();
                            } else {
                                label = "";
                            }
                        }
                    }
                }

                if ("javax.faces.component.UIInput.REQUIRED".equals(summary)) {
                    fm.setDetail(label + " " + fm.getDetail());
                    fm.setSummary(label + " " + fm.getSummary());
                } else if ("Error de conversion".equals(summary)) {
                    fm.setDetail(label + detalle);
                    fm.setSummary(label + summary);
                } else if ("javax.faces.validator.LengthValidator.MAXIMUM".equals(summary)
                        || "javax.faces.validator.LengthValidator.MINIMUM".equals(summary)) {
                    fm.setDetail(label + fm.getDetail());
                    fm.setSummary(label + fm.getSummary());
                } else if ("javax.faces.convert.NumberConverter".equals(summary)) {
                    fm.setDetail(label + fm.getDetail());
                    fm.setSummary(label + fm.getSummary());
                }

            }
        }

    }

    public void afterPhase(PhaseEvent e) {
    }
}
