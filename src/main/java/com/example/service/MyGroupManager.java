/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aleksander
 */

@Stateless
public class MyGroupManager {
    
    @PersistenceContext
    EntityManager entityManager;
    
    public String getGroupnameByUsername(String username) {
        return entityManager.createNamedQuery("groupByUsername").setParameter("username", username).getSingleResult().toString();
    }
}
