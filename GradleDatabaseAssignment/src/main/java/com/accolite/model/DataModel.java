package com.accolite.model;

// DataModel.java
public class DataModel {
    String date;
    String month;
    String team;
    String panelName;
    String round;
    String skill;
    String time;
    String candidateCurrentLoc;
    String candidatePreferredLoc;
    String candidateName;

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCandidateCurrentLoc(String candidateCurrentLoc) {
        this.candidateCurrentLoc = candidateCurrentLoc;
    }

    public void setCandidatePreferredLoc(String candidatePreferredLoc) {
        this.candidatePreferredLoc = candidatePreferredLoc;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getTeam() {
        return team;
    }

    public String getPanelName() {
        return panelName;
    }


    public String getRound() {
        return round;
    }

    public String getSkill() {
        return skill;
    }


    public String getTime() {
        return time;
    }

    public String getCandidateCurrentLoc() {
        return candidateCurrentLoc;
    }

    public String getCandidatePreferredLoc() {
        return candidatePreferredLoc;
    }


    public String getCandidateName() {
        return candidateName;
    }

}