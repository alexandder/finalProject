/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.web;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.richfaces.event.ItemChangeEvent;

/**
 *
 * @author aleksander
 */
@SessionScoped
@Named(value = "displayTab")
public class DisplayTab implements Serializable{
    
    private String displayTab = "footballerTab";

    public String getDisplayTab() {
        return displayTab;
    }

    public void setDisplayTab(String displayTab) {
        this.displayTab = displayTab;
    }
    
    public void changeTab(ItemChangeEvent event) {
        String newTab = event.getNewItemName();
        System.out.println(newTab);
        displayTab = newTab;
    }
}
