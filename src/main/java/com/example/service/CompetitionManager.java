/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.Competition;
import com.example.domain.Team;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aleksander
 */
@Stateless
public class CompetitionManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addCompetition(Competition competition, Long[] teamsId) {
        competition.setId(0);
        List<Team> teams = new ArrayList<>();
        if (teamsId.length != 0) {
            for (Long teamId : teamsId) {
                Team team = entityManager.find(Team.class, teamId);
                teams.add(team);
            }
            competition.setTeams(teams);
        }
        entityManager.persist(competition);
    }

    public void addTeamToCompetition(Competition competition, Long teamId) {
        Team toAdd = entityManager.find(Team.class, teamId);
        competition.getTeams().add(toAdd);
        entityManager.merge(competition);
    }

    public void deleteCompetition(Competition competition) {
        competition = entityManager.find(Competition.class, competition.getId());
        entityManager.remove(competition);
    }

    public void removeTeamFromCompetition(Competition competition, Long id) {
        Team team = entityManager.find(Team.class, id);
        competition = entityManager.find(Competition.class, competition.getId());
        competition.getTeams().remove(team);
        entityManager.merge(competition);
    }

    public Competition refreshCompetition(Long id) {
        return entityManager.find(Competition.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Competition> getAllCompetitions() {
        return entityManager.createNamedQuery("competition.all").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Team> getTeamsByCompetition(Long id) {
        return entityManager.createNamedQuery("teams.byCompetition").setParameter("competitionId", id).getResultList();
    }

}
