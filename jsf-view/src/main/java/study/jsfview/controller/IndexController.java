package study.jsfview.controller;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class IndexController implements Serializable {

    public String goToPerson() {
        return "success";
    }

    public String goToAddress() {
        return "success";
    }

}
