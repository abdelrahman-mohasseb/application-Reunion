package com.openclassrooms.application_reunion.model;

import java.util.Date;
import java.util.List;

public class Reunion {

    private long id;

    private String heure;

    private String lieu;

    private String sujet;

    private List<String> participants;

    public Reunion(long id, String heure, String lieu, String sujet, List<String> participants) {
    this.id = id;
    this.heure = heure;
    this.lieu = lieu;
    this.sujet = sujet;
    this.participants = participants;
    }


    public long getId() {
    return id;
    }

    public void setId(long id) {
    this.id = id;
    }

    public String getHeure() {
    return heure;
    }

    public void setHeure(String heure) {
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
