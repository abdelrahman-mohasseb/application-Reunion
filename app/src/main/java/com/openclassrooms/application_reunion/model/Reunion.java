package com.openclassrooms.application_reunion.model;

import java.util.Date;
import java.util.List;

public class Reunion {

    private int id;

    private Date heure;

    private String lieu;

    private String sujet;

    private List<String> participants;

    public Reunion(int id, Date heure, String lieu, String sujet, List<String> participants) {
    this.id = id;
    this.heure = heure;
    this.lieu = lieu;
    this.sujet = sujet;
    this.participants = participants;
    }


    public int getId() {
    return id;
    }

    public void setId(int id) {
    this.id = id;
    }

    public Date getHeure() {
    return heure;
    }

    public void setHeure(Date heure) {
    this.heure = heure;
    }

    public String getLieu() {
    return lieu;
    }

    public void setLieu(String lieu) {
    this.lieu = lieu;
    }

    public String getSujet() {
    return sujet;
    }

    public void setSujet(String sujet) {
    this.sujet = sujet;
    }

    public List<String> getParticipants() {
    return participants;
    }

    public void setParticipants(List<String> participants) {
    this.participants = participants;
    }

}
