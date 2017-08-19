/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.jsf.fsa.utils;

import java.io.Serializable;
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fseguel
 */
public abstract class FacesUtils implements Serializable {

    public static void info(String detail) {
        message("info", detail);
    }

    public static void warn(String detail) {
        message("warn", detail);
    }

    public static void error(String detail) {
        message("error", detail);
    }

    public static void error(List< String> list) {
        for (String string : list) {
            error(string);
        }
    }

    public static void fatal(String detail) {
        message("fatal", detail);
    }

    private static void message(String severity, String detail) {
        if ("info".equals(severity) && null != detail) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", detail));
        } else if ("warn".equals(severity)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN", detail));
        } else if ("error".equals(severity)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR" + detail, detail));
        } else if ("fatal".equals(severity)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL", detail));
        }
    }

    public static String getRemoteUser() {
        return getExternalContext().getUserPrincipal().getName();
    }

    private static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static String getRequestParameter(String key) {
        return ((HttpServletRequest) getExternalContext().getRequest()).
                getParameter(key);
    }

    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    public static void setSessionValue(String key, Object value) {
        HttpSession session = (HttpSession) getExternalContext().
                getSession(true);
        session.setAttribute(key, value);
    }

    public static Object getBean(String beanName) {
        Object bean = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        }

        return bean;
    }

    public static String getIp() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRemoteAddr();
    }
}
