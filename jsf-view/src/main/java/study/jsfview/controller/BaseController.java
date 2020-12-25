package study.jsfview.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.MessageFormat;
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

    public void addSuccessMessage(String i18nKey, Object... parameters) {
        addMessage(i18nKey, parameters, FacesMessage.SEVERITY_INFO);
    }

    public void addErrorMessage(String i18nKey, Object... parameters) {
        addMessage(i18nKey, parameters, FacesMessage.SEVERITY_ERROR);
    }

    public void addWarningMessage(String i18nKey, Object... parameters) {
        addMessage(i18nKey, parameters, FacesMessage.SEVERITY_WARN);
    }

    private void addMessage(String i18nKey, Object[] parameters, FacesMessage.Severity severityInfo) {
        final String message = MessageFormat.format(i18n.getString(i18nKey), parameters);
        getContext().addMessage(null, new FacesMessage(severityInfo, message, null));
    }
}
