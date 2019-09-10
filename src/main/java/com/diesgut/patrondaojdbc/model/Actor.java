package com.diesgut.patrondaojdbc.model;

import java.util.Date;

public class Actor {

    private Long actodId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;

    public Actor() {
    }

    public Actor(Long actodId, String firstName, String lastName, Date lastUpdate) {
        this.actodId = actodId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    public Long getActodId() {
        return actodId;
    }

    public void setActodId(Long actodId) {
        this.actodId = actodId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Actor{" + "actodId=" + actodId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate=" + lastUpdate + '}';
    }

}
