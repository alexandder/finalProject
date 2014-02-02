package com.example.web;

import com.example.service.MyGroupManager;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author aleksander
 */

@ManagedBean
@SessionScoped
@Named(value = "myUserBean")
public class MyUserBean implements Serializable {
    
    private String group;
    
    @Inject
    private MyGroupManager myGroupManager;
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login";
    }

    public String getGroup() {
        if(group == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                group = myGroupManager.getGroupnameByUsername(principal.getName());
            }
        }
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
