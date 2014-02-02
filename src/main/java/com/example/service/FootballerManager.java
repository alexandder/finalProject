package com.example.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.domain.Footballer;
import com.example.domain.Team;

@Stateless
public class FootballerManager {
    
    @PersistenceContext
    EntityManager entityManager;
    
    public void addFootballer(Footballer footballer, long teamId) {
        
        Team team = entityManager.find(Team.class, teamId);
        footballer.setId(0);
        footballer.setTeam(team);
        entityManager.persist(footballer);
    }
    
    public void deleteFootballer(Footballer footballer) {
        footballer = entityManager.find(Footballer.class, footballer.getId());
        entityManager.remove(footballer);
    }
    
    public void updateFootballer(Footballer footballer, long teamId) {
        Team team = entityManager.find(Team.class, teamId);
        footballer.setTeam(team);
        entityManager.merge(footballer);
    }
    
    @SuppressWarnings("unchecked")
    public List<Footballer> getAllFootballers() {
        return entityManager.createNamedQuery("footballer.all").getResultList();
    }
}
