/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.web;

import com.example.domain.Team;
import com.example.service.TeamManager;
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
@Named(value = "teamFormBean")
public class TeamFormBean implements Serializable {
    
    private Team team = new Team();
    private ListDataModel<Team> teams = new ListDataModel<>();
    private long coachId;
    
    private ListDataModel<Team> availableTeams = new ListDataModel<>();
    
    @Inject
    TeamManager teamManager;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ListDataModel<Team> getAllTeams() {
        teams.setWrappedData(teamManager.getAllTeams());
        return teams;
    }

    public void setTeams(ListDataModel<Team> teams) {
        this.teams = teams;
    }
    
    public String addTeam() {
        teamManager.addTeam(team, getCoachId());
        return "showTeams";
    }

    public String deleteTeam() {
        Team toDelete = teams.getRowData();
        teamManager.deleteTeam(toDelete);
        return null;
    }
    
    public String updateTeam() {
        teamManager.updateTeam(team, coachId);
        return "/user/showTeams";
    }
    
    public String selectTeam() {
        team = teams.getRowData();
        return "/admin/editTeam";
    }
    
    public long getCoachId() {
        return coachId;
    }

    public void setCoachId(long coachId) {
        this.coachId = coachId;
    }

    public ListDataModel<Team> getAvailableTeams() {
        
        return availableTeams;
    }

    public void setAvailableTeams(ListDataModel<Team> availableTeams) {
        this.availableTeams = availableTeams;
    }
    
}
