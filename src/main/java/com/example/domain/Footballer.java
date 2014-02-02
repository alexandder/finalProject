package com.example.domain;

import com.example.domain.annotations.Pesel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = "footballer.all", query = "Select f from Footballer f")
    
})
public class Footballer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 2, max = 35)
    private String name;

    @Size(min = 2, max = 35)
    private String lastName;

    private int goalsNumber;

    @ManyToOne
    @JoinColumn(name = "idTeam", nullable = true)
    private Team team;
    
    private boolean isInjured;
    @Pesel
    private String pesel;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGoalsNumber() {
        return goalsNumber;
    }

    public void setGoalsNumber(int goalsNumber) {
        this.goalsNumber = goalsNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isIsInjured() {
        return isInjured;
    }

    public String getPesel() {
        return pesel;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setIsInjured(boolean isInjured) {
        this.isInjured = isInjured;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
