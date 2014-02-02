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

    public String addTeamsToCompetition() {

        return null;
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
        return "showCompetition";
    }

    public String updateCompetition() {
        competitionManager.updateCompetition(competition, teamsId);
        return "showCompetitions";
    }

    public String selectCompetition() {
        competition = competitions.getRowData();
        return "showCompetition";
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
        availableTeams.setWrappedData(teamManager.getAvailableTeams(competition));
        return availableTeams;
    }

    public void setTeams(ListDataModel<Team> teams) {
        this.availableTeams = teams;
    }

}
