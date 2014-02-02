package com.example.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.domain.Footballer;
import com.example.service.FootballerManager;
import com.example.service.TeamManager;

@SessionScoped
@Named(value = "footballerFormBean")
public class FootballerFormBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Footballer footballer = new Footballer();
    private ListDataModel<Footballer> footballers = new ListDataModel<>();
    private long teamId;

    @Inject
    private FootballerManager footballerManager;

    @Inject
    private TeamManager teamManager;

    public Footballer getFootballer() {
        return footballer;
    }

    public void setFootballer(Footballer footballer) {
        this.footballer = footballer;
    }

    public ListDataModel<Footballer> getAllFootballers() {
        footballers.setWrappedData(footballerManager.getAllFootballers());
        return footballers;
    }

    public void setFootballers(ListDataModel<Footballer> footballers) {
        this.footballers = footballers;
    }

    public String addFootballer() {
        footballerManager.addFootballer(footballer, teamId);
        return "showFootballers";
    }

    public String deleteFootballer() {
        Footballer toDelete = footballers.getRowData();
        footballerManager.deleteFootballer(toDelete);
        return null;
    }
    
    public String updateFootballer() {
        footballerManager.updateFootballer(footballer, teamId);
        return "/user/showFootballers";
    }
    
    public String selectFootballer() {
        footballer = footballers.getRowData();
        return "/admin/editFootballer";
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

}
