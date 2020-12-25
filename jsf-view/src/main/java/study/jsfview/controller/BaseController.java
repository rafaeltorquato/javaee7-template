package study.jsfview.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

public abstract class BaseController implements Serializable {

    protected ResourceBundle i18n;

    public BaseController() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.i18n = ResourceBundle.getBundle("/i18n/messages", context.getViewRoot().getLocale());
    }

    public FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    public void addSuccessMessage(String i18nKey) {

        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, i18n.getString(i18nKey), null));
    }

    public void addErrorMessage(String i18nKey) {
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.getString(i18nKey), null));
    }

    public void addWarningMessage(String i18nKey) {
        getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, i18n.getString(i18nKey), null));
    }
}
