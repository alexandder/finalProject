/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.web;

import com.example.domain.Coach;
import com.example.service.CoachManager;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aleksander
 */
@SessionScoped
@Named(value = "coachFormBean")
public class CoachFormBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Coach coach = new Coach();
    private ListDataModel<Coach> coaches = new ListDataModel<>();
    private ListDataModel<Coach> availableCoaches = new ListDataModel<>();
    
    @Inject
    CoachManager coachManager;
    
    public String addCoach() {
        coachManager.addCoach(coach);
        return "showCoaches";
    }
    
    public String deleteCoach() {
        Coach toDelete = coaches.getRowData();
        coachManager.deleteCoach(toDelete);
        return null;
    }
    
    public String updateCoach() {
        coachManager.updateCoach(coach);
        return "/user/showCoaches";
    }
    
    public String selectCoach() {
        coach = coaches.getRowData();
        return "/admin/editCoach";
    }
    
    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public ListDataModel<Coach> getAllCoaches() {
        coaches.setWrappedData(coachManager.getAllCoaches());
        return coaches;
    }
 
    public void setCoaches(ListDataModel<Coach> coaches) {
        this.coaches = coaches;
    }

    public ListDataModel<Coach> getAvailableCoaches() {
        availableCoaches.setWrappedData(coachManager.getAvailableCoaches());
        return availableCoaches;
    }

    public void setAvailableCoaches(ListDataModel<Coach> availableCoaches) {
        this.availableCoaches = availableCoaches;
    }
}
