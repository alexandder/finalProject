/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.Coach;
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
public class CoachManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addCoach(Coach coach) {
        coach.setId(0);
        entityManager.persist(coach);
    }

    public void deleteCoach(Coach coach) {
        coach = entityManager.find(Coach.class, coach.getId());
        Team team = getTeamByCoach(coach);
        if (team != null) {
            team.setCoach(null);
            entityManager.merge(team);
        }
        entityManager.remove(coach);
    }

    public void updateCoach(Coach coach) {
        entityManager.merge(coach);
    }

    @SuppressWarnings("unchecked")
    public List<Coach> getAllCoaches() {
        return entityManager.createNamedQuery("coach.all").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Coach> getAvailableCoaches() {
        return entityManager.createNamedQuery("coach.available").getResultList();
    }

    public Team getTeamByCoach(Coach coach) {
        try {
            return entityManager.createNamedQuery("coach.team", Team.class).setParameter("idcoach", coach.getId()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
