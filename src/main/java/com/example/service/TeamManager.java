/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.Coach;
import com.example.domain.Competition;
import com.example.domain.Footballer;
import com.example.domain.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aleksander
 */
@Stateless
public class TeamManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addTeam(Team team, long coachId) {
        Coach coach = entityManager.find(Coach.class, coachId);
        team.setId(0);
        team.setCoach(coach);
        entityManager.persist(team);
    }

    public void deleteTeam(Team team) {
        team = entityManager.find(Team.class, team.getId());
        List<Footballer> footballers = getFootballersByTeam(team);
        if (footballers != null) {
            for (Footballer f : footballers) {
                f.setTeam(null);
                entityManager.merge(f);
            }
        }
        entityManager.remove(team);
    }

    public void updateTeam(Team team, long coachId) {
        Coach coach = entityManager.find(Coach.class, coachId);
        team.setCoach(coach);
        entityManager.merge(team);
    }

    public List<Team> getAllTeams() {
        return entityManager.createNamedQuery("team.all").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Footballer> getFootballersByTeam(Team team) {
        try {
            return entityManager.createNamedQuery("team.footballer", Footballer.class).setParameter("idteam", team.getId()).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Team> getAvailableTeams(Long id) {
        
        try {
            return entityManager.createNamedQuery("team.available", Team.class).setParameter("compID", id).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
