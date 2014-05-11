/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import com.example.domain.Competition;
import com.example.domain.Team;
import com.example.service.CompetitionManager;
import com.example.service.TeamManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aleksander
 */
@SessionScoped
@Named(value = "competitionFormBean")
public class CompetitionFormBean implements Serializable {

    private Competition competition = new Competition();
    private ListDataModel<Competition> competitions = new ListDataModel<>();
    private ListDataModel<Team> availableTeams = new ListDataModel<>();
    private Long[] teamsId;
    private long teamId;
    private Team team;
    private List<Team> teamsToAdd;
    private List<Team> teamsC;
    
    
    @Inject
    private CompetitionManager competitionManager;

    @Inject
    private TeamManager teamManager;

    public String addCompetition() {
        competitionManager.addCompetition(competition, teamsId);
        return "showCompetitions";
    }

    public String deleteCompetition() {
        Competition toDelete = competitions.getRowData();
        competitionManager.deleteCompetition(toDelete);
        return null;
    }

    public String addTeamToCompetition() {
        competitionManager.addTeamToCompetition(competition, teamId);
        return "showCompetition";
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public ListDataModel<Competition> getAllCompetitions() {
        competitions.setWrappedData(competitionManager.getAllCompetitions());
        return competitions;
    }

    public String removeFromLeague(Long id) {
        competitionManager.removeTeamFromCompetition(competition, id);
        competition = competitionManager.refreshCompetition(competition.getId());
        return "showCompetition?faces-redirect=true";
    }

    public String selectCompetition() {
        competition = competitions.getRowData();
        return "showCompetition?faces-redirect=true";
    }

    public String selectCompetitionForUpdate() {
        competition = competitions.getRowData();
        return "editCompetition";
    }

    public void setCompetitions(ListDataModel<Competition> competitions) {
        this.competitions = competitions;
    }

    public CompetitionManager getCompetitionManager() {
        return competitionManager;
    }

    public void setCompetitionManager(CompetitionManager competitionManager) {
        this.competitionManager = competitionManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public Long[] getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(Long[] teamsId) {
        this.teamsId = teamsId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ListDataModel<Team> getTeams() {
        List<Team> teams = teamManager.getAllTeams();
        List<Team> teamsIn = competitionManager.getTeamsByCompetition(competition.getId());
        List<Team> teamsFree = new ArrayList<>();
        boolean check;
        for(Team t: teams) {
            check = true;
            for(Team tIn: teamsIn){
                if (t.getName().equals(tIn.getName())) {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                teamsFree.add(t);
            }
        }
        if (!teamsFree.isEmpty())
            availableTeams.setWrappedData(teamsFree);

        return availableTeams;
    }

    public void setTeams(ListDataModel<Team> teams) {
        this.setAvailableTeams(teams);
    }

    public List<Team> getTeamsToAdd() {
        teamsToAdd = teamManager.getAllTeams();
        teamsToAdd.removeAll(competition.getTeams());
        return teamsToAdd;
    }

    public void setTeamsToAdd(List<Team> teamsToAdd) {
        this.teamsToAdd = teamsToAdd;
    }

    public ListDataModel<Team> getAvailableTeams() {
        List<Team> teams = teamManager.getAvailableTeams(competition.getId());
        availableTeams.setWrappedData(teams);
        return availableTeams;
    }

    public void setAvailableTeams(ListDataModel<Team> availableTeams) {
        this.availableTeams = availableTeams;
    }

    public List<Team> getTeamsC() {
        teamsC = competition.getTeams();
        return teamsC;
    }

    public void setTeamsC(List<Team> teamsC) {
        this.teamsC = teamsC;
    }

}
